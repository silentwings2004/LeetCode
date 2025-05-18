package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MinStack
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 155. Min Stack
 */
public class LC155_MinStack {
    /**
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     *
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     *
     *
     * Example:
     *
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> Returns -3.
     * minStack.pop();
     * minStack.top();      --> Returns 0.
     * minStack.getMin();   --> Returns -2.
     */
    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    /** initialize your data structure here. */
    // S1: Use two stacks
    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        mainStack.push(x);
        if (minStack.empty() || minStack.peek() >= x) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        if (mainStack.empty()) return;
        mainStack.pop();
        minStack.pop();
    }

    public int top() {
        if(mainStack.empty()) throw new RuntimeException("Empty stack!");
        return mainStack.peek();
    }

    public int getMin() {
        if (mainStack.empty()) throw new RuntimeException("Empty stack!");
        return minStack.peek();
    }

    // S2: Use one stack
    private Stack<Integer> minStack;
    private int min_val;
    /** initialize your data structure here. */
    public MinStack() {
        minStack = new Stack<>();
        min_val = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min_val) { // 注意 x == min_val时也要压入min_val和x2个值，否则弹栈的时候会出现错位！！！
            minStack.push(min_val);
            min_val = x;
        }
        minStack.push(x);
    }

    public void pop() {
        if (minStack.empty()) return;
        if (minStack.pop() == min_val) {
            if (!minStack.empty()) { // 弹栈至最后一个元素时必须要check上面if语句后stack是否为空，因为下面可能就无元素可弹栈了
                min_val = minStack.pop();
            } else return;

        }
    }

    public int top() {
        if (minStack.empty()) throw new RuntimeException("Empty stack!");
        return minStack.peek();
    }

    public int getMin() {
        if (minStack.empty()) throw new RuntimeException("Empty stack!");
        return min_val;
    }
}
