package ai.yarnpx.com.ai.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ai.yarnpx.com.ai.MainActivity;
import ai.yarnpx.com.ai.R;

public class GrideActivoty extends AppCompatActivity {
    private ViewPager mViewPager;
    private View view1,view2,view3;
    private List<View> list = new ArrayList<>(  );
    //小圆点
    private ImageView porint1, porint2,proint3;
    //跳过
    private ImageView toMainactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.gride_activoty );
        initView();


    }
    //初始化view
    private void initView() {

       toMainactivity = findViewById( R.id.tg_main_activity );
       toMainactivity.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent( GrideActivoty.this,MainActivity.class );
               startActivity( intent );
               finish();
           }
       } );

        porint1 = findViewById( R.id.priot1 );
        porint2 = findViewById( R.id.priot2 );
        proint3 = findViewById( R.id.priot3 );
        setImage( true,false,false );


        mViewPager = findViewById( R.id.mViewPager );
        view1 = View.inflate( this,R.layout.view1,null );
        view2 = View.inflate( this,R.layout.view2,null );
        view3 = View.inflate( this,R.layout.view3 ,null);
        view3.findViewById( R.id.com_on_main_activity ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( GrideActivoty.this,MainActivity.class );
                startActivity( intent );
                finish();
            }
        } );

        list.add( view1 );
        list.add( view2 );
        list.add( view3 );

        //适配器
        mViewPager.setAdapter( new GiirdAfapter() );

        //监听ViewPager划动
        mViewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            //切换
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        toMainactivity.setVisibility( View.VISIBLE );
                        setImage( true,false,false );
                        break;
                    case 1:
                        toMainactivity.setVisibility( View.VISIBLE );
                        setImage( false,true,false );
                        break;
                    case 2:
                        toMainactivity.setVisibility( View.GONE );
                        setImage( false,false,true );
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        } );

    }
    //适配器
    class GiirdAfapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }


        @Override
        public Object instantiateItem( ViewGroup container, int position) {
            ((ViewPager)container).addView( list.get( position ) );
            return list.get( position);

        }

        @Override
        public void destroyItem( ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView( list.get( position ) );
           // super.destroyItem( container, position, object );
        }
    }

    /**
     * 设置小贺点
     * @param isched1
     * @param isCheckd2
     * @param isCheckkd3
     */
    private void setImage(boolean isched1,boolean isCheckd2,boolean isCheckkd3){
        if(isched1){
            porint1.setBackgroundResource( R.drawable.point_on );
        }else {
            porint1.setBackgroundResource( R.drawable.point_off );
        }
        if(isCheckd2){
            porint2.setBackgroundResource( R.drawable.point_on );

        }
        else {
            porint2.setBackgroundResource( R.drawable.point_off );
        }
        if(isCheckkd3){
            proint3.setBackgroundResource( R.drawable.point_on );
        }else {
            proint3.setBackgroundResource( R.drawable.point_off );
        }
    }



}




