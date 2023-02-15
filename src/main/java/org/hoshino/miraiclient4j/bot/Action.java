package org.hoshino.miraiclient4j.bot;

import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.Message;

public interface Action {
    void sendToFriend(FriendMessage message);
//    void sendToGroup(Gro message);
    void send(Message message);
}
