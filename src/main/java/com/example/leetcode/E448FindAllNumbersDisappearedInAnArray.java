package com.example.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * <br/>
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * <br/>
 * 输入：nums = [1,1]
 * 输出：[2]
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/20
 */
public class E448FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{4,3,2,7,8,2,3,1};
        int[] nums2 = new int[]{1,1,1,1,1,1,1};
        System.out.println(findDisappearedNumbers1(nums1));
        System.out.println(findDisappearedNumbers1(nums2));
        System.out.println(findDisappearedNumbers2(nums1));
        System.out.println(findDisappearedNumbers2(nums2));
    }

    /**
     * 使用哈希表来记录出现过的数字
     * 最后按照数组长度来查询哈希表,不存在于哈希表的说明这个数字不存在
     * <br/>
     * 时间: O(n) 17ms 23.33%
     * 内存: O(n) 50.7M 5.05%
     * <br/>
     *
     * @param nums 数组
     * @return 消失的数字
     */
    public static List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 不使用额外的内存空间,修改原数组
     * 当一个数是x,那么这个x理应位于nums[x-1]这个位置上
     * 那么我们可以在这个位置上做一定的修改
     * 当x出现了,那么就对nums[x-1]这个位置上的数加上数组的长度
     * 最后再遍历一次数组,如果有一个位置上的数没有超出数组的长度,那么对应的i+1就是不存在数组中的
     * <br/>
     * 时间: O(n) 3ms 99.77%
     * 内存: O(1) 49.1M 70.49%
     * <br/>
     *
     * @param nums 数组
     * @return 消失的数字
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        // [4, 3, 2, 7, 8, 2, 3, 1]
        int n = nums.length;
        for (int num : nums) {
            // 避免加上数组长度后不知道对应数组的位置
            // 比如第四个元素7,在遍历到4的时候已经被加上8变成了15
            nums[(num - 1) % n] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
