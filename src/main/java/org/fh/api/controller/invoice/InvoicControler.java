package org.fh.api.controller.invoice;


import org.fh.api.pojo.invoice.Invoice;
import org.fh.api.service.invoice.InvoicService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/invoic")
public class InvoicControler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(InvoicControler.class);

    @Autowired
    private InvoicService invoicService;

    /**
     * 根据用户的openId查询该用户所有的发票
     * @param request   请求体对象 为从中获取user，进而获得openId
     * @return      返回该用户的所有发票列表
     */
    @ResponseBody
    @RequestMapping(value = "/getAllInvoiceByOpenId")
    public String getAllInvoiceByOpenId(HttpServletRequest request){
        LOGGER.info("getAllInvoiceByOpenId");
        List<Invoice> list = invoicService.getAllInvoiceByOpenId(request);
        return HttpEcho.success(list);
    }

}
