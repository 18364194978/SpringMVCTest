package com.lanyus.springmvcmybatis.service;

import com.lanyus.springmvcmybatis.dao.TestMapper;
import com.lanyus.springmvcmybatis.entity.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Ly on 2015/10/28.
 */

public interface TestService {

    public String print(int id);

    public void updateContent(int id, String content);
    public void delete(int id);

    public void newContent(int id, String content);
}
