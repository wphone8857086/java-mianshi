package com.study.entity;

import java.util.*;

public class EqualsTest {
        public static void main(String[] args) {
            Student s1 =new Student();
            s1.setAddr("1111");
            s1.setAge("20");
            s1.setName("allan");
            s1.setSex("male");
            s1.setWeight(60f);
            Student s2 =new Student();
            s2.setAddr("222");
            s2.setAge("20");
            s2.setName("allan");
            s2.setSex("male");
            s2.setWeight(70f);
            if(s1.equals(s2)) {
                System.out.println("s1==s2");
            }else {
                System.out.println("s1 != s2");
            }

            System.out.println( "s1 == s2:" + (s1 == s2));
            //测试 hashset
            Set<Student> set = new HashSet<>();
            set.add(s1);
            set.add(s2);
            for (Student student : set) {
                System.out.println(student.getAddr());
            }
//            System.out.println(set.);

            //测试hashmap
            Map m = new HashMap();
            m.put(s1, s1); //s1 hashCode 1136823723
            m.put(s2, s2);  //s2 hashCode 1136823723
            //hash(key) 调用的是 key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
            //也就是刚才重写 student hashCode

            //因为两个key hashCode 相同，故而对key进行 == 以及 .equals() 比较，这里就会用到刚才 student 重新的 equals 方法
            //if (p.hash == hash &&
            //                ((k = p.key) == key || (key != null && key.equals(k))))
            System.out.println(m);
            System.out.println(((Student)m.get(s1)).getAddr());

            ArrayList<Student> students = new ArrayList<>();
            students.sort(s1);
            TreeSet<Object> objects = new TreeSet<>(s1);

        }
}
