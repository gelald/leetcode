package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <br>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <br>
 * 示例：输入：n = 2；输出：2；解释：1+1 或 2
 * <br>
 * 示例：输入：n = 3；输出：3；解释：1+1+1 或 1+2 或 2+1
 * <br>
 * 示例：输入：n = 4；输出：5；解释：1+1+1+1 或 1+1+2 或 2+1+1 或 1+2+1 或 2+2
 * <br>
 * 1 <= n <= 45
 */
public class E70ClimbingStairs {

    @Test
    void climbStairs() {
        System.out.println(climbStairs03(2));
        System.out.println(climbStairs03(3));
        System.out.println(climbStairs03(4));
        System.out.println(climbStairs03(5));
        System.out.println(climbStairs03(45));
    }

    /**
     * 普通递归解法，时间复杂度过高，并且存在重复计算
     */
    public int climbStairs01(int n) {
        if (n <= 2) {
            return n;
        }
        int result = climbStairs01(n - 2) + climbStairs01(n - 1);
        return result;
    }

    /**
     * 缓存+递归解法
     */
    public int climbStairs02(int n, Map<Integer, Integer> cache) {
        if (n <= 2) {
            return n;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int result = climbStairs02(n - 1, cache) + climbStairs02(n - 2, cache);
        cache.put(n, result);
        return result;
    }

    /**
     * 动态规划解法
     */
    public int climbStairs03(int n) {
        if (n <= 2) {
            return n;
        }
        int p;
        int q = 1;
        int r = 2;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
