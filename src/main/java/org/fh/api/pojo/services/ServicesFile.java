package org.fh.api.pojo.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesFile {

    //服务所需文件id
    private String fileId;
    //服务id
    private String servicesId;
    //服务所需文件名称
    private String fileName;
    //排序
    private String orderBy;
}
