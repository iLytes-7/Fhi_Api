package org.fh.api.service.services;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.services.ServicesItemStatusMapper;
import org.fh.api.pojo.services.ServicesItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicesItemStatusService {

    @Autowired
    private ServicesItemStatusMapper servicesItemStatusMapper;

    /**
     * 保存服务项状态
     * @param servicesItemStatus 服务项状态对象
     */
    public void save(ServicesItemStatus servicesItemStatus)  {
        servicesItemStatusMapper.save(servicesItemStatus);
    }

    /**
     * 根据订单查询服务项状态
     * @param orderNo 订单编号
     * @return  服务项状态列表
     */
    public List<ServicesItemStatus> byOrderNo(String orderNo)  {
        return servicesItemStatusMapper.byOrderNo(orderNo);
    }
}
