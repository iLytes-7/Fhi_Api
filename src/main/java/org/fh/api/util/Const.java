package org.fh.api.util;

public class Const {
    public static final String NO_INTERCEPTOR_PATH = ".*/((appointmentDis)|(nurse)|(coupon/)|(banner)|(news)|" +
            "(lifecircle)|(serviceitem)|(user/appletlogin)|(user/login4IOS)|(user/decodeUserInfo)|(user/userlogin)|" +
            "(order/payCallback)|(bill/payCallback)|" +
            "(user/accountLogin4IOS)|" +
            "(user/getConfig)" +
            ").*";	//不对匹配该值的访问路径拦截（正则）
}
