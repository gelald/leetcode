package com.example.leetcode;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <br/>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <br/>
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/19
 */
public class E283MoveZeroes {
    public static void main(String[] args) {
        // int[] nums = new int[]{0, 1, 0, 3, 12};
        int[] nums = new int[]{2, 1};
        System.out.print("原数组：");
        printArray(nums);
        moveZeroes1(nums);
        // moveZeroes2(nums);
        System.out.print("\n移动0后的数组：");
        printArray(nums);
    }

    /**
     * 双指针法
     * 左指针：指向当前已经处理好的序列的尾部
     * 右指针：指向待处理的序列的头部
     * <br/>
     * 时间: O(n) 1ms 100%
     * 内存: O(1) 42.8M 30.88%
     * <br/>
     *
     * @param nums 数组
     */
    public static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int j = 0;
        for (int i = 0, length = nums.length; i < length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

    /**
     * 单指针+两次遍历
     * 第一次遍历，把所有不为0的元素移动到前面
     * 第二次遍历，把后面元素都设置为0
     * <br/>
     * 时间: O(n) 1ms 100%
     * 内存: O(1) 42.5M 62.96%
     * <br/>
     *
     * @param nums 数组
     */
    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
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
