package com.study.generics;

import java.util.ArrayList;
import java.util.List;

public class Generic01 {
    static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        countLegs(dogs);
        // 报错
//        countLegs1(dogs);
    }
}
