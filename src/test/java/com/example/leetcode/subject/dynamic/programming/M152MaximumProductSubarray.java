package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <br>
 * 测试用例的答案是一个 32-位 整数。
 * <br>
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 * <br>
 * 示例：输入: nums = [2,3,-2,4]；输出: 6；解释: 子数组 [2,3] 有最大乘积 6。
 * <br>
 * 示例：输入: nums = [-2,0,-1]；输出: 0；解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <br>
 * 1 <= nums.length <= 2 * 104
 * <br>
 * -10 <= nums[i] <= 10
 * <br>
 * nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 */
public class M152MaximumProductSubarray {

    @Test
    void maxProduct() {
        System.out.println(maxProduct01(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct01(new int[]{-2, 0, -1}));
        System.out.println(maxProduct01(new int[]{-5}));
        System.out.println(maxProduct01(new int[]{-2, 3, -4})); // 24
        System.out.println(maxProduct01(new int[]{2, -5, -2, -4, 3})); // 24
        System.out.println(maxProduct01(new int[]{2, -1, 1, 1})); // 2
    }

    public int maxProduct01(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            // 如果数组只有一个元素，那么最大的乘积就是他自己
            return nums[0];
        }
        // 因为有正数负数，所以要维护两个数组。维护最小乘积是为了解决 i 是负数的情况，有可能 i * 最小乘积是这个序列的最大乘积
        // dpMax[i] 作为子序列结尾的元素，这个子序列的最大乘积
        // dpMin[i] 作为子序列结尾的元素，这个子序列的最小乘积
        int[] dpMax = new int[length];
        int[] dpMin = new int[length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        // 记录乘积最大值
        int result = dpMax[0];
        for (int i = 1; i < length; i++) {
            // 最差的情况：nums[i]比整个数组中的任意子序列的乘积都大，所以默认dpMax[i]和dpMin[i]是nums[i]
            dpMax[i] = nums[i];
            dpMin[i] = nums[i];

            // 限制条件：必须连续的子序列，所以不能再从0开始遍历，只能选i的前一个元素

            // 以 nums[i] 结尾的子序列最大乘积的结果
            // nums[i - 1] 结尾的子序列最大乘积 * nums[i]：最常见情况
            // nums[i - 1] 结尾的子序列最小乘积 * nums[i]：最小的乘积是个负数，nums[i]也是负数，负得越小，相乘就越大
            // nums[i] 本身：说明前面乘的还不如它自己

            dpMax[i] = Math.max(dpMax[i - 1] * nums[i], Math.max(dpMin[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));

            // 记录最大值
            result = Math.max(result, dpMax[i]);
        }
        return result;
    }
}
