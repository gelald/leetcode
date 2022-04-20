package com.example.leetcode;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <br/>
 * 有效字符串需满足：
 * <br/>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <br/>
 * 输入：s = "()[]{}"
 * 输出：true
 * <br/>
 * 输入：s = "([)]"
 * 输出：false
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/2
 */
public class E20ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid1("{[]}"));
        System.out.println(isValid2("{[]}"));
    }

    /**
     * 方法一：
     * 暴力法，不断去除成对的括号
     * <br/>
     * 时间：114ms 6.07%
     * 内存：37M   5.01%
     * <br/>
     *
     * @param s 输入的符号
     * @return 符号是否匹配
     */
    public static boolean isValid1(String s) {
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replaceAll("\\(\\)", "");
            s = s.replaceAll("\\[]", "");
            s = s.replaceAll("\\{}", "");
        }
        return Objects.equals(s, "");
    }

    /**
     * 方法二：
     * 缓存右括号与左括号的映射关系
     * 迭代字符串
     * 如果当前值是左括号，则添加到栈
     * 如果当前值是右括号，则检查栈顶元素是否是与之对应的左括号
     * 如果是，则移除栈顶左括号
     * 否则直接返回false
     * 迭代到最后检查栈中是否还有没完成匹配的元素，如果有代表匹配失败
     * <br/>
     * 时间：2ms   54.06%
     * 内存：39.6M 19.78%
     * <br/>
     *
     * @param s 输入的符号
     * @return 符号是否匹配
     */
    public static boolean isValid2(String s) {
        // 存储右括号与左括号的关联关系
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Deque<Character> deque = new ArrayDeque<>();
        char[] charArray = s.toCharArray();
        for (char item : charArray) {
            if (map.containsKey(item)) {
                //是右符号
                if (map.get(item).equals(deque.peekFirst())) {
                    deque.pollFirst();
                } else {
                    return false;
                }
            } else {
                //是左符号
                deque.offerFirst(item);
            }
        }
        return deque.isEmpty();
    }
}
