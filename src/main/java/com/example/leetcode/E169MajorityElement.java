package com.example.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <br/>
 * 输入: [2, 2, 1, 1, 1, 2, 2]
 * 输出: 2
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/12
 */
public class E169MajorityElement {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement1(nums));
        System.out.println(majorityElement2(nums));
        System.out.println(majorityElement3(nums));
    }

    /**
     * 哈希
     * 先统计每一个元素出现的次数
     * 由于多数元素出现的次数一定会大于 n/2
     * 所以利用这个临界条件来寻找 value 值大于 n/2 的 key
     * <br/>
     * 时间: O(n) 23ms 5.18%
     * 内存: O(n) 46.7M 5.34%
     * <br/>
     *
     * @param nums 数组
     * @return 出现次数最多的元素
     */
    public static int majorityElement1(int[] nums) {
        Map<Integer, Long> map = Arrays.stream(nums)  // 创建流
                .boxed()                              // 自动装箱
                .collect(
                        Collectors.groupingBy(        // 分组
                                Function.identity(),  // 返回自己
                                Collectors.counting() // 返回出现次数
                        )
                );
        int count = nums.length >> 1;
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            if (entry.getValue() > count) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 排序
     * 既然出现多次的元素出现次数一定大于 n/2
     * 所以排序后,数组中间位置一定是这个元素
     * 1 1 1 2 3, length = 5, length / 2 = 2, nums[2] = 1
     * -1 0 1 1, length = 4, length / 2 = 2, nums[2] = 1
     * <br/>
     * 时间: O(n log n) 2ms 58.9%
     * 内存: O(log n) 44.5M 77.08%
     * <br/>
     *
     * @param nums 数组
     * @return 出现次数最多的元素
     */
    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法
     * 候选人初始化为nums[0],票数初始化为1
     * 当下一个元素与候选人相同,那么他的票数+1
     * 当下一个元素与候选人不同,那么他的票数-1,如果票数减到0,那么更换候选人为当前元素,票数重置为1
     * 遍历到最后的就是出现多次的元素
     * <br/>
     * 时间: O(n) 1ms 99.91%
     * 内存: O(1) 44.7M 54.80%
     * <br/>
     *
     * @param nums 数组
     * @return 出现次数最多的元素
     */
    public static int majorityElement3(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else if (--count == 0) {
                candidate = num;
                count = 1;
            }
        }
        return candidate;
    }
}
