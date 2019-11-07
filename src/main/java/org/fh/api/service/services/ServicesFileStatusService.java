package org.fh.api.service.services;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.services.ServicesFileStatusMapper;
import org.fh.api.mapper.services.ServicesItemMapper;
import org.fh.api.pojo.services.ServicesFileStatus;
import org.fh.api.pojo.services.ServicesItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicesFileStatusService {

    @Autowired
    private ServicesFileStatusMapper servicesFileStatusMapper;

    /**
     * 保存服务所需文件状态
     * @param servicesFileStatus 文件状态对象
     */
    public void save(ServicesFileStatus servicesFileStatus) {
        servicesFileStatusMapper.save(servicesFileStatus);
    }

    /**
     * 根据订单查询服务所需文件状态
     * @param orderNo 订单编号
     * @return  返回服务文件状态列表
     */
    public List<ServicesFileStatus> byOrderNo(String orderNo)  {
        return servicesFileStatusMapper.byOrderNo(orderNo);
    }
}
