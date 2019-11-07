package org.fh.api.mapper.order;

import org.apache.ibatis.annotations.Param;
import org.fh.api.pojo.order.OrderBean;
import org.fh.api.pojo.order.OrderItem;
import org.fh.api.pojo.order.OrderList;

import java.util.List;

public interface OrderMapper {


    /**
     *  查询该用户下的所有订单
     * @param param 用户的openid
     * @return 返回订单列表
     */
    public List<OrderList> getOrderList(String param);


    /**
     * 根据订单的支付状态去查询订单
     * @param param 订单的支付状态
     * @param openId   用户的openId
     * @return  返回一个订单列表
     */
    public List<OrderList> getStatusList(@Param("param")String param,@Param("openId") String openId);

    /**
     * 向数据库存储一个订单
     * @param order 订单对象
     */
    public void saveOrder(OrderBean order);
    /**
     * 根据orderid查询单个订单
     * @param param 订单的id
     * @return 返回一个订单对象，
     */
    public OrderItem getOrderById(String param);


    /**
     * 根据订单编号获取订单对象
     * @param orderNo
     * @return 订单对象
     */
    public OrderItem byOrderNo(String orderNo);


    /**
     * 根据订单编号修改支付和订单状态
     * @param payStatus 支付状态
     * @param status  订单状态
     * @param orderNo 订单号
     */
    public void modifyStatusByOrderNo(@Param("payStatus") String payStatus, @Param("status")String status, @Param("orderNo")String orderNo);

    /**
     * 根据orderNo查询单个订单
     * @param param 订单的编号 oderNo
     * @return 返回一个订单对象
     */
    public OrderItem getOrderByNo(String param);

}
