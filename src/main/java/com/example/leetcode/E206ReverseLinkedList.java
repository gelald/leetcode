package com.example.leetcode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <br/>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * <br/>
 * 输入：head = [1,2]
 * 输出：[2,1]
 * <br/>
 * 输入：head = []
 * 输出：[]
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/13
 */
public class E206ReverseLinkedList {

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
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.print("原始链表：");
        printList(listNode);
        System.out.print("\n反转链表：");
        printList(reverseList1(listNode));
        // System.out.print("\n反转链表：");
        // printList(reverseList2(listNode));
    }

    /**
     * 递归
     * 除去判断语句,每一行都是重点
     * 假设用 1->2->3->4->5 为例子
     * 终止条件的触发是head=4的时候,因为进入下一层递归调用后,head=5,head.next=null,触发终止条件,所以newHead是head=5的节点
     * head.next.next=head应该这样子理解:因为head=4,所以此时head.next=5,进而使得5.next=4
     * head.next=null应该这样子理解:因为经过上一步,5.next=4,此时4.next=5,这一句可以使得链表不形成环.如果没有这一句,当链表只有两个元素,就会报错
     * return newHead可以保证每一次返回都是返回最后一个节点5,每次修改都是修改中间节点的关系
     * <br/>
     * 时间: O(n) 0ms 100%
     * 内存: O(n) 41.1M 42.53%
     * <br/>
     *
     * @param head 链表头节点
     * @return 反转链表
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代
     * 使用双指针进行迭代
     * cur一直往后迭代,每迭代一个节点就修改这个节点的next
     * pre负责记录cur修改next后的链表,最终返回这个pre
     * <br/>
     * 时间: O(n) 因为需要遍历链表一次 0ms 100%
     * 内存: O(1) 41M 63.41%
     * <br/>
     *
     * @param head 链表头节点
     * @return 反转链表
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
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
