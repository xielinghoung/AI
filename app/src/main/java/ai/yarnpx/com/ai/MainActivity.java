package ai.yarnpx.com.ai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import ai.yarnpx.com.ai.fragment.Classification;
import ai.yarnpx.com.ai.fragment.MyMessage;
import ai.yarnpx.com.ai.fragment.Presentation;
import ai.yarnpx.com.ai.fragment.WokerOrder;
import ai.yarnpx.com.ai.ui.SetttingActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    private List<String> titleText;
    private List<Fragment> mfragments;
    private Context context;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉阴影
        getSupportActionBar().setElevation(0);
       initData();
       initView();
       
    }
    //初始化数据
    public void  initData(){
        //初始化标题
        titleText = new ArrayList<>();

        titleText.add("工单大厅");
        titleText.add("产品分类");
        titleText.add("验布报告");
        titleText.add("个人中心");
        //初始化fragments
        mfragments = new ArrayList<>();
        mfragments.add(new WokerOrder());
        mfragments.add(new Classification());
        mfragments.add(new Presentation());
        mfragments.add(new MyMessage());
    }
    //初始化View
    @SuppressLint("RestrictedApi")
    public void initView(){
        mtabLayout = findViewById(R.id.mtablayout);
        mviewPager = findViewById(R.id.mwiepager);
        floatingActionButton = findViewById(R.id.fab_seting);

        floatingActionButton.setOnClickListener(this);
        //默认隐藏
        floatingActionButton.setVisibility(View.GONE);
        //预加载
        mviewPager.setOffscreenPageLimit(mfragments.size());
        //mviewPager滑动监听
        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onPageSelected(int i) {
                if(i == 0){
                    floatingActionButton.setVisibility(View.GONE);
                }
                else {
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        // 适配器
        mviewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int i) {
                return mfragments.get(i);
            }
            //返回item的个
            @Override
            public int getCount() {
                return mfragments.size();
            }
            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return titleText.get(position);
            }
        });
        mtabLayout.setupWithViewPager(mviewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_seting:
                startActivity(new Intent(this,SetttingActivity.class));
                break;
        }
    }
}
