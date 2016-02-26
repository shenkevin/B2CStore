package cn.mstar.store.utils;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import cn.mstar.store.activity.RegisterActivity;
import cn.mstar.store.entity.RegisterOb;
import cn.mstar.store.entity.RegisterSuccessFailure;


/**
 * Created by Ultima on 7/14/2015.
 */
public class MnetworkUtils {



    // get page as request with get method
    public static String getPageAsString(String home_path) throws Exception{

        String content = "";

        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();

        URL url = new URL(home_path);
        HttpURLConnection httpurlconnection = (HttpURLConnection) url
                .openConnection();
        httpurlconnection.setRequestProperty("Content-type", "text/html");
        httpurlconnection.setRequestProperty("Accept-Charset", "utf-8");
        httpurlconnection.setRequestProperty("contentType", "utf-8");
        // 填入apikey到HTTP header
        httpurlconnection.connect();
        InputStream is = httpurlconnection.getInputStream();
//        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead = null;
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        content = sbf.toString();
        return content;
    }


    public static void getRegisterAuthSmsJson(final Context mContext, final String url) {


// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(mContext);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters
//                        CustomToast.mToast(mContext, url+"\n\n"+response.toString());
                        // send a broadcast with  a string right
                        Intent broad = new Intent(RegisterActivity.RegisterBackgroundReceiver.REQ_SEND_SMS_DONE);
                        broad.putExtra(RegisterActivity.RegisterBackgroundReceiver.SMS_OBJ, response.toString());
                        mContext.sendBroadcast(broad);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CustomToast.mToast(mContext, "发生错误了~ 等一下在试试~~");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public static void getRegisterEntityToServerResult(final Context mContext, final RegisterOb entity) {


        RequestQueue requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTERING_ENTITY_LINK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        L.d("response -> " + response);
                        L.d("--- ooo --- "+(new Gson()).toJson(entity));
                        RegisterSuccessFailure sc = (new Gson()).fromJson(response.toString(), RegisterSuccessFailure.class);
                        L.d("--- o --- " + sc.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                L.d( error.getMessage(), error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> params = new HashMap<String, String>();
                params.put("json", (new Gson()).toJson(entity));
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public static String getRegisterEntityToserverHttpPost(RegisterOb entity, String home_path) {


        // with get it looks simple.
        String content = "";

        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(home_path);
            HttpURLConnection httpurlconnection = (HttpURLConnection) url
                    .openConnection();
            httpurlconnection.setRequestProperty("Content-type", "text/html");
            httpurlconnection.setRequestProperty("Accept-Charset", "utf-8");
            httpurlconnection.setRequestProperty("contentType", "utf-8");
            httpurlconnection.connect();
            InputStream is = httpurlconnection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            content = sbf.toString();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
