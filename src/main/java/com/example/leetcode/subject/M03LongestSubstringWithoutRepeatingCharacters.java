package com.example.leetcode.subject;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <br>
 * ===
 * <br>
 * 输入: s = "abcabcbb"
 * <br>
 * 输出: 3
 * <br>
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <br>
 * ===
 * <br>
 * 输入: s = "bbbbb"
 * <br>
 * 输出: 1
 * <br>
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <br>
 * ===
 * <br>
 * 输入: s = "pwwkew"
 * <br>
 * 输出: 3
 * <br>
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <br>
 *
 * @author WuYingBin
 * date: 2022/9/26
 */
public class M03LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s1 = "abcabcbb";
        System.out.printf("输入:%s\n", s1);
        System.out.printf("结果:%s\n", lengthOfLongestSubstring(s1));
        String s2 = "bbbbb";
        System.out.printf("输入:%s\n", s2);
        System.out.printf("结果:%s\n", lengthOfLongestSubstring(s2));
        String s3 = "pwwpkew";
        System.out.printf("输入:%s\n", s3);
        System.out.printf("结果:%s\n", lengthOfLongestSubstring(s3));
    }

    /**
     * 时间复杂度：O(n)，n是字符串长度，这个方法需要迭代字符串中的字符
     */
    public static int lengthOfLongestSubstring(String s) {
        // 滑动窗口左边的索引
        int startIndex = 0;
        // 滑动窗口最大长度
        int maxLength = 0;
        char[] chars = s.toCharArray();
        // 记录元素最新位置
        Map<Character, Integer> map = new HashMap<>();
        // 索引i除了负责遍历，也充当滑动窗口右边的索引
        for (int i = 0; i < chars.length; i++) {
            // 如果元素包含在map中
            // 1.abca的情况，此时迭代到后一个a，本来最长的子串是abc，
            // 这时候出现了重复的a，我们需要把子串设置为bca，所以滑动窗口左边的索引需要往前移动，取a上一次出现的索引+1
            //
            // 2.abba的情况，此时迭代到后一个b，本来最长的子串是ab，
            // 这时候出现了重复的b，我们需要把滑动窗口滑动到b上一次出现的索引后一个索引，所以设置startIndex为2
            // 然后下一个元素是a，a上一次出现的索引是0，此时不能像上面情况处理，因为子串是ba，所以还是要保持为2
            //
            // 核心是遇到重复的元素就要重新需要子串，但是子串的索引只能加不能减
            if (map.containsKey(chars[i])) {
                startIndex = Math.max(startIndex, map.get(chars[i]) + 1);
            }
            map.put(chars[i], i);
            // 索引相减再+1才能得到元素个数，如2-0，其实有3个元素，另外要求最大的，所以每一次都要进行比较
            maxLength = Math.max(maxLength, i - startIndex + 1);
        }
        return maxLength;
    }
}
