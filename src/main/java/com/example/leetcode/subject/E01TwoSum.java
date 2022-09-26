package com.example.leetcode.subject;

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
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/2
 */
public class E01TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum1(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15}, 9)));
    }

    /**
     * 方法一：
     * 暴力法
     * 通过两次循环来找出那两个索引
     * <br/>
     * 时间：69ms，内存：36.5M
     *
     * @param nums   数组
     * @param target 目标值
     * @return 两个索引
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0, length = nums.length; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 方法二：
     * 利用HashMap查询的高效率特点，把查询工作从数组转到HashMap上
     * 1.HashMap存储结构<值, 索引>
     * 2.执行一次循环
     * 3.边循环边查找map中有没有一个键加上当前数等于target的，
     * 4.如果有，返回对应键的值（也就是这个数在原数组中的索引）和当前循环循环到的索引。
     * 5.如果没有，把当前数按<值, 索引>的规则插入map中
     * <br>
     * 时间：3ms，内存：38M
     *
     * @param nums   数组
     * @param target 目标值
     * @return 两个索引
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0, length = nums.length; i < length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
