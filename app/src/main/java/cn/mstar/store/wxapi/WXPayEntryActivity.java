package cn.mstar.store.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.mstar.store.utils.wechatpay.Constants;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        api.handleIntent(getIntent(), this);
    	
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.pay_result);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {

	}

	@Override
	public void onResp(BaseResp resp) {
		/**resp.errCode==  0 ：表示支付成功
		resp.errCode== -1 ：表示支付失败
		resp.errCode== -2 ：表示取消支付*/

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			String result = null;
			switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				result = "支付成功";
				Toast.makeText(WXPayEntryActivity.this, "支付成功",Toast.LENGTH_SHORT).show();
				break;
			case BaseResp.ErrCode.ERR_COMM:
				result = "支付失败";
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				result = "取消支付";
				break;
			default:
				result = "未知原因支付失败";
				break;
			}
			finish();
	    	Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
		}
		
	}
}