package org.fh.api.pojo.appointment;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Appointment {
    private String appointmentId; //预约表id
    private String openId;  //用户的openid
    private String appointmentDate; //预约日期
    private String appointmentDuration;     //预约时间段
    private String consultantName;          //预约的顾问姓名
    private String headPic;                  //预约的顾问头像
    private String consultantUserId;        //预约的顾问id
    private String serviceId;                   //预约的服务Id
    private String serviceName;               //预约的服务名称
    private String status;                  //状态
    private String remark;                     //备注
    private String isagree;                 //用户是否同意线下安排顾问
//    private String createTime;              //创建时间
}
