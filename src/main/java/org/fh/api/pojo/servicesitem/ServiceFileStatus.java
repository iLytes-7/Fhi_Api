package org.fh.api.pojo.servicesitem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceFileStatus {
    private String  statusId;
    private String  fileId;
    private String  orderId;
    private String  fileName;
    private String  filePath;
    private String  fileFullPath;
    private String  status;
}
