package cn.mstar.store.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.UpgradeItem;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class ApplicationUpdateThread extends Thread implements Runnable {

    MyApplication app;
    private Gson gson;

    //
    public ApplicationUpdateThread (MyApplication app) {

        this.app = app;
        gson = new Gson();
    }




    @Override
    public void run() {
        super.run();
        VolleyRequest.GetCookieRequest(app.getApplicationContext(), NewLink.CHECK_UPGRADE, new VolleyRequest.HttpStringRequsetCallBack() {

            @Override
            public void onSuccess(String result) {
                L.d("update:::", result);
                try {
                    JsonObject elm = gson.fromJson(result, JsonElement.class).getAsJsonObject().get("data").getAsJsonObject();
                    boolean ifUpdate = elm.get("ifupdate").getAsBoolean();
                    UpgradeItem it = gson.fromJson(elm, UpgradeItem.class);
                    if (Double.valueOf(it.version) > Double.valueOf(cn.mstar.store.app.Constants.VERSION_NAME))
//                        if (ifUpdate) {
                            app.needUpgrade = true;
                            app.upgradeEntity = it;
                            L.d("update:::", "needs update from "+cn.mstar.store.app.Constants.VERSION_NAME+" to "+it.version);
//                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String error) {
            }
        });
    }
}
