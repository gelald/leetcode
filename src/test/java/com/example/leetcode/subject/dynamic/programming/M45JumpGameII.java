package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
 * <br>
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
 * <br>
 * 0 <= j <= nums[i] 且 i + j < n
 * <br>
 * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
 * <br>
 * 示例：输入: nums = [2,3,1,1,4]；输出: 2；解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <br>
 * 示例：输入: nums = [2,3,0,1,4]；输出：2
 */
public class M45JumpGameII {

    @Test
    void jump() {
        System.out.println(jump01(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jump01(new int[]{2, 3, 0, 1, 4}));

        System.out.println(jump02(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jump02(new int[]{2, 3, 0, 1, 4}));
    }

    /**
     * 动态规划
     * f(i) = min(f(i), f(j) + 1), 条件: nums[j] >= i - j
     */
    public int jump01(int[] nums) {
        int length = nums.length;
        // dp[i] 表示跳跃到当前位置所需的最小步数
        int[] dp = new int[length];
        dp[0] = 0;

        for (int i = 1; i < length; i++) {
            dp[i] = length + 1;
            for (int j = 0; j < i; j++) {
                // 位置 i 能跳跃的条件：
                // 位置 j 能跳跃 && nums[j] 大于等于 j 到 i 的距离
                // 所以应该用跳跃到位置 j 的最小步数 + 1
                // 假设有多个跳到位置 i 的方案，应该取最小值
                if (nums[j] >= i - j) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[length - 1];
    }

    public int jump02(int[] nums) {
        int steps = 0;
        int end = 0;
        int maxPosition = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            // 记录能跳到的最远位置
            maxPosition = Math.max(maxPosition, nums[i] + i);

            if (i == end) {
                // 当 i 到了 end 的位置，把 end 更新为能跳到的最远位置
                // 步数加一
                // 直到下次循环 i 再次到了 end 的位置
                end = maxPosition;
                steps++;
            }
        }

        return steps;
    }
}
