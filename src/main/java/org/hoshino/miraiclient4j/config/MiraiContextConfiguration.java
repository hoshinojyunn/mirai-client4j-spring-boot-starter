package org.hoshino.miraiclient4j.config;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.constant.MiraiURL;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.properties.MiraiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(MiraiProperties.class)
public class MiraiContextConfiguration {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Bean
    public MiraiURL miraiURL(){
        return new MiraiURL();
    }

    @Bean
    @DependsOn({"applicationContextHolder"})
    public MiraiContext miraiContext(MiraiProperties properties){
        MiraiContext miraiContext = new MiraiContext();
        miraiContext.setVerifyKey(properties.getVerifyKey());
        miraiContext.setQq(properties.getQq());
        return miraiContext;
    }

}
