package com.api.college.util;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CacheUtil {

    @Autowired
    RedisTemplate<String,?> redisTemplate;

    public void deleteByPattern(String pattern){
        try{
            Set<String> keys=redisTemplate.keys(pattern);
            redisTemplate.delete(keys);
        }catch(Exception e){
            log.error("Error while deleting pattern in redis",e);
        }

    }

}
