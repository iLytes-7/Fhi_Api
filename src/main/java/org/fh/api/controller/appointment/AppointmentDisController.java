package org.fh.api.controller.appointment;

import org.fh.api.pojo.appointment.Consultant;
import org.fh.api.service.appointment.AppointmentDisService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AppointmentDisController
 * @Author Administrator
 * @Date 2019/6/19 0019  17:20
 * @Version 1.0
 **/
@RestController
@RequestMapping("/appointmentDis")
public class AppointmentDisController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AppointmentControler.class);

    @Autowired
    private AppointmentDisService appointmentDisService;

    /**
     * 查询所有的顾问列表
     * @return  返回顾问列表
     */
    @ResponseBody
    @RequestMapping(value = "/getAllConsultant" )
    public String getAllConsultant() {
        LOGGER.info("getAllConsultant");
        List<Consultant> list =  appointmentDisService.getAllConsultant();
        return HttpEcho.success(list);
    }
}
