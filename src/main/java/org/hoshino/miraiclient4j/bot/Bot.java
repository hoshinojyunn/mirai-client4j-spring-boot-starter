package org.hoshino.miraiclient4j.bot;


import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.bot.strategy.StrategyContext;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.baseType.At;
import org.hoshino.miraiclient4j.message.baseType.BaseType;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.hoshino.miraiclient4j.utils.MessageTemplate;
import org.hoshino.miraiclient4j.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot implements Action{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private Long qq;
    private HttpApiClient client;


    @Override
    public void send(MessageEvent event, BaseType[]message, Boolean atSender) {
        MessageChain chain = new MessageChain();
        for (BaseType baseType : message) {
            chain.appendLast(baseType);
        }
        send(event, chain, atSender);
    }

    @Override
    public void send(MessageEvent event, MessageChain chain, Boolean atSender) {
        String messageType = MessageTemplate.getMessageType(event);
        Long id = MessageTemplate.getId(event);
        LOGGER.info("发送消息链:{}", chain.toString());
        Long senderId = MessageTemplate.getSenderId(event);
        if(atSender){
            chain.appendFirst(new At(senderId));
        }
        doStrategy(chain, messageType, id);
    }

    private void doStrategy(MessageChain chain, String messageType, Long id) {
        R<JSONObject> resp = null;
        StrategyContext strategyContext = new StrategyContext(messageType, chain, client, id);
        resp = strategyContext.doExecute();
        // 执行情况
        if(resp!=null&&resp.getData().getInt("code")!=0){
            LOGGER.error("code:{}, msg:{}", resp.getData().getInt("code"), resp.getData().getStr("msg"));
        }
    }

    public Bot() {
    }

    public Bot(Long qq) {
        this.qq = qq;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public HttpApiClient getClient() {
        return client;
    }

    public void setClient(HttpApiClient client) {
        this.client = client;
    }
}
