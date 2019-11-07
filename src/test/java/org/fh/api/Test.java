package org.fh.api;


public class Test {
    public static void main(String[] args) {
       String pattern = ".*/((coupon/)).*";
        String path = "/couponMember/saveCouponMember";
       System.out.println(path.matches(pattern));

    }
}
