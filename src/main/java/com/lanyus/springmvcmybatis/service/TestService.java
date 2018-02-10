package com.lanyus.springmvcmybatis.service;

import com.lanyus.springmvcmybatis.dao.TestMapper;
import com.lanyus.springmvcmybatis.entity.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Ly on 2015/10/28.
 */

public interface TestService {

    /**
     * 打印
     * @param id
     * @return
     */
    public String print(int id);

    /**
     * 修改数据
     * @param id
     * @param content
     */
    public void updateContent(int id, String content);

    /**
     * 删除数据
     * @param id
     */
    public void delete(int id);

    /**
     * 插入数据
     * @param id
     * @param content
     */
    public void newContent(int id, String content);
}
