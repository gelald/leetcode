package com.example.leetcode.subject;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <br/>
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * <br/>
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/15
 */
public class E226InvertBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        System.out.print("原始二叉树：");
        printTree(root);
        System.out.print("\n反转二叉树：");
        printTree(invertTree1(root));
        // System.out.print("\n反转二叉树：");
        // printTree(invertTree2(root));
    }

    /**
     * 递归
     * 每次交换节点的左右子树
     * 并递归地操作节点地左子树和右子树
     * <br/>
     * 时间: O(n) 0ms 100%
     * 内存: O(n) 38.8M 64.57%
     *
     * @param root 二叉树根节点
     * @return 反转的二叉树
     */
    public static TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    /**
     * 广度优先遍历
     * 使用栈存储需要交换左右子树的节点
     * 每次从栈中取出一个节点进行交换
     * 交换完毕后把节点弹栈
     * 并继续把节点的左右子树入栈
     * <br/>
     * 时间: O(n) 0ms 100%
     * 内存: O(n) 39.1M 18.85%
     *
     * @param root 二叉树根节点
     * @return 反转的二叉树
     */
    public static TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerFirst(root);
        while (!deque.isEmpty()) {
            TreeNode peek = deque.peekFirst();
            TreeNode tmp = peek.left;
            peek.left = peek.right;
            peek.right = tmp;
            deque.pollFirst();
            if (peek.left != null) {
                deque.offerFirst(peek.left);
            }
            if (peek.right != null) {
                deque.offerFirst(peek.right);
            }
        }
        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print("[");
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root);
        while (!deque.isEmpty()) {
            TreeNode peek = deque.pollFirst();
            System.out.print(peek.val + " ");
            if (peek.left != null) {
                deque.offerFirst(peek.left);
            }
            if (peek.right != null) {
                deque.offerFirst(peek.right);
            }
        }
        System.out.print("]");
    }
}
