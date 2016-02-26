package cn.mstar.store.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.activity.MyFavoriteManagmentActivity;
import cn.mstar.store.activity.ProductDetailsActivity;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.FavoriteManagerItem;
import cn.mstar.store.interfaces.OnResultStatusListener;
import cn.mstar.store.utils.CustomToast;
import cn.mstar.store.utils.ImageLoadOptions;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;


public class FavoriteItemListFragment extends Fragment {


	private static final String INNER_DATA = "herewegowiththeshit";


	//	views
	ListView mListView;
	private int SCREEN_WIDTH;
	private FavoriteItemAdapter mAdapter;


	private boolean doNeedUpdating = false;
	private String tokenKey;


	public static FavoriteItemListFragment getInstance (String data) {

		FavoriteItemListFragment fr = new FavoriteItemListFragment();
		Bundle args = new Bundle();
		args.putString(INNER_DATA, data);
		fr.setArguments(args);
		return fr;
	}

	protected int position = -1;


	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_favorite_category, null);

		SCREEN_WIDTH = getScreenWidth(getActivity());

		String jsonCategoryData = getArguments().getString(INNER_DATA);
		// init the views.
		tokenKey = Utils.getTokenKey((MyApplication) getActivity().getApplication());
		FavoriteManagerItem.InnerData fav = (new Gson()).fromJson(jsonCategoryData, FavoriteManagerItem.InnerData.class);

		initViews (view);
		mAdapter = new FavoriteItemAdapter (getActivity(), fav);
		mListView.setAdapter(mAdapter);
		return view;
	}

	public static int getScreenWidth (Context context) {

		DisplayMetrics metrics = new DisplayMetrics();
		((MyFavoriteManagmentActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}



	private void setUpAdapter() {
		// TODO Auto-generated method stub

	}



	private void initViews(View view) {

		// 
		mListView =  (ListView) view.findViewById(R.id.lv_category_innerlist);
	}


	public class FavoriteItemAdapter extends BaseAdapter {

		Context mContext;
		List<FavoriteManagerItem.InnerData.Favorite> data;
		LayoutInflater inf;

		public FavoriteItemAdapter (Context c, FavoriteManagerItem.InnerData d) {

			mContext = c;
			if (data == null)
				data = new ArrayList<>();

			for (FavoriteManagerItem.InnerData.Favorite tmp : d.favorites) {
				data.add(tmp);
			}
			inf = LayoutInflater.from(c);
		}

		private void remove(int position) {
			if (data != null)
				data.remove(position);
			this.notifyDataSetChanged();
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

		private class MaViewHolder {

			public MaViewHolder makeItUp(View convertView) {

				this.tv_title =  (TextView) convertView.findViewById(R.id.tv_item_name);
				this.tv_price = (TextView) convertView.findViewById(R.id.tv_item_price);
				this.iv_icon = (ImageView) convertView.findViewById(R.id.iv_image);
				this.iv_delete = (ImageView) convertView.findViewById(R.id.iv_icon_del);
				return this;
			}
			public TextView tv_title, tv_price;
			public ImageView iv_icon, iv_delete;
		}

		MaViewHolder vh;
		View view = null;
		private int position;

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			view = null;
			if (convertView == null || convertView.getVisibility() == View.GONE) {
				view = inf.inflate(R.layout.favorite_category_item, null);
				vh = new MaViewHolder();
				vh = vh.makeItUp (view);
			} else {
				view = convertView;
				vh = (MaViewHolder) convertView.getTag();
			}
			final FavoriteManagerItem.InnerData.Favorite item =   (FavoriteManagerItem.InnerData.Favorite) getItem(position);
			vh.tv_title.setText(item.title);
			vh.tv_price.setText(String.valueOf(item.price));
			LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) vh.tv_title.getLayoutParams();
			params.width = SCREEN_WIDTH /2;
			vh.tv_title.setLayoutParams(params);
			// upload the image
			L.d("XXX", item.toString());
			ImageLoader.getInstance().displayImage(item.pic, vh.iv_icon, ImageLoadOptions.getOptions());
			view.setTag(vh);
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// start activity with String
					startActivityWithId(item.proId);
				}
			});
			// when we click on the button... it's delete the stuff
			vh.iv_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					deleteFromFavorite(item.proId, position);
				}
			});


			return view;
		}


		Dialog alertDialog = null;


		protected void deleteFromFavorite(final int proId, final int position) {

			if (tokenKey == null)
				tokenKey = Utils.getTokenKey((MyApplication) getActivity().getApplication());
			FavoriteItemListFragment.this.position = position;
			alertDialog = new Dialog(getActivity());
			alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			alertDialog.setContentView(R.layout.confirmation_dialog_box);
			alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			alertDialog.show();
			alertDialog.findViewById(R.id.tv_bt_cancel).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					alertDialog.dismiss();
				}
			});
			alertDialog.findViewById(R.id.tv_bt_confirm).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// launch a thread that delete the stuff...
					VolleyRequest.deleteItemsFromFavorite(getActivity(), String.valueOf(proId), tokenKey, new OnResultStatusListener() {

						@Override
						public void success(String result) {
							if (mAdapter != null && position != -1) {
								mAdapter.remove(position);
							}
							doNeedUpdating = true;
							FavoriteItemListFragment.this.position = -1;
							CustomToast.mSystemToast(mContext,
									mContext.getString(R.string.delete_success));
							updateContent();
						}

						@Override
						public void failure(String error) {
							CustomToast.mSystemToast(mContext,
									mContext.getString(R.string.delete_error));
							FavoriteItemListFragment.this.position = -1;
						}
					});
					alertDialog.dismiss();
				}
			});

			// and send the request to the db
		}

		protected void mToast(String string) {

			CustomToast.mToast(getActivity(), string);
		}

		protected void startActivityWithId(int proId) {

			Intent intent = new Intent (getActivity(), ProductDetailsActivity.class);
			intent.putExtra("proId", proId);
			intent.setAction(MyAction.productListActivityAction);
			startActivityForResult (intent, 2);
		}
	}


	public void setVisible(boolean b) {

		if (mListView != null)
			switch (mListView.getVisibility()) {
				case View.GONE:
					mListView.setVisibility (View.VISIBLE);
					break;
				case View.VISIBLE:
					mListView.setVisibility (View.GONE);
					break;
			}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}


	public void updateContent () {
//		CustomToast.mToast(getActivity(), "YYYYY doneed = "+doNeedUpdating);
		if (doNeedUpdating) {
			// update fragment.
//			CustomToast.mToast(getActivity(), "XXXXX doneed = "+doNeedUpdating);
			((MyFavoriteManagmentActivity) getActivity()).updateData ();
			doNeedUpdating = false;
		}
	}

}
