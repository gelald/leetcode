package com.example.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
 * <br/>
 * 输入：head = [1,2,2,1]
 * 输出：true
 * <br/>
 * 输入：head = [1,2]
 * 输出：false
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/16
 */
public class E234PalindromeLinkedList {

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
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.print("输入的链表：");
        printList(head);
        System.out.println("\n是否回文链表：" + isPalindrome1(head));
        System.out.println("\n是否回文链表：" + isPalindrome2(head));
        System.out.println("\n是否回文链表：" + isPalindrome3(head));
    }

    /**
     * 使用辅助栈
     * 栈有FILO特性，所以把链表各节点放入栈后再取出的顺序就是原链表的相反顺序
     * 逐一比对两个结点，如果有不相同的，那就不是，否则就是回文链表
     * <br/>
     * 时间: O(n) 9ms 29.39%
     * 内存: O(n) 53.4M 71.01%
     *
     * @param head 链表头结点
     * @return 是否回文链表
     */
    public static boolean isPalindrome1(ListNode head) {
        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode p1 = head;
        while (head != null) {
            deque.offerFirst(head);
            head = head.next;
        }
        while (!deque.isEmpty()) {
            ListNode peek = deque.pollFirst();
            if (p1.val != peek.val) {
                return false;
            }
            p1 = p1.next;
        }
        return true;
    }

    /**
     * 把链表转为数组
     * 从数组头和尾开始向中间遍历
     * 只要遍历到有不一样的值那就不是回文，否则就是回文
     * <br/>
     * 时间: O(n) 5ms 63.07%
     * 内存: O(n) 53.4M 71.01%
     *
     * @param head 链表头节点
     * @return 是否回文链表
     */
    public static boolean isPalindrome2(ListNode head) {
        List<ListNode> lists = new ArrayList<>();
        while (head != null) {
            lists.add(head);
            head = head.next;
        }
        int i = 0, j = lists.size() - 1;
        while (i <= j) {
            if (lists.get(i).val != lists.get(j).val) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 快慢指针
     * 快指针：一次走两步；慢指针：一次走一步
     * 当快指针走到最后一个结点的时候，慢指针就走到了中间，通过慢指针把链表分成两部分
     * 如果有奇数各结点，那么中间结点归为前半部分链表的部分
     * 反转后半部分的链表
     * 比较两个部分链表的值
     * <br/>
     * 时间: O(n) 5ms 63.07%
     * 内存: O(1) 没有使用额外的存储空间 53.4M 71.01%
     *
     * @param head 链表头节点
     * @return 是否回文链表
     */
    public static boolean isPalindrome3(ListNode head) {
        if (head == null) {
            return true;
        }
        // 第一部分的最后一个结点
        ListNode firstHalfEnd = endOfFirstHalf(head);
        // 第二部分反转后的头节点
        ListNode secondHalfStart = reverseLinkedList(firstHalfEnd.next);

        ListNode p1 = head;
        ListNode p2 = secondHalfStart;

        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    /**
     * 通过遍历，让快慢指针到达自己的位置上
     * 快指针：当链表结点个数是奇数时，会到最后一个结点上；偶数时，会到最后一个结点的前一个结点上
     * 慢指针：当链表结点个数是奇数时，会到长度一半的位置上，此时中间结点作为前半部分链表的一部分；偶数时，同样会到长度一半的位置上
     * 最终返回慢指针，慢指针用于切分链表的前后部分
     *
     * @param head 链表头节点
     * @return 慢指针
     */
    public static ListNode endOfFirstHalf(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 通过遍历的方式反转链表
     * 每次往后遍历时通过cur来修改当前结点的next结点
     * 使用pre记录反转后的链表
     *
     * @param head 链表头结点
     * @return 反转后的链表
     */
    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null) {
            return null;
        }
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
