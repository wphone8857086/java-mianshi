package com.study.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JianzhiOffer03 {
    //找出数组中重复的数字。
    //输入：
    //        [2, 3, 1, 0, 2, 5, 3]
    //输出：2 或 3
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(set.contains(num)){
                return num;
            }else {
                set.add(num);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(a));
    }

}
