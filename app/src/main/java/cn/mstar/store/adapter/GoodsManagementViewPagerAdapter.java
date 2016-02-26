package cn.mstar.store.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;

import cn.mstar.store.R;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.customviews.RefreshableMockFragment;
import cn.mstar.store.utils.Constants;
import cn.mstar.store.utils.NewLink;
import cn.mstar.store.utils.Utils;

/**
 * Created by Ultima on 7/11/2015.
 */
public class GoodsManagementViewPagerAdapter extends FragmentPagerAdapter {


    private static MyApplication app;
    private InnerFragmentManager innerFragmentManager;
    private String[] page_title;
    private static Context mctx;
    private static String tokenKey;

    public GoodsManagementViewPagerAdapter(FragmentManager fm, MyApplication app) {
        super(fm);
        innerFragmentManager = InnerFragmentManager.getInstance();
        this.app = app;
        mctx = app.getApplicationContext();
        page_title = mctx.getResources().getStringArray(R.array.goods_managment);
    }


    @Override
    public Fragment getItem(int position) {

        if (innerFragmentManager == null)
            innerFragmentManager = InnerFragmentManager.getInstance();
        return innerFragmentManager.getFrag(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (page_title == null) {
            return "";
        }
        return page_title[position];
    }

    @Override
    public int getCount() {
        return Constants.GOODS_MENU_TAB_POSITION.length-1;
    }

    public void clear() {
        if (innerFragmentManager != null && innerFragmentManager.frag_db != null)
            innerFragmentManager.frag_db.clear();
    }

    public void updateAll() {

        for (int i = 0; i < 4; i++) {
            String pos_ = (i+1)+"";
            if (innerFragmentManager.frag_db.get(pos_) != null)
                innerFragmentManager.frag_db.get(pos_).reload ();
        }
    }


    private static class InnerFragmentManager {

        private HashMap<String, RefreshableMockFragment> frag_db;
        private static InnerFragmentManager me;

        public static InnerFragmentManager getInstance () {
            if (me == null)
                me = new InnerFragmentManager();
            return me;
        }



        public Fragment getFrag (int position) {

            tokenKey = Utils.getTokenKey(app);
            String pos_ = (position+1)+"";
            if (frag_db == null)
                frag_db = new HashMap<>();
            if (frag_db.get(pos_) == null){
                switch (position+1) {
                    case Constants.ALL:
                        frag_db.put(pos_, RefreshableMockFragment.getInstance(NewLink.GET_ORDER_LIST_ALL, tokenKey));
                        break;
                    case Constants.WAITINGPAY:
                        frag_db.put(pos_, RefreshableMockFragment.getInstance(NewLink.GET_ORDER_LIST_WAITING_FOR_PAY, tokenKey));
                        break;
                    case Constants.WAITINGRECEIVING:
                        frag_db.put(pos_, RefreshableMockFragment.getInstance(NewLink.GET_ORDER_LIST_WAITING_FOR_RECEIVE, tokenKey));
                        break;
                    case Constants.WAITINGSENDING:
                        frag_db.put(pos_, RefreshableMockFragment.getInstance(NewLink.GET_ORDER_LIST_WAITING_FOR_SEND, tokenKey));
                        break;
                }
            }
            return frag_db.get(pos_);
        }

    }

}
