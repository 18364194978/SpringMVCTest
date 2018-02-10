package com.lanyus.user.service;

import com.lanyus.user.entity.User;

import java.util.List;

/**
 * Created by Ly on 2015/10/28.
 */

public interface UserService {

    /**
     * 获取用户数据
     * @param user
     */
    public List<User> quertUserList(User user);

    /**
     * 修改用户数据
     * @param user
     */
    public void updateUser(User user);

    /**
     * 删除用户数据
     * @param id
     */
    public void deleteUser(String id);

    /**
     * 插入用户数据
     * @param user
     */
    public void insertUser(User user);
}
