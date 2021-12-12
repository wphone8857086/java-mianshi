package com.study.entity;

import java.lang.reflect.Field;

public class StringTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
//        String str = new String("abd");
//        Field s = str.getClass().getDeclaredField("value");
//        s.setAccessible(true);
//        s.set(str, "abcd".toCharArray());
//        System.out.println(str);
//        String s1 = new String("123456");
//        String ab = s1;
//        ab = "abnd";
//        System.out.println("s1:" + s1);

        String s = new String("1");
        s.intern();
        String s2 = "1";
        // JDK6 false
        // JDK8: false  new String("1")的时候 "1"已经加入常量池中。故s.intern()返回常量池中"1"的地址。但是s没有改变。还是指向堆空间
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");//执行完这行，常量池中没有“11”
        s3.intern();
        String s4 = "11";
        //JDK6: false
        //JDK8:true  s3.intern()前 常量池中没有"11",s3.intern()后 “11“加入常量池。返回常量池中地址。同时s3也指向常量池中地址
        System.out.println(s3 == s4);






    }
}
