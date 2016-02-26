package cn.mstar.store.activity;


import cn.mstar.store.R;
import cn.mstar.store.app.Constants2;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.AddUserAddressEntity;
import cn.mstar.store.entity.EditUserAddressEntity;
import cn.mstar.store.entity.ReceiverAddress;
import cn.mstar.store.utils.NetWorkUtil;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.CustomToast;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.VolleyRequest;
import cn.mstar.store.utils.VolleyRequest.HttpStringRequsetCallBack;
import cn.mstar.store.customviews.LoadingDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CreateReceiverAddressActivity extends BaseActivity implements
		OnClickListener {

	private LinearLayout selectLayout;// 选择地址layout
	private TextView addressTV;// 所在地区
	private ImageView titleBack;
	private TextView titleName;
	private EditText nameET, phoneNumET, detailAddressET, zipCodeET;
	private CheckBox setDefaultAddressCB;// 设置默认地址CB
	private Button saveBtn;// 保存设置
	private String provinceId, cityId, countyId;// 省份 城市 区县 id
	private ReceiverAddress receiverAddress;
	private String token;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_receiver_address);
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		MyApplication.getInstance().addActivity(this);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		token=MyApplication.getInstance().tokenKey;
		selectLayout = (LinearLayout) findViewById(R.id.address_layout);
		addressTV = (TextView) findViewById(R.id.address_tv);
		titleBack = (ImageView) findViewById(R.id.title_back);
		titleBack.setVisibility(View.VISIBLE);
		titleBack.setOnClickListener(this);
		titleName = (TextView) findViewById(R.id.title_name);
		titleName.setText(getString(R.string.receiver_address));
		selectLayout.setOnClickListener(this);

		nameET = (EditText) findViewById(R.id.name_et);// 姓名
		phoneNumET = (EditText) findViewById(R.id.number_et);// 电话号码
		detailAddressET = (EditText) findViewById(R.id.detail_address_et);// 详细地址
		zipCodeET = (EditText) findViewById(R.id.zip_code_et);// 邮政编码
		setDefaultAddressCB = (CheckBox) findViewById(R.id.set_default_cb);// 设置默认地址
		Intent intent=getIntent();
		if(MyAction.manageReceiverAddressActivityItemAction.equals(intent.getAction())){//管理收货地址的 list  item点击进来的
			receiverAddress=(ReceiverAddress) intent.getSerializableExtra("ReceiverAddress");
			if(receiverAddress!=null){
				nameET.setText(receiverAddress.getName());
				phoneNumET.setText(receiverAddress.getPhone());
				zipCodeET.setText(receiverAddress.getZipCode());
				detailAddressET.setText(receiverAddress.getAddress());
				setDefaultAddressCB.setChecked(receiverAddress.getIsDefalutAddress());
				int i=receiverAddress.getFullPostAddress().lastIndexOf(" ");

				if(i>0)
					addressTV.setText(receiverAddress.getFullPostAddress().substring(0, i));
			}
		}
		saveBtn = (Button) findViewById(R.id.save_btn);
		saveBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.address_layout:// 所在地区
			intent = new Intent(this, SelectProvinceActivity.class);
			startActivityForResult(intent, 0);
			break;

		case R.id.title_back:// 返回
			finish();
			break;
		case R.id.save_btn:// 保存设置
			String name = nameET.getText().toString().trim();
			String detailAddress = detailAddressET.getText().toString().trim();
			String number = phoneNumET.getText().toString().trim();
			String zipCode = zipCodeET.getText().toString().trim();
			Boolean isDefault = setDefaultAddressCB.isChecked();
			if ("".equals(name) || "".equals(detailAddress)
					|| "".equals(number) || "".equals(zipCode)) {
				CustomToast.makeToast(this, "请填写参数", Toast.LENGTH_SHORT);
				break;
			}
			if("".equals(token)){
				intent=new Intent(this,LoginActivity.class);
				startActivityForResult(intent,1);
				break;
			}
			//如果是点击 list item进来的就修改地址
			if(MyAction.manageReceiverAddressActivityItemAction.equals(getIntent().getAction())){
				EditUserAddressEntity editUserAddressEntity=new EditUserAddressEntity();
				editUserAddressEntity.setAddressId(receiverAddress.getAddressId());
				editUserAddressEntity.setPostName(name);
				editUserAddressEntity.setMobile(number);
				if(provinceId==null ||cityId==null || countyId==null){//没有进入所在地区更改地址，就用item传递过来的id
					editUserAddressEntity.setProvinceId(receiverAddress.getProvinceId());
					editUserAddressEntity.setCityId(receiverAddress.getCityId());
					editUserAddressEntity.setCountyId(receiverAddress.getCountyId());
				}else{
					editUserAddressEntity.setProvinceId(Integer.parseInt(provinceId));
					editUserAddressEntity.setCityId(Integer.parseInt(cityId));
					editUserAddressEntity.setCountyId(Integer.parseInt(countyId));
				}
				editUserAddressEntity.setPostAddress(detailAddress);
				editUserAddressEntity.setZipCode(zipCode);
				if (isDefault) {
					editUserAddressEntity.setIsDefault(1);
				} else {
					editUserAddressEntity.setIsDefault(0);
				}
				editReceiverAddress(editUserAddressEntity);
				
			}else{//创建地址

				if(provinceId==null ||cityId==null || countyId==null){
					CustomToast.makeToast(this, "请选择地址", Toast.LENGTH_SHORT);
					break;
				}
				AddUserAddressEntity addressEntity = new AddUserAddressEntity();
				addressEntity.setPostName(name);
				addressEntity.setMobile(number);
				addressEntity.setProvinceId(Integer.parseInt(provinceId));
				addressEntity.setCityId(Integer.parseInt(cityId));
				addressEntity.setCountyId(Integer.parseInt(countyId));
				addressEntity.setPostAddress(detailAddress);
				addressEntity.setZipCode(zipCode);
				if (isDefault) {
					addressEntity.setDefault(1);
				} else {
					addressEntity.setDefault(0);
				}
				addReceiverAddress(addressEntity);
				}
			break;
		}

	}
	//修改收货地址
	private void editReceiverAddress(EditUserAddressEntity editUserAddressEntity){
		String name="",address="";
		try {
			name= URLEncoder.encode(editUserAddressEntity.getPostName(),"utf-8");
			address=URLEncoder.encode(editUserAddressEntity.getPostAddress(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String editUrl=Constants2.EDIT_RECEIVER_ADDRESS+"&key="+token+"&address_id="+editUserAddressEntity.getAddressId()+
				"&true_name="+name+"&province_id="+editUserAddressEntity.getProvinceId()+"&city_id="+
				editUserAddressEntity.getCityId()+"&area_id="+editUserAddressEntity.getCountyId()+"&address="+address+
				"&tel_phone="+editUserAddressEntity.getTelephone()+"&mob_phone="+editUserAddressEntity.getMobile()+"&is_default="+
				editUserAddressEntity.getIsDefault();
		L.e("editUrl:"+editUrl);
		VolleyRequest.GetCookieRequest(this, editUrl, new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				try {
					if(new JSONObject(result).optString("error").equals("0")){
                        setResult(RESULT_OK);
                        CustomToast.makeToast(CreateReceiverAddressActivity.this, getString(R.string.set_success), Toast.LENGTH_SHORT);
                        finish();
                    }
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String error) {

			}
		});

	}
	//添加收货地址
	private void addReceiverAddress(AddUserAddressEntity addressEntity) {
		String name="",address="";
		try {
			name= URLEncoder.encode(addressEntity.getPostName(),"utf-8");
			address=URLEncoder.encode(addressEntity.getPostAddress(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String addUrl=Constants2.ADD_RECEIVER_ADDRESS+"&key="+token+"&true_name="+name+"&province_id="+addressEntity.getProvinceId()+
				"&city_id="+addressEntity.getCityId()+"&area_id="+addressEntity.getCountyId()+"&address="+address+
				"&tel_phone="+addressEntity.getTelephone()+"&mob_phone="+addressEntity.getMobile()+"&is_default="+addressEntity.isDefault();
		L.e("addurl:"+addUrl);
		VolleyRequest.GetCookieRequest(this, addUrl, new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				try {
					if(new JSONObject(result).optString("error").equals("0")){
						JSONObject datajson=new JSONObject(result).getJSONObject("data");

						ReceiverAddress receiverAddress=new ReceiverAddress();
						receiverAddress.setName(datajson.optString("postName"));
						receiverAddress.setFullPostAddress(datajson.optString("postAddress"));
						receiverAddress.setZipCode(datajson.optString("zipCode"));
						receiverAddress.setPhone(datajson.optString("mobile"));
						receiverAddress.setProvinceId(datajson.optInt("provinceId"));
						receiverAddress.setCityId(datajson.optInt("cityId"));
						receiverAddress.setCountyId(datajson.optInt("countyId"));
						if(datajson.optInt("isDefault")==1){
							receiverAddress.setIsDefalutAddress(true);
						}else{
							receiverAddress.setIsDefalutAddress(false);
						}
						Intent intent=getIntent();
						intent.putExtra("ReceiverAddress",receiverAddress);
						setResult(RESULT_OK,intent);
						CustomToast.makeToast(CreateReceiverAddressActivity.this, getString(R.string.set_success), Toast.LENGTH_SHORT);
						finish();
					} else {
						CustomToast.makeToast(CreateReceiverAddressActivity.this, getString(R.string.set_fail), Toast.LENGTH_SHORT);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String error) {

			}
		});
	}

	// 获取用户管理模板
//	private void getUserManageMoudle(final AddUserAddressEntity addressEntity,final EditUserAddressEntity editUserAddressEntity) {
//		// TODO Auto-generated method stub
//		if(NetWorkutils.isNetworkConnected(this)){
//		VolleyRequest.GetCookieRequest(this, Constants.GET_USER_MANAGE_MOUDLE,
//				new HttpStringRequsetCallBack() {
//
//					@Override
//					public void onSuccess(String result) {
//						// TODO Auto-generated method stub
//						if (result != null) {
//							if(MyAction.manageReceiverAddressActivityItemAction.equals(getIntent().getAction())){
//								//编辑收货地址
//								if(editUserAddressEntity!=null){
//									UserAddressManageJsonBean jsonBean = (new Gson())
//											.fromJson(result,
//												UserAddressManageJsonBean.class);
//										jsonBean.data.editUserAddress.add(editUserAddressEntity);
//										String editAddressStr = (new Gson())
//												.toJson(jsonBean);
//										L.e("editAddressStr", editAddressStr);
//
//										postData(editAddressStr);
//								}
//							}else{
//								//创建收货地址
//								if(addressEntity!=null){
//									UserAddressManageJsonBean jsonBean = (new Gson())
//										.fromJson(result,
//											UserAddressManageJsonBean.class);
//									jsonBean.data.addUserAddress.add(addressEntity);
//									String cteateAddressStr = (new Gson())
//											.toJson(jsonBean);
//									L.e("cteateAddressStr", cteateAddressStr);
//
//									postData(cteateAddressStr);
//								}
//							}
//						}
//
//					}
//
//					@Override
//					public void onFail(String error) {
//						// TODO Auto-generated method stub
//
//					}
//				});
//		}else{
//			CustomToast.makeToast(this, getString(R.string.network_error), Toast.LENGTH_SHORT);
//		}
//	}
	private LoadingDialog loadingDialog;
	// 提交新建地址数据
	private void postData(final String cteateAddressStr) {
		// TODO Auto-generated method stub
		if (NetWorkUtil.isNetworkConnected(this)) {


//			isLogin(new IsLoginListener() {
//
//						@Override
//						public void OK(String result) {
//							// TODO Auto-generated method stub
//							if (loadingDialog == null)
//								loadingDialog = new LoadingDialog(CreateReceiverAddressActivity.this, getString(R.string.seting));
//							loadingDialog.show();
//							VolleyRequest.PostCookieRequest(CreateReceiverAddressActivity.this,
//									Constants.POST_USER_MANAGE_ADDRESS, cteateAddressStr,
//									new HttpStringRequsetCallBack() {
//										@Override
//										public void onSuccess(String result) {
//											// TODO Auto-generated method stub
//											if (loadingDialog != null)
//												loadingDialog.dismiss();
//											setResult(RESULT_OK);
//											CustomToast.makeToast(CreateReceiverAddressActivity.this, getString(R.string.set_success), Toast.LENGTH_SHORT);
//											finish();
//										}
//
//										@Override
//										public void onFail(String error) {
//											// TODO Auto-generated method stub
//											if (loadingDialog != null)
//												loadingDialog.dismiss();
//										}
//									});
//						}
//
//						@Override
//						public void No(String result) {
//							// TODO Auto-generated method stub
//							Intent intent = new Intent(CreateReceiverAddressActivity.this, LoginActivity.class);
//							startActivity(intent);
//						}
//					});
			
		}else{
			CustomToast.makeToast(this, getString(R.string.no_network), Toast.LENGTH_SHORT);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 0 && resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String address = bundle.getString("address");
			provinceId = bundle.getString("provinceId");
			cityId = bundle.getString("cityId");
			countyId = bundle.getString("countyId");
			if (address != null)
				addressTV.setText(address);
		}
		if(requestCode==1 && resultCode==2){
			token=data.getExtras().getString("token");
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(loadingDialog!=null)
			loadingDialog.dismiss();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.requestQueue.cancelAll(this);
		super.onDestroy();
	}

}
