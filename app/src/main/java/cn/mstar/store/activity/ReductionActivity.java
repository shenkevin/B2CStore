package cn.mstar.store.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.mstar.store.R;
import cn.mstar.store.adapter.ReductionTicketsManagementViewPagerAdapter;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.customviews.SlidingTabLayout;
import cn.mstar.store.utils.Utils;

public class ReductionActivity extends FragmentActivity {


	// viewz
	@Bind(R.id.viewpager_reduction) ViewPager mViewpager;
	@Bind(R.id.reduction_tabs)
	SlidingTabLayout tabs;
	@Bind(R.id.title_back) ImageView iv_back;
	@Bind(R.id.title_name) TextView tv_title;


	// varz
	private int launch_position = 0;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reduction);
		ButterKnife.bind(this);
		initViewz();
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		initAdapter ();
	}
	

	 private void initAdapter() {


	        // init 那个viewpager的adapter
	        mViewpager.setAdapter(new ReductionTicketsManagementViewPagerAdapter(getSupportFragmentManager(), ReductionActivity.this));
	        // 反正里面只要放那些fragment就可以了~
	        // Specify that tabs should be displayed in the action bar.
	        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true,
	        // This makes the tabs Space Evenly in Available width
	        // Setting Custom Color for the Scroll bar indicator of the Tab View
	        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
	            @Override
	            public int getIndicatorColor(int position) {
	                return getResources().getColor(R.color.little_red);
	            }
	        });
	        // Setting the ViewPager For the SlidingTabsLayout
	        tabs.setViewPager(mViewpager);
	        tabs.setAnimationCacheEnabled(false);
	        mViewpager.setCurrentItem(launch_position);
	        mViewpager.setOffscreenPageLimit(2);
	    }

	private void initViewz() {

		tv_title.setText(getString(R.string.reduction_ticket_activity_name)); tv_title.setVisibility(View.VISIBLE);
		iv_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ReductionActivity.this.finish ();
			}
		});
		iv_back.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.requestQueue.cancelAll(this);
		super.onDestroy();
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
}
