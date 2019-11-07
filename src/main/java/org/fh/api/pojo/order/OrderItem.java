package org.fh.api.pojo.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class OrderItem {
    private String orderId;
    private String servicesId;
    private String openId;
    private String orderNo;
    private String isNeedInvoice;
    private String title;
    private String taxNo;
    private String serviceImg;
    private BigDecimal price;
    private String orderAmount;
    private BigDecimal advance;
    private String payStatus;
    private String status;
    private String servicesName;
    private String remark;
    private String createTime;
    private String paymentType;//支付方式
}
