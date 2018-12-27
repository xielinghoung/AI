package ai.yarnpx.com.ai.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.adapter.OderAllAdpter;
import ai.yarnpx.com.ai.entily.DidiOoder;
import ai.yarnpx.com.ai.ui.AetailsActivity;
import ai.yarnpx.com.ai.utils.StatiClass;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class WokerOrder extends Fragment {

    private ListView mlistView;

    private List<String> oderSourcelist = new ArrayList<>(  );
    private List<String> oderIdlist = new ArrayList<>(  );
    private List<String> oderClasslist = new ArrayList<>(  );
    private List<String> oderNamelist = new ArrayList<>(  );

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workeroder,null);
        Bmob.initialize( getActivity(),StatiClass.APPLIACTION_ID );
        initView(view);
        return view;

    }

    private void initView(View view) {

        mlistView = view.findViewById(R.id.oder_mList);

        BmobQuery<DidiOoder> query = new BmobQuery<>();
        query.findObjects( new FindListener<DidiOoder>() {
            @Override
            public void done(List<DidiOoder> list, BmobException e) {
                if(e == null){
                    OderAllAdpter adpter = new OderAllAdpter(getActivity(),list);
                    mlistView.setAdapter(adpter);
                    Toast.makeText(getActivity(),"数据查询成功!",Toast.LENGTH_LONG).show();
                    for (DidiOoder didiOoder : list){
                        String  oderId = didiOoder.getOderId();
                        String oderSource = didiOoder.getOderSource();
                        String  oderClass = didiOoder.getOderClassification();
                        String oderName = didiOoder.getOderNmae();

                        oderIdlist.add( oderId );
                        oderSourcelist.add( oderSource );
                        oderClasslist.add( oderClass );
                        oderNamelist.add( oderName );
                    }
                }
                else {
                    Toast.makeText(getActivity(),"查询失败"+e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        } );

        mlistView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent( getActivity(),AetailsActivity.class );
                intent.putExtra( "oderId" ,oderIdlist.get( position ));
                intent.putExtra( "oderSource",oderSourcelist.get( position ) );
                intent.putExtra( "oderClass",oderClasslist.get( position ));
                intent.putExtra( "oderName", oderNamelist.get( position ));
                System.out.println( oderSourcelist.get( position ) );
                startActivity( intent );

            }
        } );
    }



}
