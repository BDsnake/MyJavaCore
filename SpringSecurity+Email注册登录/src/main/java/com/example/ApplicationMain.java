package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BDsnake
 * @since 2023/4/11 22:48
 */
@SpringBootApplication
@MapperScan("com.example.dao")
public class ApplicationMain {
     public static void main(String[] args) {
           SpringApplication.run(ApplicationMain.class, args);
      }

}
