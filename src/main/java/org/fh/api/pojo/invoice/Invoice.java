package org.fh.api.pojo.invoice;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    private String orderInvoiceId; //订单发票id
    private String imgUrl;  //发票的正面图片
    private String orderNo; //订单编号
    private String servicesId;  //该发票对应的服务id
    private String servicesName;    //发票对应的服务名称
    private String title;   //发票抬头
    private String taxNo;   //发票税号
}
