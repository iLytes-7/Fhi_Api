package org.fh.api.pojo.integral;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class IntegralBean {
    private String integralId; //积分ID
    private BigDecimal amount;  //金额
    private BigDecimal topUpIntegral;   //充值积分
    private BigDecimal presentIntegral;      //赠送积分
    private String openId;         //微信openid
    private String createTime;      //创建时间
    private String agent;           //经办人
    private String remark;          //备注

}
