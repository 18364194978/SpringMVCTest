package com.lanyus.springmvcmybatis.service.Impl;

import com.lanyus.springmvcmybatis.dao.TestMapper;
import com.lanyus.springmvcmybatis.entity.Test;
import com.lanyus.springmvcmybatis.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xie on 2018/2/9.
 */
@Service
public class TestServiceImpl implements TestService {
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
