package org.fh.api.controller.nurse;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.nurse.NurseDetail;
import org.fh.api.pojo.nurse.NurseList;
import org.fh.api.service.nurse.NurseService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/nurse")
public class NurseController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(NurseController.class);

    @Autowired
    private NurseService nurseService;

    /**
     * 查询所有保姆列表
     * @return 返回所有保姆对象list集合
     */
    @ResponseBody
    @RequestMapping("/nurseList")
    public String getNurseList() throws YyhBizException{
        LOGGER.info("nurseList");
        List<NurseList> list = nurseService.getNurseList();
        return HttpEcho.success(list);
    }

    /**
     * 根据id查询保姆的详情内容
     * @param pid    保姆的id
     * @return          返回保姆对象
     */
    @ResponseBody
    @RequestMapping( value = "/getNurseById/{pid}" ,method = RequestMethod.GET)
    public String getNurseById(@PathVariable("pid") String pid) throws YyhBizException{
        LOGGER.info("getNurseById");
        NurseDetail nurseDetail  = nurseService.getNurseById(pid);
        return HttpEcho.success(nurseDetail);
    }
}
