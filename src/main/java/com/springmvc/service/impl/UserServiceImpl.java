package com.springmvc.service.impl;

import com.springmvc.dao.UserDao;
import com.springmvc.model.User;
import com.springmvc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by xie on 2018/2/6.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource(name = "UserDao")
    private UserDao userDao;

    public User findByUsernameAndPwd(String name,String pwd){
        return userDao.findByUsernameAndPwd(name,pwd);
    }

    public List<User>find(User user){
        return userDao.find(user);
    }

    public void add(User user){
        userDao.add(user);
    }

    public void update(User user){
        userDao.update(user);
    }

    public void delete(String id){
        userDao.delete(id);
    }
}
