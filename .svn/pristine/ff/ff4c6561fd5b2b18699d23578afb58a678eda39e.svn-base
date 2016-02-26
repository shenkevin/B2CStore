package cn.mstar.store.customviews;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ListView;

/**阻尼ListView
 * @author wenjundu 2015-7-31
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class OverListView extends ListView {
	   private Context context;
       public OverListView(Context context, AttributeSet attrs, int defStyle) {
               
               super(context, attrs, defStyle);
               this.context=context;
               // TODO Auto-generated constructor stub
       }
       public OverListView(Context context, AttributeSet attrs  ) {
               super(context, attrs );
               this.context=context;
               // TODO Auto-generated constructor stub
       }
       public OverListView(Context context ) {
               super(context);
               this.context=context;
               // TODO Auto-generated constructor stub
       }
       private OnOverScrollListener listener;
       public void setOnOverScrollListener(OnOverScrollListener listener) {
               this.listener=listener;
       }
        
       public interface OnOverScrollListener {
               void overScrollDown(float dis);
               void overScrollUp(float dis);
               void onScrollResumeFromBottomToTop(float maxDis,float dis);
               void onScrollResumeFromTopToBottom(float maxDis,float dis);
               void onScrollResumeFinished(   );
               
       }
       private float pressY;
       private float downDis;
       private float upDis;
       
       private float pressX;
       @Override
       public boolean onInterceptTouchEvent(MotionEvent ev) {
               // TODO Auto-generated method stub
               
               if (ev.getAction()==MotionEvent.ACTION_DOWN) {
                       pressY=ev.getRawY();
                       pressX=ev.getRawX();
//                       System.out.println("onInterceptTouchEvent down "+pressY);
               }
               if (ev.getAction()==MotionEvent.ACTION_MOVE) {
                       float nowX=ev.getRawX();
                       float nowY=ev.getRawY();
                       
                       float disX=Math.abs(nowX-pressX);
                       float disY=Math.abs(nowY-pressY);
                       if (disY>=ViewConfiguration.get(context).getScaledTouchSlop()&&disY>disX) {
                               return true;
                       }
                       
               }
               return super.onInterceptTouchEvent(ev);
       }
       @Override
       public boolean onTouchEvent(final MotionEvent ev) {
               // TODO Auto-generated method stub
               switch (ev.getAction()) {
               case MotionEvent.ACTION_DOWN:
                       pressY=ev.getRawY();
//                       System.out.println("down "+pressY);
                       break;
               case MotionEvent.ACTION_MOVE:
                       int sy=getScrollY();
//                       System.out.println("move    getRawY�� " +ev.getRawY()+   "    getScrollY: "+sy  +"   getChildAt(0).getHeight()��"+getChildAt(0).getHeight()+"   getHigh():"+getHeight());
                       if (sy==0&&ev.getRawY()>pressY) {
//                               isDown=true;
                               downDis=(float) ((ev.getRawY()-pressY)/3);
                               setTranslationY(downDis);
                               if (listener!=null) {
                                       listener.overScrollDown(downDis);
                               }
                               return true;
                       }
//                       if (isDown) {
//                               
//                               setTranslationY(ev.getRawY()-pressY);
//                               
//                               return true;
//                       }
                        
                       if (sy+getHeight()==getChildAt(0).getHeight()&&ev.getRawY()<pressY) {
//                               isUp=true;
                               upDis=(float) ((ev.getRawY()-pressY)/3);
                               setTranslationY(upDis);
                               if (listener!=null) {
                                       listener.overScrollUp(upDis);
                               }
                               return true;
                               
                       }
                       
                       if (getHeight()>=getChildAt(0).getHeight()&&ev.getRawY()<pressY) {
                               upDis=(float) ((ev.getRawY()-pressY)/3);
                              setTranslationY(upDis);
                               if (listener!=null) {
                                       listener.overScrollUp(upDis);
                               }
                               return true;
                       }
//                       if (isUp) {
//                               setTranslationY(ev.getRawY()-pressY);
//                               return true;
//                               
//                       }
                       pressY=ev.getRawY();
                       try {
                               return super.onTouchEvent(ev);
                       } catch (Exception e) {
                               // TODO Auto-generated catch block
                               e.printStackTrace();
                               return false;
                       }
                        
               case MotionEvent.ACTION_UP:
               case MotionEvent.ACTION_CANCEL: 
                       final float start=getTranslationY();
                       ObjectAnimator animator=ObjectAnimator.ofFloat(this, "translationY", start,0);
                    //   animator.setDuration(500);
                       animator.setInterpolator(new AccelerateDecelerateInterpolator());
                       animator.addUpdateListener(new AnimatorUpdateListener() {
                               
                               @Override
                               public void onAnimationUpdate(ValueAnimator animation) {
                                       // TODO Auto-generated method stub
                                       if (listener!=null) {
                                               if (ev.getRawY()>pressY) {
                                                       listener.onScrollResumeFromBottomToTop(start,(Float)animation.getAnimatedValue());
                                               }else
                                                       listener.onScrollResumeFromTopToBottom(start,(Float)animation.getAnimatedValue());
                                       }
                               }
                       });
                       
                       animator.addListener(new AnimatorListenerAdapter() {
                               @Override
                               public void onAnimationEnd(Animator animation) {
                                       // TODO Auto-generated method stub
                                       super.onAnimationEnd(animation);
                                       if (listener!=null) {
                                               listener.onScrollResumeFinished();
                                       }
                               }
                       });
                       animator.start();
                       
                       break;

                
               }
               return true;
       }
}
