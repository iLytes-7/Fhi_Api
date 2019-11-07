package org.fh.api.service.banner;


import org.fh.api.globalexception.YyhBizException;
import org.fh.api.pojo.banner.BannerIndex;
import org.fh.api.mapper.banner.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     *
     * @param param bannar 图的位置
     * @return  banner图的List集合
     */
    public List<BannerIndex> getList(String param) throws YyhBizException{
        return bannerMapper.getBannerList(param);
    }
}
