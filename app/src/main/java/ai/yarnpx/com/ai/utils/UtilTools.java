package ai.yarnpx.com.ai.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 工具类
 */
public class UtilTools {

    //设置字体
    public static void setFont(Context mContent, TextView textView){
        Typeface typeface = Typeface.createFromAsset( mContent.getAssets(),"fonts/FONT.TTF" );
        textView.setTypeface( typeface );
    }

    /**
     * 保存图片到ShareUtil
     * @param mCtontent
     * @param imageView
     */
    public static void putImageToShareUtil(Context mCtontent, ImageView imageView){
        //保存
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        //第一步：将Bimap压缩成字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,80, byteArrayOutputStream);
        //第三步：用base64将字节 数组输出流转换成string
        byte[] bytesArray = byteArrayOutputStream.toByteArray();
        String imageStre = new String(Base64.encodeToString(bytesArray,Base64.DEFAULT));
        //第三步：将imageSrem保存到
        ShareUtil.putString(mCtontent,"image_title",imageStre);
    }

    /**
     * 从ShareUtil获取图片并设置到控件
     * @param mConteent
     * @param imageView
     */
    public static void setImageShareUtil(Context mConteent,ImageView imageView){
        //1、拿到保存的imageStr
        String imageStr = ShareUtil.getString(mConteent,"image_title", null);
        if(imageStr != null){
            //2.利用base64转换string
            byte[] decode = Base64.decode(imageStr, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
            //3、生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            imageView.setImageBitmap(bitmap);
        }
    }

}
