package org.fh.api.controller.services;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.services.*;
import org.fh.api.service.services.*;
import org.fh.api.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceFileStatus")
public class ServicesItemStatusController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ServicesItemStatusController.class);

    @Autowired
    //服务项状态服务
    private ServicesItemStatusService servicesItemStatusService;

    /**
     * 根据订单查询服务项状态
     * @param orderNo 订单编号
     * @return  服务项状态列表
     */
    @ResponseBody
    @RequestMapping(value = "/byOrderNo/{orderNo}", method = RequestMethod.GET)
    public String byOrderNo(@PathVariable("orderNo") String orderNo) throws YyhBizException {
        List<ServicesItemStatus> list = servicesItemStatusService.byOrderNo(orderNo);
        return HttpEcho.success(list);
    }


}
