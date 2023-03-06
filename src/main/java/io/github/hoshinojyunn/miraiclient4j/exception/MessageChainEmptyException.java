package io.github.hoshinojyunn.miraiclient4j.exception;

public class MessageChainEmptyException extends Exception{

    public MessageChainEmptyException(){
        super("消息链为空");
    }

    public MessageChainEmptyException(String message) {
        super(message);
    }
}
