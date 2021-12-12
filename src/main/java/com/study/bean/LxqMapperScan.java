package com.study.bean;
 
import org.springframework.context.annotation.Import;
 
import java.lang.annotation.*;
 
/**
 * @description: 自定义扫描注解
 * @author: Lxq
 * @date: 2020/7/13 9:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({ServiceBeanDefinitionRegistry.class})
public @interface LxqMapperScan {
 
    String value() default "";
}