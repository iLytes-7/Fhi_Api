package org.fh.api.pojo.servicesitem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceItemStatus {
    private String  statusId;
    private String  itemId;
    private String  orderId;
    private String  status;
}
