package org.fh.api.mapper.services;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.services.ServicesItemStatus;

import java.util.List;

public interface ServicesItemStatusMapper {

    /**
     * 保存服务项状态
     * @param servicesItemStatus 服务项状态对象
     */
    public void save(ServicesItemStatus servicesItemStatus) ;

    /**
     * 根据订单查询服务项状态
     * @param orderNo 订单编号
     * @return  服务项状态列表
     */
    public List<ServicesItemStatus> byOrderNo(String orderNo) ;
}
