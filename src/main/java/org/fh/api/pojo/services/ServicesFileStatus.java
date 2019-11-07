package org.fh.api.pojo.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesFileStatus {
    //服务所需文件状态id
    private String statusId;
    //服务所需文件id(关联wx_applet_service_file中FILE_ID)
    private String fileId;
    //订单编号(关联wx_applet_order中ORDER_NO)
    private String orderNo;
    //文件名称
    private String fileName;
    //文件路径
    private String filePath;
    //文件完整路径
    private String fileFullPath;
    //排序
    private String status;
}
