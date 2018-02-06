package com.springmvc.service;

import com.springmvc.model.User;

import java.util.List;
/**
 * Created by xie on 2018/2/6.
 */
public interface UserService {
    /**
     * 根据用户与密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    public User findByUsernameAndPwd(String username,String password);
    /**
     * 获取用户
     * @return
     */
    public List<User> find(User user);
    /**
     * 新增
     * @param user
     */
    public void add(User user);
    /**
     * 修改
     * @param user
     */
    public void update(User user);
    /**
     * 删除
     * @param id
     */
    public void delete(String id);
}
