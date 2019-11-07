package org.fh.api.service.bill;

import org.fh.api.dialect.ErrorCode;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.bill.BillMapper;
import org.fh.api.pojo.bill.BillBean;
import org.fh.api.pojo.user.User;
import org.fh.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillMapper billMapper;


    @Autowired
    private UserService userService;

    /**
     * 查询该用户下的账单列表
     * @param request
     * @return
     */
    public List<BillBean> getBillList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return billMapper.getBillList(user.getOpenId());
    }

    /**
     * 根据id查询账单
     * @param billId
     * @return
     */
    public BillBean getBillById(String billId) {
        return billMapper.getBillById(billId);
    }

    /**
     * 根据账单状态查询账单列表
     * @param payStatus
     * @param request
     * @return
     */
    public List<BillBean> getBillListByPayStatus(String payStatus, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return billMapper.getBillListByPayStatus(payStatus,user.getOpenId());
    }

    /**
     * 更新账单状态以及支付方式 还有发票字段
     * @param billBean
     */
    public void updateBillStatus(BillBean billBean) {
        billMapper.updateBillStatus(billBean);
    }

    /**
     * 更新账单状态
     * @param billBean
     */
    public void updateBillStatusWxPay(BillBean billBean) {
        billMapper.updateBillStatusWxPay(billBean);
    }

    /**
     * 用积分支付账单
     * @param billBean
     * @param request
     * @throws Exception
     */
    @Transactional
    public void payBillByJiFen(BillBean billBean, HttpServletRequest request) throws Exception{
        User user = (User)request.getSession().getAttribute("user");
        User newUser = userService.getUserByOpenid(user.getOpenId());
        BigDecimal jiFen = newUser.getJiFen();
        BillBean billById = getBillById(billBean.getBillId());
        BigDecimal billAmount = billById.getBillAmount();
        BigDecimal needJiFen = billAmount.multiply(new BigDecimal("10"));
        BigDecimal subtract = jiFen.subtract(needJiFen);
        int i = subtract.compareTo(BigDecimal.ZERO);
        if (i==-1){
            //表示积分数值少于该服务所需几分熟，报错，事务回滚
            throw new YyhBizException(ErrorCode.DONT_HAVE_JIFEN);
        }else {
            //修改用户表的积分
            newUser.setJiFen(subtract);
            userService.updateUserListByOpenid(newUser);
            //修改账单状态
            billById.setIsNeedInvoice(billBean.getIsNeedInvoice());
            billById.setTitle(billBean.getTitle());
            billById.setTaxNo(billBean.getTaxNo());
            billById.setPaymentType(billBean.getPaymentType());
            billById.setPayStatus("ps_03");
            billById.setStatus("OS_03");
            updateBillStatus(billById);
        }
    }

    public BillBean getBillBybillNo(String billNo) {
        return billMapper.getBillBybillNo(billNo);
    }

    public void updateBillInvoice(BillBean billBean) {
        billMapper.updateBillInvoice(billBean);
    }
}
