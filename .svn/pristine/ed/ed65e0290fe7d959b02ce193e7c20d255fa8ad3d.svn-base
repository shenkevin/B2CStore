package cn.mstar.store.utils;

import android.content.Context;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import cn.mstar.store.R;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class ElementTransparenceHelper implements ViewTreeObserver.OnScrollChangedListener{

    public ScrollView scrollView;
    public RelativeLayout viewTransparent;

    public ElementTransparenceHelper(Context ctx, ScrollView scrollView, RelativeLayout viewTransparent) {

        this.scrollView = scrollView;
        this.viewTransparent = viewTransparent;
        // we give him the item that is supposed to be transparent / not.

        // we give him the initial value of scrolling

        // and the max value of scrolling ///

        // we give him a kind of interface between the scrollview and him.
    }

    private static int previousColor;

    @Override
    public void onScrollChanged() {
        int scrollY = scrollView.getScrollY(); //for verticalScrollView
        L.d("XXX " + scrollY);
        // on a standard version, it gots to be viewable as soon as the upperview is completely invisible.
        if (previousColor != R.color.green && viewTransparent!= null) {
            // if decreasing ... transparent - // if increasing, transparent +
            viewTransparent.getBackground().setAlpha(14);
        }
    }

}
