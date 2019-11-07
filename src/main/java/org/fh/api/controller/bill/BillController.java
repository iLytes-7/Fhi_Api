package org.fh.api.controller.bill;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.fh.api.controller.order.OrderController;
import org.fh.api.pojo.bill.BillBean;
import org.fh.api.pojo.wxorder.OrderReturnInfo;
import org.fh.api.pojo.wxorder.PayCallBack;
import org.fh.api.service.bill.BillService;
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
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
@RequestMapping("/bill")
public class BillController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private BillService billService;

    /**
     * 查询所有的账单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBillList")
    public String getBillList(HttpServletRequest request){
        LOGGER.info("getBillList");
        List<BillBean> list = billService.getBillList(request);
        return HttpEcho.success(list);
    }

    /**
     * 根据id查询单个账单信息
     * @param billId
     * @return
     */
    @RequestMapping(value = "/getBillById/{billId}",method = RequestMethod.GET)
    public String getBillById(@PathVariable("billId")String billId){
        LOGGER.info("getBillById");
        BillBean billBean = billService.getBillById(billId);
        return HttpEcho.success(billBean);
    }

    /**
     * 根据No查询单个账单信息
     * @param billNo
     * @return
     */
    @RequestMapping(value = "/getBillBybillNo/{billNo}",method = RequestMethod.GET)
    public String getBillBybillNo(@PathVariable("billNo")String billNo){
        LOGGER.info("getBillBybillNo");
        BillBean billBean = billService.getBillBybillNo(billNo);
        return HttpEcho.success(billBean);
    }

    /**
     * 根据付款状态查询账单列表
     * @param payStatus
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBillListByPayStatus/{payStatus}",method = RequestMethod.GET)
    public String getBillListByPayStatus(@PathVariable("payStatus")String payStatus ,HttpServletRequest request){
        LOGGER.info("getBillListByPayStatus");
        List<BillBean> list = billService.getBillListByPayStatus(payStatus,request);
        return HttpEcho.success(list);
    }

    /**
     * 用积分支付账单
     * @param billBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/payBillByJiFen",method = RequestMethod.POST)
    public String payBillByJiFen(@RequestBody BillBean billBean,HttpServletRequest request) throws Exception {
        LOGGER.info("payBillByJiFen");
        billService.payBillByJiFen(billBean,request);
        return HttpEcho.success();
    }


    @RequestMapping(value = "/updateBillInvoice",method = RequestMethod.POST)
    public String updateBillInvoice(@RequestBody BillBean billBean) {
        LOGGER.info("updateBillInvoice");
        BillBean billBybillNo = billService.getBillBybillNo(billBean.getBillNo());
        billBybillNo.setIsNeedInvoice(billBean.getIsNeedInvoice());
        billBybillNo.setTitle(billBean.getTitle());
        billBybillNo.setTaxNo(billBean.getTaxNo());
        billService.updateBillInvoice(billBybillNo);
        return HttpEcho.success();
    }

    //微信下单
    @ResponseBody
    @RequestMapping(value = "/doOrder", method = RequestMethod.POST)
    public String doOrder(HttpServletRequest request) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        String openid = request.getParameter("openid");
        String billNo = request.getParameter("billNo");
        BillBean billBybillNo = billService.getBillBybillNo(billNo);
        LOGGER.info("订单金额:["+billBybillNo.getBillAmount()+"]");
        LOGGER.info("服务名称:["+billBybillNo.getServicesName()+"]");
        LOGGER.info("微信下单openid-------"+openid);
        SortedMap<String,String> param = new TreeMap<String,String>();
        param.put("appid", Configure.getAppID());
        LOGGER.info("appid----"+Configure.getAppID());
        param.put("mch_id",Configure.getMch_id());
        LOGGER.info("mch_id----"+Configure.getMch_id());
        String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
        param.put("nonce_str",nonce_str);
        LOGGER.info("nonce_str----"+nonce_str);
        param.put("body","fhi");
        param.put("out_trade_no",billNo);
        LOGGER.info("out_trade_no----"+billNo);
        param.put("total_fee",billBybillNo.getBillAmount().multiply(new BigDecimal("100")).intValue()+"");
        param.put("spbill_create_ip","47.52.202.14");
        param.put("notify_url","https://applet.fhi365.cn/bill/payCallback");
        param.put("trade_type","JSAPI");
        param.put("openid",openid);
        LOGGER.info("openid----"+openid);
        param.put("sign_type","MD5");
        //生成签名
        String sign = Signature.createSign("UTF-8",param);
        LOGGER.info("微信下单生成签名:--------"+sign);
        param.put("sign",sign);
        String xmlStr = Signature.getRequestXml(param);
        String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlStr);
        LOGGER.info("---------下单返回:"+result);
        XStream xStream = new XStream();
        xStream.alias("xml", OrderReturnInfo.class);
        OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
        JSONObject json = new JSONObject();
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
    public String payCallback(HttpServletRequest request) throws Exception {
        String reqParams = StreamUtil.read(request.getInputStream());
        LOGGER.info("-------支付结果:"+reqParams);
        XStream xStream = new XStream();
        xStream.alias("xml", PayCallBack.class);
        PayCallBack payCallBack = (PayCallBack)xStream.fromXML(reqParams);
        String out_trade_no = payCallBack.getOut_trade_no();
        BillBean billBybillNo = billService.getBillBybillNo(out_trade_no);
        if (billBybillNo!=null){
            billBybillNo.setPayStatus("ps_03");
            billBybillNo.setStatus("OS_03");
            billBybillNo.setPaymentType("微信支付");
            billService.updateBillStatusWxPay(billBybillNo);
        }
        return HttpEcho.success();
    }



}
