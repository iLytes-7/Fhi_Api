package org.fh.api.mapper.appointment;

import org.fh.api.pojo.appointment.Appointment;

import java.util.List;


/**
 * 说明： 微信小程序Mapper
 * 作者：yyh-Oracles
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface AppointmentMapper {

    /**
     * 保存预约信息
     * @param appointment 预约对象
     */
    public void saveAppointment(Appointment appointment);

    /**
     * 根据id查询预约管理
     * @param id    预约的id
     * @return      返回预约对象
     */
    public Appointment getAppointmentById(String id);

    /**
     * 更新代码预约信息
     * @param appointment 预约对象
     * @return  无返回
     */
    public void updateAppointmentById(Appointment appointment);
    /**
     * 查询所有的预约列表
     * @param openId  用户的openId
     * @return  返回预约列表
     */
    public List<Appointment> getAllAppointment(String openId);

}

