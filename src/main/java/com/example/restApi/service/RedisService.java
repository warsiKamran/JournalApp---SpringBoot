package com.example.restApi.service;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);   //convert into entity class
        } 
        
        catch (Exception e) {
            log.error("Exception", e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonVal = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonVal, ttl, TimeUnit.SECONDS);
        }

        catch(Exception e){
            log.error("exception", e);
        }
    }
}

