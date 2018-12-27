package ai.yarnpx.com.ai.ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import ai.yarnpx.com.ai.MainActivity;
import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.entily.UserInfo;
import ai.yarnpx.com.ai.utils.ShareUtil;
import ai.yarnpx.com.ai.views.CountsDialog;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText username,password;
    private Button loginButton,resgisterBtton;
    private CheckBox kpp_password;
    private CountsDialog dialog;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_activity );
        initView();
    }

    private void initView() {

        resgisterBtton = findViewById( R.id.resgister );
        loginButton = findViewById( R.id.login_system );
        kpp_password = findViewById( R.id.kpp_password );
        username = findViewById( R.id.username );
        password = findViewById( R.id.password );
        loginButton.setOnClickListener( this );
        resgisterBtton.setOnClickListener( this );
        //初始化dialog
        dialog = new CountsDialog(this, 100, 100, R.layout.dia_log, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_amin_style);
        //屏幕外点击无效
        dialog.setCancelable(false);
        //设置选中状态
        boolean isCheck = ShareUtil.getBoolean( this,"name",false);
        kpp_password.setChecked(isCheck);
        if(isCheck){
            //设置密码
            username.setText( ShareUtil.getString( this,"name","" ) );
            password.setText( ShareUtil.getString( this,"pass","" ) );
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_system:

                //获取输入框的值
                String name = username.getText().toString().trim();

                String pass = password.getText().toString().trim();
                //判断是否空
                if(!name.isEmpty() & !pass.isEmpty()){
                    dialog.show();
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUsername( name );
                    userInfo.setPassword( pass );
                    //登录
                    userInfo.login( new SaveListener<UserInfo>() {

                        @Override
                        public void done(UserInfo userInfo, BmobException e) {
                            dialog.dismiss();
                            if(e == null){
                                //判断邮箱是否验证
                                if(userInfo.getEmailVerified()){
                                    startActivity( new Intent( LoginActivity.this,MainActivity.class ) );
                                    Toast.makeText( LoginActivity.this,"登录成功！",Toast.LENGTH_LONG ).show();
                                    finish();
                                }else {
                                    Toast.makeText( LoginActivity.this,"邮箱没有验证",Toast.LENGTH_LONG ).show();
                                }
                            }
                            else {
                                Toast.makeText( LoginActivity.this,"登录失败!" + e.toString(), Toast.LENGTH_LONG ).show();                            }
                        }
                    } );
                }else {
                    Toast.makeText( this,"用户名密码不能为空",Toast.LENGTH_LONG ).show();
                }
                break;

            case R.id.resgister:
                startActivity( new Intent( LoginActivity.this,ResgisterAcvivity.class ) );
                break;

        }
    }
    //当用户填写完用户名各密码不点登录，而直接退出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存状态
        ShareUtil.putBoolean( this,"keeppass",kpp_password.isChecked() );
        //保存帐号密码
        if(kpp_password.isChecked()){
            ShareUtil.putString( this,"name",username.getText().toString().trim() );
            ShareUtil.putString( this,"pass",password.getText().toString().trim() );

        }
        else {
            ShareUtil.deleShare( this,"name" );
            ShareUtil.deleShare( this,"pass" );
        }
    }
}
