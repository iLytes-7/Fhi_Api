package org.fh.api.mapper.coupon;

import org.apache.ibatis.annotations.Param;
import org.fh.api.pojo.coupon.CouponMember;
import org.fh.api.pojo.coupon.CouponMemberDetail;

import java.util.List;

/**
 * 说明： 微信小程序Mapper
 * 作者：yyh-Oracles
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface CouponMemberMapper {

    /**
     * 保存用户优惠券对象
     * @param couponMember 优惠券用户对象
     */
    public void saveCouponMember(CouponMember couponMember);

    /**
     * 根据优惠券用户的id来修改优惠券用户表
     * @param couponMember 用户拥有的优惠券对象
     * @return 无返回值
     */
    public void updateCouponMemberById(CouponMember couponMember);

    /**
     * 查询该用户下所有的优惠券
     * @param openId 用户的openid
     * @return  返回优惠券列表
     */
    public List<CouponMemberDetail> selectAllCouponMemberByOpenId(String openId);

    /**
     * 查询该用户所有已使用的优惠券
     *
     * @param useStatus 优惠券的使用状态
     * @param openId   请求体
     * @return  返回优惠券列表
     */
    public List<CouponMemberDetail> selectStatusCouponMemberByOpenId(@Param("useStatus") String useStatus, @Param("openId") String openId);

    /**
     * 根据id查询用户的优惠券详情
     * @param couponMemberId    用户的优惠券id
     * @return  返回单个优惠券的详情对象
     */
    public CouponMemberDetail getCouponMemberById(String couponMemberId);

    public CouponMemberDetail getCouponMemberByCouponId(@Param("couponId")String couponId,@Param("openId")String openId);

}

