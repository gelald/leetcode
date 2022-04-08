package com.example.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push pop top 等操作,并在在常数时间内检索到最小元素的栈
 * @author WuYingBin
 * date: 2022/4/8
 */
public class E155MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
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

    static class MinStack {
        public Deque<Integer> deque;
        public int min;
        /**
         * 初始化堆栈对象
         */
        public MinStack() {
            this.deque = new ArrayDeque<>();
        }

        /**
         * 将元素x推入栈
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
         */
        public void pop() {
            Integer peek = this.deque.peek();
            if (peek == null) {
                throw new RuntimeException();
            }
            if (!peek.equals(min)) {
                this.deque.pollFirst();
            } else {
                this.deque.pollFirst();
                peek = this.deque.peek();
                if (peek == null) {
                    throw new RuntimeException();
                }
                // 重新寻找最小值
                this.min = this.deque.peek();
                for (Integer integer : this.deque) {
                    if (integer < min) {
                        min = integer;
                    }
                }
            }
        }

        /**
         * 获取堆栈顶部元素
         * @return 堆栈顶部的元素
         */
        public int top() {
            Integer peek = this.deque.peek();
            if (peek == null) {
                throw new RuntimeException();
            }
            return peek;
        }

        /**
         * 获取堆栈中最小元素
         * @return 最小元素
         */
        public int getMin() {
            return this.min;
        }
    }
}
