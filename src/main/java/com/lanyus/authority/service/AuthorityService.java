package com.lanyus.authority.service;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/2/17 0017.
 */
public interface AuthorityService {
    /**
     * 根据登录id获取菜单列表
     * @param role_ids
     */
    List<JSONObject> getMenuList(String[] role_ids);
}
