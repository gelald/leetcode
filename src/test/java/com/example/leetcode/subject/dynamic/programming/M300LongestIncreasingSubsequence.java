package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <br>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * <br>
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <br>
 * 示例：输入：nums = [10,9,2,5,3,7,101,18]；输出：4；解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <br>
 * 示例：输入：nums = [0,1,0,3,2,3]；输出：4
 * <br>
 * 示例：输入：nums = [7,7,7,7,7,7,7]；输出：1
 */
public class M300LongestIncreasingSubsequence {

    @Test
    void lengthOfLIS() {
        System.out.println(lengthOfLIS01(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS01(int[] nums) {
        // 状态定义：dp[i]的值代表以nums[i]结尾的最长递增子序列长度
        // 状态转移：0 <= j < i，考虑每轮计算新 dp[i] 时，遍历 [0, i) 区间，做以下判断
        // 1.当 nums[i] > nums[j] 时，满足递增序列，此时 dp[i] = dp[j] + 1
        // 2.当 nums[i] <= nums[j] 时，不满足递增序列，需要跳过，j 需要继续在区间 [0, i) 往后遍历
        // 上述所有 1. 情况下计算出的 dp[j]+1 的最大值，为直到 i 的最长上升子序列长度（即 dp[i] ）。实现方式为遍历 j 时，每轮执行 dp[i]=max(dp[i],dp[j]+1)。
        // 状态转移方程是：dp[i] = max(dp[j] + 1, dp[i])
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        // 用临时变量，省去最后从dp数组中找最大值
        int result = 0;
        // dp[i] 作为子序列结尾的元素，然后检查从dp[0]到dp[i-1]的序列
        for (int i = 0; i < nums.length; i++) {
            // 代表最差的情况是以这个元素结尾的话，没有任何递增子序列，所以最长递增子序列长度是1，代表它自身
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 如果 nums[i] > nums[j]，证明至少 j, i 可以组成一个递增的子序列
                // 那么往前找以 j 结尾的子序列的最大元素
                if (nums[i] > nums[j]) {
                    // 可能前面有多个符合条件的以 j 结尾的子序列，取最大的那个结果
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
