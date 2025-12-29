package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * <br>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <br>
 * 示例：输入: s = "leetcode", wordDict = ["leet", "code"]；输出: true
 * <br>
 * 示例：输入: s = "applepenapple", wordDict = ["apple", "pen"]；输出: true
 * <br>
 * 示例：输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]；输出: false
 */
public class M139WordBreak {

    @Test
    void wordBreak() {
        System.out.println(wordBreak01("leetcode", List.of("leet", "code")));
    }

    public boolean wordBreak01(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 以 i 结尾的字符串的结果依赖
                // 以 j 结尾的字符串结果 && wordDict 是否包含从 j 到 i 的子字符串
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
