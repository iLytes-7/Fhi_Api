package org.fh.api.pojo.customer;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private String customerId;  //用户的id  CUSTOMER_ID
    private String openId;          //用户的openid
    private String custName;    //用户姓名
    private String tel;         //用户电话
    private String birthday;       //用户生日BIRTHDAY
    private String email;           //用户的邮箱
    private String countryTelCode;  //用户的号码归属地
}
