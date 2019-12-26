package com.zking.wx01;

import io.lettuce.core.ScriptOutputType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinConfig;
import org.weixin4j.component.BaseComponent;
import org.weixin4j.loader.ITicketLoader;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;
import org.weixin4j.model.js.Ticket;
import org.weixin4j.spi.IEventMessageHandler;
import org.weixin4j.spi.INormalMessageHandler;
import org.weixin4j.spring.WeixinTemplate;

public class WeixinTest extends  BaseTest {

    @Autowired
    private WeixinTemplate weixinTemplate;

    @Autowired
    private Weixin weixin;

    @Autowired
    private ITokenLoader tokenLoader;

    @Autowired
    private ITicketLoader ticketLoader;

    @Autowired
    private IEventMessageHandler eventMessageHandler;

    @Autowired
    private INormalMessageHandler normalMessageHandler;


    @Test
    public void test1() throws  Exception{
        System.out.println("xxxxx");
        System.out.println(tokenLoader);
        System.out.println(ticketLoader);
        System.out.println(eventMessageHandler);
        System.out.println(normalMessageHandler);
    }

    @Test
    public void testToken() throws  Exception{
        Token token = weixin.getToken();
        System.out.println(token);

        //Ticket jsApiTicket = weixin.getJsApiTicket();
        //System.out.println(jsApiTicket);

        WeixinConfig weixinConfig = weixin.getWeixinConfig();
    }


    @Test
    public void test3() throws Exception{
        //BaseComponent base = weixinTemplate.base();
        //Token token = base.token();
        //System.out.println(token.getAccess_token());

        Weixin weixin = weixinTemplate.getWeixinFactory().getWeixin();
        Token token = weixin.getToken();
        System.out.println(token.getAccess_token());
    }
}
