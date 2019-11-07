package org.fh.api.service.nurse;

import org.fh.api.mapper.nurse.NurseMapper;
import org.fh.api.pojo.nurse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NurseService {

    @Autowired
    private NurseMapper nurseMapper;
    public List<NurseList> getNurseList() {
        return nurseMapper.getNurseList();
    }


    @Transactional
    public NurseDetail getNurseById(String pid) {
        NurseDetail nurseDetail = new NurseDetail();
        NursePersonInfo nursePersonInfo = getNursePersonInfo(pid);
        List<NurseEdu> nurseEduList = getNurseEdu(pid);
        List<NurseWorkEx> nurseWorkExList = getNurseWorkEx(pid);
        List<NurseCommu> nurseCommuList = getNurseCommu(pid);
        List<NurseOtherSkill> nurseOtherSkillList = getNurseOtherSkill(pid);
        nurseDetail.setNursePersonInfo(nursePersonInfo);
        nurseDetail.setNurseEduList(nurseEduList);
        nurseDetail.setNurseWorkExList(nurseWorkExList);
        nurseDetail.setNurseCommuList(nurseCommuList);
        nurseDetail.setNurseOtherSkillList(nurseOtherSkillList);
        return nurseDetail;
    }

    /**
     * 根据pid获取当前保姆的个人信息
     * @param pid 保姆的pid 个人信息id
     * @return 返回保姆的个人信息
     */
    public NursePersonInfo getNursePersonInfo(String pid){

        return nurseMapper.getNursePersonInfoId(pid);
    }

    /**
     * 根据pdi获取保姆的教育经历列表
     * @param pid 保姆的pid
     * @return 返回保姆的教育经历列表
     */
    public List<NurseEdu> getNurseEdu(String pid){

        return nurseMapper.getNurseEdu(pid);
    }

    /**
     * 根据pid 获取保姆的工作经历劣币哦啊
     * @param pid
     * @return
     */
    public List<NurseWorkEx> getNurseWorkEx(String pid){

        return nurseMapper.getNurseWorkEx(pid);
    }

    /**
     * 根据pid获取保姆的交流技能
     * @param pid
     * @return
     */
    public List<NurseCommu> getNurseCommu(String pid){

        return nurseMapper.getNurseCommu(pid);
    }

    /**
     * 根据保姆的id获取保姆的其他技能列表
     * @param pid
     * @return
     */
    public List<NurseOtherSkill> getNurseOtherSkill(String pid){

        return nurseMapper.getNurseOtherSkill(pid);
    }
}
