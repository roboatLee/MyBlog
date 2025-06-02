package com.lee.service.impl;

import com.lee.entity.User;
import com.lee.mapper.UserMapper;
import com.lee.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lee
 * @since 2025-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(String email, String nickname, String password) {
        User user = new User();
        if(!isEmailExit(email).isEmpty()){
//            System.out.println(isEmailExit(email));
            System.out.println("邮箱已经存在");
            return false;
        }
        user.setNikename(nickname);
        user.setPassword(password);
        user.setEmail(email);
        userMapper.insert(user);
        return false;
    }

    @Override
    public User login(String email, String password) {
        //检查是否存在邮箱
        List<User> users = isEmailExit(email);
        if(users == null){
            System.out.println("邮箱不存在");
        }
        User user = users.get(0);
        if(!Objects.equals(user.getPassword(), password)){
            System.out.println("密码不正确");
        }
        return user;
    }

    public List<User> isEmailExit(String email){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("email", email);
        List<User> users = userMapper.selectByMap(columnMap);
        return users;
    }
}
