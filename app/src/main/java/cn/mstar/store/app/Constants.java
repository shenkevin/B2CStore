package cn.mstar.store.app;

/**
 * Created by Ultima on 7/11/2015.
 */
public class Constants {


	public static final String VERSION_NAME = "1.0";

	public static final String GOODS_MANAGMENT_ID = "goodsmanagemntid";

	public static final String START_ACTIVITY_FOR_RESULT_KEY = "MESSAGE";

	public static final int
	ALL = 1,
	WAITINGPAY = 2,
	WAITINGSENDING = 3,
	WAITINGRECEIVING = 4,
	WAITINGRETRIEVING = 5;


	public static  final int[] GOODS_MENU_TAB_POSITION = {ALL, WAITINGPAY, WAITINGSENDING, WAITINGRECEIVING, WAITINGRETRIEVING};

	public static final int ALREADY_USED = 3;

	public static final int EXPIRED = 4;

	public static final int NOT_USED = 2;

	public static  final int[] REDUCTION_TICKETS_TAB_POSITION = {ALL, NOT_USED, ALREADY_USED, EXPIRED};

//
//		public static final String IPADRESS = "192.168.1.240:8083";
//	public static final String IPADRESS = "192.168.1.47:8082";
//	  public static final String IPADRESS = "211.162.71.165:8082";

//	192.168.1.47:8082//App/UserOrder/WaitPay?page=1



	public enum Day {
		WAITING_PAYMENT, WAITING_RECEIVING, WAIT_RETRIEVING, WAIT_SENDING;
	}

	// favorite
	//    /App/Favorite/entity


}
