package com.lee.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.entity.User;
import com.lee.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author KitenLee
 * * @date 2025/5/31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserBatisTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void TestFunction(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", "1057526780@qq.com");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("User: " + users.get(0));
    }

    @Test
    public void StringCode(){

    }
}
