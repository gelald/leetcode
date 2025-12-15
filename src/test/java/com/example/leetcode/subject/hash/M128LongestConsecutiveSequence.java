package com.example.leetcode.subject.hash;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <br>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <br>
 * 示例：输入：nums = [100,4,200,1,3,2]；输出：4；解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <br>
 * 示例：输入：nums = [0,3,7,2,5,8,4,6,0,1]；输出：9
 * <br>
 * 示例：输入：nums = [1,0,1,2]；输出：3
 */
public class M128LongestConsecutiveSequence {

    @Test
    void longestConsecutive() {
        int[] nums1 = new int[]{100, 4, 200, 1, 3, 2}; // 4
        int[] nums2 = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}; // 9
        int[] nums3 = new int[]{1, 0, 1, 2}; // 3
        int[] nums4 = new int[]{5}; // 1
        int[] nums5 = new int[]{}; // 0
        System.out.println(longestConsecutive01(nums1));
        System.out.println(longestConsecutive01(nums2));
        System.out.println(longestConsecutive01(nums3));
        System.out.println(longestConsecutive01(nums4));
        System.out.println(longestConsecutive01(nums5));
    }

    /**
     * 时间复杂度：O(n)
     * <br>
     * 在内部 while 循环中，虽然看起来是嵌套循环，但实际上每个元素最多被访问两次（一次在外层循环中，一次在内层循环中）
     */
    public int longestConsecutive01(int[] nums) {
        // 存入 HashSet 去重
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // 保证空数组也满足算法
        int result = 0;

        // 开始遍历去重后的数组
        for (Integer i : set) {
            if (set.contains(i - 1)) {
                // 如果 set 中存在 i - 1, 那么说明当前 i 不是最长序列的第一个元素, 需要跳过
                continue;
            }

            int j = i + 1;
            while (set.contains(j)) {
                // 不停用 i + 1 去查询 set 中是否有这个元素，如果有说明序列还能延续
                j++;
            }
            // 如果退出循环，说明最长序列中的最后一个元素是 j - 1，因为 j++ 后不满足条件了

            // 每一轮都有一个 result 出来，最终取最大的那个
            result = Math.max(result, j - i);
        }

        return result;
    }
}
