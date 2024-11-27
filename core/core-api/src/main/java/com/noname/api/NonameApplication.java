package com.noname.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.noname.domain",
        "com.noname.maindb",
        "com.noname.api"
})
@SpringBootApplication
public class NonameApplication {

    public static void main(String[] args) {
        SpringApplication.run(NonameApplication.class, args);

    }
}
