package com.study.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HashTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void hash1() {
        //putAll方法：用 m 中提供的多个散列字段设置到 key 对应的散列表中。
        //
        //entries方法：根据密钥获取整个散列存储。
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userName", "pan_junbiao的博客");
        userMap.put("blogRemark", "您好，欢迎访问 pan_junbiao的博客");
        redisTemplate.opsForHash().putAll("userHash", userMap);
        System.out.println(redisTemplate.opsForHash().get("userHash", "userName"));
        System.out.println(redisTemplate.opsForHash().entries("userHash"));
        redisTemplate.delete("userHash");
    }

    @Test
    public void hash2() {
        //put方法：设置 hashKey 的值。
        //
        //get方法：从键中的散列获取给定 hashKey 的值
        redisTemplate.opsForHash().put("userHash", "userName", "pan_junbiao的博客");
        redisTemplate.opsForHash().put("userHash", "blogUrl", "https://blog.csdn.net/pan_junbiao");
        redisTemplate.opsForHash().put("userHash", "blogRemark", "您好，欢迎访问 pan_junbiao的博客");
        System.out.println("用户名称：" + redisTemplate.opsForHash().get("userHash", "userName"));
        System.out.println("博客地址：" + redisTemplate.opsForHash().get("userHash", "blogUrl"));
        System.out.println("博客信息：" + redisTemplate.opsForHash().get("userHash", "blogRemark"));
        redisTemplate.delete("userHash");
    }

    @Test
    public void hash3() {

        //values方法：根据密钥获取整个散列存储的值。
        //keys方法：根据密钥获取整个散列存储的键。
        redisTemplate.opsForHash().put("userHash", "userName", "pan_junbiao的博客");
        redisTemplate.opsForHash().put("userHash", "blogRemark", "您好，欢迎访问 pan_junbiao的博客");
        System.out.println("散列存储的值：" + redisTemplate.opsForHash().values("userHash"));
        System.out.println("散列存储的键：" + redisTemplate.opsForHash().keys("userHash"));
        redisTemplate.delete("userHash");
    }


    @Test
    public void hash4() {
        //hasKey方法：确定 hashKey 是否存在。
        //size方法：获取 key 所对应的散列表的大小个数
        redisTemplate.opsForHash().put("userHash", "userName", "pan_junbiao的博客");
        redisTemplate.opsForHash().put("userHash", "blogUrl", "https://blog.csdn.net/pan_junbiao");
        redisTemplate.opsForHash().put("userHash", "blogRemark", "您好，欢迎访问 pan_junbiao的博客");
        System.out.println(redisTemplate.opsForHash().hasKey("userHash", "userName"));
        System.out.println(redisTemplate.opsForHash().hasKey("userHash", "age"));
        System.out.println(redisTemplate.opsForHash().size("userHash"));
        redisTemplate.delete("userHash");
    }

    @Test
    public void hash5() {
        //删除给定的 hashKeys
        redisTemplate.opsForHash().put("userHash", "userName", "pan_junbiao的博客");
        redisTemplate.opsForHash().put("userHash", "blogRemark", "您好，欢迎访问 pan_junbiao的博客");
        System.out.println(redisTemplate.opsForHash().delete("userHash", "blogRemark"));
        System.out.println(redisTemplate.opsForHash().entries("userHash"));
        redisTemplate.delete("userHash");
    }
}
