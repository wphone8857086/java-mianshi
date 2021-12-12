package com.study.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
//@Qualifier(value = "HelloServiceImpl1")
public class HelloServiceImpl1 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl1");
    }
}