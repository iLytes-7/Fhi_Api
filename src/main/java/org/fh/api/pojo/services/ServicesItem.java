package org.fh.api.pojo.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesItem {

    //服务项id
    private String itemId;
    //服务id
    private String servicesId;
    //服务项名称
    private String itemName;
    //排序
    private String orderBy;
}
