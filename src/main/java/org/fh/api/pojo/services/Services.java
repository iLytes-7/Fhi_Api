package org.fh.api.pojo.services;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 服务实体
 */

@Getter
@Setter
public class Services {
    //服务id
    private String servicesId;
    //是否首页显示
    private String homePageDisplay;
    //服务icon图片
    private String serviceIcon;
    //服务缩略图
    private String serviceImg;
    //服务类型(关联字典表中的BIANMA字段)
    private String serviceType;
    //服务描述
    private String serviceDesc;
    //服务内容
    private String serviceContent;
    //价格
    private BigDecimal price;
    //付款方式
    private String payType;
    //首付款
    private BigDecimal advance;
    //排序
    private int orderBy;


}
