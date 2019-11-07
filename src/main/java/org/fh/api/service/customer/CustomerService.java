package org.fh.api.service.customer;


import org.apache.commons.lang3.StringUtils;
import org.fh.api.dialect.ErrorCode;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.customer.CustomerMapper;
import org.fh.api.pojo.customer.Customer;
import org.fh.api.pojo.user.User;
import org.fh.api.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper ;
    /**
     * 根据用户的openid查询customer表中是否存在这个customer
     * @param openId  用户的openid
     * @return      返回一个用户
     */
    public Customer getCustomerByOpenId (String openId) throws YyhBizException{
        return customerMapper.getCustomerByOpenId(openId);
    }

    /**
     * 客户编辑信息 不存在就新增，存在就修改
     * @param customer  客户对象
     * @param request   请求体
     * @throws YyhBizException
     */
    @Transactional
    public void buildCustomer(Customer customer, HttpServletRequest request,String validateCode) throws YyhBizException {
        //验证码
        String code = (String) request.getSession().getAttribute("validateCode");
        if(StringUtils.isNotEmpty(validateCode) && code.equals(validateCode)){
            //在session中拿到用户，根据用户的openId查询在customer表中是否存在该用户
            User user = (User) request.getSession().getAttribute("user");
            customer.setOpenId(user.getOpenId());
            Customer newCustomer = getCustomerByOpenId(user.getOpenId());
            //如果存在这个customer，我们就更新这个customer
            if (newCustomer!=null){
                updataCustomerByOpenid(customer);
            }else {//如果不存在这个customer我们就直接新增这个customer去
                customer.setCustomerId(Tools.get32UUID());
                saveCustomer(customer);
            }
        }else{
            throw new YyhBizException(ErrorCode.DYNAMIC_CODE_ERROR);
        }

    }
    /**
     * 存储一个新的customer
     * @param customer 需要我们存储进去的customer
     */
    public void  saveCustomer (Customer customer) throws YyhBizException{
        customerMapper.saveCustomer(customer);
    }

    /**
     * 根据openId更新我们的customer
     * @param customer 需要我们更新的customer信息存储在这个customer对象里面
     */
    public void updataCustomerByOpenid(Customer customer) throws YyhBizException{
        customerMapper.updataCustomerByOpenid(customer);
    }


    /**
     * 根据customerId 来查询对应的customer
     * @param param customerId customer的id
     * @return  返回一个customer
     */
    public Customer getCustomerByCustId(String param) throws YyhBizException{
        return customerMapper.getCustomerByCustId(param);
    }

    public String getValidateCode(String tel) throws YyhBizException {
        try {
            String validateCode = String.valueOf(Tools.getRandomNum());
            Tools.sendSMS(tel,validateCode);
            return validateCode;
        }catch (Exception e){
            throw new YyhBizException(ErrorCode.SYS_ERROR,e);
        }
    }
}
