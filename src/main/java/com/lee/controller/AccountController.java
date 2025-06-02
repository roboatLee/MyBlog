package com.lee.controller;


import com.lee.entity.User;
import com.lee.entity.Users.TokenUserInfoDto;
import com.lee.service.IUserService;
import com.lee.utils.MinioUtils;
import com.lee.utils.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author KitenLee
 * * @date 2025/5/31
 */
@RequestMapping("/account")
@RestController
public class AccountController {
    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MinioUtils minioUtils;

    @RequestMapping("/register")
    public boolean register(
            String email,
            String nickname,
            String password,
            String checkCodeKey,
            String checkCode
    ){
        String code =  redisUtils.get("checkCode:"+checkCodeKey);
        if(!Objects.equals(checkCode, code)){
            System.out.println("验证码错误");
            return false;
        }
        userService.register(email,nickname,password);
        return true;
    }

    @RequestMapping("/getCheckcode")
    public Map<String, String> getCheckCode(){
        Map<String, String> result = new HashMap<>();
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100,24);

        //获得具体数字
        String code = captcha.text();
        //获得照片格式
        String checkCode = captcha.toBase64();
        //获得唯一表示符
        String checkCodeKey = UUID.randomUUID().toString();

        //存储到redis
        redisUtils.setx("checkCode:"+checkCodeKey,code,600);

        //返回结果
        result.put("checkCode", checkCode);
        result.put("checkCodeKey",checkCodeKey);

        return result;
    }

    @RequestMapping("/login")
    public TokenUserInfoDto login(
            String email,
            String password,
            String checkCodeKey,
            String checkCode
    ){
        //查看验证码是否正确
        String code =  redisUtils.get("checkCode:"+checkCodeKey);
        if(!Objects.equals(checkCode, code)){
            System.out.println("验证码错误");
        }
        //查看密码验证码是否正确
        User user = userService.login(email,password);

        //生成token
        TokenUserInfoDto tokenUserInfoDto =new TokenUserInfoDto();
        tokenUserInfoDto.setUserId(user.getId());
        tokenUserInfoDto.setNikeName(user.getNikename());
        tokenUserInfoDto.setToken(checkCodeKey);
        redisUtils.set("user:"+checkCodeKey,String.valueOf(user.getId()));

        return tokenUserInfoDto;
    }

    @RequestMapping("/editPersonal")
    public void editPersonal(
            Integer Id,
            String nikename,
            String personalSignature,
            String avaterPosition

    ){
        User user = new User();
        user.setId(Id);
        user.setNikename(nikename);
        user.setPersonalSignature(personalSignature);
        user.setAvatarPosition(avaterPosition);
        userService.updateById(user);
        return;
    }

    @RequestMapping("/uploadavater")
    public String uploadAvaterFile(
            @RequestParam("file")MultipartFile file
    ){
        String fileName = new String();
        try{
            fileName =  minioUtils.uploadFile(file);
        }catch (Exception e){
            System.out.println(e);
        }
        return  fileName;
    }

    @RequestMapping("/getPersonal/{id}")
    public User getPersonal(
        @PathVariable Integer id
    ){
        User user = userService.getById(id);
        return  user;
    }
}
