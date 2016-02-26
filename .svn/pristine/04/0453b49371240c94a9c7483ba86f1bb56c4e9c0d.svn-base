package cn.mstar.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.adapter.LogisticsAdapter;
import cn.mstar.store.app.Constants2;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.CommonJson;
import cn.mstar.store.entity.ExpressInfo;
import cn.mstar.store.entity.LogisticsEntity;
import cn.mstar.store.entity.ShipInfo;
import cn.mstar.store.interfaces.HttpRequestCallBack;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;

/**
 * 物流详情页
 * @author wenjundu
 *
 */
public class LogisticsDetialsActivity extends BaseActivity {

	private TextView companyTV,waybillTV,orderTimeTV;//公司名称，运单单号,下单时间
	private String token;
	private ListView listView;
	private List<ShipInfo> list;
	private LogisticsAdapter adapter;
	MyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logistics_details);
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		app = (MyApplication) getApplication();
		init();
	}

	private void init() {
		companyTV= (TextView) findViewById(R.id.logistics_company_tv);
		waybillTV= (TextView) findViewById(R.id.waybill_number_tv);
		orderTimeTV= (TextView) findViewById(R.id.Order_time_tv);
		listView= (ListView) findViewById(R.id.logistics_info_list);
		token = app.tokenKey;
		list=new ArrayList<ShipInfo>();
		adapter=new LogisticsAdapter(this,list);
		listView.setAdapter(adapter);
		if(token.equals("")){
			Intent intent=new Intent(this, LoginActivity.class);
			startActivityForResult(intent,1);
		}else{
			getLogisticsInfo();
		}
	}

	//获取物流信息
	private void getLogisticsInfo(){
		String logisticsInfoUrl= Constants2.GET_LOGISTICS_INFO_URL+"&key="+token+"&shippingCode="+"560353808491"+"&eCode="+"tiantian";
		L.e("logisticsInfoUrl:" + logisticsInfoUrl);
		VolleyRequest.GetRequest(this, logisticsInfoUrl, new HttpRequestCallBack() {
			@Override
			public void onSuccess(JSONObject jsonObject) {
				L.e(jsonObject.toString());
				CommonJson<LogisticsEntity> cj = CommonJson.fromJson(jsonObject.toString(), LogisticsEntity.class);
				if (cj.getError().equals("0")) {
					LogisticsEntity logisticsEntity = cj.getData();
					ExpressInfo expressInfo = logisticsEntity.expressInfo;
					ShipInfo[] shipInfos = logisticsEntity.shipInfo;
					companyTV.setText(expressInfo.geteName());
					waybillTV.setText(expressInfo.getShippingCode());
					orderTimeTV.setText(expressInfo.getAddTime());
					list.addAll(Arrays.asList(shipInfos));
					adapter.notifyDataSetChanged();
				}
			}

			@Override
			public void onFailure(String fail) {

			}
		});
	}

	@Override
	protected void onDestroy() {
		MyApplication.requestQueue.cancelAll(this);
		super.onDestroy();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==1 && resultCode==2){
			if(data!=null){
				token=data.getExtras().getString("token");
				getLogisticsInfo();
			}
		}
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.custom_in_anim, R.anim.custom_out_anim);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.custom_in_anim, R.anim.custom_out_anim);
	}


}
