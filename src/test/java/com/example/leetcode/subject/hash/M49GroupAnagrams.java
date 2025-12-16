package com.example.leetcode.subject.hash;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <br>
 * 示例：输入：strs = ["eat", "tea", "tan", "ate", "nat", "bat"]；输出：[["bat"],["nat","tan"],["ate","eat","tea"]]
 * <br>
 * 示例：输入: strs = [""]；输出：[[""]]
 * <br>
 * 示例：输入: strs = ["a"]；输出: [["a"]]
 */
public class M49GroupAnagrams {

    @Test
    void groupAnagramsHandle() {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // [["bat"],["nat","tan"],["ate","eat","tea"]]

        System.out.println(groupAnagrams(new String[]{""}));
        // [[""]]

        System.out.println(groupAnagrams(new String[]{"a"}));
        // [["a"]]
    }

    /**
     * n: 输入的字符串数组 strs 的长度
     * k: 字符串的平均长度
     * <br>
     * 时间复杂度: O(n * k log k)
     * <ul>
     *     <li>遍历数组 strs，共 n 次循环</li>
     *     <li>字符串转为字符数组 O(k)</li>
     *     <li>字符数组排序 O(k log k)</li>
     * </ul>
     * 空间复杂度: O(n * k) (map存储、临时创建的字符数组)
     */
    private List<List<String>> groupAnagrams(String[] strs) {
        /*
        互为字母异位词的两个字符串，包含的字母相同，但是顺序不同，可以考虑把他们按自然语言排序
        假设用HashMap来记录，以排序后的结果作为key，把原字符串放入value，value是一个集合
        最后把HashMap转换为ArrayList来输出
         */
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 把字符串转换为字符数组
            char[] charArray = str.toCharArray();
            // 对字符数组排序来达到对字符串排序的效果
            Arrays.sort(charArray);
            // 把字符数组转换为字符串来作为 HashMap 的 key
            String key = new String(charArray);
            // 把相同排序结果的字符串放到同一个结果集合中
            List<String> value = map.getOrDefault(key, new ArrayList<>());
            value.add(str);
            // 更新这个 HashMap
            map.put(key, value);
        }
        // 对HashMap的values集合转换为线性集合返回，可以任意顺序
        ArrayList<List<String>> result = new ArrayList<>(map.values());
        return result;
    }
}
