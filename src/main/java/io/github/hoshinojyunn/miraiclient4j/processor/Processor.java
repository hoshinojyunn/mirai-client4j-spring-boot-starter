package io.github.hoshinojyunn.miraiclient4j.processor;

import io.github.hoshinojyunn.miraiclient4j.message.MessageEvent;

@FunctionalInterface
public interface Processor {
    void process(MessageEvent message, String cmd) throws Exception;
}
