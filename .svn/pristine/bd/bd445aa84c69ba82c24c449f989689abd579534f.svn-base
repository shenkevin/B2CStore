package cn.mstar.store.entity;

import java.util.List;

/**
 * 用户地址管理模型  类 用于GSON解析
 * @author wenjundu
 *
 */
public class UserAddressManageJsonBean {
	/*
	 * {"data":{"tokenKey":"0f0f63c3-4f3d-44a6-98ed-7e9c37f8041c",
	 * "addUserAddress":[{"postName":"%d0%a1K","postAddress":"民治水尾56栋","zipCode":"533262","telephone":null,"mobile":"15994767200","email":null,"provinceId":19,"cityId":236,"countyId":2334,"isDefault":0}],
	 * "editUserAddress":[],
	 * "deleteUserAddress":[]},
	 * "response":"",
	 * "error":"",
	 * "message":""}
	 */
	public String response;
	public String error;
	public String message;
	public Data data;
	public class Data{
		public String tokenKey;
		public List<AddUserAddressEntity> addUserAddress;
		public List<EditUserAddressEntity> editUserAddress;
		public List<DeleteUserAddressEntity> deleteUserAddress;
//		public class AddUserAddress{
//			public String postName;
//			public String postAddress;
//			public String zipCode;
//			public String telephone;
//			public String mobile;
//			public String email;
//			public String provinceId;
//			public String cityId;
//			public String countyId;
//			public boolean isDefault;
//		}
//		public class EditUserAddress{
//			
//		}
//		public class DeleteUserAddress{
//			public int addressId; 
//		}
	}
	
}
