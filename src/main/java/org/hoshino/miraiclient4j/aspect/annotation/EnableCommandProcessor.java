package org.hoshino.miraiclient4j.aspect.annotation;

import org.hoshino.miraiclient4j.config.CommandProcessorSupport;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(CommandProcessorSupport.class)
@Inherited
public @interface EnableCommandProcessor {
}
