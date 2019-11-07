package org.fh.api.service.order;

import org.fh.api.dialect.ErrorCode;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.order.OrderMapper;
import org.fh.api.pojo.customer.Customer;
import org.fh.api.pojo.order.OrderBean;
import org.fh.api.pojo.order.OrderItem;
import org.fh.api.pojo.order.OrderList;
import org.fh.api.pojo.services.*;
import org.fh.api.pojo.user.User;
import org.fh.api.service.customer.CustomerService;
import org.fh.api.service.services.*;
import org.fh.api.service.user.UserService;
import org.fh.api.util.HttpEcho;
import org.fh.api.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    //订单服务
    private OrderService orderService;
    @Autowired
    //用户 服务
    private UserService userService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    //公司服务 服务
    private ServicesService servicesService;
    @Autowired
    //服务项所需文件服务
    private ServicesFileService servicesFileService;

    @Autowired
    //服务项状态服务
    private ServicesItemStatusService servicesItemStatusService;

    @Autowired
    //服务所需文件状态服务
    private ServicesFileStatusService servicesFileStatusService;
    @Autowired
    //服务项服务
    private ServicesItemService servicesItemService;


    /**
     * 查询该用户下的所有订单
     * @param request 请求，为拿到session里面的对应用户账号
     * @return  返回订单列表list
     */
    public List<OrderList> getList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return orderMapper.getOrderList(user.getOpenId());
    }

    /**
     * 积分支付的流程
     * @param orderNo   订单号
     * @param request   请求体
     * @return  无返回
     * @throws YyhBizException 自定义异常
     */
    @Transactional
    public String payOrderByJiFen(String orderNo, HttpServletRequest request) throws YyhBizException {
        User user = (User) request.getSession().getAttribute("user");
        User newUser = userService.getUserByOpenid(user.getOpenId());
        BigDecimal jiFen = newUser.getJiFen();
        OrderItem orderByNo = orderService.getOrderByNo(orderNo);
        BigDecimal price = new BigDecimal(orderByNo.getOrderAmount());
        BigDecimal trans = new BigDecimal("10");
        BigDecimal needJiFen = price.multiply(trans);
        BigDecimal subtract = jiFen.subtract(needJiFen);
        int i = subtract.compareTo(BigDecimal.ZERO);
        if (i==-1){
            //表示积分数值少于该服务所需几分熟，报错，事务回滚
            throw new YyhBizException(ErrorCode.DONT_HAVE_JIFEN);
        }else {
            //修改用户表的积分
            newUser.setJiFen(subtract);
            userService.updateUserListByOpenid(newUser);
            //修改订单状态
            OrderItem order = byOrderNo(orderNo);
            if(order != null){
                orderService.modifyStatusByOrderNo("ps_03","OS_02",orderNo);
            }
            add(order,orderNo);
        }
        return HttpEcho.success();
    }

    /**
     * 添加服务流程方法
     * @param order 订单
     * @param orderNo   订单号
     */
    public void add(OrderItem order,String orderNo){
        //根据订单编号获取服务ID
        Services services = servicesService.byId(order.getServicesId());
        List<ServicesItemStatus> itemStatusList = servicesItemStatusService.byOrderNo(orderNo);
        //判断该笔订单是否已经有服务项
        if(itemStatusList == null || itemStatusList.size() == 0){
            //根据服务ID获取服务项列表
            List<ServicesItem> itemList = servicesItemService.byServicesId(services.getServicesId());
            //循环insert到服务项状态表
            itemList.forEach(p -> {
                ServicesItemStatus itemStatus = new ServicesItemStatus();
                itemStatus.setStatusId(Tools.get32UUID());
                itemStatus.setOrderNo(orderNo);
                itemStatus.setItemId(p.getItemId());
                itemStatus.setStatus("未开始"); //未开始,进行中,已完成
                servicesItemStatusService.save(itemStatus);
            });
        }
        //判断该笔订单是否有所需文件
        List<ServicesFileStatus> fileStatusList = servicesFileStatusService.byOrderNo(orderNo);
        if(fileStatusList == null || fileStatusList.size() == 0){
            ///根据服务ID获取服务项文件
            List<ServicesFile> fileList = servicesFileService.byServicesId(services.getServicesId());
            //循环insert到服务项文件表状态表
            fileList.forEach(p -> {
                ServicesFileStatus fileStatus = new ServicesFileStatus();
                fileStatus.setStatusId(Tools.get32UUID());
                fileStatus.setFileId(p.getFileId());
                fileStatus.setOrderNo(orderNo);
                fileStatus.setStatus("未上传"); //未上传,审核中,审核通过
                servicesFileStatusService.save(fileStatus);
            });
        }
    }
    /**
     * 根据订单状态查询订单列表
     * @param param 订单状态
     * @param openId    该用户的openId
     * @return 返回一个订单列表
     */
    public List<OrderList> getStatusList(String param ,String openId){
        return orderMapper.getStatusList(param,openId);
    }

    /**
     * 向数据库存储一个订单
     * @param order 订单对象
     */
    public void saveOrder(OrderBean order) {
        orderMapper.saveOrder(order);
    }

    /**
     * 根据orderid查询单个订单
     * @param param 订单的id
     * @return 返回一个订单对象，
     */
    public OrderItem getOrderById(String param) {
        return orderMapper.getOrderById(param);
    }


    /**
     * 根据订单编号获取订单对象
     * @param orderNo
     * @return 订单对象
     */
    public OrderItem byOrderNo(String orderNo){
        return orderMapper.byOrderNo(orderNo);
    }


    /**
     * 根据订单编号修改支付和订单状态
     * @param payStatus 支付状态
     * @param status  订单状态
     * @param orderNo 订单号
     */
    public void modifyStatusByOrderNo(String payStatus,String status,String orderNo){
        orderMapper.modifyStatusByOrderNo(payStatus,status,orderNo);
    }

    /**
     * 根据orderNo查询单个订单
     * @param param 订单的编号 oderNo
     * @return 返回一个订单对象
     */
    public OrderItem getOrderByNo(String param) {
        return orderMapper.getOrderByNo(param);
    }

    /**
     * 新建创建一个订单
     * @param order     订单对象
     * @param request   请求
     * @return  返回一个订单编号
     * @throws YyhBizException
     */
    public String buildOrder(OrderBean order,HttpServletRequest request) throws YyhBizException {
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否绑定个人信息
        String openId = user.getOpenId();
        Customer cust = customerService.getCustomerByOpenId(openId);
        if(cust == null){
            throw new YyhBizException(ErrorCode.NOT_BAND_PERSONAL_INFO);
        }
        Services services = servicesService.byId(order.getServicesId());
        order.setOrderNo(Tools.getOrderNo());
        order.setOrderId(Tools.get32UUID());
        order.setServicesId(services.getServicesId());
        order.setOrderAmount(services.getPrice().toString());
        order.setStatus("OS_01");
        order.setPayStatus("ps_01");
        order.setOpenId(user.getOpenId());
        saveOrder(order);
        return order.getOrderNo();
    }
}
