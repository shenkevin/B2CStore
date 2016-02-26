package cn.mstar.store.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**获取屏幕高度宽度
 * @author wenjundu 2015-7-17
 *
 */
public class Screen {

    private int width;  
    private int height;  
    public Screen(Activity context) {  
        DisplayMetrics metrics = new DisplayMetrics();  
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics );  
        width = metrics.widthPixels;  
        height = metrics.heightPixels;  
    }  
    /** 
     * @return 屏幕宽度 in pixel 
     */  
    public int getWidth() {  
        return width;  
    }  
  
    /** 
     * 
     * @return 屏幕高度 in pixel 
     */  
    public int getHeight() {  
        return height;  
    }  
  
    @Override  
    public String toString() {  
        return "Screen{" +  
                "width=" + width +  
                ", height=" + height +  
                '}';  
    }  

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Activity activity) {

        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            //LogHelper.e("Exception", "*****EXCEPTION*****\n", e);
        }

        return 0;

    }

    /**
     * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
     *
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    
}
