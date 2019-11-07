package org.fh.api.pojo.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderBean {
    private String orderId;  //数据库维护
    private String servicesId;
    private String openId;
    private String orderNo;
    private String orderAmount;
    private String isNeedInvoice;
    private String title;
    private String taxNo;
    private String payStatus;
    private String status;
    private String remark;
    private String paymentType;//支付方式
}
