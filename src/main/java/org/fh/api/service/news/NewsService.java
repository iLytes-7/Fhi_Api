package org.fh.api.service.news;


import org.fh.api.globalexception.YyhBizException;
import org.fh.api.mapper.news.NewsMapper;
import org.fh.api.pojo.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;


    /**
     * 查询所有头条消息
     * @return 返回所有消息对象list集合
     */
    public List<News> getNewsList() throws YyhBizException {
        return newsMapper.getNewsList();
    }


    /**
     * 根据id查询消息的详情内容
     * @param newsId    消息的id
     * @return          返回消息对象
     */
    public News getNewsById(String newsId) throws YyhBizException {
        return newsMapper.getNewsById(newsId);
    }
}
