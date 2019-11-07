package org.fh.api.controller.customer;


import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.customer.Customer;
import org.fh.api.pojo.user.User;
import org.fh.api.service.customer.CustomerService;
import org.fh.api.util.HttpEcho;
import org.fh.api.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/customer")
public class CustomerControler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CustomerControler.class);

    @Autowired
    private CustomerService customerService;

    /**
     *
     * @param customer  customer对象
     * @param request   请求对象
     * @return  调用HttpEcho返回内容
     */

    @ResponseBody
    @RequestMapping(value = "/saveCustomer/{validateCode}", method = RequestMethod.POST)
    public String buildCustomer(@RequestBody Customer customer, HttpServletRequest request,@PathVariable("validateCode") String validateCode) throws YyhBizException {
        LOGGER.info("saveCustomer");
        customerService.buildCustomer(customer,request,validateCode);
        return HttpEcho.success();
    }


    /**
     *
     * @param param     客户的id
     * @return          返回客户对象
     */
    @ResponseBody
    @RequestMapping(value = "/getCustomerByCustId/{param}", method = RequestMethod.GET)
    public String getCustomerByCustId(@PathVariable  String param ) throws YyhBizException{
        LOGGER.info("getCustomerByCustId");
        Customer customer =  customerService.getCustomerByCustId(param);
        return HttpEcho.success(customer);
    }


    /**
     *
     * @param openId   微信用户的openid
     * @return      客户列表
     */
    @ResponseBody
    @RequestMapping(value = "/getCustomerByOpenId/{openId}", method = RequestMethod.GET)
    public String getCustomerByOpenId(@PathVariable  String openId ) throws YyhBizException{
        LOGGER.info("saveCustomer");
        Customer customer =  customerService.getCustomerByOpenId(openId);
        return HttpEcho.success(customer);
    }

    /**
     * 获取验证码
     * @param request   请求体
     * @param tel   电话号码
     * @return  无返回值
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/getValidateCode/{tel}", method = RequestMethod.GET)
    public String getValidateCode(HttpServletRequest request,@PathVariable("tel") String tel) throws YyhBizException{
        String validateCode = customerService.getValidateCode(tel);
        request.getSession().setAttribute("validateCode",validateCode);
        return HttpEcho.success();
    }

    /**
     * 查询用户已经存在的信息
     * @param request 请求体
     * @return  返回客户对象
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/getOldCustomerInfo", method = RequestMethod.GET)
    public String loadCustomer(HttpServletRequest request) throws YyhBizException {
        User user = (User) request.getSession().getAttribute("user");
        Customer customerByOpenId = customerService.getCustomerByOpenId(user.getOpenId());
        if (customerByOpenId == null){
            return  HttpEcho.success();
        }else {
            return  HttpEcho.success(customerByOpenId);
        }

    }



}
