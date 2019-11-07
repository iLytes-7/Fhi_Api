package org.fh.api.service.services;

import org.fh.api.mapper.services.ServicesFileMapper;
import org.fh.api.pojo.services.ServicesFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicesFileService {

    @Autowired
    private ServicesFileMapper servicesFileMapper;

    /**
     * 根据服务id获取服务项列表
     * @param servicesId 服务id
     * @return 服务项列表
     */
    public List<ServicesFile> byServicesId(String servicesId){
        return servicesFileMapper.byServicesId(servicesId);
    }
}
