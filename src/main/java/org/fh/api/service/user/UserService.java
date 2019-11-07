package org.fh.api.service.user;


import net.sf.json.JSONObject;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.user.UserMapper;
import org.fh.api.pojo.user.User;
import org.fh.api.util.AesCbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;


    /**
     * 根据opid去查询有没有这个用户
     * @param openid  用户的openid
     * @return  返回一个用户对象
     */

    public User getUserByOpenid(String openid) throws YyhBizException{
        User userByOpenid = userMapper.getUserByOpenid(openid);
        return userByOpenid;
    }

    /**
     * 根据opid去修改这个用户
     * @param user 传入用户信息
     */
    public void updateUserListByOpenid(User user) throws YyhBizException{
        userMapper.updateUserListByOpenid(user);
    }



    /**
     * 存储一个新的用户
     * @param user 传入一个用户进行存储
     */
    public void saveUser (User user) throws YyhBizException{
        userMapper.saveUser(user);
    }


    /**
     * 用户微信联合登录
     * @param param  微信用户的信息
     * @param request   请求体
     * @return  返回用户的sessionId
     * @throws Exception
     */
    @Transactional
    public String decodeUserInfo(String param,HttpServletRequest request) throws Exception{
        String sessionId = request.getSession().getId();
        String encryptedData = JSONObject.fromObject(param).get("encryptedData").toString();
        String session_key = JSONObject.fromObject(param).get("session_key").toString();
        String iv = JSONObject.fromObject(param).get("iv").toString();
        com.alibaba.fastjson.JSONObject userInfoJSON = AesCbcUtil.getUserInfo(encryptedData, session_key, iv);
        User user = getUserByOpenid(userInfoJSON.get("openId").toString());
        //根据openid查询到该用户则表明用户存在，只是修改用户信息
        if(user != null){
            user.setOpenId(userInfoJSON.get("openId").toString());
            user.setNickName(userInfoJSON.get("nickName").toString());
            user.setHeadImg(userInfoJSON.get("avatarUrl").toString());
            updateUserListByOpenid(user);
            request.getSession().setAttribute("user",user);
        }else{
            //如果查询不到该用户，保存用户信息
            User newUser = new User();
            newUser.setMemberId(UUID.randomUUID().toString());
            newUser.setOpenId(userInfoJSON.get("openId").toString());
            newUser.setNickName(userInfoJSON.get("nickName").toString());
            newUser.setHeadImg(userInfoJSON.get("avatarUrl").toString());
            saveUser(newUser);
            request.getSession().setAttribute("user",newUser);
        }
        return sessionId;
    }
}
