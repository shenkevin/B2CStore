package cn.mstar.store.customviews;


import cn.mstar.store.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**删除对话框
 * @author wenjund
 *
 */
public class TipsDialog extends Dialog {
private TextView tipsTV;//提示
private TextView cancelBtn,confirmBtn;//取消 确认
private Context context;
private OnTipsDialogListener listener;
	public TipsDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		View view = getLayoutInflater().inflate(R.layout.confirmation_dialog_box,
				null);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		ViewGroup.LayoutParams params = DialogMeasureUtil.measureDialog(
//				getWindow(), 1, 1);
		setContentView(view);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(true);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		tipsTV=(TextView) findViewById(R.id.tv_delete_confirmation);
		cancelBtn=(TextView) findViewById(R.id.tv_bt_cancel);
		confirmBtn=(TextView) findViewById(R.id.tv_bt_confirm);
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(listener!=null)
					listener.cancel();
			}
		});
		confirmBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(listener!=null)
					listener.confirm();
			}
		});
		
	}
	//设置提示文字
    public void setTipText(String tips){
    	if(tips!=null){
    		tipsTV.setText(tips);
    	}
    }
    public void show(){
    	super.show();
    }
    public void dismiss(){
    	super.dismiss();
    }
    public void setOnTipsDialogListener(OnTipsDialogListener listener){
    	this.listener=listener;
    }
    public interface OnTipsDialogListener{
    	public void confirm();
    	public void cancel();
    }
}
