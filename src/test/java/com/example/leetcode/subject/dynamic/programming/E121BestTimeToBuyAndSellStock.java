package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <br>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <br>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <br>
 * 示例：输入：[7,1,5,3,6,4]；输出：5；解释：只能选择第二天（价格=1）时买入，选择第五天（价格=6）卖出
 * <br>
 * 示例：输入：[7,6,4,3,1]；输出：0；解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class E121BestTimeToBuyAndSellStock {

    @Test
    void maxProfit() {
        System.out.println(maxProfit01(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit01(new int[]{7, 6, 4, 3, 1}));
        System.out.println(maxProfit02(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit02(new int[]{7, 6, 4, 3, 1}));
    }

    /**
     * 贪心算法，也作为动态规划的空间优化
     */
    public int maxProfit01(int[] prices) {
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }

        int min = prices[0];
        int result = 0;

        for (int i = 1; i < length; i++) {
            min = Math.min(min, prices[i]);
            result = Math.max(result, (prices[i] - min));
        }

        return result;
    }

    /**
     * 动态规划
     */
    public int maxProfit02(int[] prices) {
        // 维护dp数组的同时，也维护最小值
        // dp[i] = max(dp[i-1], prices[i]-min)
        // 在第i天获得最大的收益是 max(前一天的最大收益, 今天的价格-累计最小值)

        int length = prices.length;
        if (length <= 1) {
            return 0;
        }

        int min = prices[0];
        int[] dp = new int[length];
        dp[0] = 0;

        for (int i = 1; i < length; i++) {
            min = Math.min(min, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
        }

        return dp[length - 1];
    }
}
