package ai.yarnpx.com.ai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.entily.CourierData;

public class CourirAdpter extends BaseAdapter {

    private Context mContent;
    private List<CourierData> mlist;
    //布局加载器
    private LayoutInflater layoutInflater;
    private CourierData data;

    public  CourirAdpter(Context mContent,List<CourierData> mlist){
        this.mContent = mContent;
        this.mlist = mlist;
        //获取Content系统服务
        layoutInflater = (LayoutInflater) mContent.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if(convertView == null){
            //第一次加载
            viewHoder = new ViewHoder();
            convertView = layoutInflater.inflate( R.layout.layout_oder_item ,null);
            viewHoder.remark = convertView.findViewById( R.id.remark );
            viewHoder.zonme = convertView.findViewById( R.id.zone );
            viewHoder.datatime = convertView.findViewById( R.id.datatime );
            //设置缓存
            convertView.setTag( viewHoder );
        }else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        //设置数据
        data = mlist.get( position );
        viewHoder.remark.setText( data.getRemark() );
        viewHoder.zonme.setText( data.getZone() );
        viewHoder.datatime.setText( data.getDatatime() );

        return convertView;
    }

    class ViewHoder{
        private TextView remark;
        private TextView zonme;
        private TextView datatime;

    }
}
