package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * <br>
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <br>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <br>
 * 示例：输入：[1,2,3,1]；输出：4；解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)
 * <br>
 * 示例：输入：[2,7,9,3,1]；输出：12；解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * <br>
 * 1 <= nums.length <= 100
 * <br>
 * 0 <= nums[i] <= 400
 */
public class M198HouseRobber {

    @Test
    void rob() {
        System.out.println(rob01(new int[]{1, 2, 3, 1}));
        System.out.println(rob01(new int[]{2, 7, 9, 3, 1}));
        System.out.println(rob01(new int[]{2, 1, 1, 2}));

        System.out.println(rob02(new int[]{1, 2, 3, 1}));
        System.out.println(rob02(new int[]{2, 7, 9, 3, 1}));
        System.out.println(rob02(new int[]{2, 1, 1, 2}));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int rob01(int[] nums) {
        // 1 和 2 是动态规划的边界条件，1 间房子偷窃金额是第 1 间房子的金额，2 间房子偷窃金额是第 1 间和第 2 间房子中金额的最大值
        // f(1) = nums[0], f(2) = Math.max(nums[0], nums[1])
        // 如果是有 3 间房子，解法应该是：f(3) = Math.max(f(1) + nums[2], f(2))
        // 求出前 3-2 间房子的最大金额加上最后一间房子的金额的结果，求出 3-1 间房子的最大金额，这两个金额的最大值
        // 如果是有 7 间房子，解法应该是：f(7) = Math.max(f(5) + nums[6], f(6))
        // 有 k 间房子，要计算最大的偷窃金额的话有两种选择：f(k-2) + nums[k]、f(k-1) 计算他们的最大值

        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        // 存储每一个子问题的结果
        // 当去到最后一个问题的时候，这个子问题就是总问题
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public int rob02(int[] nums) {
        // 用临时变量代替 dp 数组
        // 因为每次用到 dp 数组都是前两个和前一个结果

        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        // 当前结果，初始值：第 2 轮结果
        int currentResult = Math.max(nums[0], nums[1]);
        // 前一轮结果，初始值：第 1 轮结果
        int previousResult = nums[0];

        for (int i = 2; i < length; i++) {
            // 第 3 轮结果 = max(第 1 轮结果 + 第 3 个元素, 第 2 轮结果)
            // 计算后，当前结果：第 3 轮结果；前一轮结果：第 2 轮结果
            // 第 4 轮结果 = max(第 2 轮结果 + 第 4 个元素, 第 3 轮结果)
            // 计算后，当前结果：第 4 轮结果；前一轮结果：第 3 轮结果
            int temp = currentResult;
            currentResult = Math.max(previousResult + nums[i], currentResult);
            previousResult = temp;
        }

        return currentResult;
    }
}
