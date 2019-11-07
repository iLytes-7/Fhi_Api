package org.fh.api.config;

import org.fh.api.dialect.ErrorCode;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.user.User;
import org.fh.api.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginInterceptor implements HandlerInterceptor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOGGER.info("---------------------开始进入请求地址拦截----------------------------");
        String path = httpServletRequest.getServletPath();
        String sessionId = httpServletRequest.getSession().getId();
        LOGGER.info("sessionId:"+sessionId);
        if(path.matches(Const.NO_INTERCEPTOR_PATH)){
            return true;
        }else{
            User user = (User) httpServletRequest.getSession().getAttribute("user");
            if(user == null){
                throw new YyhBizException(ErrorCode.NO_LOGIN);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOGGER.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LOGGER.info("---------------视图渲染之后的操作-------------------------0");
    }
}
