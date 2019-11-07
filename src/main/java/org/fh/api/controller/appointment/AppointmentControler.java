package org.fh.api.controller.appointment;


import com.mashape.unirest.http.exceptions.UnirestException;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.appointment.Appointment;
import org.fh.api.pojo.user.User;
import org.fh.api.service.appointment.AppointmentService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentControler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AppointmentControler.class);

    @Autowired
    private AppointmentService appointmentService;

    /**
     * 插入新的预约记录
     * @param appointment 预约对象
     * @param request   请求体对象
     * @return  返回预约id号
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/saveAppointment", method = RequestMethod.POST)
    public String saveAppointment(@RequestBody Appointment appointment, HttpServletRequest request) throws YyhBizException, UnirestException {
        LOGGER.info("saveAppointment");
        return HttpEcho.success(appointmentService.buildAppointment(appointment,request));
    }
    /**
     * 查询所有的预约列表
     * @param request  请求体对象
     * @return  返回预约列表
     */
    @ResponseBody
    @RequestMapping(value = "/getAllAppointment" )
    public String getAllAppointment(HttpServletRequest request) {
        LOGGER.info("getAllAppointment");
        User user = (User) request.getSession().getAttribute("user");
        List<Appointment> list = appointmentService.getAllAppointment(user.getOpenId());
        return HttpEcho.success(list);
    }

    /**
     * 根据id查询预约管理
     * @param id    预约的id
     * @return      返回预约对象
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/getAppointmentById/{id}", method = RequestMethod.GET)
    public String getAppointmentById(@PathVariable("id") String id ){
        LOGGER.info("getAppointmentById");
        return HttpEcho.success(appointmentService.getAppointmentById(id));
    }

    /**
     * 更新代码预约信息
     * @param appointment 预约对象
     * @return  无返回
     */
    @ResponseBody
    @RequestMapping(value = "/updateAppointmentById", method = RequestMethod.POST)
    public String updateAppointmentById(@RequestBody Appointment appointment){
        LOGGER.info("updateAppointmentById");
        appointmentService.updateAppointmentById(appointment);
        String msg = "success";
        return HttpEcho.success(msg);
    }

}
