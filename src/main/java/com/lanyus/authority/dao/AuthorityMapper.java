package com.lanyus.authority.dao;

import com.lanyus.authority.entity.Authority;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/17 0017.
 */
public interface AuthorityMapper {
    /**
     * 根据登录id获取菜单列表
     * @param param
     * @return
     */
    List<Authority> getMenuList(Map<String,Object> param);
}
