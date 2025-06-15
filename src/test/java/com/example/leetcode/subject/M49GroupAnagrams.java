package com.example.leetcode.subject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author ngwingbun
 * date: 2025/6/12
 */
public class M49GroupAnagrams {

    @Test
    void groupAnagramsHandle() {
        List<List<String>> result1 = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        List<List<String>> expectedResult1 = List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea"));
        Assertions.assertEquals(result1.size(), expectedResult1.size());

        List<List<String>> result2 = groupAnagrams(new String[]{""});
        List<List<String>> expectedResult2 = List.of(List.of(""));
        Assertions.assertEquals(result2.size(), expectedResult2.size());

        List<List<String>> result3 = groupAnagrams(new String[]{"a"});
        List<List<String>> expectedResult3 = List.of(List.of("a"));
        Assertions.assertEquals(result3.size(), expectedResult3.size());
    }


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
            // 把字符数组转换为字符串来作为HashMap的key
            String key = new String(charArray);
            // 把相同排序结果的字符串放到同一个结果集合中
            List<String> value = map.getOrDefault(key, new ArrayList<>());
            value.add(str);
            // 更新这个HashMap
            map.put(key, value);
        }
        // 对HashMap的values集合转换为线性集合返回，可以任意顺序
        ArrayList<List<String>> result = new ArrayList<>(map.values());
        System.out.println(result);
        return result;
    }
}
