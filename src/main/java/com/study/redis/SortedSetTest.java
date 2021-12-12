package com.study.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortedSetTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void Zset1() {
        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<>("pan_junbiao的博客_01", 9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<>("pan_junbiao的博客_02", 1.5);
        ZSetOperations.TypedTuple<String> objectTypedTuple3 = new DefaultTypedTuple<>("pan_junbiao的博客_03", 7.4);

        Set<ZSetOperations.TypedTuple<String>> typles = new HashSet<>();
        typles.add(objectTypedTuple1);
        typles.add(objectTypedTuple2);
        typles.add(objectTypedTuple3);
        redisTemplate.opsForZSet().add("typles", "pan_junbiao的博客_04", 3.5);

        System.out.println(redisTemplate.opsForZSet().add("typles", typles));
        System.out.println(redisTemplate.opsForZSet().range("typles", 0, -1));
        redisTemplate.opsForZSet().incrementScore("typles", "pan_junbiao的博客_04", 5.5);
        Cursor<ZSetOperations.TypedTuple<String>> cursor = redisTemplate.opsForZSet().scan("typles", ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<String> item = cursor.next();
            System.out.println(item.getValue() + " 的分数值：" + item.getScore());
        }
        System.out.println(redisTemplate.opsForZSet().rangeByScore("typles", 9, 9));
    }

    @Test
    public void Zset3() {
        //从有序集合中移除一个或者多个元素。
        System.out.println(redisTemplate.opsForZSet().add("zset3", "pan_junbiao的博客_01", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset3", "pan_junbiao的博客_02", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset3", "pan_junbiao的博客_03", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset3", "pan_junbiao的博客_04", 1.0));
        System.out.println(redisTemplate.opsForZSet().remove("zset3", "pan_junbiao的博客_02"));
        System.out.println(redisTemplate.opsForZSet().range("zset3", 0, -1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset3", 0, 1));
        System.out.println(redisTemplate.opsForZSet().range("zset3", 0, -1));
        redisTemplate.delete("zset3");
    }

    @Test
    public void Zset4() {
        //返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列。
        System.out.println(redisTemplate.opsForZSet().add("zset4", "pan_junbiao的博客_01", 9.6));
        System.out.println(redisTemplate.opsForZSet().add("zset4", "pan_junbiao的博客_02", 11.5));
        System.out.println(redisTemplate.opsForZSet().add("zset4", "pan_junbiao的博客_03", 7.4));
        System.out.println(redisTemplate.opsForZSet().range("zset4", 0, -1));
        System.out.println(redisTemplate.opsForZSet().rank("zset4", "pan_junbiao的博客_02"));
    }

    @Test
    public void Zset5() {

        //range方法：通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列。
        //rangeByScore方法：通过分数区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列。
        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<>("pan_junbiao的博客_01", 9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<>("pan_junbiao的博客_02", 1.5);
        ZSetOperations.TypedTuple<String> objectTypedTuple3 = new DefaultTypedTuple<>("pan_junbiao的博客_03", 7.4);

        Set<ZSetOperations.TypedTuple<String>> typles = new HashSet<ZSetOperations.TypedTuple<String>>();
        typles.add(objectTypedTuple1);
        typles.add(objectTypedTuple2);
        typles.add(objectTypedTuple3);

        System.out.println(redisTemplate.opsForZSet().add("zset5", typles));
        System.out.println(redisTemplate.opsForZSet().range("zset5", 0, -1));
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset5", 0, 8));
    }

    @Test
    public void Zset6() {
        //count方法：通过分数返回有序集合指定区间内的成员个数。
        //size方法：获取有序集合的成员数。
        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<>("pan_junbiao的博客_01", 9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<>("pan_junbiao的博客_02", 1.5);
        ZSetOperations.TypedTuple<String> objectTypedTuple3 = new DefaultTypedTuple<>("pan_junbiao的博客_03", 7.4);

        Set<ZSetOperations.TypedTuple<String>> typles = new HashSet<ZSetOperations.TypedTuple<String>>();
        typles.add(objectTypedTuple1);
        typles.add(objectTypedTuple2);
        typles.add(objectTypedTuple3);

        redisTemplate.opsForZSet().add("zset6", typles);
        System.out.println("分数在0至8区间内的成员个数：" + redisTemplate.opsForZSet().count("zset6", 0, 8));
        System.out.println("有序集合的成员数：" + redisTemplate.opsForZSet().size("zset6"));
    }

    @Test
    public void Zset7() {
        //获取指定成员的score值
        redisTemplate.opsForZSet().add("zset7", "pan_junbiao的博客_01", 9.6);
        redisTemplate.opsForZSet().add("zset7", "pan_junbiao的博客_02", 1.5);
        redisTemplate.opsForZSet().add("zset7", "pan_junbiao的博客_03", 7.4);

        System.out.println("pan_junbiao的博客_01的分数：" + redisTemplate.opsForZSet().score("zset7", "pan_junbiao的博客_01"));
        System.out.println("pan_junbiao的博客_02的分数：" + redisTemplate.opsForZSet().score("zset7", "pan_junbiao的博客_02"));
        System.out.println("pan_junbiao的博客_03的分数：" + redisTemplate.opsForZSet().score("zset7", "pan_junbiao的博客_03"));
    }

    @Test
    public void Zset8() {
        //移除指定索引位置的成员，有序集合成员按照分数值递增(从小到大)顺序排列。
        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<>("pan_junbiao的博客_01", 9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<>("pan_junbiao的博客_02", 1.5);
        ZSetOperations.TypedTuple<String> objectTypedTuple3 = new DefaultTypedTuple<>("pan_junbiao的博客_03", 7.4);

        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);

        System.out.println(redisTemplate.opsForZSet().add("zset8", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset8", 0, -1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset8", 1, 5));
        System.out.println(redisTemplate.opsForZSet().range("zset8", 0, -1));
    }

    @Test
    public void Zset9() {
        //遍历 zset。
        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<>("pan_junbiao的博客_01", 9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<>("pan_junbiao的博客_02", 1.5);
        ZSetOperations.TypedTuple<String> objectTypedTuple3 = new DefaultTypedTuple<>("pan_junbiao的博客_03", 7.4);

        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<ZSetOperations.TypedTuple<String>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);

        System.out.println(redisTemplate.opsForZSet().add("zset9", tuples));
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("zset9", ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue() + " 的分数值：" + item.getScore());
        }
    }
}
