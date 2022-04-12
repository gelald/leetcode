package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <br/>
 * 输入：[3,2,3]
 * 输出：[3]
 * <br/>
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/12
 */
public class M229MajorityElementII {
    public static void main(String[] args) {
        System.out.println(majorityElement1(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
        System.out.println(majorityElement1(new int[]{3, 2, 3}));
        System.out.println(majorityElement2(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
        System.out.println(majorityElement2(new int[]{3, 2, 3}));
    }

    public static List<Integer> majorityElement1(int[] nums) {
        List<Integer> result = new ArrayList<>(2);
        Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * 摩尔投票法
     * 投票阶段-计数阶段
     * <br/>
     * 时间: 1ms 99.96%
     * 内存: 45M 42.30%
     * <br/>
     *
     * @param nums 数组
     * @return 众数
     */
    public static List<Integer> majorityElement2(int[] nums) {
        // 出现超过 n/3 次的元素一定小于等于 3-1=2 个
        List<Integer> result = new ArrayList<>(2);
        // 投票阶段
        int candidate1 = nums[0], candidate2 = nums[0];
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            // 如果和候选人1匹配上了,那么候选人1的票数+1
            if (candidate1 == num) {
                count1++;
                continue;
            }
            // 如果和候选人2匹配上了,那么候选人2的票数+1
            if (candidate2 == num) {
                count2++;
                continue;
            }
            // 如果和候选人1、2都没有匹配上，且候选人1的票数扣光了
            // 那么候选人1更换为当前元素，且票数重置为1
            if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
                continue;
            }
            // 如果和候选人1、2都没有匹配上，且候选人2的票数扣光了
            // 那么候选人2更换为当前元素，且票数重置为1
            if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
                continue;
            }
            // 如果和候选人1、2都没有匹配上，而且候选人1、2的票数都扣光了
            // 那么同时抵扣两个候选人的票数
            count1--;
            count2--;
        }
        // 计数阶段
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        // 出现次数超过 n/3 的元素可以放入到最后的结果中
        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }
}
