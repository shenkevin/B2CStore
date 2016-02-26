package cn.mstar.store.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.protocol.HTTP;

/**
 * 自定义 Request 往里添加同一cookie
 *
 * @author wenjundu 2015-7-22
 *
 */
public class CookieStringtRequest extends StringRequest {
	private Map<String, String> mHeaders = new HashMap<String, String>();
	public static String cookie = "";// 静态标示 cookie
	//
	public static Boolean isTruncate = true;// 截取第一次获得的cookie

	public CookieStringtRequest(int method, String url, String jsonRequest,
								Listener<String> listener, ErrorListener errorListener) {

		super(method, url, listener, errorListener);
	}

	public void setCookie() {
		L.e("cookie:" + cookie);
		mHeaders.put("accept", "*/*");
		mHeaders.put("connection", "Keep-Alive");
		if (!cookie.equals(""))
			mHeaders.put("Cookie", cookie);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return mHeaders;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		String TYPE_UTF8_CHARSET = "charset=UTF-8";
		//String temp_cookie="";
		try {
			String type = response.headers.get(HTTP.CONTENT_TYPE);
			if (type == null) {
				type = TYPE_UTF8_CHARSET;
				response.headers.put(HTTP.CONTENT_TYPE, type);
			}
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			// mHeader = response.headers.toString();
//			 	L.e("LOG", "get headers in parseNetworkResponse "
//							+ response.headers.toString());
			 //使用正则表达式从reponse的头中提取cookie内容的子串
			Pattern pattern = Pattern.compile("Set-Cookie.*?;");
			Matcher m = pattern.matcher(response.headers.toString());
			if (m.find()) {
				//temp_cookie = m.group();
				// L.e("LOG","cookie from server "+ cookie);
				// 去掉cookie末尾的分号
				if (isTruncate) {
					isTruncate = false;
					L.e("temp_cookie:"+ m.group());
					cookie =  m.group().substring(11,  m.group().length() - 1);
				}
			}

			//L.e("LOG", "cookie substring " + cookie);
			return Response.success(jsonString,
					HttpHeaderParser.parseCacheHeaders(response));

		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

}
