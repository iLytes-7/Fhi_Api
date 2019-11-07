package org.fh.api.controller.banner;


import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.banner.BannerIndex;
import org.fh.api.service.banner.BannerService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerControler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BannerControler.class);

    @Autowired
    private BannerService bannerService;

    /**
     *
     * @param param bannar 图的位置
     * @return  banner图的List集合
     */
    @ResponseBody
    @RequestMapping(value = "/list/{param}", method = RequestMethod.GET)
    public String getBanner(@PathVariable("param") String param) throws YyhBizException {
        LOGGER.info("list");
        List<BannerIndex> list = bannerService.getList(param);
        return HttpEcho.success(list);
    }
}
