package org.fh.api.controller.integral;

import lombok.extern.slf4j.Slf4j;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.integral.IntegralBean;
import org.fh.api.service.integral.IntegralService;
import org.fh.api.util.HttpEcho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/integral")
@Slf4j
public class IntegralController {
    @Autowired
    private IntegralService integralService;

    /**
     * 查询该用户的所有充值记录
     * @param request   请求体
     * @return  返回充值记录列表
     */
    @RequestMapping("/getIntegralList")
    public String getIntegralList(HttpServletRequest request){
        log.info("getIntegralList");
        List<IntegralBean> list =  integralService.getIntegralList(request);
        return HttpEcho.success(list);
    }

    /**
     * 查询该用户的所有充值记录
     * @param request   请求体
     * @return  返回充值记录列表
     */
    @RequestMapping("/getIntegral")
    public String getIntegral(HttpServletRequest request) throws YyhBizException {
        log.info("getIntegral");
        BigDecimal list =  integralService.getIntegral(request);
        return HttpEcho.success(list.intValue());
    }
}
