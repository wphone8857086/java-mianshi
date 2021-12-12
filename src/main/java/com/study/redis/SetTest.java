package com.study.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set1() {
        //add方法：在无序集合中添加元素，返回添加个数；如果存在重复的则不进行添加。
        //members方法：返回集合中的所有成员。
        redisTemplate.delete("citySet");
        String[] citys = new String[]{"北京", "上海", "广州", "深圳"};
        System.out.println(redisTemplate.opsForSet().add("citySet", citys));
        System.out.println(redisTemplate.opsForSet().add("citySet", "香港", "澳门", "台湾", "北京"));
        //返回集合中的所有元素
        System.out.println(redisTemplate.opsForSet().members("citySet"));
    }


    @Test
    public void set2() {
        //移除集合中一个或多个成员。
        String[] citys = new String[]{"北京", "上海", "广州", "深圳"};
        System.out.println(redisTemplate.opsForSet().add("citySet", citys));
        redisTemplate.opsForSet().remove("citySet", "深圳");
        System.out.println(redisTemplate.opsForSet().members("citySet"));

        System.out.println(redisTemplate.opsForSet().remove("citySet", citys));
    }

    @Test
    public void set3() {
        //移除并返回集合中的一个随机元素。
        redisTemplate.delete("citySet");
        String[] citys = new String[]{"北京", "上海", "广州", "深圳"};
        System.out.println(redisTemplate.opsForSet().add("citySet", citys));
        System.out.println(redisTemplate.opsForSet().pop("citySet"));
        System.out.println(redisTemplate.opsForSet().members("citySet"));
    }

    @Test
    public void set4() {
        //将 member 元素移动并赋值给另一个key
        redisTemplate.delete("citySet");
        String[] citys = new String[]{"北京", "上海", "广州", "深圳"};
        String[] citys2 = new String[]{"北京", "上海", "广州"};
        System.out.println(redisTemplate.opsForSet().add("citySet", citys));
        System.out.println(redisTemplate.opsForSet().add("citySet2", citys2));
        System.out.println(redisTemplate.opsForSet().move("citySet", "深圳", "citySet2"));
        System.out.println(redisTemplate.opsForSet().members("citySet"));
        System.out.println(redisTemplate.opsForSet().members("citySet2"));
    }


    @Test
    public void set5() {
        //用于遍历 Set。
        redisTemplate.delete("citySet");
        String[] citys = new String[]{"北京", "上海", "广州", "深圳"};
        System.out.println(redisTemplate.opsForSet().add("citySet", citys));
        Cursor<Object> cursor = redisTemplate.opsForSet().scan("citySet", ScanOptions.NONE);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    @Test
    public void set6() {
        redisTemplate.delete("citySet1");
        redisTemplate.delete("citySet2");
        //Set<V> intersect(K key1, K key2)方法、Long intersectAndStore(K key1, K key2, K destKey)方法：交集。
        //Set<V> union(K key1, K key2)方法、Long unionAndStore(K key1, K key2, K destKey)方法：并集。
        //Set<V> difference(K key1, K key2)方法、Long differenceAndStore(K key1, K key2, K destKey)方法：差集。
        String[] city1 = new String[]{"北京", "深圳", "昆明", "上海", "广州"};
        String[] city2 = new String[]{"北京", "深圳", "昆明", "成都"};
        System.out.println(redisTemplate.opsForSet().add("citySet1", city1));
        System.out.println(redisTemplate.opsForSet().add("citySet2", city2));

        //返回集合中的所有元素
        System.out.println("城市集合1：" + redisTemplate.opsForSet().members("citySet1"));
        System.out.println("城市集合2：" + redisTemplate.opsForSet().members("citySet2"));

        //求交集、并集、差集（方式一）
        System.out.println("求交集、并集、差集（方式一）:");
        System.out.println("交集：" + redisTemplate.opsForSet().intersect("citySet1", "citySet2"));
        System.out.println("并集：" + redisTemplate.opsForSet().union("citySet1", "citySet2"));
        System.out.println("差集 1 d 2：" + redisTemplate.opsForSet().difference("citySet1", "citySet2"));
        System.out.println("差集 2 d 1" + redisTemplate.opsForSet().difference("citySet2", "citySet1"));

        //求交集、并集、差集（方式二）
        redisTemplate.opsForSet().intersectAndStore("citySet1", "citySet2", "intersectCity");
        redisTemplate.opsForSet().unionAndStore("citySet1", "citySet2", "unionCity");
        redisTemplate.opsForSet().differenceAndStore("citySet1", "citySet2", "differenceCity");
        System.out.println("求交集、并集、差集（方式二）:");
        System.out.println("交集：" + redisTemplate.opsForSet().members("intersectCity"));
        System.out.println("并集：" + redisTemplate.opsForSet().members("unionCity"));
        System.out.println("差集：" + redisTemplate.opsForSet().members("differenceCity"));
    }

}
