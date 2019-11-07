package org.fh.api.mapper.invoice;

import org.fh.api.pojo.invoice.Invoice;

import java.util.List;
/**
 * 说明： 微信小程序Mapper
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface InvoicMapper {
    /**
     * 根据用户的openId查询该用户所有的发票
     * @param openId 用户的openId
     * @return 返回该用户的所有发票列表
     */
    public List<Invoice> getAllInvoiceByOpenId(String openId);

}

