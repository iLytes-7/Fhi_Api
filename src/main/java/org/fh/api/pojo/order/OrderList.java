package org.fh.api.pojo.order;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderList {
    private String orderId; //订单ID
    private String serviceId; //服务ID
    private String serviceName; //服务名称
    private String serviceImg; //服务名称
    private String orderNo; // 订单编号
    private String orderAmount; //订单金额
    private String payStatus; //支付状态
    private String status; //订单状态
    private String paymentType;//支付方式
}
