package com.lee.service;

import com.lee.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2025-05-31
 */
public interface IUserService extends IService<User> {

    boolean register(String email, String nickname, String password);

    User login(String email,String password);
}
