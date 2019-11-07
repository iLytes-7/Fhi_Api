package org.fh.api.pojo.servicesitem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceitemDetail {
    private String servicesID;
    private String serviceImg;
    private String serviceDesc;
    private String serviceContent;
    private BigDecimal price;
    private String servicesName;
    private String displayPrice;
}
