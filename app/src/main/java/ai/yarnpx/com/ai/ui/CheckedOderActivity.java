package ai.yarnpx.com.ai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ai.yarnpx.com.ai.R;

/**
 * 开始验布
 */
public class CheckedOderActivity extends BaseActivity implements View.OnClickListener {

    private RadioGroup checked_defed;
    private RadioGroup checked_position;
    private RadioGroup checked_deffcis;
    private Button submit;
    private Button reSubmit;

    private RadioButton checked_defed_one;
    private RadioButton checked_defed_two;
    private RadioButton checked_defed_three;
    private RadioButton checked_defed_for;

    private RadioButton checked_position_one;
    private RadioButton checked_position_two;
    private RadioButton checked_position_three;
    private RadioButton checked_position_for;

    private RadioButton checked_deffcis_one;
    private RadioButton checked_deffcis_two;
    private RadioButton checked_deffcis_three;
    private RadioButton checked_deffcis_for;

    private String radioButtonDefed;
    private String radioButtonPosinion;
    private String radioButtonDeffics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chech_cloth_layout);
        iniview();
    }

    private void iniview() {

        checked_defed = findViewById(R.id.checked_defed);
        checked_defed_one = findViewById(R.id.checked_defed_one);
        checked_defed_two = findViewById( R.id.checked_defed_two );
        checked_defed_three = findViewById( R.id.checked_defed_three );
        checked_defed_for = findViewById( R.id.checked_defed_for );

        checked_position = findViewById(R.id.checked_position);
        checked_position_one = findViewById( R.id.checked_position_one );
        checked_position_two = findViewById( R.id.checked_position_two );
        checked_position_three = findViewById( R.id.checked_position_three );
        checked_position_for = findViewById( R.id.checked_position_for );

        checked_deffcis = findViewById(R.id.checked_deffcis);
        checked_deffcis_one = findViewById( R.id.checked_deffcis_one );
        checked_deffcis_two = findViewById( R.id.checked_deffcis_two);
        checked_deffcis_three = findViewById( R.id.checked_deffcis_three );
        checked_deffcis_for = findViewById( R.id.checked_deffcis_for );

        submit = findViewById(R.id.submit);
        reSubmit = findViewById(R.id.re_submit);

        submit.setOnClickListener(this);
        reSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                for (int i = 0 ; i < checked_defed.getChildCount(); i++){

                    RadioButton radioButton = (RadioButton) checked_defed.getChildAt( i );
                    if(radioButton.isChecked()){
                        radioButtonDefed = radioButton.getText().toString();
                    }
                }
                for (int i = 0 ; i < checked_position.getChildCount(); i ++){
                    RadioButton radioButton = (RadioButton) checked_position.getChildAt( i );
                    if(radioButton.isChecked()){
                        radioButtonPosinion = radioButton.getText().toString();
                    }
                }
                for (int i = 0; i < checked_deffcis.getChildCount(); i++){
                    RadioButton radioButton = (RadioButton) checked_deffcis.getChildAt(i);
                    if(radioButton.isChecked()){
                        radioButtonDeffics = radioButton.getText().toString();
                    }
                }

                System.out.println(radioButtonDefed + radioButtonPosinion + radioButtonDeffics);
                break;
            case R.id.re_submit:
                checked_defed_one.setSelected( false );
                checked_defed_two.setSelected( false );
                checked_defed_three.setSelected( false );
                checked_defed_for.setSelected( false );

                checked_position_one.setSelected( false );
                checked_position_two.setSelected( false );
                checked_position_three.setSelected( false );
                checked_position_for.setSelected( false );

                checked_deffcis_one.setSelected( false );
                checked_deffcis_two.setSelected( false );
                checked_deffcis_three.setSelected( false );
                checked_deffcis_for.setSelected( false );
                break;
        }
    }
}
