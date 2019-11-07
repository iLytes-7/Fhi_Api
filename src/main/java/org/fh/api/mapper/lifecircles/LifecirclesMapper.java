package org.fh.api.mapper.lifecircles;

import org.fh.api.pojo.lifecircles.Lifecircles;

import java.util.List;

/**
 * 说明： 微信小程序Mapper
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface LifecirclesMapper {
    /**
     * 根据生活圈种类查询生活圈列表
     * @param kind 生活圈种类
     * @return  返回生活圈列表
     */
    public List<Lifecircles> getCircleKind(String kind);

    /**
     * 根据是否首页显示查询生活圈列表
     * @return  返回首页显示的列表
     */
    public List<Lifecircles> getCircleRecommend();

    /**
     * 根据生活圈id查询单条的生活圈内容
     * @param id 生活圈id
     * @return  返回生活圈对象
     */
    public Lifecircles getCircleNewsById(String id);

}

