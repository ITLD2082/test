package com.ld.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ld.bean.Province;
import com.ld.mapper.ProvinceMapper;
import com.ld.service.ProvinceService;
import com.ld.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    ProvinceMapper provinceMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public String findList() {
        String json = (String) redisTemplate.opsForValue().get("province");
        List<Province> list=null;
        //如果为空，表示需要联网获取
        if (StringUtils.isEmpty(json)){
            list=provinceMapper.findProvinces();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json=objectMapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //存储数据到redis
            redisUtil.set("province",json);
        }else {
            //如果存在，表示缓存有数据，直接取出缓存数据
            System.out.println("使用缓存数据...");
        }
        return json;
    }
}
