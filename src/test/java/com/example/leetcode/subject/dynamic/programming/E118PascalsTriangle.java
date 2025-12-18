package com.example.leetcode.subject.dynamic.programming;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <br>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <br>
 * 示例：输入: numRows = 5；输出：[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <br>
 * 示例：输入: numRows = 1；输出: [[1]]
 * <br>
 * 1 <= numRows <= 30
 */
public class E118PascalsTriangle {

    @Test
    void generate() {
        System.out.println(generate01(1));
        System.out.println(generate01(2));
        System.out.println(generate01(3));
        System.out.println(generate01(4));
        System.out.println(generate01(5));
        System.out.println(generate01(6));
    }

    /**
     * 时间复杂度：O(numRows²)
     * 空间复杂度：O(numRows²)
     */
    public List<List<Integer>> generate01(int numRows) {
        // 存储最终结果的集合
        List<List<Integer>> result = new ArrayList<>();
        // 第一行元素
        List<Integer> firstRow = new ArrayList<>();
        // 第一行一定是 1
        firstRow.add(1);
        result.add(firstRow);
        if (numRows == 1) {
            // 如果只有一行，直接返回不参与循环
            return result;
        }

        // 循环，直到结果集中有numRows行
        while(result.size() < numRows) {
            List<Integer> currentRow = new ArrayList<>();
            // 获取上一行元素，需要进行相加运算
            List<Integer> lastRow = result.get(result.size() - 1);
            // 循环终止条件：第几行就需要有几个元素
            for (int i = 0; i < result.size() + 1; i++) {
                if (i == 0) {
                    // 每一行的首尾都是1
                    currentRow.add(1);
                    continue;
                }
                if (i == result.size()) {
                    // 每一行的首尾都是1
                    currentRow.add(1);
                    continue;
                }
                // 剩下的元素是上方左右元素相加
                // 获取上一行的 i-1 和 i 号元素，相加
                currentRow.add(lastRow.get(i - 1) + lastRow.get(i));
            }
            result.add(currentRow);
        }

        return result;
    }

    /**
     * 逻辑优化
     */
    public List<List<Integer>> generate02(int numRows) {
        // 预分配空间
        List<List<Integer>> result = new ArrayList<>(numRows);
        // 第一行
        result.add(List.of(1));
        for (int i = 1; i < numRows; i++) {
            // 预分配空间
            List<Integer> currentRow = new ArrayList<>(i + 1);
            // 首尾都是1
            currentRow.add(1);
            for (int j = 1; j < i; j++) {
                // 左上方的数 + 正上方的数
                List<Integer> lastRow = result.get(i - 1);
                currentRow.add(lastRow.get(j - 1) + lastRow.get(j));
            }
            // 首尾都是1
            currentRow.add(1);
            result.add(currentRow);
        }
        return result;
    }
}
