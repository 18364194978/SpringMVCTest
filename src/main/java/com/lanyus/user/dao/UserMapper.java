package com.lanyus.user.dao;

import com.lanyus.user.entity.User;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);


    int updateByPrimaryKey(User record);
}