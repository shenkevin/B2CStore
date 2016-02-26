package cn.mstar.store.entity;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class OrderDetailsEntity {

    /*
      "data": {
        "order": {
            "orderid": "7000000000037201",
            "postName": "likuo",
            "mobile": "1111",
            "status": "10",
            "paymentName": "货到付款",
            "paymentId": "0",
            "address": "河北省秦皇岛市卢龙县 111",
            "orderTotalPrice": "1690.00",
            "statusTxt": "待付款",
            "addtime": "2015-08-20 14:56:55"
        },
        "orderItems": [
            {
                "proNo": "DEFHJ0005",
                "itemId": "281",
                "orderId": "7000000000037201",
                "title": "千禧之星 足金金条黄金砖投资收藏结婚送礼正品 百年好合",
                "proSpecialId": "440",
                "proId": "440",
                "price": "5020.00",
                "totalPrice": 0,
                "num": "0",
                "specialTitle": null,
                "pic": "http://www.fanerjewelry.com/data/upload/shop/store/goods/1/1_04922802529231243_240.jpg"
            },
            {
                "proNo": "FQPTU0002",
                "itemId": "483",
                "orderId": "7000000000037201",
                "title": "千禧之星 pt950铂金项链女款 O字链 百搭吊坠款 约3.38g约46cm",
                "proSpecialId": "437",
                "proId": "437",
                "price": "1690.00",
                "totalPrice": 1690,
                "num": "1",
                "specialTitle": "约3.38g约46cm",
                "pic": "http://www.fanerjewelry.com/data/upload/shop/store/goods/1/1_04922783814223891_240.jpg"
            }
        ]
    }
      */

    public OrderInformations order;
    public OrderItems[] orderItems;


    public class OrderItems {

        public String proNo;
        public int itemId;
        public String orderId;
        public String title;
        public int proSpecialId,  proId;
        public Double price, totalPrice;
        public int num;
        public String specialTitle;
        public String pic;
        public int refund_state;
        public int evaluation_state;

        @Override
        public String toString() {
            return "OrderItems{" +
                    "proNo='" + proNo + '\'' +
                    ", itemId=" + itemId +
                    ", orderId='" + orderId + '\'' +
                    ", title='" + title + '\'' +
                    ", proSpecialId=" + proSpecialId +
                    ", proId=" + proId +
                    ", price=" + price +
                    ", totalPrice=" + totalPrice +
                    ", num=" + num +
                    ", specialTitle='" + specialTitle + '\'' +
                    ", pic='" + pic + '\'' +
                    '}';
        }
    }

    public class OrderInformations {

        public String orderId, postName, mobile,out_trade_no;
        public int status;
        public String paymentName;
        public int paymentId;
        public String address;
        public Double orderTotalPrice;
        public String statusTxt, addtime;

        @Override
        public String toString() {
            return "OrderInformations{" +
                    "orderid='" + orderId + '\'' +
                    ", postName='" + postName + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", status=" + status +
                    ", paymentName='" + paymentName + '\'' +
                    ", paymentId=" + paymentId +
                    ", address='" + address + '\'' +
                    ", orderTotalPrice=" + orderTotalPrice +
                    ", statusTxt='" + statusTxt + '\'' +
                    ", addtime='" + addtime + '\'' +
                    '}';
        }
    }



}
