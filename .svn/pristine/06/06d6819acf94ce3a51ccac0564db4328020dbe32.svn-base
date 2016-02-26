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
import cn.mstar.store.utils.L;
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

/**选择城市
 * @author duwenjun
 *
 */
public class SelectCityActivity extends BaseActivity implements OnClickListener{

	private ImageView titleBack;
	private TextView titleName;
	private ListView cityList;
	private AreaAdapter adapter;
	private List<AreaEntity> list;
	private TextView addressTV;
	private AreaEntity temp;
	private String tempAddress;
	private String provinceId;//省会id
	private String showCityUrl;//显示城市列表的URL
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_city);
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
		cityList.setAdapter(adapter);
		Intent intent=getIntent();
		if(MyAction.selectProvinceActivityAction.equals(intent.getAction())){
			temp=(AreaEntity) intent.getSerializableExtra("AreaEntity");
			provinceId=temp.getId();
			tempAddress=intent.getExtras().getString("tempaddress");
			if(tempAddress!=null)
				addressTV.setText(temp.getName());
		}
		if(temp!=null){
			showCityUrl=Constants2.SELECT_PROVINCE_URL+"&area_id="+temp.getId();
			LoadCity(showCityUrl);
			
		}
		cityList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			
				// TODO Auto-generated method stub
				Intent intent=new Intent(SelectCityActivity.this,SelectCountyActivity.class);
				
				intent.putExtra("tempAddress", tempAddress+list.get(position).getName());
				intent.putExtra("AreaEntity", list.get(position));
				intent.putExtra("provinceId", provinceId);
				intent.setAction(MyAction.selectCityActivityAction);
				startActivityForResult(intent, 0);
			}
		});
	}
	//加载城市列表
	private void LoadCity(String url) {
		// TODO Auto-generated method stub
		final LoadingDialog loadingDialog=new LoadingDialog(this, getString(R.string.pull_to_refresh_footer_refreshing_label));
		loadingDialog.show();
		L.e("城市列表url:", url);
		VolleyRequest.GetCookieRequest(this, url, new HttpStringRequsetCallBack() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				if(loadingDialog!=null)
					loadingDialog.dismiss();
				try {
					JSONArray jsonArray=((new JSONObject(result)).getJSONArray("data"));
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
		cityList=(ListView) findViewById(R.id.city_list);
		addressTV=(TextView) findViewById(R.id.address_tv);
		titleBack=(ImageView) findViewById(R.id.title_back);
		titleBack.setVisibility(View.VISIBLE);
		titleName=(TextView) findViewById(R.id.title_name);
		titleName.setText(getString(R.string.select_address));
		titleBack.setOnClickListener(this);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==0 && resultCode==RESULT_OK){

			setResult(RESULT_OK,data);
			finish();
		}
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
