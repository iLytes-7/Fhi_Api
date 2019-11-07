package org.fh.api.controller.coupon;

import org.fh.api.pojo.coupon.Coupon;
import org.fh.api.service.coupon.CouponService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/coupon")
public class CouponController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;
    /**
     * 查询所有的已经发布出来的优惠券
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCouponByPosition/{position}",method = RequestMethod.GET)
    public String getAllCoupon(@PathVariable("position") String position){
        LOGGER.info("getCouponByPosition");
        List<Coupon> list = couponService.getCouponByPosition(position);
        return HttpEcho.success(list);
    }

    /**
     * 根据id 查询出对应的优惠券
     * @param id    优惠券di
     * @return  返回优惠券对象
     */
    @ResponseBody
    @RequestMapping(value = "/getCouponById/{id}",method = RequestMethod.GET)
    public String getCouponById(@PathVariable("id") String id){
        LOGGER.info("getCouponById");
        Coupon coupon = couponService.getCouponById(id);
        return HttpEcho.success(coupon);
    }

}
