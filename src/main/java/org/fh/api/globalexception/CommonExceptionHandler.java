package org.fh.api.globalexception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fh.api.dialect.ErrorCode;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class CommonExceptionHandler implements HandlerExceptionResolver {
	
	private final static Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		PrintWriter out = null;
		try {
			/* 使用response返回 */
			response.setStatus(HttpStatus.OK.value()); // 设置状态码
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			if(ex instanceof YyhBizException){
//				logger.info("EnvisionBizException异常");
				YyhBizException ee = (YyhBizException) ex;
				if(ee.hasParams()){
					String errorMsg = ee.getEc().getMsg();
					String formatMsg = String.format(errorMsg,ee.getParams().toArray((Object[])new String[ee.getParams().size()]));
					out.write(HttpEcho.fail(null, ee.getEc().getCode(), formatMsg));
				}else{
					out.write(HttpEcho.fail(null, ee.getEc().getCode(), ee.getEc().getMsg()));
				}
			}else{
				logger.error("系统异常捕获:", ex);
				out.print(HttpEcho.fail(null, ErrorCode.SYS_ERROR.getCode(), ErrorCode.SYS_ERROR.getMsg()));
			}
			
		} catch (IOException e) {
			logger.error(ErrorCode.SYS_ERROR.getMsg(), e);
		}finally {
			out.flush();
			out.close();
		}

		return mv;
	}

}
