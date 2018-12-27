package ai.yarnpx.com.ai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ai.yarnpx.com.ai.R;

/**
 * 工单确认页
 */
public class OderSure extends BaseActivity implements View.OnClickListener {

    private Button yesOderwoker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oder_ok_activity);

        initView();

    }

    private void initView() {
        yesOderwoker = findViewById(R.id.yes_oderwoker);
        yesOderwoker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,CheckedOderActivity.class);
        startActivity(intent);
    }
}
