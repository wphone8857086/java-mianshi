package com.study.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MaoPao {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private static void maopao(int[] a) {
        int temp;
        for (int i = 0; i < a.length; i++) {
            //a[j + 1],未避免数组下标越界，再循环中 j < length-1
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.add("1");
        Map map = new HashMap<>();
        map.put("1","1");
        int[] a = {5, 3, 8, 6, 9,4,11,2};
        maopao(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        int i = "asdazw15q1fw".hashCode();
        System.out.println(i);
        System.out.println( 15 & i);
        System.out.println(i & 15 );


    }

}
