package com.example.leetcode.subject;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <br/>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <br/>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/24
 */
public class E21MergeTwoSortedLists {

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
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.print("两条链表第一条: ");
        printList(l1);
        System.out.print("\n两条链表第二条: ");
        printList(l2);
        ListNode result1 = mergeTwoLists(l1, l2);
        System.out.print("\n合并后的链表: ");
        printList(result1);
        // ===================================
        ListNode l3 = null;
        ListNode l4 = new ListNode(5);
        System.out.print("\n两条链表第一条: ");
        printList(l3);
        System.out.print("\n两条链表第二条: ");
        printList(l4);
        ListNode result2 = mergeTwoLists(l3, l4);
        System.out.print("\n合并后的链表: ");
        printList(result2);
    }

    /**
     * 递归做法
     * 递归终止条件: 其中一个链表为空时，即可结束递归
     * 递归内容: 比较list1和list2的结点值，较小的结点的next指向其余结点的合并结果
     * <br/>
     * 时间: O(m+n) 0ms 100%
     * 内存: O(m+n) 40.6M 83.88%
     * <br/>
     *
     * @param list1 list1链表
     * @param list2 list2链表
     * @return 合并结果
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 如果list1是空，那么后面直接拼接list2
        if (list1 == null) {
            return list2;
        }
        // 如果list2是空，那么后面直接拼接list1
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            // 如果list1的结点值较小，那么它的next指向其它结点
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            // 如果list2的结点值较小，那么它的next指向其它结点
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }

    public static void printList(ListNode head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print("-");
            }
        }
        System.out.print("]");
    }
}
