package cn.mstar.store.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import cn.mstar.store.customviews.AppFragment;
import cn.mstar.store.entity.OrdersItem;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.VolleyRequest;
import cn.mstar.store.utils.VolleyRequest.HttpStringRequsetCallBack;


public class Goods_MockFragment extends AppFragment {


	public static final String DATA_SOURCE = "DATA_SOURCE";

	public static Fragment getInstance (String LINK) {

		Fragment frg = new Goods_MockFragment();
		Bundle args = new Bundle ();
		args.putString(DATA_SOURCE, LINK);
		frg.setArguments(args);
		return frg;
	}

	private View view;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initViews(view);
		this.view = view;
		hideActionBar ();
		// we put a fragment inside this... i think i can easily put a listFragment inside.

		// the only thing that change is the elements...

		// i think for each elem, i just create a listfragment...
		// send him the data and the adapter and let him do the things by him self.

		// I AM THE ONE RETRIEVING THE DATA AND MANAGING THE SITUATIONS...

		String data_source = getArguments().getString(DATA_SOURCE);
		hide_all(view);
		getLny_loading_layout(view).setVisibility(View.VISIBLE);
		// start loading the data...
		loadData (data_source);
	}


	protected void setUpListFragment(List<OrdersItem> data, String link) {

		// set list adapter NO NEED TO ADD ANOTHER FRAGMENT ...


	}

	private void loadData(final String link) {

		VolleyRequest.GetCookieRequest(getActivity(), /*Constants.ORDER_ALL*/link+"?page=1", new HttpStringRequsetCallBack() {

			@Override
			public void onSuccess(String result) {

				// we have the string here, so we just
//				L.d("XXX ORDER ",result);
				List<OrdersItem> order_items = null;
				try {
					Gson gson = new Gson();
					JsonElement elm = gson.fromJson(result, JsonElement.class).getAsJsonObject().get("data");
//					L.d("XXX ELEM 1 " + elm.toString());
					OrdersItem.Prototype tmp = gson.fromJson(elm, OrdersItem.Prototype.class);
//					L.d("XXX ORDER ", tmp.toString());
					// it is full from now.
					// make a list of what we need from here.
					if (order_items == null) {
						order_items = new ArrayList<OrdersItem>();
					}
					if (tmp != null && tmp.OrderList != null) {
						for (OrdersItem.Prototype.OrderList little : tmp.OrderList) {
							OrdersItem item = new OrdersItem(little);
							order_items.add(item);
						}
						L.d("XXX DATA ", link);
						//						ListAdapter adapter = new GoodsManagement_InnerAdapter(getActivity(), order_items);
						//						setListAdapter(adapter);
						// create a listFragment that shows the stuffs
						//						CustomToast.mSystemToast(getActivity(), order_items.toString());
						if (order_items.size() > 0) {
							setUpListFragment(order_items, link);
							L.d("XXX STATUS ", order_items.size()+"");
						}
						else
							noResultFound();
					} else {
						noResultFound ();
					}
				} catch  (NullPointerException e){

					e.printStackTrace();
					VolleyRequest.manifestNetworkError(getActivity(), null);
					noResultFound ();
					// let the main fragment know that there is no result...
					noResultFound ();
				} catch (Exception e) {
					e.printStackTrace();
					noResultFound ();
				}
			}

			@Override
			public void onFail(String error) {
				// check if the problem comes from the network, if yes... then shows ~
				// this can only be caused by network
				networkExceptionError ();
			}
		});

	}

	protected void networkExceptionError() {

		hide_all(view);
		getLny_network_error_layout(view).setVisibility(View.VISIBLE);
	}


	protected void noResultFound() {

		hide_all(view);
		getLny_noresult(view).setVisibility(View.VISIBLE);
	}









	//	public static final int LOADING_START = 12, LOADING_END = 13;

	/*
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		//set color scheme
		setColorScheme(R.color.color_scheme_1_1, R.color.color_scheme_1_2,
				R.color.color_scheme_1_3, R.color.color_scheme_1_4);

		// launch refreshing
		initiateRefresh ();

		setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {

				initiateRefresh();
			}
		});

	}


	private void initiateRefresh() {

		VolleyRequest.GetCookieRequest(getActivity(), Constants.ORDER_ALL+"?page=1", new HttpStringRequsetCallBack() {

			@Override
			public void onSuccess(String result) {

				// we have the string here, so we just 
				List<OrdersItem> order_items = null;
				try {
					OrdersItem.Prototype tmp = (new Gson()).fromJson(result, OrdersItem.Prototype.class);
					// it is full from now.
					// make a list of what we need from here.
					if (order_items == null) {
						order_items = new ArrayList<OrdersItem>();
					}
					if (tmp != null && tmp.data != null) {
						for (OrdersItem.Prototype.InnerData little : tmp.data) {
							OrdersItem item = new OrdersItem(little);
							order_items.add(item);
						}
						ListAdapter adapter = new GoodsManagement_InnerAdapter(getActivity(), order_items);
						setListAdapter(adapter);
					}
				} catch  (Exception e){

					e.printStackTrace();
					VolleyRequest.manifestNetworkError(getActivity(), "exception");
					// let the main fragment know that there is no result...
					NoResultException ();
				} finally {
					setRefreshing(false);					
				}
			}

			@Override
			public void onFail(String error) {
			}
		});

	}

	public static android.support.v4.app.Fragment getInstance() {
		android.support.v4.app.Fragment fr = new Goods_AllCasesFragment();
		return fr;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}


	private void onRefreshComplete(List<OrdersItem> result) {

		// Remove all items from the ListAdapter, and then replace them with the new items
		GoodsManagement_InnerAdapter adapter = (GoodsManagement_InnerAdapter) getListAdapter();
		result.clear();
		for (OrdersItem cheese : result) {
			adapter.add(cheese);
		}
		// Stop the refreshing indicator
		setRefreshing(false);
	}*/
}
