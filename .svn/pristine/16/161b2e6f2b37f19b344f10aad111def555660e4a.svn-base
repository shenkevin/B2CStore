package cn.mstar.store.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.mstar.store.R;
import cn.mstar.store.activity.IndentDetailsActivity;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.db.entityToSave.ProAndSpecialIdz;
import cn.mstar.store.entity.RegisterOb;
import cn.mstar.store.entity.ShoppingCartItem;
import cn.mstar.store.interfaces.HttpRequestCallBack;
import cn.mstar.store.interfaces.OnResultStatusListener;

/**
 * volley get post请求
 * @author wenjundu 2015-07-03
 */
public class VolleyRequest {


	public static void cleanCookie () {
		CookieStringtRequest.cookie = "";
		CookieStringtRequest.isTruncate = true;
	}


	//！！！！！！！！get请求为了保证cookie一致  后来不要使用该方法！！！！！！！！！！
	public static void GetRequest(Context context,String url,final HttpRequestCallBack callback){


		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, url, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						if(callback!=null)
							callback.onSuccess(response);
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				if(callback!=null)
					callback.onFailure(error.toString());
			}

		});

		MyApplication.requestQueue.add(jsonObjectRequest);
	}


	//volley get操作 保证cookie一致
	public static void GetCookieRequestPurePage (Context context, String url,final HttpStringRequsetCallBack callBack){

		CookieStringtRequest jsonObjectRequest=new CookieStringtRequest(Method.GET, url, null, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				if(callBack!=null)
					callBack.onSuccess(response);
//				callBack.onSuccess(android.text.Html.fromHtml(response).toString());
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				if(callBack!=null){
					callBack.onFail(error.toString());
				}
			}
		});
		/*jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
		jsonObjectRequest.setCookie();
		MyApplication.requestQueue.add(jsonObjectRequest);
	}

	public static void GetCookieRequest (Context context, String url,final HttpStringRequsetCallBack callBack){

		CookieStringtRequest jsonObjectRequest=new CookieStringtRequest(Method.GET, url, null, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				if(callBack!=null)
					callBack.onSuccess(android.text.Html.fromHtml(response).toString());
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				if(callBack!=null){
					callBack.onFail(error.toString());
				}
			}
		});
		/*jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
		jsonObjectRequest.setCookie();
		MyApplication.requestQueue.add(jsonObjectRequest);
	}

	//volley Post操作 保证cookie一致
	public static void PostCookieRequest(Context context,String url,final String poststring,final HttpStringRequsetCallBack callBack){
		L.e(poststring);



		CookieStringtRequest jsonObjectRequest=new CookieStringtRequest(Method.POST, url, poststring, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				if(callBack!=null)
					callBack.onSuccess(response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				if(callBack!=null)
					callBack.onFail(error.toString());
			}
		}){

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map=new HashMap<String, String>();
				map.put("json", poststring);
				return map;
			}

		};
	/*	jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
		jsonObjectRequest.setCookie();
		MyApplication.requestQueue.add(jsonObjectRequest);
	}

	public static void Logout(final MyApplication application, final HttpStringRequsetCallBack req) {


		// 从服务获取tokenkey&用户名名
		final Context mctx = application.getApplicationContext();
		final SharedPreferences channel = mctx.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
		String username = channel.getString(Constants.SP_USERNAME, "");
		String tokenKey = channel.getString(Constants.SP_TOKENKEY, "");
		cleanCookie();
		Utils.LoginClean(application, true);
		// 退出登录
		VolleyRequest.GetCookieRequest(mctx, NewLink.LOGOUT_ACT + "&username=" + username + "&key=" + tokenKey + "&client=android", new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				// 退出登录成功
				try {
					RegisterOb ob = (new Gson()).fromJson(result, RegisterOb.class);
					if (ob != null && ob.data != null && !"".equals(ob.data.error)) {
						// 退出登录成功
						if (req != null)
							req.onSuccess("");
						Utils.LoginClean(application, true);
					} else {
						if (req != null)
							req.onFail("");
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (req != null)
						req.onFail("");
				}
			}

			@Override
			public void onFail(String error) {
				if (req != null)
					req.onFail(mctx.getString(R.string.network_error));
			}
		});
	}

	//	 判断用户有没有在线
	public static void checkLogStatus(final MyApplication app, final LogonStatusLinstener logonStatusLinstener) {

		/*SharedPreferences channel = mctx.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
		final String tokenKey = channel.getString(Constants.SP_TOKENKEY, "0");

		VolleyRequest.GetCookieRequest(mctx, NewLink.CHECK_LOG_STATUS_ACT+"&key="+tokenKey, new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				L.d("XXX ", result);
				Gson gson = new Gson();
				try {
					JsonObject elm = gson.fromJson(result, JsonElement.class).getAsJsonObject();
					String tmp = elm.get("data").getAsString();
					if ("false".equals(tmp)) {
						if (logonStatusLinstener != null)
							logonStatusLinstener.NO();
					} else {
						if (logonStatusLinstener != null)
							logonStatusLinstener.OK(tokenKey);//已经登录就返回tokenkey
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (logonStatusLinstener != null)
						logonStatusLinstener.NO();
				}
			}

			@Override
			public void onFail(String error) {
				if (logonStatusLinstener != null)
					logonStatusLinstener.OK(mctx.getString(R.string.network_error));
			}
		});*/
		if ("".equals(app.tokenKey)) {
			if (logonStatusLinstener != null)
				logonStatusLinstener.NO();
		} else {
			if (logonStatusLinstener != null)
				logonStatusLinstener.OK(app.tokenKey);//已经登录就返回tokenkey
		}
	}

	public static void addInShoppingCart(final MyApplication app, ProAndSpecialIdz[] item, final String tokenKey, final OnResultStatusListener listener) {

//		http://www.fanerjewelry.com/mobile/index.php?act=member_cart&op=cart_add&key=6ef5a32527f2b6203ccf4ae14d09ecef &proId=519&number=1
		String proId = "", numberz = "";
		for (ProAndSpecialIdz iz: item) {
			// proId
			if (!"".equals(proId))
				proId+="|";
			if (iz.specialId != 0)
				proId+=iz.specialId;
			else
				proId+=iz.proId;
			// number
			if (!"".equals(numberz))
				numberz+="|";
			numberz+=iz.number;
		}

		final String link = NewLink.ADD_TO_SHOPPING_CART + "&key=" + tokenKey + "&proId=" + proId + "&number=" + numberz;
		VolleyRequest.GetCookieRequest(app.getApplicationContext(), link, new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				Gson gson = new Gson();
				L.d("XXX", result);
				JsonObject elm = gson.fromJson(result, JsonElement.class).getAsJsonObject();
				try {
					JsonObject elm1 = elm.get("data").getAsJsonObject();
					String tK =  elm1.get("tokenKey").getAsString();
					L.d("XXX", tK+"  ***  "+tokenKey);
					if (tokenKey.equals(tK)) {
						// 添加成功
						if (listener != null)
							listener.success(tokenKey);
						app.frg_isFrg_shoppingcart_needUpdate = true;
					} else {
						// 添加失败
						if (listener != null)
							listener.failure("0");
					}
				} catch (Exception e) {
					e.printStackTrace();
					// 添加失败
					try {
						if (listener != null)
							listener.failure(elm.get("message").getAsString());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void onFail(String error) {
				// 网络请求问题
				if (listener != null)
					listener.failure("-1");
			}
		});

	}

	public static void deleteShoppingCartItem(final MyApplication application, ShoppingCartItem item, final OnResultStatusListener listener) {


//		http://www.fanerjewelry.com/mobile/index.php?act=member_cart&op=cart_del&key=6ef5a32527f2b6203ccf4ae14d09ecef&cartId=3
		Context mctx = application.getApplicationContext();
		final String tokenKey = Utils.getTokenKey(application);
		final String link = NewLink.DEL_FROM_SHOPPING_CART + "&key=" + tokenKey + "&cartId=" + item.cartId;
		VolleyRequest.GetCookieRequest(mctx, link, new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				Gson gson = new Gson();
				L.d("XXX", result);
				try {
					JsonObject elm = gson.fromJson(result, JsonElement.class).getAsJsonObject().get("data").getAsJsonObject();
					String tK =  elm.get("tokenKey").getAsString();
//					L.d("XXX", tK+"  ***  "+tokenKey);
					if (tokenKey.equals(tK)) {
						// 添加成功
						if (listener != null)
							listener.success(tokenKey);
						application.frg_isFrg_shoppingcart_needUpdate = true;
					} else {
						// 添加失败
						if (listener != null)
							listener.failure("0");
					}
				} catch (Exception e) {
					e.printStackTrace();
					// 添加失败
					if (listener != null)
						listener.failure("-1");
				}
			}

			@Override
			public void onFail(String error) {
				// 网络请求问题
			}
		});

	}

	public static void requestOrderData(IndentDetailsActivity indentDetailsActivity, OnResultStatusListener listener) {

	}


	//新加接口 ，保证cookie一致的volley网络请求回调这个接口
	public  interface HttpStringRequsetCallBack{
		void onSuccess(String result);
		void onFail(String error);
	}

	public static void manifestNetworkError(Context mContext, @Nullable String error) {
		CustomToast.mSystemToast(mContext, /*mContext.getString(R.string.network_error)*/error == null ? mContext.getString(R.string.network_error) : error);
	}

	public interface LogonStatusLinstener{
		void OK(String token);//已经登录返回token
		void NO();
	}


	public static void deleteItemsFromFavorite(final Context mContext, final String proId,  final String tokenKey,
											   final OnResultStatusListener onResultStatusListener) {

		VolleyRequest.GetCookieRequest(mContext, NewLink.DEL_FAVORITE_ACT + "&key=" + tokenKey + "&goods_id=" + proId, new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				onResultStatusListener.success(result);
			}

			@Override
			public void onFail(String error) {
				onResultStatusListener.failure(error);
			}
		});
	}




	public static void addItemsFromFavorite(final Context mContext, final String proId, final String tokenKey,
											final OnResultStatusListener onResultStatusListener) {

		String link = NewLink.ADD_FAVORITE_ACT + "&key=" + tokenKey + "&goods_id=" + proId;
		L.d("fav::", link);
		VolleyRequest.GetCookieRequest(mContext, link, new HttpStringRequsetCallBack() {
			@Override
			public void onSuccess(String result) {
				onResultStatusListener.success(result);
			}

			@Override
			public void onFail(String error) {
				onResultStatusListener.failure(error);
			}
		});
	}




}
