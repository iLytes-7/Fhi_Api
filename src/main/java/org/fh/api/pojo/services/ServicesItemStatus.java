package org.fh.api.pojo.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesItemStatus {

    //服务项状态
    private String statusId;
    //服务id
    private String itemId;
    //订单编号
    private String orderNo;
    //状态
    private String status;
    //服务项名称
    private String itemName;
    //
    private String remark;

}
