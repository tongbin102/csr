package com.project.csr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project")
@MapperScan("com.project.*.dao")
public class CsrApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsrApiApplication.class, args);
    }

}
