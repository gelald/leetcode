package com.example.leetcode.subject;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * <br/>
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <br/>
 * 比如：
 * headA:[1,2,3,4,5,6]
 * headB:[9,5,6]
 * 相交节点是5
 *
 * @author WuYingBin
 * date: 2022/4/11
 */
public class E160IntersectionOfTwoLinkedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {

        ListNode commonNode = new ListNode(8);
        commonNode.next = new ListNode(4);
        commonNode.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = commonNode;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = commonNode;

        System.out.println(headA);
        System.out.println(headB);
        System.out.println(getIntersectionNode1(headA, headB).val);
        System.out.println(getIntersectionNode2(headA, headB).val);
    }

    /**
     * A和B同时遍历
     * A遍历完就遍历B，B遍历完就遍历A
     * 因为 headA.length + headB.length = headB.length + headA.length
     * 所以如果是有相交的，最终一定会在那个相交点相遇
     * 1234569[56]
     * 9561234[56]
     * <br/>
     * 时间: O(m+n) m=headA.length,n=headB.length，因为需要分别遍历AB链表 1ms  98.66%
     * 内存: O(1)  43.9MB  70.75%
     * <br/>
     *
     * @param headA A链表
     * @param headB B链表
     * @return 相交点
     */
    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode hA = headA, hB = headB;
        while (hA != hB) {
            hA = hA == null ? headB : hA.next;
            hB = hB == null ? headA : hB.next;
        }
        return hA;
    }

    /**
     * 哈希法
     * 把A链表所有节点放入HashSet
     * 把B链表放入HashSet时，逐一检查是否已经包含了当前节点
     * <br/>
     * 时间: O(m+n) m=headA.length,n=headB.length，因为需要分别遍历AB链表  7ms  11.83%
     * 内存: O(m) m=headA.length，因为至少需要存储A链表的所有节点  43.9MB  69.91%
     * <br/>
     *
     * @param headA A链表
     * @param headB B链表
     * @return 相交点
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode hA = headA, hB = headB;
        Set<ListNode> set = new HashSet<>();
        while (hA != null) {
            set.add(hA);
            hA = hA.next;
        }
        while (hB != null) {
            if (set.contains(hB)) {
                return hB;
            }
            set.add(hB);
            hB = hB.next;
        }
        return null;
    }
}
