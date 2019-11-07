package org.fh.api.controller.servicesitem;


import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.services.SimpleService;
import org.fh.api.pojo.servicesitem.*;
import org.fh.api.service.servicesitem.ServiceitemService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/serviceitem")
public class ServiceitemController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ServiceitemController.class);

    @Autowired
    private ServiceitemService serviceitemService;


    /**
     * 查询首页显示icon
     * @return  首页显示的list集合
     */
    @ResponseBody
    @RequestMapping("/indexList")
    public String getIndexServiceitemList() throws YyhBizException {
        LOGGER.info("indexList");
        List<ServiceitemIndex> list = serviceitemService.getIndexList();
        return HttpEcho.success(list);
    }

    /**
     * 模糊查询服务名称
     * @param servicesName 模糊的服务名称
     * @return  返回服务列表
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/getAllSimpleService/{servicesName}", method = RequestMethod.GET)
    public String getAllSimpleService(@PathVariable("servicesName") String servicesName) throws YyhBizException {
        LOGGER.info("getAllSimpleService");
        List<SimpleService> list = serviceitemService.getAllSimpleService(servicesName);
        return HttpEcho.success(list);
    }


    /**
     * 查询所有服务的分类
     * @return 查询出来的所有的分类
     */
    @ResponseBody
    @RequestMapping("/categoryList")
    public String getCategoryList() throws YyhBizException {
        LOGGER.info("categoryList");
        List<ServiceitemCategory> list = serviceitemService.getCategoryList("6a19655d869d45e190e61c7fe46d6405");
        return HttpEcho.success(list);
    }


    /**
     * 查询服务详情内容
     * @param param 服务的serviceId
     * @return  返回含有一个服务对象的list
     */
    @ResponseBody
    @RequestMapping(value = "/itemDetail/{param}", method = RequestMethod.GET)
    public String getIndexServiceitemList(@PathVariable("param") String param) throws YyhBizException {
        LOGGER.info("itemDetail");
        List<ServiceitemDetail> list = serviceitemService.getItemDetail(param);
        return HttpEcho.success(list);
    }


    /**
     * 新增文件状态
     * @param fileStatus    服务文件状态对象
     * @return  无返回值
     */
    @ResponseBody
    @RequestMapping(value = "/saveServiceFileStatus", method = RequestMethod.POST)
    public String saveServiceFileStatus(@RequestBody ServiceFileStatus fileStatus ) throws YyhBizException {
        LOGGER.info("saveServiceFileStatus");
        serviceitemService.saveServiceFileStatus(fileStatus);
        return HttpEcho.success();
    }


    /**
     * 通过orderId查询服务项文件状态
     * @param param orderId
     * @return  返回服务文件状态列表
     */
    @ResponseBody
    @RequestMapping(value = "/getServiceFileStatus/{param}", method = RequestMethod.GET)
    public String getServiceFileStatus(@PathVariable("param") String param) throws YyhBizException {
        LOGGER.info("getServiceFileStatus");
        List<ServiceFileStatus> list = serviceitemService.getServiceFileStatus(param);
        return HttpEcho.success(list);
    }

    /**
     * 新增服务项状态
     * @param serviceItemStatus 服务项状态对象
     * @return  无返回值
     */
    @ResponseBody
    @RequestMapping(value = "/saveServiceItemStatus", method = RequestMethod.POST)
    public String saveServiceItemStatus(@RequestBody ServiceItemStatus serviceItemStatus) throws YyhBizException {
        LOGGER.info("saveServiceItemStatus");
        serviceitemService.saveServiceItemStatus(serviceItemStatus);
        return HttpEcho.success();
    }


    /**
     * 通过orderid查询服务项文件状态
     * @param param  订单的orderid
     * @return  返回服务项状态list列表
     */
    @ResponseBody
    @RequestMapping(value = "/getServiceItemStatus/{param}", method = RequestMethod.GET)
    public String getServiceItemStatus(@PathVariable("param") String param) throws YyhBizException {
        LOGGER.info("getServiceItemStatus");
        List<ServiceItemStatus> list = serviceitemService.getServiceItemStatus(param);
        return HttpEcho.success(list);
    }

}
