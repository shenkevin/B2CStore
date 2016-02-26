package cn.mstar.store.customviews;

import cn.mstar.store.R;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**产品筛选pop
 * @author wenjundu 
 *
 */
public class FilterPopup extends PopupWindow implements OnClickListener{
	private View mPopView;
	private TextView btnReset,btnConfirm;//重置 确定
	private ListView listView;
	private OnDialogListener listener;
	public FilterPopup(Context context,int height) { 
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mPopView= inflater.inflate(R.layout.dialog_filter_modul, null);
		
		btnReset=(TextView) mPopView.findViewById(R.id.reset_tv);
		btnConfirm=(TextView) mPopView.findViewById(R.id.confirm_tv);
		listView=(ListView) mPopView.findViewById(R.id.filter_listview);
		getData();
        this.setContentView(mPopView);

        this.setWidth(LayoutParams.WRAP_CONTENT);
		this.setHeight(height);
		// 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 点击外面的控件也可以使得PopUpWindow dimiss
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Fiter_PopupAnimation);

        ColorDrawable dw = new ColorDrawable(0xffffffff);//0xb0000000
       // ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);//半透明颜色

	}
	//获取筛选数据
	private void getData() {
		// TODO Auto-generated method stub
		
	}
	public interface OnDialogListener{
		public void filter();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.reset_tv:

			break;

		case R.id.confirm_tv:
			if(listener!=null)
				listener.filter();
			break;
		}
	} 
}
