package ai.yarnpx.com.ai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import ai.yarnpx.com.ai.R;

/**
 * 工单详情页
 */
public class AetailsActivity extends  BaseActivity implements View.OnClickListener {

    private Button oderWokerDerails;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        String oderId = intent.getStringExtra( "oderId");
        String oderSource = intent.getStringExtra("oderSource");
        String oderClass = intent.getStringExtra( "oderClass");
        String oderName = intent.getStringExtra( "oderName");
        //设置标题
        getSupportActionBar().setTitle( oderSource );

        oderWokerDerails = findViewById(R.id.oder_woker_derails);
        oderWokerDerails.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.oder_woker_derails:
                Intent intent = new Intent(this,OderSure.class);
                startActivity(intent);
                break;
        }
    }
}
