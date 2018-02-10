package com.lanyus.springmvcmybatis.dao;

import com.lanyus.springmvcmybatis.entity.Test;

public interface TestMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);


    int updateByPrimaryKey(Test record);
}