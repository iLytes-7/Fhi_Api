package org.fh.api.service.coupon;


import org.fh.api.dialect.ErrorCode;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.coupon.CouponMemberMapper;
import org.fh.api.pojo.coupon.CouponMember;
import org.fh.api.pojo.coupon.CouponMemberDetail;
import org.fh.api.pojo.user.User;
import org.fh.api.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CouponMemberService {

    @Autowired
    private CouponMemberMapper couponMemberMapper;


    /**
     * 保存用户领取优惠券信息
     * @param couponMember 用户的优惠券对象
     * @param request   请求对象
     * @return  优惠券用户表的id
     */
    @Transactional
    public String saveCouponMember(CouponMember couponMember, HttpServletRequest request) throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        String couponId = couponMember.getCouponId();
        CouponMemberDetail couponMemberDetail = getCouponMemberByCouponId(couponId,user.getOpenId());
        if (couponMemberDetail != null){
           throw new YyhBizException(ErrorCode.SAVE_ERROR);
        }
        couponMember.setCouponMemberId(Tools.get32UUID());
        couponMember.setOpenId(user.getOpenId());
        couponMember.setUseStatus("unused");
        couponMemberMapper.saveCouponMember(couponMember);
        return couponMember.getCouponMemberId();
    }

    /**
     * 根据优惠券用户的id来修改优惠券用户表
     * @param couponMember 用户拥有的优惠券对象
     * @return 无返回值
     */
    public void updateCouponMemberById(CouponMember couponMember) {
        couponMemberMapper.updateCouponMemberById(couponMember);
    }

    /**
     * 查询该用户下所有的优惠券
     * @param request 请求体
     * @return  返回优惠券列表
     */
    public List<CouponMemberDetail> selectAllCouponMemberByOpenId(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<CouponMemberDetail> list = couponMemberMapper.selectAllCouponMemberByOpenId(user.getOpenId());
        for (CouponMemberDetail item : list) {
            String validateTime = item.getValidateTime();
            String expireTime = item.getExpireTime();
            item.setValidateTime(validateTime.substring(0,10));
            item.setExpireTime(expireTime.substring(0,10));
        }
        return list;
    }

    /**
     * 查询该用户所有已使用的优惠券
     *
     * @param useStatus 优惠券的使用状态
     * @param request   请求体
     * @return  返回优惠券列表
     */
    public List<CouponMemberDetail> selectStatusCouponMemberByOpenId(String useStatus, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return couponMemberMapper.selectStatusCouponMemberByOpenId(useStatus,user.getOpenId());
    }

    /**
     * 根据id查询用户的优惠券详情
     * @param couponMemberId    用户的优惠券id
     * @return  返回单个优惠券的详情对象
     */
    public CouponMemberDetail getCouponMemberById(String couponMemberId) {
        return couponMemberMapper.getCouponMemberById(couponMemberId);
    }


    public CouponMemberDetail getCouponMemberByCouponId(String couponId,String openId) {
        return couponMemberMapper.getCouponMemberByCouponId(couponId,openId);
    }
}
