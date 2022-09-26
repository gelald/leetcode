package com.example.leetcode.subject;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <br>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <br>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <br>
 *
 * @author WuYingBin
 * date: 2022/9/26
 */
public class M02AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        printList(l1);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        printList(l2);
        System.out.println();
        printList(addTwoNumbers(l1, l2));
        System.out.println();
        System.out.println();
        ListNode l3 = new ListNode(7);
        l3.next = new ListNode(8);
        l3.next.next = new ListNode(9);
        printList(l3);
        ListNode l4 = new ListNode(3);
        l4.next = new ListNode(2);
        printList(l4);
        System.out.println();
        printList(addTwoNumbers(l3, l4));
    }

    public static void printList(ListNode listNode) {
        System.out.print("[");
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
            if (listNode != null) {
                System.out.print("-");
            }
        }
        System.out.print("]");
    }

    /**
     * 时间复杂度：O(max(m,n))，m和n代表两个链表的长度，长度为m代表需要迭代m次
     * 空间复杂度：O(1)，返回值不计算的话就没有产生中间存储
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 定义头节点
        ListNode pre = new ListNode(0);
        // 定义链表
        ListNode cur = pre;
        // 记录是否需要进位
        int carry = 0;
        // 直到两个链表都空了，才结束遍历
        while (l1 != null || l2 != null) {
            // 如果这一位是空，那么当0处理
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            // 求和 1、2链表再加上上一轮的进位
            int sum = a + b + carry;

            // 记录本轮的进位
            carry = sum / 10;
            // 如果有进位，那么这个和就要减去10
            sum = sum % 10;
            // 比如16，carry=1，sum=6

            cur.next = new ListNode(sum);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 如果两个链表都遍历完，还需要进位，那么再在前面加一个1节点
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

}
