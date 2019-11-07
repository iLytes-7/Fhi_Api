package org.fh.api.pojo.news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {
    private String newsId;      //id
    private String newsTitle;   //新闻标题
    private String releaseDate; //发布日期
    private String browseTime;  //浏览次数
    private String shareTimes;  //分享次数
    private String newsImg;     //新闻缩略图
    private String content;     //内容
}
