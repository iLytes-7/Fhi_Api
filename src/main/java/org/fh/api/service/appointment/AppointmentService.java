package org.fh.api.service.appointment;


import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.fh.api.dialect.ErrorCode;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.appointment.AppointmentMapper;
import org.fh.api.pojo.appointment.Appointment;
import org.fh.api.pojo.appointment.WechatInfo;
import org.fh.api.pojo.customer.Customer;
import org.fh.api.pojo.user.User;
import org.fh.api.service.customer.CustomerService;
import org.fh.api.service.services.ServicesService;
import org.fh.api.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private CustomerService customerService;
    @Autowired
    //公司服务 服务
    private ServicesService servicesService;

    /**
     * 构建appointment字段数据
     * @param appointment 预约对象
     * @param request   请求
     * @return  返回预约id
     * @throws YyhBizException
     */
    public Object buildAppointment(Appointment appointment, HttpServletRequest request) throws YyhBizException, UnirestException {
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否绑定个人信息
        String openId = user.getOpenId();
        Customer cust = customerService.getCustomerByOpenId(openId);
        if(cust == null){
            throw new YyhBizException(ErrorCode.NOT_BAND_PERSONAL_INFO);
        }
        appointment.setAppointmentId(Tools.get32UUID());
        appointment.setStatus("已预约");
        appointment.setOpenId(user.getOpenId());

//        String bodyparams = "{'openid':'oqpJj0rpO4SeeScV1Tbu0Az-yx4k,oqpJj0lVEzDBc7bGK1a4CLnctIvw," +
//                "oqpJj0gPTYFLTTSDC8KNc7klTJFs,oqpJj0oDtTfJfk4xtJyX-Ye5fo04','text':'asdasd'}";
        String openid = "oqpJj0rpO4SeeScV1Tbu0Az-yx4k,oqpJj0lVEzDBc7bGK1a4CLnctIvw," +
                "oqpJj0gPTYFLTTSDC8KNc7klTJFs,oqpJj0oDtTfJfk4xtJyX-Ye5fo04";

        String text =
                "客户:'"+cust.getCustName()+"',预约顾问咨询服务，时间为"+appointment.getAppointmentDate()+appointment.getAppointmentDuration()+",客户电话:"+cust.getTel();
        String ftext = null;
        if (appointment.getIsagree()=="true"){
             ftext = text+",该客户同意线下公司可以为其调整顾问，详情请登陆FHi后台管理端查看。http://www.fhi365.cn:8080/admin/login";
        }else {
             ftext = text+",详情请登陆FHi后台管理端查看。http://www.fhi365.cn:8080/admin/login";
        }
        WechatInfo wechatInfo = new WechatInfo();
        wechatInfo.setOpenid(openid);
        wechatInfo.setText(ftext);
        Gson gson = new Gson();
        gson.toJson(wechatInfo);
        HttpResponse<String> response =
                Unirest.post("https://cloud.fhi365.cn/fhi/api/notification/notication/wx")
                        .header("Content-Type","application/json")
                        .body(gson.toJson(wechatInfo))
                        .asString();
        System.out.println(response.toString());
        saveAppointment(appointment);
        return appointment.getAppointmentId();
    }

    /**
     * 保存预约信息
     * @param appointment 预约对象
     */
    private void saveAppointment(Appointment appointment) {
        appointmentMapper.saveAppointment(appointment);
    }

    /**
     * 根据id查询预约管理
     * @param id    预约的id
     * @return      返回预约对象
     */
    public Appointment getAppointmentById(String id) {
        return appointmentMapper.getAppointmentById(id);
    }
    /**
     * 更新代码预约信息
     * @param appointment 预约对象
     * @return  无返回
     */
    public void updateAppointmentById(Appointment appointment) {
        appointmentMapper.updateAppointmentById(appointment);
    }

    /**
     * 查询所有的预约列表
     * @param openId  用户的openid
     * @return  返回预约列表
     */
    public List<Appointment> getAllAppointment(String openId) {
        List<Appointment> list =  appointmentMapper.getAllAppointment(openId);
        return list;

    }
}
