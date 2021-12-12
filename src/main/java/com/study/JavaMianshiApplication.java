package com.study;

import com.study.bean.LxqMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@LxqMapperScan("com.study.bean")
public class JavaMianshiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMianshiApplication.class, args);
    }

}
