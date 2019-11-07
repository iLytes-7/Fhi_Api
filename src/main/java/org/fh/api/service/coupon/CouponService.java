package org.fh.api.service.coupon;


import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.banner.BannerMapper;
import org.fh.api.mapper.coupon.CouponMapper;
import org.fh.api.pojo.banner.BannerIndex;
import org.fh.api.pojo.coupon.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponMapper couponMapper;

    /**
     * 查询所有的已经发布出来的优惠券
     * @return
     */
    public List<Coupon> getCouponByPosition(String position) {
       return couponMapper.getCouponByPosition(position);
    }

    /**
     * 根据id 查询出对应的优惠券
     * @param id    优惠券di
     * @return  返回优惠券对象
     */
    public Coupon getCouponById(String id) {
        return couponMapper.getCouponById(id);
    }
}
