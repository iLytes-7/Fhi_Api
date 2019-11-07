package org.fh.api.mapper.customer;

import org.fh.api.pojo.banner.BannerIndex;
import org.fh.api.pojo.customer.Customer;

import java.util.List;

/**
 * 说明： 微信小程序Mapper
 * 作者：yyh-Oracles
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface CustomerMapper {

    /**
     * 根据用户的openid查询customer表中是否存在这个customer
     * @param openId  用户的openid
     * @return      返回一个customer
     */
    public Customer getCustomerByOpenId(String openId);


    /**
     * 存储一个新的用户
     * @param customer
     */
    public void saveCustomer(Customer customer);

    /**
     * 根据openId更新我们的customer
     * @param customer 需要我们更新的customer信息存储在这个customer对象里面
     */
    public void updataCustomerByOpenid(Customer customer);

    /**
     * 根据customerId 来查询对应的customer
     * @param param customerId customer的id
     * @return  返回一个customer
     */
    public Customer getCustomerByCustId(String param);


}

