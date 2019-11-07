package org.fh.api.pojo.nurse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NurseEdu {
    private String eduId;
    private String schoolName;   //学校名称
    private String eduType;      //学历
    private String beginDate;      //开始时间
    private String endDate;     //结束时间
}
