package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给你一个整数 n ，返回和为 n 的完全平方数的最少数量 。
 * <br>
 * 完全平方数是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <br>
 * 示例：输入：n = 12；输出：3 ；解释：12 = 4 + 4 + 4
 * <br>
 * 示例：输入：n = 13；输出：2 ；解释：13 = 4 + 9
 * <br>
 * 1 <= n <= 10^4
 */
public class M279PerfectSquares {

    @Test
    void numSquares() {
//        System.out.println(numSquares01(3));
//        System.out.println(numSquares01(12));
//        System.out.println(numSquares01(13));
//        System.out.println(numSquares01(28));
        System.out.println(numSquares01(96));
    }

    public int numSquares01(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            //最差的情况，i 这个数只能由 1 组成
            // dp[1] = 1
            // dp[2] = 2 (2个1组成)
            // dp[3] = 3 (3个1组成)
            dp[i] = i;
            // 遍历所有完全平方数，范围从 1 到 i 的平方根
            for (int j = 1; j * j <= i; j++) {
                // dp[i - j * j] 表示 i - j * j 的最少完全平方数个数
                // 当 i = 4
                // 初始化 dp[4] = 4
                // dp[4 - 1 * 1] = dp[3] = 3
                // dp[4 - 2 * 2] = dp[0] = 0
                // 最后加一后最小值是 1 (4自己就是完全平方数)
                // 当 i = 5
                // 初始化 dp[5] = 5
                // dp[5 - 1 * 1] = dp[4] = 1
                // dp[5 - 2 * 2] = dp[1] = 1
                // 最后加一后最小值是 2 (4 + 1)
                // 当 i = 10
                // 初始化 dp[10] = 10
                // dp[10 - 1 * 1] = dp[9] = 1
                // dp[10 - 2 * 2] = dp[6] = 3
                // dp[10 - 3 * 3] = dp[1] = 1
                // 最后加一后最小值是 2 (9 + 1)

                // 为什么要加 1，因为在运算过程中，i 减去了 j * j，这里就是一个完全平方数，所以最终结果要加上他
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
