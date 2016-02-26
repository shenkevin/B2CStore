package cn.mstar.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.customviews.ScaleImageView;
import cn.mstar.store.entity.Product;
import cn.mstar.store.utils.ImageLoadOptions;

/**产品列表适配器
 * @author wenjundu 2015-7-13
 *
 */
public class ProductListAdapter extends BaseAdapter {
	private Context context;
	private List<Product> productList;
	private LayoutInflater inflater;

	private static ViewGroup.LayoutParams parentParams;

	public ProductListAdapter(Context context,List<Product> productList){
		this.context=context;
		this.productList=productList;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return productList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	ViewGroup.LayoutParams layoutParams;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		Product product=productList.get(position);
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_product_list, null);
			viewHolder.productIV=(ScaleImageView) convertView.findViewById(R.id.product_img);
			viewHolder.productName=(TextView) convertView.findViewById(R.id.product_name);
			viewHolder.productPrice=(TextView) convertView.findViewById(R.id.product_price);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// we get the layout paramz, we anlayze it, and set up to the next view.



		ImageLoader.getInstance().displayImage(product.getImageUrl(), viewHolder.productIV, ImageLoadOptions.getOptions());
		viewHolder.productName.setText(product.getName());
		viewHolder.productPrice.setText(""+product.getPrice());
		return convertView;
	}


	public class ViewHolder{
		public ScaleImageView productIV;//产品图片
		public TextView productName,productPrice;//产品名称,价格

	}
}
