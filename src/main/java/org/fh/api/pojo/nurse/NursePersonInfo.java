package org.fh.api.pojo.nurse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NursePersonInfo {
    private String pid;          //保姆id
    private String headImg;      //保姆头像
    private String video;        //保姆视频
    private String introduce;    //保姆简介
    private String pName;        //保姆姓名
    private String birthday;     //生日
    private String age;          //年龄
    private String sex;          //性别
    private String country;      //国家
    private String workYears;    //工作年限
}
