package cn.mstar.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mstar.store.R;
import cn.mstar.store.fragments.ExchangePointsFragment;
import cn.mstar.store.interfaces.ActivityFragment_Relation_Interface;
import cn.mstar.store.utils.Utils;

public class ExchangePointActivity extends FragmentActivity implements ActivityFragment_Relation_Interface {


	@Bind(R.id.lny_loading_layout) protected LinearLayout lny_loading_layout;
	@Bind(R.id.lny_network_error_view) protected LinearLayout lny_network_error_layout;
	@Bind(R.id.lny_no_result) protected LinearLayout lny_noresult;
	@Bind(R.id.framelayout_main) protected FrameLayout framelayout_main;


	// actionbar items
	@Bind(R.id.title_back) ImageView iv_back;
	@Bind(R.id.title_name) TextView tv_title;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mock);
		ButterKnife.bind(this);
		initVarz();
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		// when we get it, it is loading till the moment the inner fragment tells him to stop loading.


		all_invisible ();
		lny_loading_layout.setVisibility(View.VISIBLE);
		// add it inside the frame layout
		Fragment frg =   new ExchangePointsFragment();
		FragmentManager trans = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fragmentTransaction = trans.beginTransaction();
		fragmentTransaction.add(R.id.framelayout_main, frg, "image_fragment");
		fragmentTransaction.show(frg);
		fragmentTransaction.commit();
		// then show the fragment...
		framelayout_main.setVisibility(View.VISIBLE);
	}

	@OnClick (R.id.title_back)
	public void back () {
		finish ();
	}


	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	private void initVarz () {
		iv_back.setVisibility(View.VISIBLE);tv_title.setVisibility(View.VISIBLE); tv_title.setText(getString(R.string.exchangepoints_activity));
	}


	public void all_invisible() {
		lny_loading_layout.setVisibility(View.GONE);
		lny_network_error_layout.setVisibility(View.GONE);
		lny_noresult.setVisibility(View.GONE);
		framelayout_main.setVisibility(View.GONE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exchange_points, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setLoading(boolean state) {
		all_invisible ();
		if (state) {
			lny_loading_layout.setVisibility(View.VISIBLE);
		} else {
			lny_loading_layout.setVisibility(View.GONE);
		}
	}

	@Override
	public void setNetworkError(boolean state) {	all_invisible();
		if (state) {
			lny_network_error_layout.setVisibility(View.VISIBLE);
		} else {
			lny_network_error_layout.setVisibility(View.GONE);
		}
	}


	public static int getScreenHeigth (Context context) {

		DisplayMetrics metrics = new DisplayMetrics();
		((ExchangePointActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}

	public static int getScreenWidth (Context context) {

		DisplayMetrics metrics = new DisplayMetrics();
		((ExchangePointActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}


	@Override
	public void setNoResult(boolean state) {	all_invisible();
		if (state) {
			lny_noresult.setVisibility(View.VISIBLE);
		} else {
			lny_noresult.setVisibility(View.GONE);
		}
	}

	public void setMyTitle(String title) {
		tv_title.setText(title);
	}
}
