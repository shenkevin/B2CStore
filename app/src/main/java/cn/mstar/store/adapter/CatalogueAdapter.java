package cn.mstar.store.adapter;
import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.entity.MoreClassifyData;
import cn.mstar.store.utils.L;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CatalogueAdapter extends BaseAdapter {
	private Context context;
	private List<MoreClassifyData> moreClassifyList;
	private LayoutInflater inflater;
	private int selectedPosition;
	public CatalogueAdapter(Context context ,List<MoreClassifyData> moreClassifyList){
	
		this.context=context;
		this.moreClassifyList=moreClassifyList;
		inflater=LayoutInflater.from(context);
		//默认选中位置为0
		selectedPosition=0;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return moreClassifyList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return moreClassifyList.get(position);
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
			convertView=inflater.inflate(R.layout.item_classify_catalogue, null);
			viewHolder.tv_menu=(TextView) convertView.findViewById(R.id.tv_catalogue);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.tv_menu.setText(moreClassifyList.get(position).getCategoryName());
		if (selectedPosition == position) {
			convertView.setBackgroundResource(R.drawable.btn_morefilter_down);
		} else {
			convertView.setBackgroundResource(R.drawable.btn_morefilter_nor);
		}
		return convertView;
	}

	
	public class ViewHolder{
		TextView tv_menu;
	}
	
	public void setSelectedPosition(int position) {
		selectedPosition = position;
	}
}
