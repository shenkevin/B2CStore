package cn.mstar.store.customviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.mstar.store.R;
import cn.mstar.store.activity.IndentDetailsActivity;
import cn.mstar.store.adapter.GoodsManagement_InnerAdapter;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.OrderListItem;
import cn.mstar.store.utils.CustomToast;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.NewLink;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;

public class RefreshableMockFragment extends Fragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener{


    private static final String TK = "tokenKey";
    // views
    protected LinearLayout lny_loading_layout, lny_network_error_layout, lny_noresult;
    protected ListView lv_list;
    private View actionbar;

    private static final String LINK = "LINK";
    private View rootView;
    private int preLast = 0;
    private int PAGE_COUNT = 1;
    private String BASE_LINK;
    private GoodsManagement_InnerAdapter adapter;
    private android.view.View footerview;
    private String tokenKey;
    private int maxPage = -1, totalItem = -1;
    private int pageCount = 10;
    private boolean finalFooterSet = false;
    private TextView wifi_retry;
    private boolean allowToAppend = false;

    public static RefreshableMockFragment getInstance (String link, String tk) {

        RefreshableMockFragment frg = new RefreshableMockFragment();
        Bundle argz = new Bundle();
        argz.putString(LINK, link);
        argz.putString(TK, tk);
        frg.setArguments(argz);
        return frg;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_refreshable_mock, container, false);
        lv_list = (ListView) rootView.findViewById(R.id.listview);
        initViews(rootView);
        ButterKnife.bind(rootView);
//        setRetainInstance(true);
        footerview = LayoutInflater.from(getActivity()).inflate(R.layout.product_list_footer_view, null);
        lny_loading = footerview.findViewById(R.id.lny_loading_layout);
        loadingCompletet = footerview.findViewById(R.id.tv_loadingcomplete);
        adapter = null;
        allowToAppend = true;
        BASE_LINK = getArguments().getString(LINK);
        this.tokenKey = getArguments().getString(TK);
        BASE_LINK = BASE_LINK+"&key="+tokenKey;
        // retrieve the data and be sure
        // need an adapter
        PAGE_COUNT = 1;
        loadData();
        hide_all(rootView);
        getLny_loading_layout(rootView).setVisibility(View.VISIBLE);
        lv_list.setOnScrollListener(this);
        lv_list.setOnItemClickListener(this);

        wifi_retry = (TextView) rootView.findViewById(R.id.wifi_retry);
        wifi_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload ();
            }
        });
        return rootView;
    }

    public void reload() {
        maxPage = -1;
        finalFooterSet = false;
        lv_list.removeFooterView(footerview);
        lny_loading.setVisibility(View.GONE);
        loadingCompletet.setVisibility(View.GONE);
        adapter = null;
        lv_list.setOnScrollListener(this);
        lv_list.setOnItemClickListener(this);
        hide_all(rootView);
        getLny_loading_layout(rootView).setVisibility(View.VISIBLE);
        PAGE_COUNT = 1;
        L.d("reload:::", "curpage " + PAGE_COUNT + " pageCount " + pageCount);
        loadData();
        allowToAppend = true;
    }

    private void appendData() {
        if (allowToAppend) {
            L.d("reload:::", "append bcs pagecount " + pageCount + " and maxPage = " + maxPage);
            if (maxPage == -1 || PAGE_COUNT < maxPage) {
                PAGE_COUNT++;
                loadData();
            } else {
                noResultFound();
            }
        }
    }


    private void loadData() {

        String baseLink = BASE_LINK + "&curpage="+PAGE_COUNT+ "&page="+pageCount;
        L.d("reload:::",baseLink );
        VolleyRequest.GetCookieRequest(getActivity(), baseLink, new VolleyRequest.HttpStringRequsetCallBack() {

            @Override
            public void onSuccess(String result) {

                L.d("XXX ORDER ", result);
                try {
                    Gson gson = new Gson();
                    JsonElement elm = gson.fromJson(result, JsonElement.class).getAsJsonObject().get("data");
                    if (maxPage == -1) {
                        // get the max item count.
                        totalItem = elm.getAsJsonObject().get("list_count").getAsInt();
                        // each page needs
                        maxPage = totalItem / pageCount;
                        if (!(totalItem % pageCount == 0)) {
                            maxPage++;
                        }
                    }
                    L.d("reload:::", "maxpage is " + maxPage);
//					L.d("XXX ELEM 1 " + elm.toString());
                    OrderListItem[] tmp = gson.fromJson(elm.getAsJsonObject().get("OrderList"), OrderListItem[].class);
                    JSONObject jsonObject = new JSONObject(result);
                    for (OrderListItem item : tmp
                            ) {
                        L.d("tmp:::", "tmp is " + item.toString());
                    }
                    if (tmp != null && tmp.length != 0) {
                        setUpAdapter(tmp);
                    } else
                        noResultFound();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    noResultFound();
                } catch (Exception e) {
                    e.printStackTrace();
                    noResultFound();
                } finally {
                    noResultFound();
                }
            }

            @Override
            public void onFail(String error) {
                // check if the problem comes from the network, if yes... then shows ~
                // this can only be caused by network
                networkExceptionError();
            }
        });

    }

    private void setUpAdapter(OrderListItem[] order_items) {

        List<OrderListItem> itemz = fromArrayToList(order_items);

        L.d("XXX CCC "+ itemz.toString());
        // set up adapter.
        if (adapter == null) {
            adapter = new GoodsManagement_InnerAdapter(getActivity(), itemz);
            if (lv_list != null) {
//                footerview = LayoutInflater.from(getActivity()).inflate(R.layout.product_list_footer_view, null);
                lv_list.addFooterView(footerview);
                lv_list.setAdapter(adapter);
                hide_all(rootView);
                lv_list.setVisibility(View.VISIBLE);
            }
        } else {
            if (PAGE_COUNT == 1)
                adapter = new GoodsManagement_InnerAdapter(getActivity(), itemz);
            else
                adapter.addAll(itemz);
        }

        if (itemz.size() < pageCount) {
            L.e("ZXXX", "orderitemsize " + order_items + " pageCount" + pageCount);
            setFinalFooter();
        }
        setFinalFooter();
    }

    private List<OrderListItem> fromArrayToList(OrderListItem[] order_items) {

        List<OrderListItem> list = new ArrayList<>();
        for (OrderListItem it: order_items
                ) {
            list.add(it);
        }
        return list;
    }

    View lny_loading ;
    View loadingCompletet;

    private void setFinalFooter() {
//
        if ((!finalFooterSet && totalItem == adapter.getCount()) || (lny_loading.getVisibility() == View.VISIBLE && totalItem == adapter.getCount())) {
            lny_loading.setVisibility(View.GONE);
            loadingCompletet.setVisibility(View.VISIBLE);
            lv_list.setOnScrollListener(null);
            if (lny_loading.getVisibility() == View.GONE && loadingCompletet.getVisibility() == View.VISIBLE) {
                finalFooterSet = true;
                allowToAppend = false;
            }
        }
    }

    protected void hide_all (View view) {
        getLny_loading_layout(view).setVisibility(View.GONE);
        getLny_network_error_layout(view).setVisibility(View.GONE);
        getLny_noresult(view).setVisibility(View.GONE);
        getLv_list(view).setVisibility(View.GONE);
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

        // if the listadapter is = 0... then this... otherwise, the only the bottom progress stuff.

        if (adapter == null) {
            hide_all(rootView);
            getLny_noresult(rootView).setVisibility(View.VISIBLE);
        } else {
            // change footer
            setFinalFooter();
        }
    }

    protected void initViews (View view) {
        lny_loading_layout = (LinearLayout) view.findViewById(R.id.lny_loading_layout);
        lny_network_error_layout = (LinearLayout) view.findViewById(R.id.lny_network_error_view);
        lny_noresult = (LinearLayout) view.findViewById(R.id.lny_no_result);
        actionbar = view.findViewById(R.id.the_title_layout);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        reload();
//        CustomToast.makeToast(getActivity(), "Update -- fragment", Toast.LENGTH_SHORT);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (adapter != null && adapter.getCount() >= totalItem) {
            setFinalFooter();
        }
    }

    @Override
    public void onScroll(AbsListView lw, final int firstVisibleItem,
                         final int visibleItemCount, final int totalItemCount) {

        // Make your calculation stuff here. You have all your
        // needed info from the parameters of this function.

        // Sample calculation to determine if the last
        // item is fully visible.
//                firstVisibleItem + visibleItemCount >= totalItemCount-1
        final int lastItem = firstVisibleItem + visibleItemCount;
        if (lastItem >= totalItemCount - 1) {
            if (preLast != lastItem) { //to avoid multiple calls for last item
                preLast = lastItem;
//                                CustomToast.mSystemToast(getActivity(), "bottom reached");
                // append data.
//                        if (!finalFooterSet)
                appendData();
//                            L.d("XXX", "append data");
//                        } /*else {
//                            setFinalFooter();
//                        }
                L.d("bottom of listivew ");
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position < adapter.getCount()) {

            // there should be an onclick listener passed to each of the viewz.



            final OrderListItem orderItem = (OrderListItem) adapter.getItem(position);
            if (Integer.valueOf(orderItem.orderInfo.status) == 20) {
                return;
            }
            final LoadingDialog dialog = new LoadingDialog(getActivity());
            dialog.show();
            final String link = NewLink.GOPAY_FOR_ORDER + "&key=" + Utils.getTokenKey((MyApplication) getActivity().getApplication()) + "&OrderNum=" + orderItem.orderInfo.orderId;
//                L.d("XXX", link);
            L.d("link:::", link);
            VolleyRequest.GetCookieRequest(getActivity(), link, new VolleyRequest.HttpStringRequsetCallBack() {
                @Override
                public void onSuccess(String result) {

                    L.d("append link " + link);
                    L.d("append res " + result);
                    // check if the answer looks like something...
                    L.d("requestUrl", result);
                    if (!"".equals(result)) {
                        Intent intent = new Intent(getActivity(), IndentDetailsActivity.class);
                        intent.setAction(MyAction.goPayAction);
                        // the actions have to get down before we jump to the next.
                        L.e(orderItem.orderInfo.orderId);
                        intent.putExtra("data", result);
                        intent.putExtra("link", link);
                        if (dialog != null) {
                            dialog.cancel();
                            dialog.dismiss();
                        }
                        startActivityForResult(intent, Activity.RESULT_OK);
                    } else {
                        CustomToast.makeToast(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT);
                    }
                    if (dialog != null) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFail(String error) {
                    CustomToast.makeToast(getActivity(), getString(R.string.network_error), Toast.LENGTH_SHORT);
                    if (dialog != null) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                }
            });
        }
    }
}
