package org.hoshino.miraiclient4j.commandListener;

import org.hoshino.miraiclient4j.aspect.annotation.CommandListener;
import org.hoshino.miraiclient4j.aspect.annotation.OnCommand;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.baseType.Image;
import org.hoshino.miraiclient4j.message.baseType.Plain;
import org.hoshino.miraiclient4j.message.MessageEvent;

import javax.annotation.Resource;

@CommandListener
public class ImageListener {
    @Resource
    private Bot bot;

    @OnCommand(command = "/image")
    public void sendImage(MessageEvent event){
        MessageChain messageChain = new MessageChain();
        messageChain.append(new Plain("你要的图片")).append(new Image("https://tse1-mm.cn.bing.net/th/id/OIP-C.YKoZzgmubNBxQ8j-mmoTKAHaEK?pid=ImgDet&rs=1"));
        bot.send(event, messageChain);
    }
}
