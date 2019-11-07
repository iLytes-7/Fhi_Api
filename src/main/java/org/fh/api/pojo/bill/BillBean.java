package org.fh.api.pojo.bill;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BillBean {
    private String billId;  //账单ID
    private String servicesId;  //服务ID
    private String servicesName;//服务名称
    private String openId;  //用户openID
    private String billNo;  //账单编号
    private BigDecimal billAmount;  //账单金额
    private String isNeedInvoice;   //是否需要发票
    private String title;   //公司抬头
    private String taxNo;   //税号
    private String payStatus;   //支付状态
    private String status;  //账单状态
    private String remark;  //备注
    private String paymentType; //支付方式
    private String createTime;  //创建时间
}
