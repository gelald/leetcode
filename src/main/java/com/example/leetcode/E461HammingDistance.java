package com.example.leetcode;

/**
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * <br/>
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * <br/>
 * 输入：x = 1, y = 4
 * <br/>
 * 输出：2
 * <br/>
 * 输入：x = 3, y = 1
 * <br/>
 * 输出：1
 * <br/>
 * @author WuYingBin
 * date: 2022/4/21
 */
public class E461HammingDistance {
    public static void main(String[] args) {
        System.out.println(hammingDistance1(1, 3));
        System.out.println(hammingDistance1(4, 1));
        System.out.println(hammingDistance2(1, 3));
        System.out.println(hammingDistance2(4, 1));
    }

    /**
     * 计算两个数的异或值,然后统计1的个数
     * 因为同为0,同为1,异或后都是0;只有不同的位置才是1,所以需要统计1的个数
     * <br/>
     * 时间: O(log C = log 2^31 = 31) 0ms 100%
     * 内存: O(1) 38.4M 31.59%
     * <br/>
     *
     * @param x x
     * @param y y
     * @return 两个数的汉明距离
     */
    public static int hammingDistance1(int x, int y) {
        int result = x ^ y;
        int count = 0;
        while (result != 0) {
            count++;
            // 不断删除最右侧的1
            result = result & (result - 1);
        }
        return count;
    }

    /**
     * 计算两个数的异或值,然后统计1的个数
     * 因为同为0,同为1,异或后都是0;只有不同的位置才是1,所以需要统计1的个数
     * 使用内置函数来统计
     * <br/>
     * 时间: O(1) 0ms 100%
     * 内存: O(1) 38.5M 20.94%
     * <br/>
     *
     * @param x x
     * @param y y
     * @return 两个数的汉明距离
     */
    public static int hammingDistance2(int x, int y) {
        int result = x ^ y;
        return Integer.bitCount(result);
    }
}
