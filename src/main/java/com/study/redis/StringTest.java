package com.study.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void string1() {
        redisTemplate.opsForValue().set("userName", "pan_junbiao的博客");
        redisTemplate.opsForValue().set("blogUrl", "https://blog.csdn.net/pan_junbiao");
        redisTemplate.opsForValue().set("blogRemark", "您好，欢迎访问 pan_junbiao的博客");
        System.out.println(redisTemplate.opsForValue().get("userName"));
        System.out.println(redisTemplate.opsForValue().get("blogUrl"));
        System.out.println(redisTemplate.opsForValue().get("blogRemark"));
    }

    @Test
    public void string2() {
        //设置的是3s失效，3s之内查询有结果，3s之后返回null
        redisTemplate.opsForValue().set("blogRemark", "您好，欢迎访问 pan_junbiao的博客", 3, TimeUnit.SECONDS);
        try {
            Object s1 = redisTemplate.opsForValue().get("blogRemark");
            System.out.println("博客信息：" + s1);
            Thread.currentThread().sleep(2000);

            Object s2 = redisTemplate.opsForValue().get("blogRemark");
            System.out.println("博客信息：" + s2);
            Thread.currentThread().sleep(1000);

            Object s3 = redisTemplate.opsForValue().get("blogRemark");
            System.out.println("博客信息：" + s3);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Test
    public void string3() {
        //设置键的字符串并返回其旧值
        redisTemplate.opsForValue().set("blogRemark", "pan_junbiao的博客");
        Object oldVaule = redisTemplate.opsForValue().getAndSet("blogRemark", "您好，欢迎访问 pan_junbiao的博客");
        Object newVaule = redisTemplate.opsForValue().get("blogRemark");
        System.out.println("旧值：" + oldVaule);
        System.out.println("新值：" + newVaule);
    }

    @Test
    public void string4() {
        redisTemplate.delete("blogRemark");
        //设置value的序列化规则，否则会报错
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.opsForValue().append("blogRemark", "您好，欢迎访问 ");
        System.out.println(redisTemplate.opsForValue().get("blogRemark"));
        redisTemplate.opsForValue().append("blogRemark", "pan_junbiao的博客");
        System.out.println(redisTemplate.opsForValue().get("blogRemark"));
    }

    @Test
    public void string5() {
        redisTemplate.opsForValue().set("userName", "pan_junbiao的博客");
        System.out.println("Value值：" + redisTemplate.opsForValue().get("userName"));
        System.out.println("Value值的长度：" + redisTemplate.opsForValue().size("userName"));
    }


}
