package com.lanyus.user.service.Impl;

import com.lanyus.user.dao.UserMapper;
import com.lanyus.user.entity.User;
import com.lanyus.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xie on 2018/2/9.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    /**
     * 获取用户数据
     * @param user
     */
    public List<User> quertUserList(User user){
        List<User> list = new ArrayList<User>();
        return list;
    }

    /**
     * 修改数据
     * @param user
     */
    public void updateUser(User user) {

    }

    /**
     * 删除数据
     * @param id
     */
    public void deleteUser(String id) {

    }

    /**
     * 插入数据
     * @param user
     */
    public void insertUser(User user) {

    }
}
