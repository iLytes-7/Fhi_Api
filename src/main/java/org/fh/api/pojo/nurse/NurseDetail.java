package org.fh.api.pojo.nurse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NurseDetail {
    private NursePersonInfo nursePersonInfo; //保姆个人信息部分
    private List<NurseEdu> nurseEduList;  //保姆教育信息部分
    private List<NurseWorkEx> nurseWorkExList;    //保姆工作经历部分
    private List<NurseCommu> nurseCommuList;       //交流技能
    private List<NurseOtherSkill> nurseOtherSkillList;      //其他技能名称
}
