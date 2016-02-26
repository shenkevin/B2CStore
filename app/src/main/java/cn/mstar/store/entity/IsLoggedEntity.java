package cn.mstar.store.entity;

public class IsLoggedEntity {

	
	
	/*
  
  {"data":null,"response":null,"error":"1","message":"false"}
  
  *
  */

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
