package ai.yarnpx.com.ai.utils;

import android.util.Log;

public class L  {
    //开关
    private  static  final Boolean DEBUG = true;
    //TAG
    private static final String TAG = "Smartbutler";

    public static void d(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }
    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }
    public static void e(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }
    public static void w(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }
}
