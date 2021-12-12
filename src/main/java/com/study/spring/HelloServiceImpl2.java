package com.study.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Qualifier(value = "HelloServiceImpl2")
@Primary
public class HelloServiceImpl2 implements HelloService{

    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl2");
    }
}