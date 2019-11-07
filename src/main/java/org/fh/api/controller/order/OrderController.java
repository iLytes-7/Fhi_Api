package org.fh.api.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringUtils;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.order.OrderBean;
import org.fh.api.pojo.order.OrderItem;
import org.fh.api.pojo.order.OrderList;
import org.fh.api.pojo.user.User;
import org.fh.api.pojo.wxorder.OrderReturnInfo;
import org.fh.api.pojo.wxorder.PayCallBack;
import org.fh.api.service.order.OrderService;
import org.fh.api.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    //订单服务
    private OrderService orderService;


    /**
     * 查询该用户下的所有订单
     * @param request 请求，为拿到session里面的对应用户账号
     * @return  返回订单列表list
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public String getList(HttpServletRequest request){
        LOGGER.info("orderlist");
        List<OrderList> list = orderService.getList(request);
        return HttpEcho.success(list);
    }

    /**
     * 根据orderid查询单个订单
     * @param param 订单的id
     * @return 返回一个订单对象，
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderById/{param}", method = RequestMethod.GET)
    public String getOrderById(@PathVariable("param") String param){
        LOGGER.info("getOrderById");
        OrderItem order = orderService.getOrderById(param);
        return HttpEcho.success(order);
    }


    /**
     * 根据orderNo查询单个订单
     * @param param 订单的编号 oderNo
     * @return 返回一个订单对象
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderByNo/{param}", method = RequestMethod.GET)
    public String getOrderByNo(@PathVariable("param") String param){
        LOGGER.info("getOrderByNo");
        OrderItem order = orderService.getOrderByNo(param);
        return HttpEcho.success(order);
    }



    /**
     * 根据订单的支付状态去查询订单
     * @param param 订单的支付状态
     * @param request   请求体
     * @return  返回一个订单列表
     */
    @ResponseBody
    @RequestMapping(value = "/statuslist/{param}", method = RequestMethod.GET)
    public String getStatusList(@PathVariable("param") String param,HttpServletRequest request){
        LOGGER.info("statuslist");
        User user = (User) request.getSession().getAttribute("user");
        List<OrderList> list = orderService.getStatusList(param,user.getOpenId());
        return HttpEcho.success(list);
    }


    /**
     * 积分支付的方式
     * @param orderNo   订单编号
     * @param request   请求体
     * @return  无返回
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/payOrderByJiFen/{orderNo}", method = RequestMethod.GET)
    public String payOrderByJiFen(@PathVariable("orderNo") String orderNo,HttpServletRequest request) throws YyhBizException {
        LOGGER.info("payOrderByJiFen");
        orderService.payOrderByJiFen(orderNo,request);
        return HttpEcho.success();
    }

    /**
     * 新增一个订单
     * @param order 订单对象
     * @param request   请求体
     * @return  返回订单编号
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrder(@RequestBody OrderBean order,HttpServletRequest request) throws YyhBizException{
        LOGGER.info("saveOrder");
        return HttpEcho.success(orderService.buildOrder(order,request));
    }

    //微信下单
    @ResponseBody
    @RequestMapping(value = "/doOrder", method = RequestMethod.POST)
    public String doOrder(HttpServletRequest request) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        String openid = request.getParameter("openid");
        String orderNo = request.getParameter("orderNo");
        OrderItem order = orderService.byOrderNo(orderNo);
        LOGGER.info("订单金额:["+order.getOrderAmount()+"]");
        LOGGER.info("服务名称:["+order.getServicesName()+"]");
        LOGGER.info("微信下单openid-------"+openid);
        SortedMap<String,String> param = new TreeMap<String,String>();
        param.put("appid",Configure.getAppID());
        LOGGER.info("appid----"+Configure.getAppID());
        param.put("mch_id",Configure.getMch_id());
        LOGGER.info("mch_id----"+Configure.getMch_id());
        String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
        param.put("nonce_str",nonce_str);
        LOGGER.info("nonce_str----"+nonce_str);
        param.put("body","fhi");
        param.put("out_trade_no",orderNo);
        LOGGER.info("out_trade_no----"+orderNo);
        param.put("total_fee",new BigDecimal(order.getOrderAmount()).multiply(new BigDecimal(100)).intValue()+"");
        param.put("spbill_create_ip","47.52.202.14");
        param.put("notify_url","https://applet.fhi365.cn/order/payCallback");
        param.put("trade_type","JSAPI");
        param.put("openid",openid);
        LOGGER.info("openid----"+openid);
        param.put("sign_type","MD5");
        //生成签名
        String sign = Signature.createSign("UTF-8",param);
        LOGGER.info("微信下单生成签名:----------"+sign);
        param.put("sign",sign);
        String xmlStr = Signature.getRequestXml(param);
        String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlStr);
        LOGGER.info("---------下单返回:"+result);
        XStream xStream = new XStream();
        xStream.alias("xml", OrderReturnInfo.class);
        xStream.setClassLoader(OrderController.class.getClassLoader());
        OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
        JSONObject json = new JSONObject();
        if(!"SUCCESS".equalsIgnoreCase(returnInfo.getReturn_code())){
            json.put("return_msg", returnInfo.getReturn_msg());
            return HttpEcho.fail(json, -1, returnInfo.getReturn_msg());
        }
        if(!"SUCCESS".equalsIgnoreCase(returnInfo.getResult_code())){
            json.put("err_code", returnInfo.getErr_code());
            json.put("err_code_des", returnInfo.getErr_code_des());
            return HttpEcho.fail(json, -1, returnInfo.getErr_code());
        }

        json.put("prepay_id", returnInfo.getPrepay_id());
        json.put("nonce_str",nonce_str);
        return HttpEcho.success(json);
    }

    //再次签名
    @ResponseBody
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String sign(HttpServletRequest request) throws IOException, IllegalAccessException {
        String repay_id = request.getParameter("repay_id");
        String nonce_str = request.getParameter("nonce_str");
        LOGGER.info(repay_id);
        LOGGER.info("repay_id:------------"+repay_id);
        long time = System.currentTimeMillis()/1000;
        SortedMap<String,String> param = new TreeMap<String,String>();
        param.put("appId",Configure.getAppID());
        param.put("timeStamp",String.valueOf(time));
        param.put("nonceStr",nonce_str);
        param.put("package","prepay_id="+repay_id);
        param.put("signType","MD5");
        //生成签名
        String sign = Signature.createSign("UTF-8",param);
        LOGGER.info("-------再签名:"+ sign);
        param.put("paySign",sign);
        return HttpEcho.success(param);
    }
    /**
     * 微信支付回调
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/payCallback")
    public String payCallback(HttpServletRequest request) throws IOException {
        String reqParams = StreamUtil.read(request.getInputStream());
        LOGGER.info("-------支付结果:"+reqParams);
        XStream xStream = new XStream();
        xStream.alias("xml", PayCallBack.class);
        PayCallBack payCallBack = (PayCallBack)xStream.fromXML(reqParams);
        //需要添加一笔交流流水待开发!
        String orderNo = payCallBack.getOut_trade_no();
        //修改订单状态
        OrderItem order = orderService.byOrderNo(orderNo);
        //判断支付方式是否为首付还是一次性付款
        if(order != null){
            orderService.modifyStatusByOrderNo("ps_03","OS_02",orderNo);
        }
        orderService.add(order,orderNo);
        return HttpEcho.success();
    }

    //APPIOS下单
    @ResponseBody
    @RequestMapping(value = "/doOrder4IOS", method = RequestMethod.POST)
    public String doOrder4IOS(HttpServletRequest request) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        String openid = request.getParameter("openid");
        String orderNo = request.getParameter("orderNo");
        OrderItem order = orderService.byOrderNo(orderNo);
        LOGGER.info("订单金额:["+order.getOrderAmount()+"]");
        LOGGER.info("服务名称:["+order.getServicesName()+"]");
        User user = (User)request.getSession().getAttribute("user");
        LOGGER.info("微信下单openid-------"+ user.getOpenId() + "  nickname---" + user.getNickName());
        SortedMap<String,String> param = new TreeMap<String,String>();
        param.put("appid",Configure.getAppIdIOS());
        LOGGER.info("appid----"+Configure.getAppIdIOS());
        param.put("mch_id",Configure.getMch_id());
        LOGGER.info("mch_id----"+Configure.getMch_id());
        String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
        param.put("nonce_str",nonce_str);
        LOGGER.info("nonce_str----"+nonce_str);
        param.put("body","掌上fhi" + (StringUtils.isEmpty(order.getServicesName())?"":("-"+order.getServicesName())));
        param.put("out_trade_no",orderNo);
        LOGGER.info("out_trade_no----"+orderNo);
        param.put("total_fee",new BigDecimal(order.getOrderAmount()).multiply(new BigDecimal(100)).intValue() +"");
        param.put("spbill_create_ip","47.52.202.14");
        param.put("notify_url","https://applet.fhi365.cn/order/payCallback4IOS");
        param.put("trade_type","APP");
        param.put("sign_type","MD5");
        //生成签名
        String sign = Signature.createSign("UTF-8",param);
        LOGGER.info("微信下单生成签名:----------"+sign);
        param.put("sign",sign);
        String xmlStr = Signature.getRequestXml(param);
        String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlStr);
        LOGGER.info("---------下单返回:"+result);
        XStream xStream = new XStream();
        xStream.alias("xml", OrderReturnInfo.class);
        xStream.setClassLoader(OrderController.class.getClassLoader());
        Object o = xStream.fromXML(result);
        OrderReturnInfo returnInfo = (OrderReturnInfo)o;
        SortedMap<String, String> appParam = new TreeMap<>();
        if(!"SUCCESS".equalsIgnoreCase(returnInfo.getReturn_code())){
            appParam.put("return_msg", returnInfo.getReturn_msg());
            return HttpEcho.fail(appParam, -1, returnInfo.getReturn_msg());
        }
        if(!"SUCCESS".equalsIgnoreCase(returnInfo.getResult_code())){
            appParam.put("err_code", returnInfo.getErr_code());
            appParam.put("err_code_des", returnInfo.getErr_code_des());
            return HttpEcho.fail(appParam, -1, returnInfo.getErr_code());
        }
        appParam.put("appid", Configure.getAppIdIOS());
        appParam.put("partnerid", Configure.getMch_id());
        appParam.put("prepayid", returnInfo.getPrepay_id());
        String nonce_str2 = RandomStringGenerator.getRandomStringByLength(32);
        appParam.put("noncestr", nonce_str2);
        appParam.put("timestamp", String.valueOf(new Date().getTime() / 1000));
        appParam.put("package", "Sign=WXPay");
        //生成签名
        String sign2 = Signature.createSign("UTF-8", appParam);
        appParam.put("sign", sign2);

        return HttpEcho.success(JSONObject.toJSON(appParam));
    }


    /**
     * IOS支付回调
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/payCallback4IOS")
    public String payCallback4IOS(HttpServletRequest request) throws IOException {
        String reqParams = StreamUtil.read(request.getInputStream());
        LOGGER.info("-------支付结果:"+reqParams);
        XStream xStream = new XStream();
        xStream.alias("xml", PayCallBack.class);
        PayCallBack payCallBack = (PayCallBack)xStream.fromXML(reqParams);
        //需要添加一笔交流流水待开发!
        String orderNo = payCallBack.getOut_trade_no();

        //修改订单状态
        OrderItem order = orderService.byOrderNo(orderNo);
        //判断订单状态是否已经支付
        if(order.getPayStatus().equals("ps_03")){
            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        }
        //判断签名
//        String sign = Signature.createSign("UTF-8",param);

        //判断支付方式是否为首付还是一次性付款
        if(order != null){
            orderService.modifyStatusByOrderNo("ps_03","OS_02",orderNo);
        }
        orderService.add(order,orderNo);
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
