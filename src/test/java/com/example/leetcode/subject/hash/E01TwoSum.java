package com.example.leetcode.subject.hash;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <br/>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class E01TwoSum {

    @Test
    void twoSum() {
        int[] nums = new int[]{11, 7, 2, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum01(nums, target)));
        System.out.println(Arrays.toString(twoSum02(nums, target)));
    }

    /**
     * 方法一：暴力法
     * <br>
     * 通过两次循环来找出那两个索引
     * <br>
     * 时间复杂度：O(n²) 因为经历两次循环
     */
    public int[] twoSum01(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 方法二：哈希法
     * <br>
     * 一边循环一边把数据存入 HashMap 中，结构: <code>{key: 数组值, value: 数组索引}</code>
     * <br>
     * 时间复杂度：O(n)，用 HashMap 的空间换了一次循环
     */
    public int[] twoSum02(int[] nums, int target) {
        // {key: 数组值, value: 数组索引}
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
