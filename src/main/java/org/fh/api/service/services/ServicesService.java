package org.fh.api.service.services;

import org.fh.api.mapper.order.OrderMapper;
import org.fh.api.mapper.services.ServicesMapper;
import org.fh.api.pojo.order.OrderBean;
import org.fh.api.pojo.order.OrderItem;
import org.fh.api.pojo.order.OrderList;
import org.fh.api.pojo.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    @Autowired
    private ServicesMapper servicesMapper;

    /**
     * 根据服务ID查询对象
     * @param servicesId
     * @return 服务对象
     */
    public Services byId(String servicesId){
        return servicesMapper.byId(servicesId);
    }

}
