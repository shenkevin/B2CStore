package cn.mstar.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.entity.BuyProductEntity;
import cn.mstar.store.entity.Product;
import cn.mstar.store.utils.ImageLoadOptions;
import cn.mstar.store.utils.L;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class RemboursementProductAdapter  extends BaseAdapter {

    private static final String NO_ACTION = "NO_ACTION";
    private HashMap<String, String> checkedItemMap;
    private final String POSITION = "position";
    private Context context;
    private List<BuyProductEntity> list;
    private LayoutInflater inflater;
    private OnPlusMinusListener listener;
    private int SCREEN_WIDTH;
    private boolean isFirst = true;
    public static final String TRUE = "1", FALSE = "0";

    public RemboursementProductAdapter(Context context, List<BuyProductEntity> list, int screenWidth){
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
        SCREEN_WIDTH = screenWidth;
        checkedItemMap = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).refund_state == 0)
                checkedItemMap.put(POSITION+i, TRUE);
            else
                checkedItemMap.put(POSITION+i, NO_ACTION);
//            else
//                checkedItemMap.put(POSITION+i, FALSE);
        }
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
        View view;
        if(convertView==null){
            viewHolder=new ViewHolder();
            view =inflater.inflate(R.layout.remboursement_listitem_layout, null);
            viewHolder.productIV=(ImageView) view.findViewById(R.id.product_img);
            viewHolder.productName=(TextView) view.findViewById(R.id.product_name);
            viewHolder.productNorms=(TextView) view.findViewById(R.id.product_norms);
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            viewHolder.wholeview = (RelativeLayout) view.findViewById(R.id.wholeview);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder=(ViewHolder) convertView.getTag();
        }
        final BuyProductEntity entity=list.get(position);
        L.e(entity.getProduct().getImageUrl());
        if (entity.refund_state != 0) {
            viewHolder.checkBox.setVisibility(View.GONE);
        } else {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        }
        if(entity.getProduct().getImageUrl()!=null && !"".equals(entity.getProduct().getImageUrl()))
            ImageLoader.getInstance().displayImage(entity.getProduct().getImageUrl(), viewHolder.productIV, ImageLoadOptions.getOptions());
        viewHolder.productName.setText(entity.getProduct().getName());
        viewHolder.productNorms.setText(entity.getNorms());

        if (checkedItemMap != null) {
            String isChecked = checkedItemMap.get(POSITION + position);
            viewHolder.checkBox.setChecked(isChecked.equals(TRUE)? true : false);
        }

        final ViewHolder finalViewHolder = viewHolder;
        finalViewHolder.wholeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ic = finalViewHolder.checkBox.isChecked();
                finalViewHolder.checkBox.setChecked(!ic);
                String key = POSITION + position;
                if (!checkedItemMap.get(key).equals(NO_ACTION))
                    checkedItemMap.put(key, (ic == true ? FALSE:TRUE));
                L.d("ZZZ", "pos " + position + " ischek " + !ic);
            }
        });

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder.productName.getLayoutParams();
        params.width = (SCREEN_WIDTH/2);
        viewHolder.productName.setLayoutParams(params);

//viewHolder.checkBox.set
        return view;
    }

    public int[] getCheckedItems() {

        int[] checkedItems = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (checkedItemMap.get(POSITION+i).equals(TRUE)) {
                Product product = list.get(i).getProduct();
                if (product.getProSpecialID() == 0) {
                    checkedItems[i] = product. getProId();
                } else {
                    checkedItems[i] = product.getProSpecialID();
                }
            }
        }
        return checkedItems;
    }

    public class ViewHolder{
        public ImageView  productIV;//产品图像
        public TextView productName,productNorms;//名称 规格 价格  数量,增加减少显示框的数量
        public CheckBox checkBox;
        public RelativeLayout wholeview;
    }

    public interface OnPlusMinusListener{
        public void plus(int position);
        public void minus(int position);
    }
}
