package cn.mstar.store.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;


import cn.mstar.store.R;
import cn.mstar.store.entity.BuyProductEntity;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.ImageLoadOptions;
import cn.mstar.store.utils.L;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BuyProductListAdapter extends BaseAdapter{

	private Context context;
	private List<BuyProductEntity> list;
	private LayoutInflater inflater;
	private OnPlusMinusListener listener;
	public BuyProductListAdapter(Context context,List<BuyProductEntity> list){
		this.context=context;
		this.list=list;
		inflater=LayoutInflater.from(context);
	}
	public void setOnPlusMinusListener(OnPlusMinusListener listener){
	this.listener=listener;
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
			convertView=inflater.inflate(R.layout.item_confirm_indent, null);
			viewHolder.productIV=(ImageView) convertView.findViewById(R.id.product_img);
			viewHolder.productName=(TextView) convertView.findViewById(R.id.product_name);
			viewHolder.productNorms=(TextView) convertView.findViewById(R.id.product_norms);
			viewHolder.productPrice=(TextView) convertView.findViewById(R.id.product_price);
			viewHolder.productNums=(TextView) convertView.findViewById(R.id.product_number);
			viewHolder.btnMinus=(ImageView) convertView.findViewById(R.id.btn_commodity_minus);
			viewHolder.btnPlus=(ImageView) convertView.findViewById(R.id.btn_commodity_plus);
			viewHolder.TVnums=(TextView) convertView.findViewById(R.id.btn_commodity_number_display);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		final BuyProductEntity entity=list.get(position);
//		L.e(entity.getProduct().getImageUrl());
	//	if(entity.getProduct().getImageUrl()!=null && !"".equals(entity.getProduct().getImageUrl()))
			ImageLoader.getInstance().displayImage(entity.getProduct().getImageUrl(), viewHolder.productIV,ImageLoadOptions.getOptions());

		viewHolder.productName.setText(entity.getProduct().getName());
		viewHolder.productNorms.setText(entity.getNorms());
		viewHolder.productNums.setText(""+entity.getBuyNum());
		viewHolder.TVnums.setText(""+entity.getBuyNum());
		viewHolder.productPrice.setText(context.getString(R.string.renminbi)+ Utils.formatedPrice(entity.getProduct().getPrice()));
		viewHolder.btnMinus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(listener!=null)
					listener.minus(position);


			}
		});
		viewHolder.btnPlus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(listener!=null)
					listener.plus(position);

			}
		});
		return convertView;
	}
	public class ViewHolder{
		public ImageView  productIV;//产品图像
		public TextView productName,productNorms,productPrice,productNums,TVnums;//名称 规格 价格  数量,增加减少显示框的数量
		public ImageView btnMinus,btnPlus;//减少 增加 显示
	}

	public interface OnPlusMinusListener{
		public void plus(int position);
		public void minus(int position);
	}
}
