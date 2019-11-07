package org.fh.api.controller.services;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.services.ServicesFileStatus;
import org.fh.api.service.services.ServicesFileStatusService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceItemStatus")
public class ServicesFileStatusController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ServicesFileStatusController.class);

    @Autowired
    private ServicesFileStatusService servicesFileStatusService;

    /**
     * 根据订单查询服务所需文件状态
     * @param orderNo 订单编号
     * @return  返回服务文件状态列表
     */
    @ResponseBody
    @RequestMapping(value = "/byOrderNo/{orderNo}", method = RequestMethod.GET)
    public String byOrderNo(@PathVariable("orderNo") String orderNo) throws YyhBizException {
        List<ServicesFileStatus> list = servicesFileStatusService.byOrderNo(orderNo);
        return HttpEcho.success(list);
    }


}
