package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <br>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <br>
 * 示例：输入：nums = [2,3,1,1,4]；输出：true；解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <br>
 * 示例：输入：nums = [3,2,1,0,4]；输出：false；解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class M55JumpGame {

    @Test
    void jumpGame() {
        System.out.println(jumpGame01(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpGame01(new int[]{3, 2, 1, 0, 4}));
        System.out.println(jumpGame02(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpGame02(new int[]{3, 2, 1, 0, 4}));
    }

    /**
     * 动态规划，当前位置i是否可达依赖前面元素j
     * <br>
     * f(i) = f(j) && nums[j] >= i - j
     */
    public boolean jumpGame01(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return true;
        }

        boolean[] dp = new boolean[length];
        dp[0] = true;

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                // j 可跳跃，并且 nums[j] 的值要大于等于 j 到 i 的距离，那么 i 是可以跳跃的
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[length - 1];
    }

    /**
     * 贪心算法
     */
    public boolean jumpGame02(int[] nums) {
        int length = nums.length;
        int max = 0;

        for (int i = 0; i < length; i++) {
            if (i > max) {
                // 说明当前能跳跃的最大值，无法跳跃到第 i 个位置
                return false;
            }
            // 能跳跃的最大值 = 当前元素位置 + 值
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
}
