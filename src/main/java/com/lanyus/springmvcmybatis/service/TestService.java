package com.lanyus.springmvcmybatis.service;

import com.lanyus.springmvcmybatis.dao.TestMapper;
import com.lanyus.springmvcmybatis.entity.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Ly on 2015/10/28.
 */
@Service
public class TestService {
    @Resource
    TestMapper testMapper;
    public String print(int id) {
        Test test = testMapper.selectByPrimaryKey(id);
        return test.getContent();
    }

    public void updateContent(int id, String content) {
        Test test = testMapper.selectByPrimaryKey(id);
        test.setContent(content);
        testMapper.updateByPrimaryKeySelective(test);
    }
    public void delete(int id) {
        testMapper.deleteByPrimaryKey(id);
    }

    public void newContent(int id, String content) {
        Test test = new Test();
        test.setId(id);
        test.setContent(content);
        testMapper.insertSelective(test);
    }
}
