package org.fh.api.service.integral;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.integral.IntegralMapper;
import org.fh.api.mapper.user.UserMapper;
import org.fh.api.pojo.integral.IntegralBean;
import org.fh.api.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Service
public class IntegralService {

    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 查询该用户的所有充值记录
     * @param request   请求体
     * @return  返回充值记录列表
     */
    public List<IntegralBean> getIntegralList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return integralMapper.getIntegralList(user.getOpenId());

    }

    public BigDecimal getIntegral(HttpServletRequest request) throws YyhBizException {
        User user = (User) request.getSession().getAttribute("user");
        User userJf = userMapper.getUserByOpenid(user.getOpenId());
        return userJf.getJiFen();
    }
}
