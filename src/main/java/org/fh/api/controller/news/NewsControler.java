package org.fh.api.controller.news;

import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.news.News;
import org.fh.api.service.news.NewsService;
import org.fh.api.util.HttpEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsControler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(NewsControler.class);

    @Autowired
    private NewsService newsService;


    /**
     * 查询所有头条消息
     * @return 返回所有消息对象list集合
     */
    @ResponseBody
    @RequestMapping("/newsList")
    public String getNewsList() throws YyhBizException{
        LOGGER.info("newsList");
        List<News> list = newsService.getNewsList();
        return HttpEcho.success(list);
    }



    /**
     * 根据id查询消息的详情内容
     * @param newsId    消息的id
     * @return          返回消息对象
     */
    @ResponseBody
    @RequestMapping( value = "/getNewsById/{newsId}" ,method = RequestMethod.GET)
    public String getNewsById(@PathVariable("newsId") String newsId) throws YyhBizException{
        LOGGER.info("getNewsById");
        News news = newsService.getNewsById(newsId);
        return HttpEcho.success(news);
    }
}
