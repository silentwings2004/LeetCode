package LC3601_3900;
import java.util.*;
public class LC3854_MinimumOperationstoMakeArrayParityAlternating {
    /**
     * You are given an integer array nums.
     *
     * An array is called parity alternating if for every index i where 0 <= i < n - 1, nums[i] and nums[i + 1] have
     * different parity (one is even and the other is odd).
     *
     * In one operation, you may choose any index i and either increase nums[i] by 1 or decrease nums[i] by 1.
     *
     * Return an integer array answer of length 2 where:
     *
     * answer[0] is the minimum number of operations required to make the array parity alternating.
     * answer[1] is the minimum possible value of max(nums) - min(nums) taken over all arrays that are parity
     * alternating and can be obtained by performing exactly answer[0] operations.
     * An array of length 1 is considered parity alternating.
     *
     * Input: nums = [-2,-3,1,4]
     * Output: [2,6]
     *
     * Input: nums = [0,2,-2]
     * Output: [1,3]
     *
     * Input: nums = [7]
     * Output: [0,0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] makeParityAlternating(int[] nums) {
        if (nums.length == 1) return new int[]{0, 0};
        int[] x = helper(nums, 0);
        int[] y = helper(nums, 1);
        return x[0] != y[0] ? (x[0] < y[0] ? x : y) : (x[1] < y[1] ? x : y);
    }

    private int[] helper(int[] nums, int t) {
        int n = nums.length, mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (((i % 2) ^ (Math.abs(nums[i]) % 2) ^ t) == 1) q.add(i);
            if (q.isEmpty() || q.get(q.size() - 1) != i) {
                mx = Math.max(mx, nums[i]);
                mn = Math.min(mn, nums[i]);
            }
        }
        int[] res = new int[2];
        int m = q.size();
        res[0] = m;
        if (m == 0) return new int[]{0, mx - mn};
        if (m == 1) {
            int v = nums[q.get(0)];
            if (n - m == 1) {
                res[1] = Math.min(Math.abs(mx - (v + 1)), Math.abs(mx - (v - 1)));
            } else {
                int[] w = new int[]{mx, mn, v + 1};
                Arrays.sort(w);
                res[1] = w[2] - w[0];
                w = new int[]{mx, mn, v - 1};
                Arrays.sort(w);
                res[1] = Math.min(res[1], w[2] - w[0]);
            }
        } else {
            Collections.sort(q, (o1, o2) -> nums[o1] - nums[o2]);
            int a = nums[q.get(0)], b = nums[q.get(m - 1)];
            if (n - m == 1) {
                int[] w = new int[]{mx, a + 1, b + 1};
                Arrays.sort(w);
                res[1] = w[2] - w[0];
                w = new int[]{mx, a - 1, b - 1};
                Arrays.sort(w);
                res[1] = Math.min(res[1], w[2] - w[0]);
                w = new int[]{mx, a + 1, b - 1};
                Arrays.sort(w);
                res[1] = Math.min(res[1], w[2] - w[0]);
                w = new int[]{mx, a - 1, b + 1};
                Arrays.sort(w);
                res[1] = Math.min(res[1], w[2] - w[0]);
            } else {
                int[] w = new int[]{mx, mn, a + 1, b + 1};
                Arrays.sort(w);
                res[1] = w[3] - w[0];
                w = new int[]{mx, mn, a - 1, b - 1};
                Arrays.sort(w);
                res[1] = Math.min(res[1], w[3] - w[0]);
                w = new int[]{mx, mn, a + 1, b - 1};
                Arrays.sort(w);
                res[1] = Math.min(res[1], w[3] - w[0]);
                w = new int[]{mx, mn, a - 1, b + 1};
                Arrays.sort(w);
                res[1] = Math.min(res[1], w[3] - w[0]);
            }
        }
        return res;
    }
}