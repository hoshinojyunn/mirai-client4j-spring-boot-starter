package io.github.hoshinojyunn.miraiclient4j.exception;

public class MessageTypeException extends Exception{

    public MessageTypeException() {
        super("消息类型错误");
    }

    public MessageTypeException(String message) {
        super(message);
    }

}
