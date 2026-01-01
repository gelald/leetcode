package com.example.leetcode.subject.binary.search;

import org.junit.jupiter.api.Test;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <br>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <br>
 * 示例：输入: nums = [1,3,5,6], target = 5；输出: 2
 * <br>
 * 示例：输入: nums = [1,3,5,6], target = 2；输出：1
 * <br>
 * 示例：输入: nums = [1,3,5,6], target = 7；输出: 4
 */
public class E35SearchInsertPosition {

    @Test
    void searchInsert() {
        System.out.println(searchInsert01(new int[]{1, 3, 5, 6}, 5));
        System.out.println(searchInsert01(new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert01(new int[]{1, 3, 5, 6}, 7));

        System.out.println(searchInsert01(new int[]{1, 3, 5, 6, 8, 10, 21, 25, 30, 30}, 7));
    }

    public int searchInsert01(int[] nums, int target) {
        // 数组左指针
        int left = 0;
        // 数组右指针
        int right = nums.length - 1;
        int result = nums.length;

        // 二分查找，条件是左指针一直位于右指针左边
        while (left <= right) {
            // 中间索引计算公式
            int middle = (right - left) / 2 + left;
            if (target <= nums[middle]) {
                result = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return result;
    }
}
