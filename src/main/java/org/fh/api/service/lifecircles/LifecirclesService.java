package org.fh.api.service.lifecircles;


import org.fh.api.mapper.lifecircles.LifecirclesMapper;
import org.fh.api.pojo.lifecircles.Lifecircles;
import org.fh.api.util.DateDistance;
import org.fh.api.util.DelHTMLTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifecirclesService {

    @Autowired
    private LifecirclesMapper lifecirclesMapper;

    /**
     * 根据生活圈种类查询生活圈列表
     * @param kind 生活圈种类
     * @return  返回生活圈列表
     */
    public List<Lifecircles> getCircleKind(String kind) {
        List<Lifecircles> list =  lifecirclesMapper.getCircleKind(kind);
        for ( Lifecircles item : list) {
            String msg = DateDistance.CalculateTime(item.getCreateTime());
            item.setCreateTime(msg);
            String html = DelHTMLTag.delHTMLTag(item.getContent());
            item.setContent(html);
        }
        return list;
    }

    /**
     * 根据是否首页显示查询生活圈列表
     * @return  返回首页显示的列表
     */
    public List<Lifecircles> getCircleRecommend() {
        List<Lifecircles> list =  lifecirclesMapper.getCircleRecommend();
        for ( Lifecircles item : list) {
            String msg = DateDistance.CalculateTime(item.getCreateTime());
            item.setCreateTime(msg);
            String html = DelHTMLTag.delHTMLTag(item.getContent());
            item.setContent(html);
        }
        return list;
    }

    /**
     * 根据生活圈id查询单条的生活圈内容
     * @param id 生活圈id
     * @return  返回生活圈对象
     */
    public Lifecircles getCircleNewsById(String id) {
        Lifecircles lifecircles = lifecirclesMapper.getCircleNewsById(id);
        String msg = DateDistance.CalculateTime(lifecircles.getCreateTime());
        lifecircles.setCreateTime(msg);
        return lifecircles;
    }
}
