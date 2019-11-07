package org.fh.api.mapper.integral;

import org.fh.api.pojo.integral.IntegralBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IntegralMapper {
    public List<IntegralBean> getIntegralList(String openId);

}
