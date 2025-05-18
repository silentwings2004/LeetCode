package LC2401_2700;
import java.util.*;
public class LC2420_FindAllGoodIndices {
    /**
     * You are given a 0-indexed integer array nums of size n and a positive integer k.
     *
     * We call an index i in the range k <= i < n - k good if the following conditions are satisfied:
     *
     * The k elements that are just before the index i are in non-increasing order.
     * The k elements that are just after the index i are in non-decreasing order.
     * Return an array of all good indices sorted in increasing order.
     *
     * Input: nums = [2,1,1,1,3,4,1], k = 2
     * Output: [2,3]
     *
     * Input: nums = [2,1,1,2], k = 2
     * Output: []
     *
     * Constraints:
     *
     * n == nums.length
     * 3 <= n <= 10^5
     * 1 <= nums[i] <= 10^6
     * 1 <= k <= n / 2
     * @param nums
     * @param k
     * @return
     */
    // S1: 前后缀分解
    // time = O(n), space = O(n)
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n], g = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            if (i > 0 && nums[i] <= nums[i - 1]) f[i] = f[i - 1] + 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            g[i] = 1;
            if (i + 1 < n && nums[i] <= nums[i + 1]) g[i] = g[i + 1] + 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = k; i < n - k; i++) {
            if (f[i - 1] >= k && g[i + 1] >= k) res.add(i);
        }
        return res;
    }

    // S2: Monotonic Stack
    // time = O(n), space = O(n)
    public List<Integer> goodIndices2(int[] nums, int k) {
        int n = nums.length;
        List<Integer> res = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (stack.size() >= k) set.add(i);
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) stack.clear();
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            if (stack.size() >= k && set.contains(i)) res.add(0, i);
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) stack.clear();
            stack.push(i);
        }
        return res;
    }
}
/**
 * 前后缀分解
 * f(i):
 * ai-1 > ai: 1
 * ai-1 <= ai: f(i-1) + 1
 * g(i):
 * ai+1 < ai: 1
 * ai+1 >= ai: g(i+1) + 1
 * f(i-1) >= k && g(i+1) >= k
 */
