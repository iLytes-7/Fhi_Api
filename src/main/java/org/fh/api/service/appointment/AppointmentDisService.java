package org.fh.api.service.appointment;

import org.fh.api.mapper.appointment.AppointmentDisMapper;
import org.fh.api.pojo.appointment.Consultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AppointmentDisService
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/19 0019  17:21
 * @Version 1.0
 **/
@Service
public class AppointmentDisService {

    @Autowired
    private AppointmentDisMapper appointmentDisMapper;

    /**
     * 查询所有的顾问列表
     * @return  返回顾问列表
     */
    public List<Consultant> getAllConsultant() {
        return  appointmentDisMapper.getAllConsultant();
    }
}
