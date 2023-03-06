package io.github.hoshinojyunn.miraiclient4j.aspect.annotation;



import io.github.hoshinojyunn.miraiclient4j.config.ApplicationConfiguration;
import io.github.hoshinojyunn.miraiclient4j.config.BotInitConfiguration;
import io.github.hoshinojyunn.miraiclient4j.controller.MessageController;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({BotInitConfiguration.class, ApplicationConfiguration.class, MessageController.class})
public @interface EnableMiraiClient4j {
}
