package LC901_1200;
import java.util.*;
public class LC1172_DinnerPlateStacks {
    /**
     * You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks
     * has the same maximum capacity.
     *
     * Implement the DinnerPlates class:
     *
     * DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks capacity.
     * void push(int val) Pushes the given integer val into the leftmost stack with a size less than capacity.
     * int pop() Returns the value at the top of the rightmost non-empty stack and removes it from that stack, and
     * returns -1 if all the stacks are empty.
     * int popAtStack(int index) Returns the value at the top of the stack with the given index index and removes it
     * from that stack or returns -1 if the stack with that given index is empty.
     *
     * Input
     * ["DinnerPlates", "push", "push", "push", "push", "push", "popAtStack", "push", "push", "popAtStack", "popAtStack",
     * "pop", "pop", "pop", "pop", "pop"]
     * [[2], [1], [2], [3], [4], [5], [0], [20], [21], [0], [2], [], [], [], [], []]
     * Output
     * [null, null, null, null, null, null, 2, null, null, 20, 21, 5, 4, 3, 1, -1]
     *
     * Constraints:
     *
     * 1 <= capacity <= 2 * 10^4
     * 1 <= val <= 2 * 10^4
     * 0 <= index <= 10^5
     * At most 2 * 10^5 calls will be made to push, pop, and popAtStack.
     * @param capacity
     */
    List<Stack<Integer>> stacks;
    TreeSet<Integer> set;
    int capacity;
    public LC1172_DinnerPlateStacks(int capacity) {
        this.stacks = new ArrayList<>();
        this.set = new TreeSet<>();
        this.capacity = capacity;
    }

    // time = O(logn), space = O(n)
    public void push(int val) {
        if (set.isEmpty()) { // all previous stacks are full
            stacks.add(new Stack<>());
            set.add(stacks.size() - 1);
        }

        Integer fk = set.first();
        stacks.get(fk).push(val);
        if (stacks.get(fk).size() == capacity) set.remove(fk);
    }

    // time = O(logn), space = O(n)
    public int pop() {
        return helper(stacks.size() - 1);
    }

    // time = O(logn), space = O(n)
    public int popAtStack(int index) {
        return helper(index);
    }

    private int helper(int i) {
        if (i < 0 || i >= stacks.size() || stacks.get(i).isEmpty()) return -1;

        int res = stacks.get(i).pop();
        set.add(i); // idx-th stack must not full after pop()

        // the last element in our list of stacks MUST be nonempty.
        while (stacks.size() > 0 && stacks.get(stacks.size() - 1).isEmpty()) {
            int lk = set.last();
            set.remove(lk); // if empty, remove from both set and stacks
            stacks.remove(lk);
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    class DinnerPlates {
        List<Stack<Integer>> q;
        TreeSet<Integer> s1, s2;
        int c, idx;
        public DinnerPlates(int capacity) {
            q = new ArrayList<>();
            s1 = new TreeSet<>();
            s2 = new TreeSet<>();
            c = capacity;
            idx = 0;

            s1.add(0);
            q.add(new Stack<>());
        }

        public void push(int val) {
            if (s1.size() == 0) {
                q.add(new Stack<>());
                idx++;
                s1.add(idx);
            }
            Integer fk = s1.first();
            q.get(fk).push(val);
            if (q.get(fk).size() == c) s1.remove(fk);
            s2.add(fk);
        }

        public int pop() {
            if (s2.size() == 0) return -1;
            return popAtStack(s2.last());
        }

        public int popAtStack(int index) {
            if (!s2.contains(index)) return -1;
            int t = q.get(index).pop();
            if (q.get(index).size() == 0) s2.remove(index);
            s1.add(index);
            return t;
        }
    }

    // S3
    class DinnerPlates2 {
        // time = O(nlogn), space = O(n)
        List<Stack<Integer>> stks;
        PriorityQueue<Integer> pq;
        int last, capacity;
        public DinnerPlates2(int capacity) {
            stks = new ArrayList<>();
            pq = new PriorityQueue<>();
            last = -1;
            this.capacity = capacity;
        }

        public void push(int val) {
            if (pq.isEmpty()) {
                int id = stks.size();
                stks.add(new Stack<>());
                pq.offer(id);
            }
            Stack<Integer> stk = stks.get(pq.peek());
            stk.push(val);
            last = Math.max(last, pq.peek());
            if (stk.size() == capacity) pq.poll();
        }

        public int pop() {
            return popAtStack(last);
        }

        public int popAtStack(int index) {
            if (index == -1 || index > last) return -1;
            Stack<Integer> stk = stks.get(index);
            if (stk.size() == 0) return -1;
            int res = stk.pop();
            if (stk.size() == capacity - 1) pq.offer(index);
            while (last >= 0 && stks.get(last).size() == 0) last--;
            return res;
        }
    }
}