package org.fh.api.pojo.appointment;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Consultant {
    private String userId; //  顾问id
    private String name;  //顾问名称
    private String headPic; //顾问图片
    private String bz;     //顾问备注
}
