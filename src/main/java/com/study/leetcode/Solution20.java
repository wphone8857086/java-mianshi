package com.study.leetcode;

import java.util.*;

public class Solution20 {
    /**
     * 算法原理
     *
     * 栈先入后出特点恰好与本题括号排序特点一致，即若遇到左括号入栈，遇到右括号时将对应栈顶左括号出栈，
     * 则遍历完所有括号后 stack 仍然为空；建立哈希表 dic 构建左右括号对应关系：key 右括号，
     * value 左括号；这样查询 2个括号是否对应只需 O(1) 时间复杂度；建立栈 stack，
     * 遍历字符串 并按照算法流程一一判断。
     *
     */
    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        //map 里面key为右括号，用来判断右括号入栈，栈中是否有与之value 对应得左括号。
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        // ]{ }])
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            //左括号会先入栈
            if (map.containsKey(charAt)) {
                //stack.isEmpty() 是用来判断字符开头为有括号的情况，这种情况下
                //map中含有右括号的key,但是栈中无数据。
                //stack.peek() 为栈中首节点，判断当前字符key中value是否与栈中首节点组成成对括号。
                if (stack.isEmpty() || stack.peek() != map.get(charAt)) {
                    return false;
                }
                //满足条件，从栈中弹出首节点，当前字符不入栈。
                stack.pop();
            } else {
                //map 中不存在即为左括号字符，进行入栈处理。
                stack.push(charAt);
            }
        }


        return stack.isEmpty();
    }

    public static void main(String[] args) {
        //
        String str = "()";
        isValid(str);
    }
}
