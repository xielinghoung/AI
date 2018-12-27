package ai.yarnpx.com.ai.application;

import android.app.Application;


import com.tencent.bugly.crashreport.CrashReport;

import ai.yarnpx.com.ai.utils.StatiClass;
import ai.yarnpx.com.ai.utils.UtilTools;
import cn.bmob.v3.Bmob;

public class BaseAppliaction extends Application {
    //创建
    @Override
    public void onCreate() {
        //bugly
       CrashReport.initCrashReport(getApplicationContext(), StatiClass.BUGLY_APP_ID, true);
        super.onCreate();
        //初始化bmob
        Bmob.initialize( this,StatiClass.APPLIACTION_ID );
    }
}
