package com.cjh.wx01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weixin4j.spring.web.WeixinJieruController;

/**
 * 微信开发者接入
 */
@Controller
@RequestMapping("/weixin/jieru")
public class JieruController extends WeixinJieruController {
}