package org.hoshino.miraiclient4j.aspect.annotation;



import org.hoshino.miraiclient4j.config.ApplicationConfiguration;
import org.hoshino.miraiclient4j.config.BotInitConfiguration;
import org.hoshino.miraiclient4j.controller.MessageController;
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
