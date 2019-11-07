package org.fh.api.mapper.services;

import org.fh.api.pojo.services.ServicesItem;
import org.fh.api.pojo.servicesitem.*;

import java.util.List;

public interface ServicesItemMapper {

    /**
     * 根据服务id获取服务项列表
     * @param servicesId 服务id
     * @return 服务项列表
     */
    public List<ServicesItem> byServicesId(String servicesId);
}
