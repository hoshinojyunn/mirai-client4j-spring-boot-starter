package org.hoshino.miraiclient4j.applicationListener;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public class ApplicationShutdownListener implements ApplicationListener<ContextClosedEvent> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private HttpApiClient httpApiClient;
    private Bot bot;

    public ApplicationShutdownListener(HttpApiClient httpApiClient, Bot bot) {
        this.httpApiClient = httpApiClient;
        this.bot = bot;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        LOGGER.info("mirai client closed,application will be destroyed soon");
        R<JSONObject> resp = httpApiClient.releaseSession(bot.getQq());
        if(resp.getData().getInt("code") == 0)
            LOGGER.info("session release completed");
        else
            LOGGER.error("something went wrong when contextClosedEvent tried to release session!");
    }
}
