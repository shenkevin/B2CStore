package cn.mstar.store.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.mstar.store.R;
import cn.mstar.store.activity.BaseActivity;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.customviews.LoadingDialog;
import cn.mstar.store.entity.SmsPhoneRegisterData;
import cn.mstar.store.utils.Constants;
import cn.mstar.store.utils.CustomToast;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.NewLink;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;

/*
* 管理用户注册所有操作。
* 步骤：
* 1. 让用户填写关于自己的消息：手机号码， username，password，邮箱，
* 2. 发送获取验证码请求sendAuthCodeReq；将受到短息的时候，同过 SmsBroadcastReceiver监听并发送一个RegisterBackgroundReceiver，
* 		让当前activity进行后序的操作。
* 3. 检查用户信息是否对并把常数发给服务判断是否能登录。
* */


public class RegisterActivity extends BaseActivity {


	// Constants
	private static final int TIME_LEFT = 21001;

	private void initViews() {

		tv_actionbar_middle = (TextView) findViewById(R.id.title_name);
		tv_get_auth_code = (TextView) findViewById(R.id.tv_get_auth_code);
		tv_actionbar_right = (TextView) findViewById(R.id.tv_filter);
		tv_noaccount_register = (TextView) findViewById(R.id.tv_noaccount_register);
		iv_makepasswordvisible = (ImageView) findViewById(R.id.iv_makepasswordvisible);
		ed_auth_number = (EditText) findViewById(R.id.ed_register_auth_number);
		ed_password = (EditText) findViewById(R.id.ed_register_password);
		ed_phonenumber = (EditText) findViewById(R.id.ed_phone_number);
//		ed_username = (EditText) findViewById(R.id.ed_register_username);
		iv_actionbar_left = (ImageView) findViewById(R.id.title_back);
	}

	// Viewz
	@Bind(R.id.title_name) TextView tv_actionbar_middle;
	@Bind(R.id.tv_filter) TextView tv_actionbar_right;
	@Bind(R.id.tv_noaccount_register)  TextView tv_noaccount_register;
	@Bind(R.id.tv_get_auth_code) TextView tv_get_auth_code;
	@Bind(R.id.ed_phone_number) EditText ed_phonenumber;
	@Bind(R.id.ed_register_password) EditText ed_password;
	@Bind(R.id.ed_register_auth_number) EditText ed_auth_number;
//	@Bind(R.id.ed_register_username) EditText ed_username;
	@Bind(R.id.title_back) ImageView iv_actionbar_left;
	@Bind(R.id.iv_makepasswordvisible) ImageView iv_makepasswordvisible;


	// variables.
	RegisterBackgroundReceiver backgroundReceiver;
	ResendTimeDecount decountThread;
	String code = "";
	boolean isDecounting = false;
	LoadingDialog dialog;
	Gson gson;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ButterKnife.bind(this);
		gson = new Gson();
		tv_actionbar_right.setVisibility(View.VISIBLE);
		Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
		Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
		tv_actionbar_right.setText(R.string.login);
		tv_actionbar_middle.setText(R.string.register);

		tv_actionbar_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
				// send the usernmae as parameter
				i.putExtra(Constants.LOGIN_SUCCESS_USERNAME, ed_phonenumber.getText().toString());
				startActivity(i);
				CustomToast.mToast(RegisterActivity.this, "注册成功~ 请登录");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// launch to LOGIN ACTIVITY
				finish();
			}
		});

		tv_noaccount_register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				login();
			}
		});
		tv_get_auth_code.setOnClickListener(new TvAuthOnclickListener());
		iv_makepasswordvisible.setOnTouchListener(new MyTouchListener());
		iv_actionbar_left.setVisibility(View.VISIBLE);
		iv_actionbar_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}


	private void sendAuthCodeReq(final String no) {


		VolleyRequest.GetCookieRequest(RegisterActivity.this, NewLink.REGISTERING_AUTH_CODE + no.trim(), null

					/*
    发信息有没有成功，我们先不判断他，只要收到了信息，用户就可以算是成功注册了。
    没有收到，等60秒钟之后再试试登录。
    				*/
		);

	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_register, menu);
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

	public void login() {

		// start login activity
		Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(i);
		finish();
		CustomToast.mToast(this, "login");
	}

	public void register(View view) {
		CustomToast.mToast(this, "register");
		String username = ed_phonenumber.getText().toString();
		String password = ed_password.getText().toString();
		String phone_number = ed_phonenumber.getText().toString();
		code = ed_auth_number.getText().toString();
		if (username.length() >= 2 && password.length() >= 6 && code.trim().length() == 6) {
			// with the number left... launch the datas.
			launchRegistering (username, password, phone_number);
		}
	}


	class MyTouchListener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {


			switch (event.getAction() & MotionEvent.ACTION_MASK) {

				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:

					//=====Write down your Finger Pressed code here
					CustomToast.mToast(RegisterActivity.this, "Key down");
					ed_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

					ed_password.setSelection(ed_password.length());
					return true;

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
					CustomToast.mToast(RegisterActivity.this, "Key up");
					ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					ed_password.setSelection(ed_password.length());
					//=====Write down you code Finger Released code here

					return true;
			}

			return false;

		}
	}

	private android.os.Handler mHandler = new android.os.Handler () {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
				case TIME_LEFT:
					// get the time left and modify the view
					int  left_time = msg.arg1;
					if (left_time == 60) {
						tv_get_auth_code.setTextColor(getResources().getColor(R.color.hint_foreground_material_light));
						tv_get_auth_code.setBackgroundResource(R.drawable.login_register_button_bg);
						tv_get_auth_code.setText("等" + left_time + "秒再试");
						tv_get_auth_code.setOnClickListener(null);
					} else if (left_time == 0) {
						tv_get_auth_code.setTextColor(getResources().getColor(R.color.little_red));
						tv_get_auth_code.setBackgroundResource(R.drawable.register_get_btn);
						tv_get_auth_code.setText("获取验证码");
						tv_get_auth_code.setOnClickListener(new TvAuthOnclickListener());
						//                        decountThread.stopListening();
					} else {
						// faire le decount.
						if (decountThread.isListening())
							tv_get_auth_code.setText("等"+left_time+"秒再试");
					}
					break;
			}
		}
	};


	private class ResendTimeDecount extends Thread implements Runnable{

		int timeLeft;

		public ResendTimeDecount (int time) {
			timeLeft = time;
			isDecounting = true;
		}

		@Override
		public void run() {
			super.run();
			//
			for (int i = timeLeft; i >= 0 && isDecounting ; i --) {

				try {
					Thread.sleep(1000);

					// after a waiting time change the content of that view.
					Message msg = mHandler.obtainMessage();
					msg.what = TIME_LEFT;
					msg.arg1 = i;
					mHandler.sendMessage(msg);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

		public void stopListening() {

			timeLeft = 0;
			Message msg = mHandler.obtainMessage();
			msg.what = TIME_LEFT;
			msg.arg1 = 0;
			mHandler.sendMessage(msg);
			isDecounting = false;
		}

		public boolean isListening() {
			return isDecounting;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		backgroundReceiver = new RegisterBackgroundReceiver();
		IntentFilter intentFilter2 = new IntentFilter();
		intentFilter2.addAction(RegisterBackgroundReceiver.REQ_SEND_SMS_DONE);
		intentFilter2.addAction(RegisterBackgroundReceiver.REQ_SEND_SMS_DONE1);
		intentFilter2.addAction(RegisterBackgroundReceiver.REQ_REGISTER_CLEAN);
		registerReceiver(backgroundReceiver, intentFilter2);
	}


	public class RegisterBackgroundReceiver extends BroadcastReceiver {

		public static final String REQ_SEND_SMS_DONE = "REQ_SEND_SMS_DONE";
		public static final String REQ_SEND_SMS_DONE1 = "REQ_SEND_SMS_DONE1";

		public static final String SMS_OBJ  = "SMS_OBJ";
		public static final String SMS_CODE = "SMS_CODE";
		public static final String REQ_REGISTER_CLEAN = "REQ_REGISTER_CLEAN";
		public static final String REQ_CLEAN_OBJ = "REQ_CLEAN_OBJ";

		@Override
		public void onReceive(Context context, Intent intent) {

			// look at the data that is sent and make the things
			if (intent.getAction().equals(REQ_SEND_SMS_DONE)) {
				String obj = intent.getStringExtra(SMS_OBJ);
				// put it inside the auth code view, and press the button automatically.
				Gson gson = new Gson();
				SmsPhoneRegisterData data = gson.fromJson(obj, SmsPhoneRegisterData.class);
				if (data.data.msg.toLowerCase().trim().equals("ok")) {
				} else {
				}
			} else if (intent.getAction().equals(REQ_SEND_SMS_DONE1)) {

				// start a dialog from here...
				dialog = new LoadingDialog(RegisterActivity.this, getString(R.string.pull_to_refresh_footer_refreshing_label));
				dialog.show();
				code = intent.getStringExtra(SMS_CODE);
				decountThread.stopListening();
				// put it inside the auth code view, and press the button automatically.
				ed_auth_number.setText(code);
				// if the other datas are also ok, then automatically launch the connexion
				String username = ed_phonenumber.getText().toString();
				String password = ed_password.getText().toString();
				String phone_number = ed_phonenumber.getText().toString();
				if (username.length() >= 6 && !password.trim().equals(username.toString()) && password.length() >= 6 && code.trim().length() == 6) {
					// with the number left... launch the datas.
					launchRegistering (username, password, phone_number);
				}
			}
		}
	}

	/* 进行注册 */
	private void launchRegistering (final String username, final String password, final String phone_number) {


		dialog = new LoadingDialog(RegisterActivity.this, getString(R.string.pull_to_refresh_footer_refreshing_label));
		dialog.show();
//		act=login&op=register&username=shopa&password=123456&phoneNum=13254545454&password_confirm=123456&email=1204588525@qq.com&client=wap
		/*String link = NewLink.REGISTERING_ACT + "&username=" + username +
				"&password=" + password + "&phoneNum=" + phone_number + "&password_confirm=" + password +
				"&code="+code+"&client=android";*/
		String link = NewLink.REGISTERING_ACT + "&username=" + phone_number +
				"&password=" + password +"&password_confirm=" + password +
				"&code="+code+"&client=android";
		L.d("XXX " + link);
		VolleyRequest.GetCookieRequest(RegisterActivity.this, link, new VolleyRequest.HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				// 获取对象
				Gson gson = new Gson();
				L.d("XXX " + result);
				JsonElement elm1 = gson.fromJson(result, JsonElement.class).getAsJsonObject();
				try {
//					L.d("XXX " + result);
					JsonElement elm = gson.fromJson(result, JsonElement.class).getAsJsonObject().get("error");
					int error = elm.getAsInt();
					// 判断有没有tokenkey
					if (error == 0) {
						// 注册成功
						registerSuccess(username, password, null);
					} else {
						// 注册失败
						registerFailure(gson.fromJson(result, JsonElement.class).getAsJsonObject().get("message").getAsString());
					}
				} catch (Exception e) {
					e.printStackTrace();
					// 注册失败
					try {
						registerFailure(elm1.getAsJsonObject().get("message").getAsString());
					} catch (Exception e2) {
						e2.printStackTrace();
						registerFailure("");
					}
				}
			}

			@Override
			public void onFail(String error) {
				// 平常都是网络异常
				networkException();
			}
		});
	}



	private void registerFailure(String error) {
		if (!"".equals(error))
			CustomToast.makeToast(RegisterActivity.this, error, Toast.LENGTH_SHORT);
		else
			CustomToast.makeToast(RegisterActivity.this, getString(R.string.register_error), Toast.LENGTH_SHORT);
		dialogDismiss();
	}

	private void networkException() {
		dialogDismiss();
	}

	private void registerSuccess(String username, String password, String tokenKey) {
		CustomToast.makeToast(RegisterActivity.this, getString(R.string.register_success), Toast.LENGTH_SHORT);
		// 把用户数据保存在本地
//		Utils.LoginSuccess ((MyApplication) getApplication(), username, password, tokenKey, null, 0);
		dialogDismiss();
		// 从本地获取用户消息并把他退出登录
		VolleyRequest.Logout((MyApplication) getApplication(), new VolleyRequest.HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				finish();
			}

			@Override
			public void onFail(String error) {
				finish();
			}
		});
	}


	private void dialogDismiss() {
		if (dialog != null) {
			dialog.dismiss(); dialog.cancel();
			dialog = null;
		}
	}

	private class TvAuthOnclickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// send auth code request.

			// if the phone number is correct...
			// and the password is longer that 6caracts and with numbers and
			// letters merged... then launch the authentification.
			ed_auth_number.setText("");
			String phoneNumber = ed_phonenumber.getText().toString().trim();
			if (phoneNumber == "" || phoneNumber.length() != 11) {

				CustomToast.mToast(RegisterActivity.this, getString(R.string.R_string_number_format_wrong));
			} else {
				decountThread = new ResendTimeDecount(60);
				decountThread.start();
				sendAuthCodeReq(phoneNumber);
			}
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.requestQueue.cancelAll(this);
		unregisterReceiver(backgroundReceiver);
		android.os.Debug.stopMethodTracing();
		super.onDestroy();
	}

}
