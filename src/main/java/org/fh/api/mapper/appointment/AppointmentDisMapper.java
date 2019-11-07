package org.fh.api.mapper.appointment;

import org.fh.api.pojo.appointment.Consultant;

import java.util.List;

/**
 * @ClassName AppointmentDisMapper
 * @Author Administrator
 * @Date 2019/6/19 0019  17:22
 * @Version 1.0
 **/
public interface AppointmentDisMapper {

    /**
     * 查询所有的顾问列表
     * @return  返回顾问列表
     */
    public List<Consultant> getAllConsultant();

}
