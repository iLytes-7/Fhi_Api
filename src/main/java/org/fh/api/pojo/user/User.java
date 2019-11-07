package org.fh.api.pojo.user;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class User {
    private String memberId;
    private String openId;
    private String unionId;
    private String nickName;
    private String fullName;
    private String sex;
    private String tel;
    private String country;
    private String province;
    private String city;
    private String headImg;
    private BigDecimal jiFen;
    private String companyName;
    private String taxNo;
    private String createTime;
}
