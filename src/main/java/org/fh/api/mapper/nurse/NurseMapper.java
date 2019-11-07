package org.fh.api.mapper.nurse;

import org.fh.api.pojo.nurse.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NurseMapper {

    List<NurseList> getNurseList();

    NursePersonInfo getNursePersonInfoId(String pid);

    List<NurseEdu> getNurseEdu(String pid);

    List<NurseWorkEx> getNurseWorkEx(String pid);

    List<NurseCommu> getNurseCommu(String pid);

    List<NurseOtherSkill> getNurseOtherSkill(String pid);
}

