package com.lanyus.account.service;

import com.lanyus.account.entity.Company;
import com.lanyus.account.entity.Region;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
public interface AccountService {

    /**
     * 根据条件查询地区信息的数量
     * @param param
     * @return
     */
    int findRegionCount(Map<String,Object> param);

    /**
     * 根据条件查询地区信息的list
     * @param param
     * @return
     */
    List<Region> finRegionList(Map<String,Object> param);

    /**
            * 根据条件查询地区信息的list
     * @param param
     * @return
             */
    List<Region> checkRegionList(Map<String,Object> param);

    /**
     * 根据条件添加地区信息
     * @param region
     * @return
     */
    void addRegion(Map<String,Object> region);
    /**
     * 根据条件修改地区信息
     * @param region
     * @return
     */
    void editRegion(Map<String,Object> region);
    /**
     * 根据条件删除地区信息
     * @param region
     * @return
     */
    void delRegion(Map<String,Object> region);
    /**
     * 根据条件查询公司信息的数量
     * @param param
     * @return
     */
    int getCompanyCount(Map<String,Object> param);
    /**
     * 根据条件查询公司信息的list
     * @param param
     * @return
     */
    List<Company> getCompanyList(Map<String,Object> param);
}
