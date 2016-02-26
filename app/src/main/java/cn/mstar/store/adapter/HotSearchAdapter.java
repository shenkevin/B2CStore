package cn.mstar.store.adapter;

import java.util.List;

import cn.mstar.store.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 热门搜索的adapter
 * @author 2015-7-15
 *
 */
public class HotSearchAdapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	private LayoutInflater inflater;
	public HotSearchAdapter(Context context,List<String> list){
		this.context=context;
		this.list=list;
		inflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_hot_search_listview, null);
			viewHolder.searchTv=(TextView) convertView.findViewById(R.id.search_tv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.searchTv.setText(list.get(position));
		return convertView;
	}

	public class ViewHolder{
		TextView searchTv;
	}
}
