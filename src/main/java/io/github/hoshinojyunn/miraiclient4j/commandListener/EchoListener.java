package io.github.hoshinojyunn.miraiclient4j.commandListener;

import io.github.hoshinojyunn.miraiclient4j.aspect.annotation.CommandListener;
import io.github.hoshinojyunn.miraiclient4j.aspect.annotation.OnCommand;
import io.github.hoshinojyunn.miraiclient4j.bot.Bot;
import io.github.hoshinojyunn.miraiclient4j.message.MessageChain;
import io.github.hoshinojyunn.miraiclient4j.message.MessageEvent;
import io.github.hoshinojyunn.miraiclient4j.message.baseType.Plain;
import io.github.hoshinojyunn.miraiclient4j.utils.MessageTemplate;

@CommandListener
public class EchoListener {
    private Bot bot;

    public EchoListener(Bot bot) {
        this.bot = bot;
    }

    @OnCommand(command = "/echo")
    public void echo(MessageEvent message) throws Exception {
        String body = MessageTemplate.parseBody(message);
        MessageChain messageChain = new MessageChain().appendLast(new Plain(body));
        bot.send(message, messageChain, true);
    }
}
