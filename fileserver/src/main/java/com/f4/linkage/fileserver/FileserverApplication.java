package com.f4.linkage.fileserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileserverApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileserverApplication.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(FileserverApplication.class, args);
        String[] names = context.getBeanDefinitionNames();
        for(int i = 0; i < names.length; i++){
            LOGGER.info("name " + i + " is " + names[i]);
        }
    }

}
