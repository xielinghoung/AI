package ai.yarnpx.com.ai.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ai.yarnpx.com.ai.MainActivity;
import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.utils.ShareUtil;
import ai.yarnpx.com.ai.utils.StatiClass;
import ai.yarnpx.com.ai.utils.UtilTools;

public class SplashActivity extends AppCompatActivity {

    /**
     * 1 延时2000ms
     * 2 判断程序是否第一次执行
     * 3 自定义字体
     * 4 项目的activity主题
     */
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splash_activity );

        initView();
    }
    private Handler handler = new Handler(  ){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );

            switch (msg.what){
                case StatiClass.HEADER_SPLASH:
                    //判断程序是否第一次运行
                    if(isFist()){
                        startActivity( new Intent( SplashActivity.this,GrideActivoty.class ) );

                    }else {
                        startActivity( new Intent( SplashActivity.this,LoginActivity.class ) );

                    }
                    finish();
                    break;
            }

        }

    };


    private void initView() {

        handler.sendEmptyMessageAtTime( StatiClass.HEADER_SPLASH,2000);
        //设置字体
        //UtilTools.setFont( this,textView );
    }

    /**
     * 判断程序是否第一次运行
     * @return
     */
    private boolean isFist() {
        Boolean is_fist = ShareUtil.getBoolean( this,StatiClass.IS_SPLAS_FIST,true );
        if(is_fist){
            ShareUtil.putBoolean( this,StatiClass.IS_SPLAS_FIST,false );
            return true;
        }else {
            return false;
        }
    }
    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
