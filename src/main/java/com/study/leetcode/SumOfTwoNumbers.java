package com.study.leetcode;

public class SumOfTwoNumbers {

    //两数之和 1.
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i <nums.length ; i++) {
            for (int j = i+1; j < nums.length ; j++) {
                if (target ==  nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0};
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 3,7,1};
        int[] ints = twoSum(a, 6);
        System.out.println(ints);
    }
}
