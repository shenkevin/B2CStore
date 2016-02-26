package cn.mstar.store.parse;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.mstar.store.entity.BannerItemData;

/**
 * 首页请求的 json数据解析
 * @author duwenjun 2015-7-8
 *
 */
public class HomeJsonParse {
	
	public static void parseJson(JSONObject jsonObj,ParseCallBack callBack){
		if(jsonObj!=null){
			try {
				JSONObject homeDataObject = jsonObj.getJSONObject("data");
				JSONArray homebannerArray = homeDataObject.getJSONArray("rotatePic");			
				ArrayList<BannerItemData> bannerList=new ArrayList<BannerItemData>();	
				for(int i =0;i<homebannerArray.length();i++){
            		BannerItemData bannerItemData=new BannerItemData();
            		bannerItemData.setIndex(homebannerArray.getJSONObject(i).getInt("index"));
            		bannerItemData.setTitle(homebannerArray.getJSONObject(i).getString("title"));
            		bannerItemData.setImageUrl(homebannerArray.getJSONObject(i).getString("pic"));
            		bannerItemData.setUrl(homebannerArray.getJSONObject(i).getString("url"));
            		bannerItemData.setCategoryId(homebannerArray.getJSONObject(i).getInt("categoryId"));
            		bannerItemData.setProId(homebannerArray.getJSONObject(i).getInt("proId"));
            		bannerItemData.setKeyword(homebannerArray.getJSONObject(i).getString("keyword"));
            		bannerItemData.setActionKey(homebannerArray.getJSONObject(i).getInt("actionKey"));
            		bannerList.add(bannerItemData);
            	}
				 String homeUrl=homeDataObject.getString("webPage");
				 if(callBack!=null){
					 callBack.onSuccess(bannerList, homeUrl);
				 }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			callBack.onFailure("数据为null");
		}
	}
	public interface ParseCallBack{
		public void  onSuccess(ArrayList<BannerItemData> list,String url);
		public void onFailure(String failresult);
	}
}
