package cn.mstar.store.customviews;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/**
 * 选择模式的 RelativeLayout
 * @author wenjundu
 *
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable{

	private boolean mChecked = false;  
	public CheckableRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setChecked(boolean checked) {
		// TODO Auto-generated method stub
		 if (mChecked != checked) {  
	            mChecked = checked;  
	            refreshDrawableState(); 
	            for (int i = 0, len = getChildCount(); i < len; i++) {
	                View child = getChildAt(i);  
	                if(child instanceof Checkable){  
	                    ((Checkable) child).setChecked(checked);  
	                }  
	            }
		 }
	}

	@Override
	public boolean isChecked() {
		// TODO Auto-generated method stub
		return mChecked;
	}

	@Override
	public void toggle() {
		// TODO Auto-generated method stub
		setChecked(!mChecked);  
	}


}
