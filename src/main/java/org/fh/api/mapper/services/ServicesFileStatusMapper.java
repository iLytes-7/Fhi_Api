package org.fh.api.mapper.services;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.services.ServicesFileStatus;
import org.fh.api.pojo.services.ServicesItemStatus;

import java.util.List;

public interface ServicesFileStatusMapper {

    /**
     * 保存服务所需文件状态
     * @param servicesFileStatus 文件状态对象
     */
    public void save(ServicesFileStatus servicesFileStatus) ;


    /**
     * 根据订单查询服务所需文件状态
     * @param orderNo 订单编号
     * @return  返回服务文件状态列表
     */
    public List<ServicesFileStatus> byOrderNo(String orderNo);
}
