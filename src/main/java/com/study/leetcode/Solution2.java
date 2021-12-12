package com.study.leetcode;

public class Solution2 {
    
     public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode();
        ListNode head = p;
        int carry = 0;
        while (l1 != null && l2 != null) {
            p.next = new ListNode();
            p = p.next;
            int num = l1.val + l2.val + carry;
            p.val = num % 10;
            carry = num / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            p.next = new ListNode();
            p = p.next;
            int num = l1.val + carry;
            p.val = num % 10;
            carry = num / 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            p.next = new ListNode();
            p = p.next;
            int num = l2.val + carry;
            p.val = num % 10;
            carry = num / 10;
            l2 = l2.next;
        }
        if (carry != 0) {
            p.next = new ListNode();
            p = p.next;
            p.val = carry;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(2,l1);
        ListNode listNode = new ListNode(1, l2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(5,l3);
        ListNode listNode2 = new ListNode(6, l4);
        addTwoNumbers(listNode, listNode2);

    }


}
