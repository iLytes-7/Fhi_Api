package org.fh.api.service.invoice;


import org.fh.api.mapper.invoice.InvoicMapper;
import org.fh.api.pojo.invoice.Invoice;
import org.fh.api.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class InvoicService {

    @Autowired
    private InvoicMapper invoicMapper;

    /**
     * 根据用户的openId查询该用户所有的发票
     * @param request 请求体对象 为从中获取user，进而获得openId
     * @return 返回该用户的所有发票列表
     */
    public List<Invoice> getAllInvoiceByOpenId(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return invoicMapper.getAllInvoiceByOpenId(user.getOpenId());
    }
}
