package org.fh.api.mapper.banner;

import org.fh.api.pojo.banner.BannerIndex;

import java.util.List;

/**
 * 说明： 微信小程序Mapper
 * 作者：yyh-Oracles
 * 时间：2019-04-09
 * csyyh
 * @version
 */
public interface BannerMapper {

    /**
     *
     * @param param bannar 图的位置
     * @return  banner图的List集合
     */
    public List<BannerIndex> getBannerList(String param);
}

