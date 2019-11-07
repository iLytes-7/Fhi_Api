package org.fh.api.mapper.news;

import org.fh.api.pojo.news.News;

import java.util.List;

/**
 * 说明： 微信小程序Mapper
 * 作者：yyh-Oracles
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface NewsMapper {

    /**
     * 查询所有头条消息
     * @return 返回所有消息对象list集合
     */
    List<News> getNewsList();


    /**
     * 根据id查询消息的详情内容
     * @param newsId    消息的id
     * @return          返回消息对象
     */
    News getNewsById(String newsId);

}

