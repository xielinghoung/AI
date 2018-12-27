package ai.yarnpx.com.ai.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.adapter.CourirAdpter;
import ai.yarnpx.com.ai.entily.CourierData;
import ai.yarnpx.com.ai.utils.StatiClass;

/**
 * 工单完成进度查询
 */
public class Completion extends AppCompatActivity implements View.OnClickListener {

    private EditText oderType;
    private EditText oderNuber;
    private Button oderQuery;
    private ListView mlistView;
    private List<CourierData> mlist = new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completion);

        initView();
    }

    private void initView() {
        oderType = findViewById(R.id.oder_type);
        oderNuber = findViewById(R.id.oder_number);
        oderQuery = findViewById(R.id.oder_query);
        oderQuery.setOnClickListener(this);
        mlistView = findViewById(R.id.mList);
    }

    @Override
    public void onClick(View v) {
        //拼接url
        String url = "http://v.juhe.cn/exp/index?key="+StatiClass.JUHE_APP_KEY+"&com=zto&no=75115936442373";
        switch (v.getId()){
            case R.id.oder_query:
                RxVolley.get(url, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        super.onSuccess(t);
                        //Toast.makeText( Completion.this,t,Toast.LENGTH_LONG ).show();
                        //解析数据
                        pramsJson(t);
                    }
                });
                break;
        }
    }
    //解析数据
    private void pramsJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject( t );
            JSONObject resultJson = jsonObject.getJSONObject( "result" );
            JSONArray jsonArray = resultJson.getJSONArray( "list" );
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject result = (JSONObject) jsonArray.get( i );
                CourierData courierData = new CourierData();
                courierData.setRemark( result.getString( "remark" ));
                courierData.setZone( result.getString( "zone" ) );
                courierData.setDatatime( result.getString( "datetime"  ));
                mlist.add( courierData );
            }
            CourirAdpter adpter = new CourirAdpter( this,mlist );
            mlistView.setAdapter( adpter );

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
