package ai.yarnpx.com.ai.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.entily.UserInfo;
import ai.yarnpx.com.ai.utils.ShareUtil;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class ResgisterAcvivity extends BaseActivity implements View.OnClickListener {

    private EditText username,resgisterType,jianjie,password1, password2,emailTyoe;
    private RadioGroup sexGroup;
    private Button resgisterButton;
    private boolean isGrame = true;
    private CheckBox kpp_password;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.resgister_activity );
        initView();
    }

    private void initView() {
        username = findViewById( R.id.username );
        resgisterType= findViewById( R.id.resgiter_type );
        jianjie = findViewById( R.id. resgister_abstract);
        password1 = findViewById( R.id.password1 );
        password2 = findViewById( R.id.password2 );
        emailTyoe = findViewById( R.id.resgiter_email );
        kpp_password = findViewById( R.id.kpp_password );
        sexGroup = findViewById( R.id. resgister_sex);

        resgisterButton = findViewById( R.id.submit_resgister );
        resgisterButton.setOnClickListener( this );


    }

    @Override
    public void onClick(View v) {
        //获取控件的值
        String name = username.getText().toString().trim();
        String resType = resgisterType.getText().toString().trim();
        String desc = jianjie.getText().toString().trim();
        String pass = password1.getText().toString().trim();
        String password = password2.getText().toString().trim();
        String eamail = emailTyoe.getText().toString().trim();
        //判断是否为空
        if(!name.isEmpty() & !resType.isEmpty()  & !pass.isEmpty() & !password.isEmpty() & !eamail.isEmpty()){
            if(!pass.equals( password )){
                //先取得姓别
                sexGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.is_body){
                            isGrame = true;
                        }else if(checkedId == R.id.is_grid){
                            isGrame = false;
                        }
                    }
                } );
                //判断简介是否为空
                if(!desc.isEmpty()){
                    desc = "这个人什么都没有留下";
                }
                else {
                    Toast.makeText( this,"二次输入的密码不一致",Toast.LENGTH_LONG ).show();
                }
            }
            //注册的实现
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername( name );
            userInfo.setPassword( pass );
            userInfo.setSex( isGrame );
            userInfo.setJianjie( desc );
            userInfo.setEmail( eamail );

            userInfo.signUp( new SaveListener<UserInfo>() {

                @Override
                public void done(UserInfo userInfo, BmobException e) {
                    if(e == null){
                        Toast.makeText( ResgisterAcvivity.this,"注册成功",Toast.LENGTH_LONG ).show();
                        finish();
                    }else {
                        Toast.makeText( ResgisterAcvivity.this,"注册失败" + e.toString(),Toast.LENGTH_LONG ).show();

                    }
                }
            } );

        }else {
            Toast.makeText( this,"输入框不能为空",Toast.LENGTH_LONG ).show();
        }
    }

}
