package ai.yarnpx.com.ai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ai.yarnpx.com.ai.R;
import ai.yarnpx.com.ai.entily.DidiOoder;

public class OderAllAdpter extends BaseAdapter {
    private Context mContent;
    private List<DidiOoder> mlist;
    private DidiOoder data;
    //布局加载器
    private LayoutInflater inflater;

    public OderAllAdpter(Context mContent,List<DidiOoder> mlist){
        this.mContent = mContent;
        this.mlist = mlist;
        //获取Content系统服务
        inflater = (LayoutInflater) mContent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if(convertView == null){
            viewHoder = new ViewHoder();
            //加载布局文件
            convertView = inflater.inflate(R.layout.layout_oder_all_item,null);
            viewHoder.oderId = convertView.findViewById(R.id.oder_id);
            viewHoder.oderSource = convertView.findViewById(R.id.oder_source);
            viewHoder.oderName = convertView.findViewById(R.id.oder_name);
            viewHoder.oderClass = convertView.findViewById(R.id.oder_class);
            //设置缓存
            convertView.setTag(viewHoder);
        }else {
            //获取缓存中的数据
            viewHoder = (ViewHoder) convertView.getTag();
        }
        //设置数据
        data = mlist.get( position );
        viewHoder.oderId.setText(data.getOderId());
        viewHoder.oderSource.setText(data.getOderSource());
        viewHoder.oderName.setText(data.getOderNmae());
        viewHoder.oderClass.setText(data.getOderClassification());

        return convertView;
    }

    class ViewHoder{
        private TextView oderId;
        private TextView oderSource;
        private TextView oderName;
        private TextView oderClass;
    }
}
