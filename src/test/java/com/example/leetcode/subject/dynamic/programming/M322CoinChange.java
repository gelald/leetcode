package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <br>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <br>
 * 你可以认为每种硬币的数量是无限的。
 * <br>
 * 示例：输入：coins = [1, 2, 5], amount = 11；输出：3 ；解释：11 = 5 + 5 + 1
 * <br>
 * 示例：输入：coins = [2], amount = 3；输出：-1；解释：没有任何一种硬币组合能组成总金额
 * <br>
 * 示例：输入：coins = [1], amount = 0；输出：0
 */
public class M322CoinChange {

    @Test
    void coinChange() {
        System.out.println(coinChange01(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange01(new int[]{2}, 3));
        System.out.println(coinChange01(new int[]{1}, 0));
    }

    public int coinChange01(int[] coins, int amount) {
        // 如果目标值是0，那就不用凑了，直接返回0
        if (amount == 0) {
            return 0;
        }
        // 定义dp数组，存储的结果是从1到amount金额所需的最少硬币数
        int[] dp = new int[amount + 1];
        // 索引0处存储结果为0，因为金额0不需要任何硬币
        dp[0] = 0;
        // 遍历dp数组，开始填充每一个金额所需的硬币数
        for (int i = 1; i <= amount; i++) {
            // 最大的硬币数初始化为目标金额+1，即使结果全都由面值为1的硬币组成，都比这个初始结果小
            dp[i] = amount + 1;
            // 遍历硬币数组，开始
            for (int coin : coins) {
                // 如果遍历到的硬币面值比目标金额还大，那么这一枚硬币不使用
                if (coin > i) {
                    continue;
                }
                // 对于第k元的解决方案：遍历硬币数组，不断尝试每一个硬币
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        // 初始化的结果一定大于amount，如果无法找到合适的子问题解，只能返回-1
        if (dp[amount] > amount) {
            return -1;
        } else {
            return dp[amount];
        }
    }
}
