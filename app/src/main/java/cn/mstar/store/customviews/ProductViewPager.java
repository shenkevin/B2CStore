package cn.mstar.store.customviews;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ProductViewPager extends ViewPager {

	private HashMap<Integer, Object> mObjs = new LinkedHashMap<Integer, Object>();
	
	public ProductViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ProductViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		}  catch (IllegalArgumentException e) {
			//不理会		
			return false;
		}catch(ArrayIndexOutOfBoundsException e ){
			//不理会
			return false;
		}
	}
	public View findViewFromObject(int position) {
		Object o = mObjs.get(Integer.valueOf(position));
		if (o == null) {
			return null;
		}
		PagerAdapter a = getAdapter();
		View v;
		for (int i = 0; i < getChildCount(); i++) {
			v = getChildAt(i);
			if (a.isViewFromObject(v, o))
				return v;
		}
		return null;
	}
	

	public void setObjectForPosition(Object obj, int position) {
		mObjs.put(Integer.valueOf(position), obj);
	}
	
}
