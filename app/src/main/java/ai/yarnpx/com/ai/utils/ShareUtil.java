package ai.yarnpx.com.ai.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtil {
    private static final String NAME = "config";

    /**
     * 增加string 类型SharedPreferences的值
     * @param mContent
     * @param key
     * @param value　
     */
    public static void putString(Context mContent,String key,String value){

        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        sp.edit().putString( key,value ).commit();
    }

    /**
     * 获取string 类型SharedPreferences的值
     * @param mContent
     * @param key
     * @param defValue 默认值
     * @return
     */
    public static String getString(Context mContent,String key,String defValue){
        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        return sp.getString( key,defValue );
    }

    /**
     * 增加int 类型SharedPreferences的值
     * @param mContent
     * @param key
     * @param value
     */
    public static void putInt(Context mContent,String key,int value){
        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        sp.edit().putInt( key,value ).commit();
    }

    /**
     * 获取int 类型SharedPreferences的值
     * @param mContent
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context mContent,String key,int defValue){
        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        return sp.getInt( key,defValue );
    }

    /**
     * 增加boolean 类型SharedPreferences的值
     * @param mContent
     * @param key
     * @param value
     */
    public static void putBoolean(Context mContent,String key,boolean value){
        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        sp.edit().putBoolean(key,value ).commit();
    }

    /**
     * 获取boolean 类型SharedPreferences的值
     * @param mContent
     * @param key
     * @param defValue
     * @return
     */
    public static Boolean getBoolean(Context mContent,String key,boolean defValue){
        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        return sp.getBoolean(key,defValue );
    }

    /**
     * 单个删除
     * @param mContent
     * @param key
     */
    public static void deleShare(Context mContent,String key){
        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        sp.edit().remove( key ).commit();
    }

    /**
     * 删除全部
     * @param mContent
     */
    public static void deleShareAll(Context mContent){
        SharedPreferences sp = mContent.getSharedPreferences( NAME,mContent.MODE_PRIVATE );
        sp.edit().clear();
    }


}
