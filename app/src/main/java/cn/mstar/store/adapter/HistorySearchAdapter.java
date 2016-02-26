package cn.mstar.store.adapter;

import java.util.List;

import cn.mstar.store.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistorySearchAdapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	private LayoutInflater inflater;
	private OnDeleteListener deleteListener;
	public HistorySearchAdapter(Context context,List<String> list){
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_history_search_listview, null);
			viewHolder.searchTV=(TextView) convertView.findViewById(R.id.search_tv);
			viewHolder.deleteIV=(ImageView) convertView.findViewById(R.id.delete_iv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.searchTV.setText(list.get(position));
		viewHolder.deleteIV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(deleteListener!=null)
					deleteListener.delete(position);
			}
		});
		return convertView;
	}

	public void setOnDeleteListener(OnDeleteListener deleteListener){
		this.deleteListener=deleteListener;
	}
	public interface OnDeleteListener{
		void delete(int position);
	}
	public class ViewHolder{
		TextView searchTV;
		ImageView deleteIV;
	}
}
