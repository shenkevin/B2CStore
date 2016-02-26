package cn.mstar.store.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mstar.store.R;
import cn.mstar.store.activity.ConfirmIndentActivity;
import cn.mstar.store.activity.MainActivity;
import cn.mstar.store.adapter.ShoppingCarLvAdapter;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.customviews.LoadingDialog;
import cn.mstar.store.entity.ShoppingCartItem;
import cn.mstar.store.interfaces.OnGoodsCheckedListener;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.NewLink;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;

public class ShoppingCartFragment extends Fragment implements OnGoodsCheckedListener {

    private static final java.lang.String CAR_DATA = "CAR_DATA";
    private     int SCREENWIDTH;
    //    private RelativeLayout rel_bottom;
    private ShoppingCarLvAdapter mAdapter;
    private int SCREEN_WIDTH;
    @Bind(R.id.ck_checkall) CheckBox ck_checkAll;
    @Bind(R.id.tv_totalPrice) TextView tv_totalPrice;
    @Bind(R.id.bt_go_pay) Button bt_go_pay;
    @Bind(R.id.wifi_retry) TextView tv_wifi_retry;
    protected LinearLayout lny_loading_layout, lny_network_error_layout, lny_noresult;
    protected ListView lv_list;
    private View rootView;
    private boolean sustain = true;
    private View footerview;
    private View actionbar;
    private MyApplication app;
    private String link;
    private int cartTotalItem = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
        ButterKnife.bind(this, rootView);
        app = (MyApplication) getActivity().getApplication();
        initViews(rootView);
        hide_all(rootView);
        SCREEN_WIDTH = getScreenWidth();
        getLny_loading_layout(rootView).setVisibility(View.VISIBLE);
        // we retrieve some data from this
        link = NewLink.LIST_SHOPPING_CART+"&key="+ Utils.getTokenKey((MyApplication) getActivity().getApplication());
        // change the data to a list
        initVarz ();
        inflateDatas();
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
//        inflateDatas();
    }


    private void makeToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.wifi_retry)
    public void inflateDatas() {
        i_showProgressDialog();
        if (ck_checkAll!= null)
            ck_checkAll.setChecked(false);
        link+="&size=240&page=10000";
        VolleyRequest.GetCookieRequest(getActivity(), link, new VolleyRequest.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                // get the result as a JsonObject and
                // turn the result into the ShoppingCardObject.
                try {
                    L.d("XXX:::", result);
                    Gson gson = new Gson();
                    JsonArray item = gson.fromJson(result, JsonObject.class).getAsJsonObject("data").get("cartInfo").getAsJsonArray();
                    cartTotalItem = gson.fromJson(result, JsonObject.class).getAsJsonObject("data").get("list_count").getAsInt();
                    Type type = new TypeToken<ShoppingCartItem[]>() {
                    }.getType();
                    ShoppingCartItem[] itemz = gson.fromJson(item, type);
                    List<ShoppingCartItem> data = new ArrayList<>();
                    for (ShoppingCartItem tmp : itemz) {
                        data.add(tmp);
                    }
                    if (data == null || data.size() == 0) {
                        noResultFound();
                    } else {
                        setAdapter(data);
                    }
                    i_dismissProgressDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                    noResultFound();
                } finally {
                    i_dismissProgressDialog();
                }
            }

            @Override
            public void onFail(String error) {
                // network error certainly
                networkExceptionError();
                i_dismissProgressDialog();
            }
        });
    }

    private void setAdapter(List<ShoppingCartItem> data) {

        // set up adapter.
        L.e("XXX", data.toString());
        mAdapter = null;
        if (mAdapter == null) {
            mAdapter = new ShoppingCarLvAdapter((MainActivity) getActivity(), data, SCREEN_WIDTH);
            if (lv_list != null) {
//                footerview = LayoutInflater.from(getActivity()).inflate(R.layout.product_list_footer_view, null);
//                lv_list.addFooterView(footerview);
                lv_list.setAdapter(mAdapter);
                hide_all(rootView);
                lv_list.setVisibility(View.VISIBLE);
            }
        } /*else if (mAdapter.getCount() > 0){
            mAdapter.addAll(data);
        }*/


        ck_checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (sustain) {
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    // get the ckbox from the viewz
                    LinearLayout lny = (LinearLayout) lv_list.getChildAt(i);
                    if (lny != null) {
                        CheckBox ck = (CheckBox) lny.findViewById(R.id.ck_radiobutton_category_name);
                        if (ck != null)
                            ck.setChecked(ck_checkAll.isChecked());
                        if (mAdapter != null)
                            mAdapter.checkAll(ck_checkAll.isChecked());
                    }
                }
                if (mAdapter.getCount() == 0) {
                    if (ck_checkAll.isChecked())
                        ck_checkAll.setChecked(false);
                }
//                }
//                sustain = true;
            }
        });
        bt_go_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // outsideCheckedGoods 是一个  List<ShoppingCartItem>
                if (bt_go_pay.isEnabled() && outsideCheckedGoods != null && outsideCheckedGoods.size() > 0) {
                    Intent intent = new Intent(getActivity(), ConfirmIndentActivity.class);
                    // 把所有产品传给下一个activity
                    intent.setAction(MyAction.goPayAction);
                    intent.putExtra("outsideCheckedGoods", (Serializable) outsideCheckedGoods);
                    L.d("shop:::", outsideCheckedGoods.toString());
                    startActivity(intent);
                    // 启动activity
//                    intent.put
                }
            }
        });
        i_dismissProgressDialog();
    }

    private void initVarz () {
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(getString(R.string.myshoppingcar));
    }

    //actionbar
    @Bind(R.id.title_name) TextView tv_title;

    LoadingDialog dialog;
    public void i_showProgressDialog() {
        /*dialog = new LoadingDialog(getActivity());
        dialog.show();*/
        setLoading(rootView);
    }

    public void i_dismissProgressDialog () {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
            dialog = null;
        }
    }

    public int getScreenWidth () {

        DisplayMetrics metrics = new DisplayMetrics();
        (getActivity()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }


    public static Fragment getInstance(String s) {
        Fragment frg = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(CAR_DATA, s);
        frg.setArguments(args);
        return frg;
    }

    List<ShoppingCartItem> outsideCheckedGoods;

    @Override
    public void updateBottom(List<ShoppingCartItem> checkedGoods) {
        if (checkedGoods == null || (checkedGoods != null && checkedGoods.size() == 0))
            ck_checkAll.setChecked(false);
        outsideCheckedGoods = checkedGoods;
        Double total = .0;
        for (ShoppingCartItem item: checkedGoods
                ) {
            total+=item.price*item.number;
        }
        sustain = false;
        if (checkedGoods.size() != mAdapter.getCount()) {
            ck_checkAll.setChecked(false);
            // make them not to influence the others.
        } else {
            ck_checkAll.setChecked(true);
            // make them not to influence the others.
        }
        tv_totalPrice.setText(getString(R.string.yuan_char)+ Utils.formatedPrice(total));
        if (checkedGoods.size() == 0) {
            bt_go_pay.setEnabled(false);
        } else {
            bt_go_pay.setEnabled(true);
        }
    }

    protected void hide_all (View view) {
        getLny_loading_layout(view).setVisibility(View.GONE);
        getLny_network_error_layout(view).setVisibility(View.GONE);
        getLny_noresult(view).setVisibility(View.GONE);
        getLv_list(view).setVisibility(View.GONE);
    }

    protected void setLoading (View view) {
        hide_all(view);
        getLny_loading_layout(view).setVisibility(View.VISIBLE);
    }


    public ListView getLv_list(View view) {
        if (lv_list == null)
            lv_list = (ListView) view.findViewById(R.id.listview);
        return lv_list;
    }


    protected void networkExceptionError() {

        hide_all(rootView);
        getLny_network_error_layout(rootView).setVisibility(View.VISIBLE);
    }


    protected void noResultFound() {

        hide_all(rootView);
        getLny_noresult(rootView).setVisibility(View.VISIBLE);
//
    }

    protected void initViews (View view) {
        lny_loading_layout = (LinearLayout) view.findViewById(R.id.lny_loading_layout);
        lny_network_error_layout = (LinearLayout) view.findViewById(R.id.lny_network_error_view);
        lny_noresult = (LinearLayout) view.findViewById(R.id.lny_no_result);
        actionbar = view.findViewById(R.id.the_title_layout);
        lv_list = (ListView) view.findViewById(android.R.id.list);
    }


    protected LinearLayout getLny_loading_layout(View v) {
        if (lny_loading_layout == null)
            lny_loading_layout = (LinearLayout) v.findViewById(R.id.lny_loading_layout);
        return lny_loading_layout;
    }


    protected LinearLayout getLny_network_error_layout(View v) {
        if (lny_network_error_layout == null)
            lny_network_error_layout = (LinearLayout) v.findViewById(R.id.lny_network_error_view);
        return lny_network_error_layout;
    }


    protected LinearLayout getLny_noresult(View v) {
        if (lny_noresult == null)
            lny_noresult = (LinearLayout) v.findViewById(R.id.lny_no_result);
        return lny_noresult;
    }


}
