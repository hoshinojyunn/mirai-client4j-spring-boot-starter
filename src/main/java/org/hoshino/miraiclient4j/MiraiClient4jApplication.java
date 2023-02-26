package org.hoshino.miraiclient4j;

import org.hoshino.miraiclient4j.aspect.annotation.EnableMiraiClient4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableMiraiClient4j
public class MiraiClient4jApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MiraiClient4jApplication.class, args);
    }
}
