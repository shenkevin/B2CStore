package cn.mstar.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.activity.BaseActivity;
import cn.mstar.store.entity.ShipInfo;
import cn.mstar.store.utils.L;

/**
 * Created by Administrator on 2015/8/25.
 */
public class LogisticsAdapter extends BaseAdapter{
    private Context context;
    private List<ShipInfo> list;
    private LayoutInflater inflater;
    public LogisticsAdapter(Context context,List<ShipInfo> list){
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_logistics_details_content,null);
            viewHolder.catalogTV= (TextView) convertView.findViewById(R.id.contactitem_catalog);
            viewHolder.contentTV= (TextView) convertView.findViewById(R.id.item_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ShipInfo shipInfo=list.get(position);
        if(position==0){
            viewHolder.catalogTV.setVisibility(View.VISIBLE);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String date=sdf.format(new java.util.Date());
            if (shipInfo.getTime().split(" ")[0].equals(date)) {
                viewHolder.catalogTV.setText("今天");
            }else{
                viewHolder.catalogTV.setText(shipInfo.getTime().split(" ")[0]);
            }
            viewHolder.contentTV.setText(shipInfo.getContext());

        }else{

            if(shipInfo.getTime().split(" ")[0].equals(list.get(position-1).getTime().split(" ")[0])){
                viewHolder.catalogTV.setVisibility(View.GONE);
                viewHolder.contentTV.setText(shipInfo.getContext());
            }else{
                viewHolder.catalogTV.setVisibility(View.VISIBLE);
                viewHolder.catalogTV.setText(shipInfo.getTime().split(" ")[0]);
                viewHolder.contentTV.setText(shipInfo.getContext());
            }
        }

        return convertView;
    }

    class ViewHolder{
        public TextView catalogTV,contentTV;
    }
}
