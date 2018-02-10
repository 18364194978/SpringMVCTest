package com.lanyus.user.dao;

import com.lanyus.user.entity.User;

import java.util.Map;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

//    Map<String,Object> getuser(String account);
    User getuser(Map map);

    int updateByPrimaryKeySelective(User record);


    int updateByPrimaryKey(User record);
}