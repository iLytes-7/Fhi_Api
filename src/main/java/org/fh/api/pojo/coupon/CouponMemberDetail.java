package org.fh.api.pojo.coupon;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @ClassName CouponMenberDetail
 * @Author iLytes
 * @Date 2019/6/19 0019  10:19
 * @Version 1.0
 **/
@Getter
@Setter
public class CouponMemberDetail {
    private String  couponMemberId; //用户拥有优惠券id
    private String  openId; // 用户的openid
    private String  couponId; //对应的优惠券ID
    private String  couponType; //优惠券类型
    private String  servicesId; //服务定向时有效的服务ID
    private String  couponTitle; //优惠券标题
    private String  couponRemark; //优惠券内容
    private BigDecimal discountLimitAmt;    //使用门槛
    private BigDecimal discountAmt; //减免金额
    private String validateTime; //生效日期
    private String expireTime;  //过期时间
    private String createTime; //领取日期
    private String useStatus;  //使用状态
    private String useDate;    //使用日期
}
