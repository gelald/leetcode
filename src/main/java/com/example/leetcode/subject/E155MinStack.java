package com.example.leetcode.subject;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 设计一个支持 push pop top 等操作,并在在常数时间内检索到最小元素的栈
 *
 * @author WuYingBin
 * date: 2022/4/8
 */
public class E155MinStack {
    public static void main(String[] args) {
        /*MinStack1 minStack = new MinStack1();
        minStack.push(-2);
        System.out.println("minStack.push(-2) ==> " + minStack.deque);
        minStack.push(0);
        System.out.println("minStack.push(0) ==> " + minStack.deque);
        minStack.push(-3);
        System.out.println("minStack.push(-3) ==> " + minStack.deque);
        System.out.println("minStack.getMin() ==> " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.pop() ==> " + minStack.deque);
        System.out.println("minStack.top() ==> " + minStack.top());
        System.out.println("minStack.getMin() ==> " + minStack.getMin());*/

        /*MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        System.out.println("minStack.push(-2) ==> " + minStack.deque);
        minStack.push(0);
        System.out.println("minStack.push(0) ==> " + minStack.deque);
        minStack.push(-3);
        System.out.println("minStack.push(-3) ==> " + minStack.deque);
        System.out.println("minStack.getMin() ==> " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.pop() ==> " + minStack.deque);
        System.out.println("minStack.top() ==> " + minStack.top());
        System.out.println("minStack.getMin() ==> " + minStack.getMin());*/

        MinStack3 minStack = new MinStack3();
        minStack.push(-2);
        System.out.println("minStack.push(-2) ==> " + minStack.deque);
        minStack.push(0);
        System.out.println("minStack.push(0) ==> " + minStack.deque);
        minStack.push(-3);
        System.out.println("minStack.push(-3) ==> " + minStack.deque);
        System.out.println("minStack.getMin() ==> " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.pop() ==> " + minStack.deque);
        System.out.println("minStack.top() ==> " + minStack.top());
        System.out.println("minStack.getMin() ==> " + minStack.getMin());
    }

    /**
     * 每次push、pop操作都手动实现最小值的查询
     * <br/>
     * 时间: 4ms   94.39%
     * 内存: 43MB  57.70%
     * <br/>
     */
    static class MinStack1 {
        public Deque<Integer> deque;
        public int min;

        /**
         * 初始化堆栈对象
         */
        public MinStack1() {
            this.deque = new ArrayDeque<>();
        }

        /**
         * 将元素x推入栈
         * 每次插入后,都需要确定最小元素
         *
         * @param x 元素
         */
        public void push(int x) {
            this.deque.offerFirst(x);
            if (this.deque.size() == 1) {
                this.min = x;
            } else {
                if (x < min) {
                    min = x;
                }
            }
        }

        /**
         * 删除堆栈顶部元素
         * 如果删除的不是最小元素,直接删除
         * 如果删除的恰好是最小元素,那么在删除后要重新寻找最小元素
         * 如果删除后栈空了,那么最小元素就是默认值
         */
        public void pop() {
            Integer peek = this.deque.peekFirst();
            if (peek == null) {
                throw new RuntimeException();
            }
            if (!peek.equals(min)) {
                this.deque.pollFirst();
            } else {
                this.deque.pollFirst();
                if (this.deque.isEmpty()) {
                    this.min = 0;
                    return;
                }
                peek = this.deque.peekFirst();
                if (peek == null) {
                    throw new RuntimeException();
                }
                // 重新寻找最小值
                this.min = this.deque.peekFirst();
                for (Integer integer : this.deque) {
                    if (integer < min) {
                        min = integer;
                    }
                }
            }
        }

        /**
         * 获取堆栈顶部元素
         *
         * @return 堆栈顶部的元素
         */
        public int top() {
            Integer peek = this.deque.peekFirst();
            if (peek == null) {
                throw new RuntimeException();
            }
            return peek;
        }

        /**
         * 获取堆栈中最小元素
         *
         * @return 最小元素
         */
        public int getMin() {
            return this.min;
        }
    }

    /**
     * 辅助栈，每次插入元素时都同时插入当前最小值到辅助栈内
     * <br/>
     * 时间: O(1)  4ms  94.39%
     * 内存: O(n)  43.2MB  41.02%
     * <br/>
     */
    static class MinStack2 {
        public Deque<Integer> deque;
        public Deque<Integer> minDeque;

        public MinStack2() {
            this.deque = new ArrayDeque<>();
            this.minDeque = new ArrayDeque<>();
            this.minDeque.offerFirst(Integer.MAX_VALUE);
        }

        public void push(int x) {
            if (this.deque.isEmpty()) {
                this.deque.offerFirst(x);
                this.minDeque.offerFirst(x);
            } else {
                this.deque.offerFirst(x);
                this.minDeque.offerFirst(Math.min(x, this.minDeque.getFirst()));
            }
        }

        public void pop() {
            this.deque.pollFirst();
            this.minDeque.pollFirst();
        }

        public int top() {
            return this.deque.peekFirst();
        }

        public int getMin() {
            return this.minDeque.peekFirst();
        }
    }

    /**
     * 栈中存储的结构为[当前元素，插入元素后的最小值]
     * <br/>
     * 时间: O(1)  4ms  94.39%
     * 内存: O(n)  42.9MB  70.94%
     * <br/>
     */
    static class MinStack3 {
        public Deque<Integer[]> deque;

        public MinStack3() {
            this.deque = new LinkedList<>();
        }

        public void push(int x) {
            if (this.deque.isEmpty()) {
                this.deque.offerFirst(new Integer[]{x, x});
            } else {
                this.deque.offerFirst(new Integer[]{x, Math.min(x, this.deque.getFirst()[1])});
            }
        }

        public void pop() {
            this.deque.pollFirst();
        }

        public int top() {
            return this.deque.peekFirst()[0];
        }

        public int getMin() {
            return this.deque.peekFirst()[1];
        }
    }
}
