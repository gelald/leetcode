package com.example.leetcode.subject;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <br/>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <br/>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <br/>
 *
 * @author WuYingBin
 * date: 2022/4/19
 */
public class E121BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices1 = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices2 = new int[]{7, 6, 4, 3, 1};
        System.out.println(maxProfit1(prices1));
        System.out.println(maxProfit1(prices2));
        System.out.println(maxProfit2(prices1));
        System.out.println(maxProfit2(prices2));
    }

    /**
     * 暴力法
     * 每次遍历一个元素的时候，遍历它后面的元素，计算各自的差值，最大的差值就是最大利润
     * <br/>
     * 时间: O(n^2)
     * 内存: O(1)
     * <br/>
     *
     * @param prices 价格
     * @return 最大利润
     */
    public static int maxProfit1(int[] prices) {
        int maxProfit = 0;
        for (int i = 0, length = prices.length; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
    }

    /**
     * 一次遍历
     * 在遍历的过程中
     * 确定最小值和计算最大利润，每次只能完成其一
     * 而且计算最大利润总是在确定了最小值之后完成的
     * <br/>
     * 时间: O(n) 1ms 100%
     * 内存: O(1) 57.3M 58%
     * <br/>
     *
     * @param prices 价格
     * @return 利润
     */
    public static int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}
