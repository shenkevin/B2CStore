package cn.mstar.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mstar.store.R;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.customviews.LoadingDialog;
import cn.mstar.store.entity.FavoriteManagerItem;
import cn.mstar.store.fragments.FavoriteItemListFragment;
import cn.mstar.store.utils.Constants;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.MaFragmentManagerSpace;
import cn.mstar.store.utils.NewLink;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;


public class MyFavoriteManagmentActivity extends FragmentActivity {


	private static int SCREENWIDTH ;
	ListView lv_favorite_list;
	FrameLayout frameLayout;
	RelativeLayout rel_all_items;
	ImageView iv_indicator;
	ViewHolder vh;
	ImageView iv_back;
	TextView tv_toptitle;
	private int ALL_FAVORITE_COUNT = 0;
	private FragmentManager fragmentManager;
	private boolean firstTransaction = true;
	private FavoriteItemListFragment previousFragment;
	private String tokenKey;
	@Bind(R.id.lny_loading_layout) protected LinearLayout lny_loading_layout;
	@Bind(R.id.lny_network_error_view) protected LinearLayout lny_network_error_layout;
	@Bind(R.id.lny_no_result) protected LinearLayout lny_noresult;
	@Bind(R.id.lny_content) LinearLayout lny_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_favorite_managment);
		tokenKey = Utils.getTokenKey((MyApplication) getApplication());
		// init the progress stuffs.
		ButterKnife.bind(this);
		all_invisible();
		lny_loading_layout.setVisibility(View.VISIBLE);
		initViews();
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		// load favorite data from server
		initTopBar(getString(R.string.myfavorite_title));
		SCREENWIDTH = getScreenWidth (this);
//		Toast.makeText(this, "screenwidth is "+SCREENWIDTH, Toast.LENGTH_SHORT).show();
		loadData();
	}

	public void all_invisible() {
		lny_loading_layout.setVisibility(View.GONE);
		lny_network_error_layout.setVisibility(View.GONE);
		lny_noresult.setVisibility(View.GONE);
		lny_content.setVisibility(View.GONE);
	}

	public void networkExceptionError() {

		all_invisible();
		lny_network_error_layout.setVisibility(View.VISIBLE);
	}


	public void noResultFound() {

		// if the listadapter is = 0... then this... otherwise, the only the bottom progress stuff.
		all_invisible();
		lny_noresult.setVisibility(View.VISIBLE);
	}

	public int getScreenWidth (Context context) {

		DisplayMetrics metrics = new DisplayMetrics();
		((MyFavoriteManagmentActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

	private LoadingDialog dialog;
	private List<FavoriteManagerItem.InnerData> listview_data;


	@OnClick(R.id.wifi_retry)
	public void reloadData () {
		all_invisible();
		lny_loading_layout.setVisibility(View.VISIBLE);
		loadData();
	}


	private void loadData() {

		// show a dialog.
		dialog = new LoadingDialog(MyFavoriteManagmentActivity.this);
		String link = NewLink.GET_FAVORITE_LIST+"&key="+tokenKey +"&page=10000"
				+"&size="+(SCREENWIDTH < 700 ? "60" : "240");
		dialog.show();
		L.d("XXX", link);
		VolleyRequest.GetCookieRequest(MyFavoriteManagmentActivity.this, link, new VolleyRequest.HttpStringRequsetCallBack() {

			@Override
			public void onSuccess(String result) {

				try {
					// if you get here it's because you actually logged in.
					L.d("XXX", result);
					if (result != null && !result.equals("")) {
						FavoriteManagerItem item = (new Gson()).fromJson(result, FavoriteManagerItem.class);
						if (item != null) {
							showTheResult(item);
						} else {
							noResultFound();
						}
						if (dialog != null) {
							dialog.cancel();
							dialog = null;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
//					VolleyRequest.manifestNetworkError (MyFavoriteManagmentActivity.this, "exception");
					// no result if the adapter is null or the count is == 0
					noResultFound();
					if (dialog != null) {
						dialog.cancel();
						dialog = null;
					}
				}
			}

			@Override
			public void onFail(String error) {
//				VolleyRequest.manifestNetworkError (MyFavoriteManagmentActivity.this, error);
				networkExceptionError();
				if (dialog != null) {
					dialog.cancel();
					dialog = null;
				}
			}
		});

	}

	protected void showTheResult(FavoriteManagerItem favoriteManagerItem) {
		L.d("XXX", favoriteManagerItem.toString());
		if (favoriteManagerItem != null) {
			FavoriteManagerItem.InnerData[] data = favoriteManagerItem.data;
			// boucler.
			listview_data = new ArrayList<>();
			ALL_FAVORITE_COUNT = 0;
			for (FavoriteManagerItem.InnerData item : data) {

				// every item is a different item list.
				listview_data.add(item);
				ALL_FAVORITE_COUNT+= item.count;
				append (item.favorites);
			}
		}
		vh.tv_count.setText("( "+ ALL_FAVORITE_COUNT +" )");
		// put all the favorites into a same array.
		// set up an adapter for the listview
		if (listview_data != null && listview_data.size() > 0) {
			lv_favorite_list.setAdapter(new FavoriteControlListAdapter(MyFavoriteManagmentActivity.this, listview_data));
			// put the data inside the view.
			final FavoriteManagerItem.InnerData item =   (new FavoriteManagerItem()).new InnerData();
			//					 item.favorites = (Favorite[]) totalFavorite.toArray();
			item.favorites = (FavoriteManagerItem.InnerData.Favorite[]) totalFavorite.toArray(new FavoriteManagerItem.InnerData.Favorite[totalFavorite.size()]);
			updateContainerFragment (ULTIMA, item);
			lv_favorite_list.setVisibility(View.GONE);
			rel_all_items.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// if the list is expanded ... hide

					// if it is close, then expand it.
					if (lv_favorite_list.getVisibility() == View.GONE) {

						lv_favorite_list.setVisibility(View.VISIBLE);
						// change the indicator src image
						resetTopBarContent (vh, -1);
						vh.iv_icon.setImageResource(R.drawable.icon_down);
						//					updateContainerFragment(99, ALL_DATA)

						// get the previous fragment a set it at hidden.
						previousFragment.setVisible (false);
					}
					else {
						lv_favorite_list.setVisibility(View.GONE);
						updateContainerFragment (ULTIMA, item);
						// change the indicator src image
						vh.iv_icon.setImageResource(R.drawable.icon_nor);
						//							previousFragment.setVisible (true);
					}
					// change the orientation of the icon by the way.
				}
			});
		} else  {
			resetTopBarContent (vh, 0);
		}
		all_invisible();
		lny_content.setVisibility(View.VISIBLE);
	}

	public void initTopBar (String top_title) {
		iv_back = (ImageView) findViewById(R.id.title_back); iv_back.setVisibility(View.VISIBLE);
		iv_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tv_toptitle = (TextView) findViewById(R.id.title_name);
		tv_toptitle.setText(top_title);
	}

	@Override
	protected void onDestroy() {
		// flush the other array
		MaFragmentManagerSpace.getInstance().flush ();
		MyApplication.requestQueue.cancelAll(this);
		super.onDestroy();
	}



	protected void resetTopBarContent(ViewHolder vh2, int c) {


		vh.tv_title.setText(getString(R.string.all_favorite));
		vh.tv_count.setText("( "+ ALL_FAVORITE_COUNT +" )");

		if (c == 0) {
			vh.tv_count.setText("( "+ 0 +" )");
			// show the indicator that there is no favorite.
			vh.tv_count.setVisibility(View.VISIBLE);
		}

	}

	private void initViews() {

		//
		lv_favorite_list = (ListView) findViewById(R.id.lv_favorite_below_item_list);
		frameLayout = (FrameLayout) findViewById(R.id.frame_layout_favorite);
		rel_all_items = (RelativeLayout) findViewById(R.id.rel_all_items);
		iv_indicator = (ImageView) findViewById(R.id.iv_indicator);
		{
			// init the view
			if (vh == null) {
				vh =  new  ViewHolder().makeItUp(rel_all_items);
				vh.iv_icon.setVisibility(View.VISIBLE);
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_my_favorite_managment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	void updateContainerFragment (int position, FavoriteManagerItem.InnerData item) {


		if ((item.categoryName == null || item.categoryName.equals("")) && (item.count == 0) && totalFavorite != null) {
			item.categoryName = getString(R.string.all_favorite);
			item.count = totalFavorite.size();
		}

		// change the name of the upper menu.
		vh.tv_title.setText(item.categoryName);
		vh.tv_count.setText("( "+ item.count +" )");

		if (vh.tv_count.getVisibility() == View.GONE ) {
			vh.tv_count.setVisibility(View.VISIBLE);
		}


		if (item.categoryName == null || item.categoryName.equals("")) {

		}

		// change the fragment inside the stuff.
		if (fragmentManager == null)
			fragmentManager = getSupportFragmentManager();
		// according to the number, get the supposed fragment
		{
			// hide other fragments

			FragmentTransaction transactionManger = fragmentManager.beginTransaction();
			if (previousFragment != null)
				transactionManger.hide(previousFragment);
			// otherwise replace
			if (!MaFragmentManagerSpace.getInstance().hasItem(position)) {
				transactionManger.add(R.id.frame_layout_favorite, MaFragmentManagerSpace.getInstance().getFragment(position, innerDataToGsonString(item)));
				MaFragmentManagerSpace.getInstance().getFragment(position, innerDataToGsonString(item)).setVisible (false);

			} else {
				transactionManger.show(MaFragmentManagerSpace.getInstance().getFragment(position, innerDataToGsonString(item)));
				MaFragmentManagerSpace.getInstance().getFragment(position, innerDataToGsonString(item)).setVisible (true);

			}
			firstTransaction  = false;
			previousFragment = (FavoriteItemListFragment) MaFragmentManagerSpace.getInstance().getFragment(position, innerDataToGsonString(item));
			transactionManger.commit();

		}
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {


		// when the activity sends back the result... if it's conform ~

		// then  udpate the activity ~
		if (resultCode == 2 && data != null) {
			boolean rep_ = data.getBooleanExtra (Constants.START_ACTIVITY_FOR_RESULT_KEY, false);
			if (rep_)  {
				Intent intent = new Intent(this, MyFavoriteManagmentActivity.class);
				startActivity(intent);
				finish ();
			}
		}
		//		CustomToast.mToast(this, "the reqcode resultcode is "+ requestCode + " "+resultCode);
	}

	private String innerDataToGsonString(FavoriteManagerItem.InnerData item) {
		// TODO Auto-generated method stub
		return (new Gson()).toJson(item);
	}

	public static final int ULTIMA = 94;

	List<FavoriteManagerItem.InnerData.Favorite> totalFavorite;

	public class FavoriteControlListAdapter extends BaseAdapter {


		Context mContext;
		List<FavoriteManagerItem.InnerData> data;
		LayoutInflater inf;


		public FavoriteControlListAdapter (Context c, List<FavoriteManagerItem.InnerData> d) {

			mContext = c;
			data = d;
			inf = LayoutInflater.from(c);

		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {


			// inflate the view that makes it up.
			ViewHolder vh;
			if (convertView == null) {
				convertView = inf.inflate(R.layout.myfavorite_top_lv_item, null);
				vh = new ViewHolder();
				vh = vh.makeItUp (convertView);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			final FavoriteManagerItem.InnerData item = (FavoriteManagerItem.InnerData) getItem(position);
			vh.tv_title.setText(item.categoryName);
			vh.tv_count.setText("( "+ item.count +" )");
			convertView.setTag(vh);

			// send back a signal to the activity that changes the topper view

			// and also change the fragment in charge.
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					updateContainerFragment (position, item);
					lv_favorite_list.setVisibility(View.GONE);
				}
			});

			return convertView;
		}
	}
	public class ViewHolder {


		public ViewHolder () {

		}
		public ImageView iv_icon;
		public TextView tv_title, tv_count;

		public ViewHolder makeItUp(View convertView) {
			// TODO Auto-generated method stub
			this.tv_title = (TextView) convertView.findViewById(R.id.category_name);
			this.tv_count = (TextView) convertView.findViewById(R.id.tv_number_item);
			this.iv_icon = (ImageView) convertView.findViewById(R.id.iv_indicator); this.iv_icon.setVisibility(View.GONE);
			return this;
		}
	}
	public void append(FavoriteManagerItem.InnerData.Favorite[] favorites) {

		if (totalFavorite == null || needUpdating) {
			totalFavorite = new ArrayList<>();
			needUpdating = false;
		}
		for (FavoriteManagerItem.InnerData.Favorite fav: favorites) {
			totalFavorite.add(fav);
		}
	}

	private boolean needUpdating = false;

	public void updateData() {

//		CustomToast.mSystemToast(this, "data updating");
		MaFragmentManagerSpace.getInstance().flush ();
		listview_data = null;
		loadData ();
		needUpdating = true;
		// show an upper view and remove all the fragments behind...
		// reinit fragments

	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
}
