package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给你一个 只包含正整数的非空数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <br>
 * 示例：输入：nums = [1,5,11,5]；输出：true；解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <br>
 * 示例：输入：nums = [1,2,3,5]；输出：输出：false；解释：数组不能分割成两个元素和相等的子集。
 * <br>
 * 1 <= nums.length <= 200
 * <br>
 * 1 <= nums[i] <= 100
 */
public class M416PartitionEqualSubsetSum {

    @Test
    void canPartition() {
        System.out.println(canPartition01(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition01(new int[]{1, 2, 3, 5}));
    }

    public boolean canPartition01(int[] nums) {
        int length = nums.length;
        // 只有一个元素，必然无法分割成两个元素和相等的子集
        if (length == 1) {
            return false;
        }

        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }

        if (sum % 2 != 0) {
            // 如果数组和是奇数，必然无法从数组中找到一段子序列的和是 sum/2
            return false;
        }

        int target = sum / 2;
        if (maxNum > target) {
            // 如果数组中的最大元素大于 sum / 2
            // 说明数组剩余元素的和必然小于 sum / 2
            // 必然无法找到两个和相等的子序列
            return false;
        }

        // dp[i][j] 代表以 nums 在 [0, i] 区间内选择 0 个或若干个正整数，是否存在一种方案使得它们的和刚好等于 j
        boolean[][] dp = new boolean[length][target + 1];
        for (int i = 0; i < length; i++) {
            // j = 0 时，那么就不选择任何正整数
            dp[i][0] = true;
        }
        // i = 0, j = nums[0] 时，等于 true
        dp[0][nums[0]] = true;

        for (int i = 1; i < length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] > j) {
                    // 证明当前 nums[i] 不能选择，因为它本来就比 j 更大了
                    // 那么就找 [0, i - 1] 这个区间是否存在若干个数加起来等于 j
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果不选择 nums[i]，那么就找 [0, i - 1] 这个区间是否存在若干个数加起来等于 j
                    // 如果选择 nums[i]，那么就找 [0, i - 1] 这个区间是否存在若干个数加起来等于 j - nums[i]
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[length - 1][target];
    }
}
