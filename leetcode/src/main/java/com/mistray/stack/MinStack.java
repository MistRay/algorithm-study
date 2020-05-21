package com.mistray.stack;

import java.util.Stack;

class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    // 思路:
    // 使用辅助栈的方式,让取最小值的时间复杂度保持在O(1)
    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        dataStack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x){
            minStack.push(x);
        }
    }

    public void pop() {
        if (dataStack.pop().equals(minStack.peek()) && minStack.size() > 1){
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}