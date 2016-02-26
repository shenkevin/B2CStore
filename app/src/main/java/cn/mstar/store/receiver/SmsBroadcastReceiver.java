package cn.mstar.store.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import cn.mstar.store.activity.RegisterActivity;
import cn.mstar.store.utils.CustomToast;
import cn.mstar.store.utils.L;

/**
 * Created by Ultima on 7/14/2015.
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {

    private SharedPreferences preferences;
//    public static String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub


//        if(intent.getAction().equals(SMS_RECEIVED)){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        String msgBody = msgs[i].getMessageBody();
                        CustomToast.mToast(context, "Got the message"+msgBody);
                        String auth_number = msgBody.substring(msgBody.length()-6, msgBody.length());
                        Intent in = new Intent(RegisterActivity.RegisterBackgroundReceiver.REQ_SEND_SMS_DONE1);
                        in.putExtra(RegisterActivity.RegisterBackgroundReceiver.SMS_CODE, auth_number);
                        context.sendBroadcast(in);
                        L.d("XXX "+msgBody);
                    }
                }catch(Exception e){
                    Log.d("Exception caught", e.getMessage());
                }
//            }
        }
    }



}