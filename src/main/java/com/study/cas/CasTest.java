package com.study.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasTest {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);

        System.out.println(integer.compareAndSet(5, 100) +" "+ integer.get()); // true 100

        System.out.println(integer.compareAndSet(5, 30) +" "+ integer.get());  //false 100
    }


}
