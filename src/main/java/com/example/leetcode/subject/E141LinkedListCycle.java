package com.example.leetcode.subject;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <br/>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
 * <br/>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <br/>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <br/>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/7
 */
public class E141LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode initLinkedList() {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        return head;
    }

    public static ListNode initCycleList() {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = head;
        return head;
    }

    public static void main(String[] args) {
        ListNode linkedList = initLinkedList();
        ListNode cycleList = initCycleList();
        System.out.println(hasCycle1(linkedList));
        System.out.println(hasCycle1(cycleList));
        System.out.println(hasCycle2(linkedList));
        System.out.println(hasCycle2(cycleList));
    }

    /**
     * 借助Hash来判断是否存在相同的节点
     * 因为如果存在环,那么一直往后迭代一定会迭代到一个相同的节点
     * <br/>
     * 时间：O(n)  3ms  22.40%
     * 内存：O(n)  42.2M 63.68%
     * <br/>
     *
     * @param head 链表头节点
     * @return 是否存在环
     */
    public static boolean hasCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            } else {
                head = head.next;
            }
        }
        return false;
    }

    /**
     * 借助快慢指针来判断
     * 快指针:每次往后走两个节点
     * 慢指针:每次往后走一个节点
     * 如果存在环,那么两个指针一定会相遇
     * <br/>
     * 时间：O(n)  0ms  100%
     * 内存：O(1)  42.4M 46.85%
     * 常量级内存是因为只使用了两个指针
     * <br/>
     *
     * @param head 链表头节点
     * @return 是否存在环
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}
