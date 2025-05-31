package com.lee.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author KitenLee
 * * @date 2025/5/31
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    public void  set(String K,String V){
        redisTemplate.opsForValue().set(K,V);
    }


    public void setx(String K,String V,Integer time){
        redisTemplate.opsForValue().set(K,V,time, TimeUnit.SECONDS);
    }

    public String   get(String K){
        return redisTemplate.opsForValue().get(K);
    }
}
