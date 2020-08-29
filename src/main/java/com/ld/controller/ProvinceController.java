package com.ld.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ld.service.ProvinceService;
import com.ld.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pro")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;

    /**
     * 获取省份集合数据
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object findList(){
        String json=provinceService.findList();
        JSONArray list = JSONObject.parseArray(json);
        return JsonData.buildSuc("获取成功",list);
    }
}
