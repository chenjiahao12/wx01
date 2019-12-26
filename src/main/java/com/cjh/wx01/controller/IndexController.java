package com.cjh.wx01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/hello")
    @ResponseBody
    public Map<String, Object> hello() {
//        log.info("hello");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "xyz");

        return map;
    }

    @RequestMapping("/toMain")
    public String toMain(Model model) {
//        log.info("toMain");
        Integer n = 1;
        String loginCount = stringRedisTemplate.opsForValue().get("loginCount");
        if (null != loginCount) {
            n = Integer.parseInt(loginCount) + 1;
        }
        stringRedisTemplate.opsForValue().set("loginCount", n.toString());

        model.addAttribute("now", new Date().toLocaleString()+",XXXXXYYYYYYzzzzAAAA");
        model.addAttribute("loginCount", n);

        return "main";
    }

    @RequestMapping("")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/toHello")
    public String toHello() {
        return "hello";
    }
}
