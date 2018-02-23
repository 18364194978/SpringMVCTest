package com.lanyus.authority.service.Impl;

import com.lanyus.authority.service.AuthorityService;
import com.lanyus.authority.dao.AuthorityMapper;
import com.lanyus.authority.entity.Authority;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/17 0017.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public List<JSONObject> getMenuList(String[] role_ids) {
        List<JSONObject> resultList = new ArrayList<JSONObject>();
        Map<String,JSONObject> roleMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("userid", role_ids);
        List<Authority> list = authorityMapper.getMenuList(param);
        for (Authority au : list){
            if (au.getParent_id()==null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.element("id", au.getMenu_id());
                jsonObject.element("sortOrder", au.getSort_order());
                jsonObject.element("menuCode", au.getMenu_code());
                jsonObject.element("text", au.getMenu_name());
                jsonObject.element("buttons", au.getButtons());
                jsonObject.element("expanded", au.getExpanded().equals("0")? false : true);
                jsonObject.element("checked", au.getChecked());
                jsonObject.element("leaf", au.getLeaf());
                jsonObject.element("url", au.getUrl());
                jsonObject.element("iconCls", au.getIcon_cls());
                roleMap.put(au.getMenu_id().toString(),jsonObject);
            }
        }
        for (Authority au: list) {
            if (au.getParent_id()!=null) {
                String parent_id = au.getParent_id();
                JSONObject jsonObject = roleMap.get(parent_id.toString());
                if (jsonObject==null){
                    continue;
                }
                JSONArray jsonArray = null;
                if (jsonObject.containsKey("children")) {
                    jsonArray = jsonObject.getJSONArray("children");
                }
                if (jsonArray==null) {
                    jsonArray = new JSONArray();
                }
                JSONObject childrenJsonObject = new JSONObject();
                childrenJsonObject.element("id", au.getMenu_id());
                childrenJsonObject.element("sortOrder", au.getSort_order());
                childrenJsonObject.element("menuCode", au.getMenu_code());
                childrenJsonObject.element("text", au.getMenu_name());
                childrenJsonObject.element("expanded", au.getExpanded());
                childrenJsonObject.element("checked", au.getChecked());
                childrenJsonObject.element("leaf", au.getLeaf());
                childrenJsonObject.element("url", au.getUrl());
                childrenJsonObject.element("iconCls", au.getIcon_cls());
                if ("ResourceManagement".equals(au.getMenu_code())) {
                    childrenJsonObject.element("buttons", "Add,Edit,Delete,View");
                }
                jsonArray.add(childrenJsonObject);
                jsonObject.put("children",jsonArray);
                roleMap.put(parent_id.toString(),jsonObject);
            }
        }
//        遍历map取值
        for (Map.Entry<String, JSONObject> entry : roleMap.entrySet()) {
            resultList.add(entry.getValue());
        }
        return resultList;
    }
}
