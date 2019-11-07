package org.fh.api.service.servicesitem;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.services.SimpleService;
import org.fh.api.pojo.servicesitem.*;
import org.fh.api.mapper.servicesitem.ServiceitemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceitemService {
    @Autowired
    private ServiceitemMapper serviceitemMapper;

    /**
     * 查询首页显示icon
     * @return  首页显示的list集合
     */
    public List<ServiceitemIndex> getIndexList() throws YyhBizException {
        return serviceitemMapper.getIndexServiceitemList();
    }

    /**
     * 查询服务详情内容
     * @param param 服务的serviceId
     * @return  返回含有一个服务对象的list
     */
    public List<ServiceitemDetail> getItemDetail(String param) throws YyhBizException { return serviceitemMapper.getIndexServiceitemDetail(param);}


    /**
     * 查询所有服务的分类
     * @return 查询出来的所有的分类
     */
    public List<ServiceitemCategory> getCategoryList(String parentId) throws YyhBizException {
        List<ServiceitemCategory> categorylist = serviceitemMapper.getCategoryList(parentId);
        for (ServiceitemCategory category : categorylist){
            category.setSubCategory(getCategoryList(category.getId()));
        }
        return categorylist;
    }

    /**
     * 新增文件状态
     * @param fileStatus    服务文件状态对象
     * @return  无返回值
     */
    public void saveServiceFileStatus(ServiceFileStatus fileStatus) throws YyhBizException {
        serviceitemMapper.saveServiceFileStatus(fileStatus);
    }

    /**
     * 通过orderId查询服务项文件状态
     * @param param orderId
     * @return  返回服务文件状态列表
     */
    public List<ServiceFileStatus> getServiceFileStatus(String param) throws YyhBizException {
        return serviceitemMapper.getServiceFileStatus(param);
    }

    /**
     * 新增服务项状态
     * @param serviceItemStatus 服务项状态对象
     * @return  无返回值
     */
    public void saveServiceItemStatus(ServiceItemStatus serviceItemStatus) throws YyhBizException {
        serviceitemMapper.saveServiceItemStatus(serviceItemStatus);
    }

    /**
     * 通过orderid查询服务项文件状态
     * @param param  订单的orderid
     * @return  返回服务项状态list列表
     */
    public List<ServiceItemStatus> getServiceItemStatus(String param) throws YyhBizException {
        return serviceitemMapper.getServiceItemStatus(param);
    }

    /**
     * 模糊查询服务名称
     * @param servicesName 模糊的服务名称
     * @return  返回服务列表
     */
    public List<SimpleService> getAllSimpleService(String servicesName) {
        return serviceitemMapper.getAllSimpleService(servicesName);
    }
}
