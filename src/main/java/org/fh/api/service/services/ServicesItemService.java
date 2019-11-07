package org.fh.api.service.services;

import org.fh.api.mapper.services.ServicesItemMapper;
import org.fh.api.pojo.services.ServicesItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicesItemService {

    @Autowired
    private ServicesItemMapper servicesitemMapper;

    /**
     * 根据服务id获取服务项列表
     * @param servicesId 服务id
     * @return 服务项列表
     */
    public List<ServicesItem> byServicesId(String servicesId){
        return servicesitemMapper.byServicesId(servicesId);
    }
}
