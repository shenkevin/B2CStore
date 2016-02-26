package cn.mstar.store.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.mstar.store.R;
import cn.mstar.store.adapter.CatalogueAdapter;
import cn.mstar.store.adapter.ClassifySubAdapter;
import cn.mstar.store.app.Constants2;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.MoreClassifyData;
import cn.mstar.store.entity.MoreClassifySubData;
import cn.mstar.store.interfaces.HttpRequestCallBack;
import cn.mstar.store.parse.ClassifyJsonParse;
import cn.mstar.store.parse.ClassifyJsonParse.ParseCallBack;
import cn.mstar.store.utils.ImageLoadOptions;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;

import com.nostra13.universalimageloader.core.ImageLoader;

/**产品分类
 * @author wenjundu 2015-7-9
 *
 */
public class ClassifyActivity extends BaseActivity implements OnClickListener{

	//返回按钮
	private ImageView titleBack;
	//标题
	private TextView titleName;
	//左侧产品目录
	private ListView menuListView;
	//右侧产品
	private GridView productGridView;
	//展示图
	private ImageView showIV;
	//左侧listview适配器
	private CatalogueAdapter catalogueAdapter;
	//右侧GridView适配器
	private ClassifySubAdapter subAdapter;
	//左侧大类List
	private  List<MoreClassifyData> moreClassifyList;
	//右侧小类List
	private List<MoreClassifySubData> moreClassifysubList;
	//从首页传递过来的categoryId
	private String categoryId="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classify);
		initView();
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		initData();
	}
	//获取产品信息
	private void initData() {
		// TODO Auto-generated method stub
		VolleyRequest.GetRequest(this, Constants2.MORECLASSIFY_URL, new HttpRequestCallBack() {

			@Override
			public void onSuccess(JSONObject jsonObj) {
				// TODO Auto-generated method stub
				if (jsonObj != null) {
					setValueToListView(jsonObj);
				}
			}

			@Override
			public void onFailure(String failresult) {
				// TODO Auto-generated method stub

			}
		});
	}
	//解析json
	private void setValueToListView(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		ClassifyJsonParse.parseJson(jsonObj, new ParseCallBack() {

			@Override
			public void onSuccess(ArrayList<MoreClassifyData> list) {
				// TODO Auto-generated method stub
				setMoreClassifyAdapter(list);
			}


			@Override
			public void onFailure(String failresult) {
				// TODO Auto-generated method stub
				L.e(failresult);
			}
		});
	}
	//填充大类adapter
	private void setMoreClassifyAdapter(ArrayList<MoreClassifyData> list) {
		// TODO Auto-generated method stub
		moreClassifyList.addAll(list);
		catalogueAdapter.notifyDataSetChanged();

		if(moreClassifyList.size()>0){

			catalogueAdapter.setSelectedPosition(0);
			//设置横幅图片
			ImageLoader.getInstance().displayImage(moreClassifyList.get(0).getPic(), showIV, ImageLoadOptions.getOptions());
			setMoreClassifySubAdapter(moreClassifyList.get(0).getMoreclassifySubArr());
		}
		for(int i=0;i<moreClassifyList.size();i++){
			//选中从homefragment传递过来的categoryId类
			if(categoryId.equals(moreClassifyList.get(i).getCategoryId())){
				catalogueAdapter.setSelectedPosition(i);
				//设置横幅图片
				ImageLoader.getInstance().displayImage(moreClassifyList.get(i).getPic(), showIV,ImageLoadOptions.getOptions());
				setMoreClassifySubAdapter(moreClassifyList.get(i).getMoreclassifySubArr());
				break;
			}
		}
		
//		//设置横幅图片
//		ImageLoader.getInstance().displayImage(moreClassifyList.get(0).getPic(), showIV,ImageLoadOptions.getOptions());
//		setMoreClassifySubAdapter(moreClassifyList.get(0).getMoreclassifySubArr());
		
	}
	//填充小类adapter
	private void setMoreClassifySubAdapter(
			ArrayList<MoreClassifySubData> moreclassifySubArr) {
		// TODO Auto-generated method stub
		moreClassifysubList.clear();
		moreClassifysubList.addAll(moreclassifySubArr);
		subAdapter.notifyDataSetChanged();
	}

	private void initView() {
		Intent intent=getIntent();
		
		//获取HomeFragment传递过来的数据
		if(MyAction.mainActivityAction.equals(intent.getAction()))
			categoryId= intent.getExtras().getString("categoryId");
		menuListView=(ListView) findViewById(R.id.classify_list);
		productGridView=(GridView) findViewById(R.id.gv_product);
		showIV=(ImageView) findViewById(R.id.iv_product);
		titleBack=(ImageView) findViewById(R.id.title_back);
		titleBack.setVisibility(View.VISIBLE);
		titleBack.setOnClickListener(this);
		titleName=(TextView) findViewById(R.id.title_name);
		titleName.setText(getResources().getString(R.string.more_classify));
		//设置标签 
		menuListView.setTag(1);
		productGridView.setTag(2);
		
		menuListView.setOnItemClickListener(itemOnClick);
		productGridView.setOnItemClickListener(itemOnClick);
		moreClassifyList=new ArrayList<MoreClassifyData>();
		moreClassifysubList=new ArrayList<MoreClassifySubData>();
		catalogueAdapter=new CatalogueAdapter(this, moreClassifyList);
		subAdapter=new ClassifySubAdapter(this, moreClassifysubList);
		menuListView.setAdapter(catalogueAdapter);
		productGridView.setAdapter(subAdapter);
	}

	private OnItemClickListener  itemOnClick=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch ((Integer)parent.getTag()) {
			case 1: //点击左侧目录的时候
				//设置横幅图片
				ImageLoader.getInstance().displayImage(moreClassifyList.get(position).getPic(), showIV,ImageLoadOptions.getOptions());
				catalogueAdapter.setSelectedPosition(position);
				catalogueAdapter.notifyDataSetChanged();
				setMoreClassifySubAdapter(moreClassifyList.get(position).getMoreclassifySubArr());
				break;

			case 2://点击右侧gridView item跳转到产品列表页
				Intent intent =new Intent(ClassifyActivity.this,ProductListActivity.class);
				intent.putExtra("categoryId", moreClassifysubList.get(position).getCategoryId());
				intent.setAction(MyAction.classifyActivityAction);
				startActivity(intent);
				break;
			}
		}
	};
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.requestQueue.cancelAll(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_back:
			finish();
			break;

		}
	}

}
