package LC1201_1500;
import java.util.*;
public class LC1441_BuildanArrayWithStackOperations {
    /**
     * You are given an integer array target and an integer n.
     *
     * You have an empty stack with the two following operations:
     *
     * "Push": pushes an integer to the top of the stack.
     * "Pop": removes the integer on the top of the stack.
     * You also have a stream of the integers in the range [1, n].
     *
     * Use the two stack operations to make the numbers in the stack (from the bottom to the top) equal to target. You
     * should follow the following rules:
     *
     * If the stream of the integers is not empty, pick the next integer from the stream and push it to the top of the
     * stack.
     * If the stack is not empty, pop the integer at the top of the stack.
     * If, at any moment, the elements in the stack (from the bottom to the top) are equal to target, do not read new
     * integers from the stream and do not do more operations on the stack.
     * Return the stack operations needed to build target following the mentioned rules. If there are multiple valid
     * answers, return any of them.
     *
     * Input: target = [1,3], n = 3
     * Output: ["Push","Push","Pop","Push"]
     *
     * Input: target = [1,2,3], n = 3
     * Output: ["Push","Push","Push"]
     *
     * Constraints:
     *
     * 1 <= target.length <= 100
     * 1 <= n <= 100
     * 1 <= target[i] <= n
     * target is strictly increasing.
     * @param target
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        int idx = 1;
        for (int x : target) {
            while (x != idx) {
                res.add("Push");
                res.add("Pop");
                idx++;
            }
            res.add("Push");
            idx++;
        }
        return res;
    }
}
