package com.cjh.wx01.weixin;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.Configuration;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;

import java.util.concurrent.TimeUnit;

@Component
public class RedisTokenLoader implements ITokenLoader {

    private static final Logger LOG = LoggerFactory.getLogger(RedisTokenLoader.class);

    private final String ACCESS_TOKEN_KEY = "ats_wx100000000001";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String appid;

    public RedisTokenLoader() {
        this.appid = Configuration.getOAuthAppId();//从配置文件中读取
    }

    @Override
    public Token get() {
        String accessToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
        LOG.info("wechat access_token:{}", accessToken);
        return JSON.parseObject(accessToken, Token.class);
    }

    @Override
    public void refresh(Token token) {
        LOG.info("refresh wechat access_token:{}", token.toString());
        String accessToken = JSON.toJSONString(token);
        //ticket.getExpires_in() - 600L，是为了提前10分钟过期
        stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, accessToken, token.getExpires_in() - 600L, TimeUnit.SECONDS);
    }
}