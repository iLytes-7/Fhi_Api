package org.fh.api.pojo.coupon;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @ClassName Coupon
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/18 0018  14:37
 * @Version 1.0
 **/

@Getter
@Setter
public class Coupon {
    private String couponId; //优惠券id
    private String couponType;  //优惠券类型(全局优惠/服务定向)
    private String servicesId;  //服务定向时有效的服务ID
    private String couponTitle; //优惠券标题
    private String couponRemark;  //优惠券规则说明
    private BigDecimal discountLimitAmt;    //使用门槛
    private BigDecimal discountAmt; //减免金额
    private String status;  //状态
    private int totalNum;   //发行量
    private String validateTime; //生效日期
    private String expireTime;  //过期时间
    private String briefIntroduction;//优惠券简介  BRIEF_INTRODUCTION
    private String couponImg;   //优惠券图片
    private String showPosition;  //优惠券显示位置

}
