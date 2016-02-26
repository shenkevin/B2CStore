package cn.mstar.store.entity;

public class SetDefaultAddressJsonBean {
	public String response;
	public String error;
	public String message;
	public Data data;
	public class Data{
		public int addressId;
		public String tokenKey;
	}
}
