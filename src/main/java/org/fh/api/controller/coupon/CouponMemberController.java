package org.fh.api.controller.coupon;

import org.fh.api.pojo.coupon.CouponMember;
import org.fh.api.pojo.coupon.CouponMemberDetail;
import org.fh.api.service.coupon.CouponMemberService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/couponMember")
public class CouponMemberController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CouponMemberController.class);

    @Autowired
    private CouponMemberService couponMemberService;

    /**
     * 保存用户领取优惠券信息
     * @param couponMember 用户的优惠券对象
     * @param request   请求对象
     * @return  优惠券用户表的id
     */
    @ResponseBody
    @RequestMapping(value = "/saveCouponMember", method = RequestMethod.POST)
    public String saveCouponMember(@RequestBody CouponMember couponMember, HttpServletRequest request) throws Exception {
        LOGGER.info("saveCouponMember");
        return HttpEcho.success(couponMemberService.saveCouponMember(couponMember,request));
    }

    /**
     * 根据优惠券用户的id来修改优惠券用户表
     * @param couponMember 用户拥有的优惠券对象
     * @return 无返回值
     */
    @ResponseBody
    @RequestMapping(value = "/updateCouponMemberById", method = RequestMethod.POST)
    public String updateCouponMemberById(@RequestBody CouponMember couponMember)  {
        LOGGER.info("updateCouponMemberById");
        couponMemberService.updateCouponMemberById(couponMember);
        return HttpEcho.success();
    }

    /**
     * 查询该用户下所有的优惠券
     * @param request 请求体
     * @return  返回优惠券列表
     */
    @ResponseBody
    @RequestMapping(value = "/selectAllCouponMemberByOpenId" )
    public String selectAllCouponMemberByOpenId(HttpServletRequest request)  {
        LOGGER.info("selectAllCouponMemberByOpenId");
        List<CouponMemberDetail> list = couponMemberService.selectAllCouponMemberByOpenId(request);
        return HttpEcho.success(list);
    }

    /**
     *
     * @param useStatus 优惠券的使用状态
     * @param request   请求体
     * @return  返回优惠券列表
     */
    @ResponseBody
    @RequestMapping(value = "/selectStatusCouponMemberByOpenId/{useStatus}" , method = RequestMethod.GET )
    public String selectStatusCouponMemberByOpenId(@PathVariable("useStatus") String useStatus ,HttpServletRequest request)  {
        LOGGER.info("selectStatusCouponMemberByOpenId");
        List<CouponMemberDetail> list = couponMemberService.selectStatusCouponMemberByOpenId(useStatus,request);
        return HttpEcho.success(list);
    }

    /**
     * 根据id查询用户的优惠券详情
     * @param couponMemberId    用户的优惠券id
     * @return  返回单个优惠券的详情内容
     */
    @ResponseBody
    @RequestMapping(value = "/getCouponMemberById/{couponMemberId}"  , method = RequestMethod.GET )
    public String getCouponMemberById(@PathVariable("couponMemberId") String couponMemberId)  {
        LOGGER.info("getCouponMemberById");
        CouponMemberDetail couponMemberDetail =  couponMemberService.getCouponMemberById(couponMemberId);
        return HttpEcho.success(couponMemberDetail);
    }
}
