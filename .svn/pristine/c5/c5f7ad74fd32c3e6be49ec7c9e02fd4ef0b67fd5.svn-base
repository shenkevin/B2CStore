package cn.mstar.store.entity;

import java.util.Arrays;

/**
 * Created by 1 on 2015/7/17.
 */
public class LoginEntity {

    @Override
    public String toString() {
        return "LoginEntity{" +
                "response='" + response + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /*

        {
        "response": "",
        "error": "",
        "message": "",
        "datas": {
            "userName": "shopnc",
            "password": "shopnc",
            "specialIdAndNums": [],
            "proIdAndNums": [],
            "message": "true"
        }
    }
          * */
    public String response, error, message;
    public InnerData data;


    public class InnerData {

        public String username, password, token, error;
        public String[] specialIdAndNums, proIdAndNums;
        public boolean message;

        @Override
        public String toString() {
            return "InnerData{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", token='" + token + '\'' +
                    ", error='" + error + '\'' +
                    ", specialIdAndNums=" + Arrays.toString(specialIdAndNums) +
                    ", proIdAndNums=" + Arrays.toString(proIdAndNums) +
                    ", message=" + message +
                    '}';
        }
    }



}
