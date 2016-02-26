package cn.mstar.store.adapter;

import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.entity.ReceiverAddress;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ReceiverAddressAdapter extends BaseAdapter {
	private Context context ;
	private List<ReceiverAddress> list;
	private LayoutInflater inflater;
	public ReceiverAddressAdapter(Context context ,List<ReceiverAddress> list){
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
			convertView=inflater.inflate(R.layout.item_receiver_address, null);
			viewHolder.isDefaultTV=(TextView) convertView.findViewById(R.id.default_tv);
			viewHolder.NameTV=(TextView) convertView.findViewById(R.id.name_tv);
			viewHolder.phoneTV=(TextView) convertView.findViewById(R.id.phone_tv);
			viewHolder.addressTV=(TextView) convertView.findViewById(R.id.address_tv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		ReceiverAddress receiverAddress=list.get(position);
		if(receiverAddress.getIsDefalutAddress()){//是否默认地址,是显示默认
			viewHolder.isDefaultTV.setVisibility(View.VISIBLE);
		}else{
			viewHolder.isDefaultTV.setVisibility(View.GONE);
		}
		viewHolder.NameTV.setText(receiverAddress.getName());
		viewHolder.phoneTV.setText(receiverAddress.getPhone());
		viewHolder.addressTV.setText(receiverAddress.getFullPostAddress());
		return convertView;
	}
 
	class ViewHolder{
		TextView isDefaultTV,NameTV,phoneTV,addressTV;
	}
}
