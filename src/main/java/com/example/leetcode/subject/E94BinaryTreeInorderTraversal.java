package com.example.leetcode.subject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回它的中序遍历。
 * <br/>
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/6
 */
public class E94BinaryTreeInorderTraversal {

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

    public static TreeNode init() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = init();
        System.out.println(inorderTraversal1(root));
        System.out.println(inorderTraversal2(root));
    }

    /**
     * 递归
     * 中序遍历的方式为:左-根-右
     * <br/>
     * 时间：O(n)  0ms  100%
     * 内存：O(n)  39.8M 11%
     * <br/>
     *
     * @param root 根节点
     * @return 输出
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>(10);
        inorder(root, result);
        return result;
    }

    public static void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    /**
     * 迭代
     * 一路往左迭代并入栈
     * 当遇到空节点就逐一出栈并放入到结果集合中
     * 然后把当前指针指向右节点
     * 循环以上过程,直到节点和栈都为空才结束
     * <br/>
     * 时间：O(n)  0ms  100%
     * 内存：O(n)  39.5M 41.49%
     * <br/>
     *
     * @param root 根节点
     * @return 输出
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>(10);
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            // 一直往左子树方向遍历,每走一次就将当前节点保存到栈中
            // 模拟了递归调用的过程
            if (root != null) {
                stack.offerFirst(root);
                root = root.left;
            }
            // 当前节点为空,说明左子树到头了,需要出栈并保存到结果集
            // 转向右子树,继续之前的过程
            else {
                root = stack.pollFirst();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

}
