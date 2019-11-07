package org.fh.api.pojo.lifecircles;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lifecircles {
    private String lifeCircleId; //生活圈Id
    private String lifeCircleType;  //生活圈类型
    private String recommendation;  //是否首页显示
    private String img;     //缩略图
    private String title;   //标题
    private String content; //内容
    private String browseTimes; //浏览次数
    private String shareTime;   //分享次数
    private String createTime;
    private String briefIntroduction;   //简介
}
