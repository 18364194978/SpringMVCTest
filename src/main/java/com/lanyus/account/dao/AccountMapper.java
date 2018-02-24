package com.lanyus.account.dao;

import com.lanyus.account.entity.Region;

import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2018/2/24.
 */
public interface AccountMapper {
    /**
     * 根据获取地区列表
     * @param param
     * @return
     */
    List<Region> getRegionList(Map<String,Object> param);
}
