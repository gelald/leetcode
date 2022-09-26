package com.example.leetcode.subject;

/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * <br/>
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * <br/>
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/19
 */
public class E338CountingBits {
    public static void main(String[] args) {
        int n = 9;
        int[] ans = countBits2(n);
        printArray(ans);
    }

    /**
     * 找规律
     * 0：0 ==> 0
     * 1：1 ==> 1
     * 2：10 ==> 1
     * 3：11 ==> 2
     * 4：100 ==> 1
     * 5：101 ==> 2
     * 6：110 ==> 2
     * 7：111 ==> 3
     * 奇数：前面一位的个数+1
     * 偶数：除以2对应的数的个数
     *
     * @param n 输入
     * @return 0 <= i <= n 中的每个 i，其二进制中1个个数的数组
     */
    public static int[] countBits1(int n) {
        // 新建数组，默认第0个就是0
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 判断奇数偶数
            if ((i % 2) == 0) {
                // 偶数
                // ans[6] = ans[3]
                ans[i] = ans[i / 2];
            } else {
                // 奇数
                // ans[7] = ans[6] + 1
                ans[i] = ans[i - 1] + 1;
            }
        }
        return ans;
    }

    /**
     * 上面一种做法的优化写法
     * 判断奇数偶数用的%2的方式，但是位运算比模运算效率更高
     * 而且判断奇数偶数的决定性因素是最后一位是否是1
     * 所以(i & 1) ==> (i % 2)
     * 除法同样处理
     * 所以(i >> 1) ==> (i / 2)
     * 最后奇数的情况下
     * (i >> 1) == ((i - 1) >> 1)
     * <br/>
     * 时间: 1ms 99.97%
     * 内存: 45.4M 41.96%
     * <br/>
     *
     * @param n 输入
     * @return 0 <= i <= n 中的每个 i，其二进制中1个个数的数组
     */
    public static int[] countBits2(int n) {
        // 新建数组，默认第0个就是0
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // ans[6] = ans[3] + 0
            // ans[7] = ans[3] + 1 = ans[6] + 1
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

    public static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i != nums.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}
