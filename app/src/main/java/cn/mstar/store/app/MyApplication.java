package cn.mstar.store.app;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import cn.mstar.store.R;
import cn.mstar.store.db.entityToSave.ProAndSpecialIdz;
import cn.mstar.store.entity.MstarGeoPoint;
import cn.mstar.store.entity.UpgradeItem;
import cn.mstar.store.interfaces.MstarLocationListener;
import cn.mstar.store.utils.L;

import android.app.Activity;
import android.content.Context;


public class MyApplication extends com.activeandroid.app.Application {
	private static MyApplication mInstance = null;
	// 运用list来保存们每一个activity
	private List<Activity> mList = new LinkedList<Activity>();
	// volley请求队列
	public static RequestQueue requestQueue = null;
	public LocationClient mLocationClient;
	public MyBDLocationListener mMyLocationListener;
	private MstarLocationListener mstarLocationListener;

	// 登录数据
	public String loginName, username, password, tokenKey, pic, points;

	// fragment 更行条件
	public boolean isFrg_me_needUpdate = true, frg_isFrg_shoppingcart_needUpdate = true;
	public boolean needUpgrade = false;
	public UpgradeItem upgradeEntity = null;


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ActiveAndroid.initialize(this);
		mInstance = this;
		// 允許調試
		L.isDebug = true;
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
//		SDKInitializer.initialize(this);
//		initBaiduLocClient();
		//初始化 volley请求队列
		requestQueue = Volley.newRequestQueue(this);
		//初始化ImageLoader
		initImageLoader(getApplicationContext());
	}

	public static MyApplication getInstance() {
		return mInstance;
	}

	public void addActivity(Activity activity) {
		mList.add(activity);
	}
	/**
	 * 初始化百度定位sdk
	 */
	private void initBaiduLocClient() {
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyBDLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);

	}
	public void setMstarLocationListener(MstarLocationListener mstarLocationListener){
		this.mstarLocationListener=mstarLocationListener;
	}

	public void cleanLoginInfo() {
		points = pic = tokenKey = password = username = "";
		isFrg_me_needUpdate = frg_isFrg_shoppingcart_needUpdate = true;
	}

	public class MyBDLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			L.e("...........");
			MstarGeoPoint geoPoint=new MstarGeoPoint(location.getLatitude(), location.getLongitude());
			geoPoint.setAddress(getResources().getString(R.string.current_location)+location.getAddrStr());
			if(mstarLocationListener!=null)
				mstarLocationListener.successed(geoPoint);
		}
	}

	// 关闭每一个list内的activity
	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null) {
					L.e("MyApplication", "" + activity);
					activity.finish();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
	//文件缓存地址
	public static String getFileCachePath(Context context){
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"MstarStore/FileCache");
		return cacheDir.getPath();
	}

	/** 初始化ImageLoader */
	public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"MstarStore/Cache");// 获取到缓存的目录地址
		// 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLICATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				// 线程池内加载的数量
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCache(new WeakMemoryCache())
				.denyCacheImageMultipleSizesInMemory()
						//.discCacheFileNameGenerator(new Md5FileNameGenerator())
						// 将保存的时候的URI名称用MD5 加密
						//.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
						// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// 全局初始化此配置
	}
	// 杀进程
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		System.gc();
	}
}
