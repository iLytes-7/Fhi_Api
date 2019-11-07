package org.fh.api.pojo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Loginmeg {
    //临时登录凭证
    private String code;
    //用户敏感信息
    private String encryptedData;
    //用户非敏感信息
    private String rawData;
    //解密算法的向量
    private String iv;
    //签名
    private String signature;
}
