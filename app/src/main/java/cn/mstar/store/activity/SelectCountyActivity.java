package cn.mstar.store.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.mstar.store.R;
import cn.mstar.store.adapter.AreaAdapter;
import cn.mstar.store.app.Constants2;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.AreaEntity;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;
import cn.mstar.store.utils.VolleyRequest.HttpStringRequsetCallBack;
import cn.mstar.store.customviews.LoadingDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 选择区县
 * @author wenjundu 
 *
 */
public class SelectCountyActivity extends BaseActivity implements OnClickListener{
	private ImageView titleBack;
	private TextView titleName;
	private ListView countyListView;
	private TextView addressTV;
	private AreaAdapter adapter;
	private List<AreaEntity> list;
	private AreaEntity temp;
	private String tempAddress;
	private String provinceId;
	private String cityId;
	private String showCountyUrl;//显示区域url
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_county);
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		MyApplication.getInstance().addActivity(this);
		init();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		list=new ArrayList<AreaEntity>();
		adapter=new AreaAdapter(this, list);
		countyListView.setAdapter(adapter);
		Intent intent=getIntent();
		if(MyAction.selectCityActivityAction.equals(intent.getAction())){
			temp=(AreaEntity) intent.getSerializableExtra("AreaEntity");
			provinceId=intent.getExtras().getString("provinceId");
			cityId=temp.getId();
			tempAddress=intent.getExtras().getString("tempAddress");
			if(tempAddress!=null)
				addressTV.setText(temp.getName());
		}
		if(temp!=null){
			showCountyUrl= Constants2.SELECT_PROVINCE_URL+"&area_id="+provinceId+"&area_id="+cityId;
			Loadcounty(showCountyUrl);
			}
		countyListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 Bundle bundle = new Bundle(); 
			     bundle.putString("address", tempAddress+list.get(position).getName()); 
			     bundle.putString("countyId", list.get(position).getId());
			     bundle.putString("cityId", cityId);
			     bundle.putString("provinceId", provinceId);
				setResult(RESULT_OK ,getIntent().putExtras(bundle));
				finish();
			}
		});
	}
	//加载县区列表
	private void Loadcounty(String url) {
		// TODO Auto-generated method stub
		final LoadingDialog loadingDialog=new LoadingDialog(this, getString(R.string.pull_to_refresh_footer_refreshing_label));
		loadingDialog.show();
		VolleyRequest.GetCookieRequest(this, url, new HttpStringRequsetCallBack() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				if(loadingDialog!=null)
					loadingDialog.dismiss();
				JSONArray jsonArray;
				try {
					jsonArray = (new JSONObject(result)).getJSONArray("data");
					for(int i=0;i<jsonArray.length();i++){
						String id=jsonArray.getJSONObject(i).getString("id");
						String parentId=jsonArray.getJSONObject(i).getString("parentId");
						String name=jsonArray.getJSONObject(i).getString("name");
						AreaEntity areaEntity=new AreaEntity(id, parentId, name);
						list.add(areaEntity);
					}
					adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			@Override
			public void onFail(String error) {
				// TODO Auto-generated method stub
				if(loadingDialog!=null)
					loadingDialog.dismiss();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		countyListView=(ListView) findViewById(R.id.county_list);
		addressTV=(TextView) findViewById(R.id.address_tv);
		titleBack=(ImageView) findViewById(R.id.title_back);
		titleBack.setVisibility(View.VISIBLE);
		titleName=(TextView) findViewById(R.id.title_name);
		titleName.setText(getString(R.string.select_address));
		titleBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.requestQueue.cancelAll(this);
		super.onDestroy();
	}

}
