package LC1201_1500;
import java.util.*;
public class LC1381_DesignaStackWithIncrementOperation {
    /**
     * Design a stack which supports the following operations.
     *
     * Implement the CustomStack class:
     *
     * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack
     * or do nothing if the stack reached the maxSize.
     * void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
     * int pop() Pops and returns the top of stack or -1 if the stack is empty.
     * void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements
     * in the stack, just increment all the elements in the stack.
     *
     * Input
     * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
     * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
     * Output
     * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
     *
     * Constraints:
     *
     * 1 <= maxSize <= 1000
     * 1 <= x <= 1000
     * 1 <= k <= 1000
     * 0 <= val <= 100
     * At most 1000 calls will be made to each method of increment, push and pop each separately.
     * @param maxSize
     */
    // S1
    // time = O(k), space = O(n)
    int[] stk;
    int tt, mx;
    public LC1381_DesignaStackWithIncrementOperation(int maxSize) {
        mx = maxSize;
        stk = new int[maxSize + 1];
        tt = 0;
    }

    public void push(int x) {
        if (tt < mx) stk[++tt] = x;
    }

    public int pop() {
        return tt == 0 ? -1 : stk[tt--];
    }

    public void increment(int k, int val) {
        for (int i = 1; i <= Math.min(k, tt); i++) stk[i] += val;
    }

    // S2: lazy add
    // time = O(1), space = O(n)
    class CustomStack {
        int[] stk, inc;
        int tt, mx;
        public CustomStack(int maxSize) {
            mx = maxSize;
            stk = new int[mx + 1];
            inc = new int[mx + 1];
            tt = 0;
        }

        public void push(int x) {
            if (tt < mx) stk[++tt] = x;
        }

        public int pop() {
            if (tt == 0) return -1;
            int val = stk[tt] + inc[tt];
            inc[tt - 1] += inc[tt];
            inc[tt] = 0;
            tt--;
            return val;
        }

        public void increment(int k, int val) {
            int x = Math.min(k, tt);
            inc[x] += val;
        }
    }

    // S3: diff array
    // time = O(1), space = O(n)
    class CustomStack2 {
        int[] nums, offset;
        int maxSize, count, diff;
        public CustomStack2(int maxSize) {
            this.maxSize = maxSize;
            this.count = 0;
            this.nums = new int[maxSize];
            this.offset = new int[maxSize];
        }

        public void push(int x) {
            if (count == maxSize) return;

            if (count >= 1) offset[count - 1] += diff;
            diff = 0;
            nums[count] = x;
            offset[count] = 0;
            count++;
        }

        public int pop() {
            if (count == 0) return -1;
            diff += offset[count - 1];
            int res = nums[count - 1] + diff;
            count--;
            return res;
        }

        public void increment(int k, int val) {
            if (count == 0) return;
            offset[Math.min(k - 1, count - 1)] += val;
        }
    }
}
/**
 * 差分数组
 * 假设当我们遇到increment(k,val)操作时，我们设置offset[k]=value。
 * 这样当我们退栈的过程中遇到第k个元素的时候，就知道从此往下继续退栈的话，所有的元素都要加上一个diff = value。
 * 如果往下退栈的过程中再遇到另一个offset[k2] = value2，就知道从k2往下继续退栈出的元素都要加上一个diff = value+value2.
 * 特别注意的是，当如果你需要入栈第k个元素的时候，你需要记录offset[k-1] = diff，同时将手头的diff清空。
 *              x x x x x x  y  ...
 *                        ^
 * offset                 5
 * diff         3
 */