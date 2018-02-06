package com.springmvc.dao;

import com.springmvc.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by xie on 2018/2/6.
 */
public interface UserDao {
    public User findByUsernameAndPwd(@Param("username") String username,@Param("password") String password);

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    public List<User>find(User user);
    /**
     * @param user
     */
    public void add(User user);
    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public void update(User user);
    /**
     * 删除用户信息
     * @param id
     */
    public void delete(String id);
}
