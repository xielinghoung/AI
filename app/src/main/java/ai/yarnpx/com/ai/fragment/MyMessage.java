package ai.yarnpx.com.ai.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import ai.yarnpx.com.ai.MainActivity;
import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.entily.UserInfo;
import ai.yarnpx.com.ai.ui.Completion;
import ai.yarnpx.com.ai.ui.LoginActivity;
import ai.yarnpx.com.ai.utils.L;
import ai.yarnpx.com.ai.utils.ShareUtil;
import ai.yarnpx.com.ai.utils.UtilTools;
import ai.yarnpx.com.ai.views.CountsDialog;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyMessage extends Fragment implements View.OnClickListener {
    private Button btn_user_exit;
    private TextView userEdit;
    private EditText edUsername;
    private EditText edResgiterType;
    private EditText edDesc;
    private EditText edSex;
    private Button modify;

    //圆形头像
    private CircleImageView profile_image;
    private Dialog dialog;
    //三个dialog
    private Button photograph;
    private Button igallery;
    private Button gallery;

    //工单完成进度
    private TextView completion;

    //相册文件
    private  File tempFile = null;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mymessage,null);
        findView(view);
        return view;
    }

    private void findView(View view) {

        btn_user_exit = view.findViewById(R.id.btn_user_exit);
        btn_user_exit.setOnClickListener(this);
        userEdit= view.findViewById( R.id.user_edit );
        userEdit.setOnClickListener( this );
        modify = view.findViewById( R.id.modify );
        modify.setOnClickListener( this );

        profile_image = view.findViewById( R.id.profile_image );
        profile_image.setOnClickListener( this );

        completion = view.findViewById(R.id.completion);
        completion.setOnClickListener(this);

        UtilTools.setImageShareUtil(getActivity(),profile_image);

        //初始化dialog
        dialog = new CountsDialog(getActivity(), 0, 0, R.layout.dialog_poto, R.style.Theme_dialog, Gravity.BOTTOM,R.style.pop_amin_style);
        //屏幕外点击无效
        dialog.setCancelable(false);
        //拍照、图库、取消
        photograph = dialog.findViewById( R.id.photograph );
        igallery = dialog.findViewById( R.id.igallery );
        gallery = dialog.findViewById( R.id.gallery );

        photograph.setOnClickListener(this);
        igallery.setOnClickListener(this);
        gallery.setOnClickListener(this);

        edUsername = view.findViewById(R.id.username);
        edResgiterType = view.findViewById(R.id.resgister_type);
        edDesc = view.findViewById(R.id.peson_desc);
        edSex = view.findViewById(R.id.peson_sex);

        //默认不可点击
        setEnable( false );
        //设置具体的值
        UserInfo userInfo= BmobUser.getCurrentUser(UserInfo.class);
        edUsername.setText(userInfo.getUsername());
        edResgiterType.setText(userInfo.getResgisterType());
        edDesc.setText(userInfo.getJianjie());
        edSex.setText(userInfo.isSex()?"男":"女");

    }
    //设置焦点
    public void setEnable(boolean is){
        edUsername.setEnabled(is);
        edResgiterType.setEnabled(is);
        edDesc.setEnabled(is);
        edSex.setEnabled(is);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            //退出登录
            case R.id.btn_user_exit:
                BmobUser.logOut();
                startActivity( new Intent( getActivity(),LoginActivity.class ) );
                getActivity().finish();

                break;
            case R.id.user_edit:
                setEnable( true );
                modify.setVisibility(View.VISIBLE  );
                break;

            case R.id.modify:
                String name = edUsername.getText().toString().trim();
                String resType = edResgiterType.getText().toString().trim();
                String desc = edDesc.getText().toString().trim();
                String sex = edSex.getText().toString().trim();
                boolean isSex;
                if(sex == "男"){
                    isSex = true;
                }else {
                    isSex = false;

                }
                UserInfo userInfo = BmobUser.getCurrentUser( UserInfo.class );
                userInfo.setUsername( name );
                userInfo.setResgisterType( resType );
                userInfo.setSex( isSex );
                userInfo.setJianjie( desc );
                userInfo.update( new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null){
                            setEnable( false );
                            modify.setVisibility( View.GONE );
                            Toast.makeText( getActivity(),"更新成功",Toast.LENGTH_LONG ).show();
                        }
                        else {
                            Toast.makeText( getActivity(),"更新失败",Toast.LENGTH_LONG ).show();

                        }
                    }
                } );
                break;

            case R.id.profile_image:
                dialog.show();
                break;
            case R.id.photograph:
                //拍照
                toCammera();
                break;
            case R.id.igallery:
                //图库
                toPicture();
                break;
            case R.id.gallery:
                //取消
                dialog.dismiss();
                break;
                //工单
            case R.id.completion:
                Intent intent = new Intent(getActivity(),Completion.class);
                startActivity(intent);
                getActivity().fileList();
                break;
        }
    }
    private final static String POTO_IMAGE_FILE_NAME = "file.jpg";
    private final static int CAMEAR_RESULT_CODE = 100;
    private final static int IMAGER_RESULT_CODE = 101;
    private final static int RESULT_RESULT_CODE = 102;

    //跳转相机
    private void toCammera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
        //判断内存卡是否可用,可用就储存
        intent.putExtra( MediaStore.EXTRA_OUTPUT,
                Uri.fromFile( new File( Environment.getExternalStorageDirectory(),POTO_IMAGE_FILE_NAME) ) );
        startActivityForResult( intent ,CAMEAR_RESULT_CODE);
        dialog.dismiss();
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent( Intent.ACTION_PICK);
        intent.setType( "image/Download/*" );
        startActivityForResult( intent,IMAGER_RESULT_CODE );
        dialog.dismiss();
    }
    //拿到相机和图库的返回值
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //RESULT_CANCELED = 0
        if(requestCode != getActivity().RESULT_CANCELED){
            switch (requestCode){
                //相册返回的数据
                case IMAGER_RESULT_CODE:
                    startPhotoZom(data.getData());
                    break;
                    //相机返回的数据
                case CAMEAR_RESULT_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(),POTO_IMAGE_FILE_NAME);
                    startPhotoZom(Uri.fromFile(tempFile));
                    break;
                case RESULT_RESULT_CODE:
                    //有可能点击舍弃
                    if(data != null){
                        //拿到图片设置
                        setImageToView(data);
                        //如果拿到了图片
                        if(tempFile != null){
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }
    //裁剪
    private void startPhotoZom(Uri uri){
        if(uri ==null){
            L.e("(uri ==null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"/img/Download/*");
        //是否裁剪
        intent.putExtra("crop","true");
        //设置宽高比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //设置裁剪图片的质量
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        //发送数据
        intent.putExtra("return_data",true);
        startActivityForResult(intent,RESULT_RESULT_CODE);


    }

    //设置图片
    public void setImageToView(Intent data){
        Bundle bundle = data.getExtras();
        if(bundle != null){
           Bitmap bitmap =  bundle.getParcelable("data");
           profile_image.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UtilTools.putImageToShareUtil(getActivity(),profile_image);
    }
}
