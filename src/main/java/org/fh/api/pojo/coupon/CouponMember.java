package org.fh.api.pojo.coupon;

import lombok.Getter;
import lombok.Setter;


/**
 * @ClassName Coupon
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/18 0018  14:37
 * @Version 1.0
 **/

@Getter
@Setter
public class CouponMember {
    private String couponMemberId; //COUPON_MEMBER_ID
    private String couponId;  //COUPON_ID
    private String openId;  //OPEN_ID
    private String servicesId; //服务id
    private String useStatus;  //使用状态
    private String useDate;    //使用日期
}
