package com.study.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void list1() {
        //leftPushAll方法：表示把一个数组插入列表中。
        //rightPushAll方法：表示向列表的最右边批量添加元素
        String[] user1 = new String[]{"1", "pan_junbiao的博客", "您好，欢迎访问 pan_junbiao的博客"};
        String[] user2 = new String[]{"2", "pan_junbiao的博客", "https://blog.csdn.net/pan_junbiao"};
        String[] user3 = new String[]{"3", "pan_junbiao的博客", "您好，欢迎访问 pan_junbiao的博客"};

        redisTemplate.opsForList().rightPushAll("user1", user1);
        redisTemplate.opsForList().rightPushAll("user2", user2);
        redisTemplate.opsForList().rightPushAll("user3", user3);

        System.out.println(redisTemplate.opsForList().range("user1", 0, -1));
        System.out.println(redisTemplate.opsForList().range("user2", 0, -1));
        System.out.println(redisTemplate.opsForList().range("user3", 0, -1));
    }

    @Test
    public void list2() {
        //List 允许重复key存在
        //leftPush方法：将所有指定的值插入在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表（从左边插入）。
        //rightPush方法：将所有指定的值插入在键的列表的尾部。如果键不存在，则在执行推送操作之前将其创建为空列表（从右边插入）。
        redisTemplate.opsForList().rightPush("userInfo", 1);
        redisTemplate.opsForList().rightPush("userInfo", "pan_junbiao的博客");
        redisTemplate.opsForList().rightPush("userInfo", "https://blog.csdn.net/pan_junbiao");
        redisTemplate.opsForList().rightPush("userInfo", "您好，欢迎访问 pan_junbiao的博客");

        System.out.println("用户编号：" + redisTemplate.opsForList().index("userInfo", 0));
        System.out.println("用户名称：" + redisTemplate.opsForList().index("userInfo", 1));
        System.out.println("博客地址：" + redisTemplate.opsForList().index("userInfo", 2));
        System.out.println("博客信息：" + redisTemplate.opsForList().index("userInfo", 3));

    }

    @Test
    public void list3() {
        //返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。如果key存在的值不是列表，则返回错误
        String[] user = new String[]{"1", "pan_junbiao的博客", "您好，欢迎访问 pan_junbiao的博客"};
        redisTemplate.opsForList().leftPushAll("user", user);
        System.out.println("列表的长度：" + redisTemplate.opsForList().size("user"));
    }


    @Test
    public void list4() {
        //在列表中 index 的位置设置 value。
        redisTemplate.delete("user");
        String[] user = new String[]{"1", "pan_junbiao的博客", "https://blog.csdn.net/pan_junbiao", "test"};
        redisTemplate.opsForList().rightPushAll("user", user);
        System.out.println(redisTemplate.opsForList().range("user", 0, -1));

        redisTemplate.opsForList().set("user", 2, "您好，欢迎访问 pan_junbiao的博客");
        redisTemplate.opsForList().set("user", 3, "opsForList test");
        System.out.println(redisTemplate.opsForList().range("user", 0, -1));
    }

    @Test
    public void list5() {
        /*
        从存储在键中的列表，删除给定“count”值的元素的第1个计数事件。其中，参数count的含义如下：
        count=0：删除等于value的所有元素。
        count>0：删除等于从头到尾移动的值的元素 (删除个数为count的绝对值)
        count<0：删除等于从尾到头移动的值的元素 (删除个数为count的绝对值)
        */
        redisTemplate.delete("user");
        String[] user = new String[]{"1", "pan_junbiao的博客", "您好，欢迎访问 pan_junbiao的博客","pan_junbiao的博客","pan_junbiao的博客"};
        redisTemplate.opsForList().rightPushAll("user", user);
        System.out.println(redisTemplate.opsForList().range("user", 0, -1));

        //将删除列表中第一次出现的pan_junbiao的博客
        redisTemplate.opsForList().remove("user", 0, "pan_junbiao的博客");
        System.out.println(redisTemplate.opsForList().range("user", 0, -1));
    }


}
