package org.fh.api.mapper.services;

import org.fh.api.pojo.services.Services;
import org.fh.api.pojo.servicesitem.*;

import java.util.List;

public interface ServicesMapper {

    /**
     * 根据服务ID查询对象
     * @param servicesId
     * @return 服务对象
     */
    public Services byId(String servicesId);

}
