package com.example.leetcode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * @author WuYingBin
 * date: 2022/4/22
 */
public class E543DiameterOfBinaryTree {
    public static int diameter = 0;
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right = new TreeNode(3);
        System.out.println(diameterOfBinaryTree(treeNode));
    }

    /**
     * 直径长度可以理解为[左子树最大深度]+[右子树最大深度]
     * 一个节点的最大深度=左子树深度和右子树深度的最大值+1(自己)
     * 利用递归算出每一个节点的直径长度
     *
     * @param root 二叉树头节点
     * @return 二叉树的直径
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return diameter;
        }
        maxDepth(root);
        return diameter;
    }

    /**
     * 时间: O(N) 0ms 100%
     * 内存: O(height) 40.9M 63.03%
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算左子树的最大深度
        int leftDiameter = maxDepth(root.left);
        // 计算右子树的最大深度
        int rightDiameter = maxDepth(root.right);

        // 计算直径
        diameter = Math.max(diameter, (leftDiameter + rightDiameter));

        // 返回节点的最大深度
        return Math.max(leftDiameter, rightDiameter) + 1;
    }
}
