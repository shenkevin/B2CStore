package cn.mstar.store.entity;

/**
 * Created by Ultima on 7/15/2015.
 */
public class RegisterSuccessFailure {


    /*

     {"response":null,"error":"1","message":"haveError","data":"非法提交数据"}

      * */

    public String response, error, message, data;

    @Override
    public String toString() {
        return "RegisterSuccessFailure{" +
                "response='" + response + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
