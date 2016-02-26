package cn.mstar.store.utils;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public class DialogMeasureUtil {
	
	/**
	 * 获取计算后布局参数
	 * @param window
	 * @param baseRate 宽度与最大宽度比
	 * @param hwRate 高度与宽度比
	 * @return
	 */
	public static LayoutParams measureDialog(Window window, double baseRate, double hwRate){
		return measureDialog(window.getWindowManager().getDefaultDisplay(), baseRate, hwRate);
	}
	
	@SuppressWarnings("deprecation")
	public static LayoutParams measureDialog(Display display, double baseRate, double hwRate){
		return measureDialog(display.getWidth(), display.getHeight(), baseRate, hwRate);
	}
	
	public static LayoutParams measureDialog(int maxWidth, int maxHeight, double baseRate, double hwRate){
		int width = -1, height = -1;
		if(maxWidth > maxHeight){
			width = (int) (maxWidth * baseRate);
		}else{
			double rate = baseRate * maxHeight / maxWidth;
			rate = rate>0.95?0.95:rate;
			width = (int) (maxWidth * rate);
		}
		height = (int) (width * hwRate);
		LayoutParams params = new LayoutParams(width, height);
		return params;
	}
	
}
