package cn.mstar.store.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.mstar.store.R;
import cn.mstar.store.adapter.ReceiverAddressAdapter.ViewHolder;
import cn.mstar.store.entity.ReceiverAddress;
import cn.mstar.store.utils.CustomToast;
import cn.mstar.store.utils.L;

public class ManageReceiverAddressAdapter extends BaseAdapter{
	private Context context ;
	private List<ReceiverAddress> list;
	private LayoutInflater inflater;
	private OnManageReceiverAddressListener manageReceiverAddressListener;
	public ManageReceiverAddressAdapter(Context context ,List<ReceiverAddress> list){
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
public void setManageReceiverAddressListener(OnManageReceiverAddressListener manageReceiverAddressListener){
	this.manageReceiverAddressListener=manageReceiverAddressListener;
}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_manage_receiver_address, null);
			viewHolder.setDefaultBtn=(RadioButton) convertView.findViewById(R.id.radiobutton);
			viewHolder.btnDelete=(TextView) convertView.findViewById(R.id.btn_delete);
			viewHolder.NameTV=(TextView) convertView.findViewById(R.id.name_tv);
			viewHolder.phoneTV=(TextView) convertView.findViewById(R.id.phone_tv);
			viewHolder.addressTV=(TextView) convertView.findViewById(R.id.address_tv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
			viewHolder.setDefaultBtn.setChecked(list.get(position).getIsDefalutAddress());
			if(list.get(position).getIsDefalutAddress()){
				viewHolder.setDefaultBtn.setText(context.getString(R.string.default_address));
			}else{
				viewHolder.setDefaultBtn.setText(context.getString(R.string.set_default_address));
			}
			viewHolder.NameTV.setText(list.get(position).getName());
			viewHolder.phoneTV.setText(list.get(position).getPhone());
			viewHolder.addressTV.setText(list.get(position).getFullPostAddress());
		//}
		//设置默认地址
		viewHolder.setDefaultBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(list.get(position).getIsDefalutAddress()){
					CustomToast.makeToast(context, context.getString(R.string.already_default_address), Toast.LENGTH_SHORT);
					return;
				}
				if(manageReceiverAddressListener!=null)
					manageReceiverAddressListener.setDefaultAddress(position);
			}
		});
		//删除地址
		viewHolder.btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(manageReceiverAddressListener!=null)
					manageReceiverAddressListener.deleteAddress(position);
			}
		});
		return convertView;
	}
 
	public class ViewHolder{
		RadioButton setDefaultBtn;//设为默认按钮
		TextView btnDelete,NameTV,phoneTV,addressTV;
	}
	public interface OnManageReceiverAddressListener{
		public void setDefaultAddress(int position);
		public void deleteAddress(int position);
	}
}
