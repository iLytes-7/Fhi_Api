package org.fh.api.controller.lifecircles;
import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.lifecircles.Lifecircles;
import org.fh.api.service.lifecircles.LifecirclesService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/lifecircle")
public class LifecirclesControler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(LifecirclesControler.class);
    @Autowired
    private LifecirclesService lifecirclesService;
    /**
     * 根据生活圈种类查询生活圈列表
     * @param kind 生活圈种类
     * @return  返回生活圈列表
     * @throws YyhBizException yyh 自定义异常
     */
    @ResponseBody
    @RequestMapping(value = "/circlekind/{kind}", method = RequestMethod.GET)
    public String getCircleKind(@PathVariable("kind") String kind) throws YyhBizException {
        LOGGER.info("getCircleKind");
        List<Lifecircles>  list =   lifecirclesService.getCircleKind(kind);
        return HttpEcho.success(list);
    }
    /**
     * 根据是否首页显示查询生活圈列表
     * @return  返回首页显示的列表
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/circlerecommend")
    public String getCircleRecommend() throws YyhBizException {
        LOGGER.info("getCircleRecommend");
        List<Lifecircles>  list =   lifecirclesService.getCircleRecommend();
        return HttpEcho.success(list);
    }

    /**
     * 根据生活圈id查询单条的生活圈内容
     * @param id 生活圈id
     * @return  返回生活圈对象
     * @throws YyhBizException
     */
    @ResponseBody
    @RequestMapping(value = "/getCircleNewsById/{id}",method = RequestMethod.GET)
    public String getCircleNewsById(@PathVariable("id") String id) throws YyhBizException {
        LOGGER.info("getCircleNewsById");
        Lifecircles lifecircles = lifecirclesService.getCircleNewsById(id);
        return HttpEcho.success(lifecircles);
    }
}
