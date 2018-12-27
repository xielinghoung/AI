package ai.yarnpx.com.ai.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 主要完成
 * 1、统一属性
 * 2、统一接口
 * 3、统一方法
 *
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示返回
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    //菜单栏操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
