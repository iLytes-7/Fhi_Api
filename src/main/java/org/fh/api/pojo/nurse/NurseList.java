package org.fh.api.pojo.nurse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NurseList {
    private String pid;  //保姆id
    private String headImg;    //保姆头像
    private String introduce;    //保姆自身简介
    private String workYears;  //保姆工作年限
}
