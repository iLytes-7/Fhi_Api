package org.fh.api.mapper.bill;

import org.apache.ibatis.annotations.Param;
import org.fh.api.pojo.bill.BillBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BillMapper {

    public List<BillBean> getBillList(String openId);


    public BillBean getBillById(String billId);

    public List<BillBean> getBillListByPayStatus(@Param("payStatus") String payStatus, @Param("openId") String openId);

    public void updateBillStatus(BillBean billBean);

    public BillBean getBillBybillNo(String billNo);

    public void updateBillInvoice(BillBean billBean);

    public void updateBillStatusWxPay(BillBean billBean);

}
