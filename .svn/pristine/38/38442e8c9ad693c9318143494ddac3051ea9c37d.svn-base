package cn.mstar.store.entity;


import java.io.Serializable;
import java.util.Arrays;

public class OrdersItem implements Serializable {

    @Override
    public String toString() {
        return "OrdersItem{" +
                "pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", specialTitle='" + specialTitle + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", num=" + num +
                ", createTime='" + createTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", proSpecialId=" + proSpecialId +
                ", proId=" + proId +
                ", count=" + count +
                ", state=" + status +
                ", statusTxt='" + statusTxt + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    // order items content.
    public String pic, name, specialTitle;
    public Double price, totalPrice;
    public int num;
    public String createTime;
    public String orderId;
    public int proSpecialId, proId, count;
    public ORDER_STATE status;
    public String statusTxt;
    public String updateTime;
    public int evaluate;


    public OrdersItem(Prototype.OrderList little) {

        this.pic = little.pic;
        this.name = little.name;
        this.specialTitle = little.specialTitle;
        this.price = little.Price;
        this.totalPrice = little.totalPrice;
        this.num = little.num;
        this.createTime = little.createTime;
        this.proSpecialId = little.proSpecialId;
        this.proId = little.proId;
        this.count = little.count;
        this.updateTime = little.updateTime;
        this.orderId = little.orderId;
        this.statusTxt = little.statusTxt;
        this.evaluate = little.evaluate;
        /*
           /// </summary>
        WaitPay = 1,
        /// <summary>
        /// 待发货
        /// </summary>
        WaitSend = 2,
        /// <summary>
        /// 待收货
        /// </summary>
        WaitTake = 3
        \*/
        // if status somewhat then ... state is somewhat.
        if (little.status == 10) {
            status = ORDER_STATE.WAITING_FOR_PAY;
        } else if (little.status == 20) {
            status = ORDER_STATE.WAITING_FOR_SENDING;
        } else if (little.status == 30) {
            status = ORDER_STATE.WAITING_FOR_RECEIVE;
        } else if (little.status == 40){
            status = ORDER_STATE.CONFIRM_RECEIVE;
        }
    }

    // something actually left is the state of the Order ~

    public enum ORDER_STATE { TRANSACTION_DONE_OK, WAITING_FOR_PAY, WAITING_FOR_RECEIVE, WAITING_FOR_RETRIEVE, WAITING_FOR_SENDING, CONFIRM_RECEIVE }


    // entity prototype
    public class Prototype {

        public OrderList[] OrderList;
        public int list_count;

        @Override
        public String toString() {
            return "Prototype{" +
                    "orderLists=" + Arrays.toString(OrderList) +
                    ", list_item_count=" + list_count +
                    '}';
        }

        public  class OrderList {

            @Override
            public String toString() {
                return "OrderList{" +
                        "pic='" + pic + '\'' +
                        ", name='" + name + '\'' +
                        ", specialTitle='" + specialTitle + '\'' +
                        ", Price=" + Price +
                        ", totalPrice=" + totalPrice +
                        ", num=" + num +
                        ", createTime='" + createTime + '\'' +
                        ", orderId='" + orderId + '\'' +
                        ", proSpecialId=" + proSpecialId +
                        ", proId=" + proId +
                        ", count=" + count +
                        ", status=" + status +
                        ", statusTxt='" + statusTxt + '\'' +
                        ", updateTime='" + updateTime + '\'' +
                        ", evaluate=" + evaluate +
                        '}';
            }

            public String pic, name, specialTitle;
            public Double Price, totalPrice;
            public int num;
            public String createTime, orderId;
            public int proSpecialId, proId, count;
            public int status;
            public String statusTxt, updateTime;
    public int evaluate;
        }
    }

}
