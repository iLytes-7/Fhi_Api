package org.fh.api.mapper.user;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.user.User;


public interface UserMapper {

    /**
     * 根据opid去查询有没有这个用户
     * @param openid  用户的openid
     * @return  返回一个用户对象
     */
    public User getUserByOpenid(String openid) throws YyhBizException;

    /**
     * 根据opid去修改这个用户
     * @param user 传入用户信息
     */
    public void updateUserListByOpenid(User user) throws YyhBizException;

    /**
     * 存储一个新的用户
     * @param user 传入一个用户进行存储
     */
    public void saveUser(User user) throws YyhBizException;

}
