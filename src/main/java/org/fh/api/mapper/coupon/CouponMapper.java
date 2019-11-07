package org.fh.api.mapper.coupon;

import org.fh.api.pojo.banner.BannerIndex;
import org.fh.api.pojo.coupon.Coupon;

import java.util.List;

/**
 * 说明： 微信小程序Mapper
 * 作者：yyh-Oracles
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface CouponMapper {

    /**
     * 查询所有的已经发布出来的优惠券
     * @return
     */
    public List<Coupon> getCouponByPosition(String position);

    /**
     * 根据id 查询出对应的优惠券
     * @param id    优惠券di
     * @return  返回优惠券对象
     */
    public Coupon getCouponById(String id);

}

