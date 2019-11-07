package org.fh.api.pojo.nurse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NurseWorkEx {
    private String expId;
    private String workContent;  //工作经历
    private String beginDate;    //工作经历开始日期
    private String endDate;      //工作经历结束日期
}
