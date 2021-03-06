package com.lanyus.account.dao;

import com.lanyus.account.entity.Region;
import com.lanyus.account.entity.Company;

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
    /**
     * 根据检查地区列表
     * @param param
     * @return
     */
    List<Region> checkRegionList(Map<String,Object> param);
    /**
     * 根据获取地区数量
     * @param param
     * @return
     */
    int getRegionCount(Map<String,Object> param);
    /**
     * 添加地区信息
     * @param param
     * @return
     */
    void addRegion(Map<String,Object> param);
    /**
     * 编辑地区信息
     * @param param
     * @return
     */
    void editRegion(Map<String,Object> param);
    /**
     * 删除地区信息
     * @param param
     * @return
     */
    void delRegion(Map<String,Object> param);
    /**
     * 根据获取公司列表
     * @param param
     * @return
     */
    List<Company> getCompanyList(Map<String,Object> param);
    /**
     * 根据获取公司数量
     * @param param
     * @return
     */
    int getCompanyCount(Map<String,Object> param);
}
