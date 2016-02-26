package cn.mstar.store.entity;

import java.io.Serializable;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class UserSelfInfoEntity implements Serializable{

    /*       "pic": "http://localhost/workspace/shopnc/data/upload/shop/common/default_goods_image_240.gif",
        "points": "12",
        "uName": "abcd",
        "tName": "测试",
        "email": "xdcdsdp@126.com",
        "sex": "0"*/
   public String pic, points, userName, tName, email, sex;

    public UserSelfInfoEntity(UserSelfInfoEntity userSelfInfoEntity) {
        this.pic = userSelfInfoEntity.pic;
        this.points = userSelfInfoEntity.points;
        this.userName = userSelfInfoEntity.userName;
        this.tName = userSelfInfoEntity.tName;
        this.email = userSelfInfoEntity.email;
        this.sex = userSelfInfoEntity.sex;


    }

    @Override
    public String toString() {
        return "UserSelfInfoEntity{" +
                "pic='" + pic + '\'' +
                ", points='" + points + '\'' +
                ", uName='" + userName + '\'' +
                ", tName='" + tName + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
