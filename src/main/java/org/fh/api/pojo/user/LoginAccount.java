package org.fh.api.pojo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAccount {
    private String openId;
    private String nickName;
    private String phone;
    private String password;

}
