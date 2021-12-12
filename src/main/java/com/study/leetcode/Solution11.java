package com.study.leetcode;

public class Solution11 {

    /**
     * 设两指针 ii , jj ，指向的水槽板高度分别为 h[i]h[i] , h[j]h[j] ，此状态下水槽面积为 S(i, j)S(i,j) 。
     * 由于可容纳水的高度由两板中的 短板 决定，因此可得如下 面积公式 ：S(i,j)=min(h[i],h[j])×(j−i)
     * 初始化： 双指针 i , j 分列水槽左右两端；
     * 循环收窄： 直至双指针相遇时跳出；
     * 更新面积最大值 max ；
     * 选定两板高度中的短板，向中间收窄一格；
     * 返回值： 返回面积最大值 max 即可；
     */

    public int maxArea(int[] height) {
        int i = 0,max  = 0;
        int j = height.length - 1;
        while (i < j) {
            max = height[i] < height[j] ? Math.max(max, (j - i) * height[i++]) :
                    Math.max(max, (j - i) * height[j--]);
        }
        return max;
    }
}
