package org.hoshino.miraiclient4j.bot;

import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.baseType.BaseType;
import org.hoshino.miraiclient4j.message.MessageEvent;

public interface Action {

    void send(MessageEvent event, BaseType... message);
    void send(MessageEvent event, MessageChain chain);
}
