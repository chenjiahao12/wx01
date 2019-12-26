package com.cjh.wx01.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.Configuration;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinBuilder;
import org.weixin4j.component.MenuComponent;
import org.weixin4j.factory.WeixinFactory;
import org.weixin4j.factory.defaults.DefaultWeixinFactory;
import org.weixin4j.model.base.Token;
import org.weixin4j.model.menu.Menu;
import org.weixin4j.model.menu.SingleButton;
import org.weixin4j.model.menu.ViewButton;
import org.weixin4j.spring.WeixinTemplate;
import sun.applet.Main;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx")
@Slf4j
public class WeixinController {


    @Autowired
    private WeixinTemplate weixinTemplate;

    @RequestMapping("/createMenu")
    @ResponseBody
    public Map<String, Object> createMenu(Model model, HttpServletRequest request) {


//        log.info("WeixinController.createMenu");
        String ctx = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//        log.info("ctx=" + ctx);

        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("code", 0);
        jsonData.put("message", "微信菜单创建成功");
        try {
            Menu menu = new Menu();
            //创建菜单按钮
            List<SingleButton> buttons = new ArrayList<SingleButton>();
            menu.setButton(buttons);

            SingleButton btn1 = new ViewButton("主界面", ctx + "/");
            buttons.add(btn1);

            SingleButton btn2 = new ViewButton("界面Y", ctx + "/toHello");
            buttons.add(btn2);

            SingleButton btn3 = new ViewButton("界面X", ctx + "/toHello");
            buttons.add(btn2);

            //设置子菜单
            System.out.println(menu.toJSONObject().toString());

            //创建自定义菜单
            Weixin weixin = weixinTemplate.getWeixinFactory().getWeixin();
            MenuComponent menu1 = weixin.menu();
            menu1.create(menu);
            model.addAttribute("message", "微信菜单创建成功");
        } catch (Exception e) {
//            log.error(e.getMessage());
            jsonData.put("code", -1);
            jsonData.put("message", "微信菜单创建失败，原因：" + e.getMessage());
        }

        return jsonData;
    }


    @RequestMapping("/test1")
    @ResponseBody
    public Map<String, Object> test1() {
//        log.info("WeixinController.test1");
        Map<String, Object> jsonData = new HashMap<String, Object>();
        System.out.println("test");
        String s1 = Configuration.getProperty("weixin4j.message.handler.normal");
        String s2 = Configuration.getProperty("weixin4j.message.handler.event");

        jsonData.put("weixin4j.message.handler.normal", s1);
        jsonData.put("weixin4j.message.handler.event", s2);
        return jsonData;
    }

    public static void main(String[] args) throws Exception {
        Weixin weixin = new Weixin();
        weixin.base().token();
        Token token = weixin.getToken();
        System.out.println(token);
    }

}
