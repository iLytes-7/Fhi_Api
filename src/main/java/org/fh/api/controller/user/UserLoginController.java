package org.fh.api.controller.user;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.Unirest;
import org.fh.api.dialect.ErrorCode;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.user.LoginAccount;
import org.fh.api.pojo.user.Loginmeg;
import org.fh.api.pojo.user.User;
import org.fh.api.service.user.UserService;
import org.fh.api.util.AesCbcUtil;
import org.fh.api.util.Configure;
import org.fh.api.util.HttpEcho;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserLoginController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    private UserService userService;


    /**
     * @param code wx.login传入的code
     * @return 返回微信的session_key
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/dealCode")
    public String dealCode(@RequestParam("code") String code) throws Exception {
        LOGGER.info(code);
        String code2Session = Unirest.get("https://api.weixin.qq.com/sns/jscode2session?appid=wxb7dd3899d065a27b&secret=a0ecf5ed5d0cdc5290ae4665b810ab00&js_code=" + code + "&grant_type=authorization_code").asString().getBody();
        return HttpEcho.success(code2Session);
    }
    /**
     * 小程序用户登录
     * @param loginmeg 登录对象，包含用户登录信息，
     * @param request  请求对象
     * @return 返回sessionid
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/appletlogin", method = RequestMethod.POST)
    public String appletlogin(@RequestBody Loginmeg loginmeg, HttpServletRequest request) throws Exception {
        LOGGER.info(loginmeg.getCode());
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code'
        String code2Session = Unirest.get("https://api.weixin.qq.com/sns/jscode2session?appid=wxb7dd3899d065a27b&secret=a0ecf5ed5d0cdc5290ae4665b810ab00&js_code=" + loginmeg.getCode() + "&grant_type=authorization_code").asString().getBody();
        System.out.println(code2Session);
        Map<String, String> map = JSON.parseObject(code2Session, Map.class);
        String session_key = map.get("session_key");
        request.getSession().setAttribute("usermap", map);
        String sessionId = request.getSession().getId();
        String encryptedData = loginmeg.getEncryptedData();
        String iv = loginmeg.getIv();
        com.alibaba.fastjson.JSONObject userInfoJSON = AesCbcUtil.getUserInfo(encryptedData, session_key, iv);
        User user = userService.getUserByOpenid(userInfoJSON.get("unionId").toString());
        //根据openid查询到该用户则表明用户存在，只是修改用户信息
        if (user != null) {
            user.setOpenId(userInfoJSON.get("unionId").toString());
            user.setNickName(userInfoJSON.get("nickName").toString());
            user.setHeadImg(userInfoJSON.get("avatarUrl").toString());
            userService.updateUserListByOpenid(user);
            request.getSession().setAttribute("user", user);
        } else {
            //如果查询不到该用户，保存用户信息
            User newUser = new User();
            newUser.setMemberId(UUID.randomUUID().toString());
            newUser.setOpenId(userInfoJSON.get("unionId").toString());
            newUser.setNickName(userInfoJSON.get("nickName").toString());
            newUser.setHeadImg(userInfoJSON.get("avatarUrl").toString());
            userService.saveUser(newUser);
            request.getSession().setAttribute("user", newUser);
        }
        return sessionId;
    }

    /**
     * 用unionid,如果appios登录
     * @param loginmeg 登录对象，包含用户登录信息，
     * @param request  请求对象
     * @return 返回sessionid
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/login4IOS", method = RequestMethod.POST)
    public String login4IOS(Loginmeg loginmeg, HttpServletRequest request) throws Exception {
        String code = loginmeg.getCode();
//        String appid = "wx8b18955b9556dc27";
//        String appsecret = "4f7d6a0996401fb0a0bfe11e05fe185f";
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", Configure.getAppIdIOS(), Configure.getAppSecretISO(), code);
        JSONObject json = Unirest.get(url).asJson().getBody().getObject();
        String access_token = json.getString("access_token");
        String openid = json.getString("openid");
        String scope = json.getString("scope");
        if (scope.contains("snsapi_userinfo")){
            String url_user = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", access_token, openid);
            JSONObject json_user = Unirest.get(url_user).asJson().getBody().getObject();
            String unionid = json_user.getString("unionid");
            User user = userService.getUserByOpenid(unionid);
            //根据unionId查询到该用户则表明用户存在，只是修改用户信息
            if (user != null) {
                user.setNickName(json_user.get("nickname").toString());
                user.setHeadImg(json_user.get("headimgurl").toString());
                user.setOpenId(unionid);
                userService.updateUserListByOpenid(user);
                request.getSession().setAttribute("user", user);
            } else {
                //如果查询不到该用户，保存用户信息
                user = new User();
                user.setMemberId(UUID.randomUUID().toString());
                user.setOpenId(unionid);
                user.setNickName(json_user.get("nickname").toString());
                user.setHeadImg(json_user.get("headimgurl").toString());
                userService.saveUser(user);
                request.getSession().setAttribute("user", user);
            }
        }else{
            throw new YyhBizException(ErrorCode.NO_WX_AUTH);
        }
        String json_result = "{\"JSESSIONID\":\""  + request.getSession().getId() + "\"}";
        System.out.println(json_result);
        return json_result;
    }


    /**
     * ios账户登录
     * @param loginAccount
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/accountLogin4IOS", method = RequestMethod.POST)
    public String accountLogin4IOS(@RequestBody LoginAccount loginAccount, HttpServletRequest request) throws Exception {
        String phone = loginAccount.getPhone();
        String password = loginAccount.getPassword();
//        User logUser = new User();
//        logUser.setTel("18684801993");
//        logUser.setPassword(password);
        //        User user = userService.loginByAccount(logUser);
        //写死
        if("18684801993".equals(phone)&&"Csyyh@luo".equals(password)){
//            oqb9T1kf1JyWqTOYtYYWTdn_JN6g
            User user = userService.getUserByOpenid("oqb9T1kf1JyWqTOYtYYWTdn_JN6g");
            request.getSession().setAttribute("user", user);
//            String json_result = "{\"JSESSIONID\":\""  + request.getSession().getId() + "\"}";
//            System.out.println(json_result);
            return HttpEcho.success(request.getSession().getId());
        }

        throw new YyhBizException(ErrorCode.LOGIN_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/getConfig", method = RequestMethod.POST)
    public String getConfig(HttpServletRequest request) throws Exception {
        return HttpEcho.success("0");
    }

    /**
     * 判断用户是否已经登录
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/checklogin")
    public String checklogin() throws Exception {
        return HttpEcho.success();
    }

    /**
     * 获取用户的
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/getUserByOpenid")
    public String getUserByOpenid(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        User userByOpenid = userService.getUserByOpenid(user.getOpenId());
        return  HttpEcho.success(userByOpenid);
    }

    /**
     * 登出
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        return HttpEcho.success();
    }



}
