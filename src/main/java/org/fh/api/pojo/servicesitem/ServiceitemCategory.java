package org.fh.api.pojo.servicesitem;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ServiceitemCategory {
    private String id;
    private String name;
    private String nameEn;
    private String serviceId;
    private String serviceImg;
    private List<ServiceitemCategory> subCategory;
}
