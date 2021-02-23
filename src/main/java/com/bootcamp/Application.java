package com.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  {
    public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
        
        // ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        // com.bootcamp.config.AppConfiguration conf = context.getBean(com.bootcamp.config.AppConfiguration.class);
        // System.out.println(conf);
       
    }
}
