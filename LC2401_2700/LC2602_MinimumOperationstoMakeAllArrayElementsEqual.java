package LC2401_2700;
import java.util.*;
public class LC2602_MinimumOperationstoMakeAllArrayElementsEqual {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * You are also given an integer array queries of size m. For the ith query, you want to make all of the elements
     * of nums equal to queries[i]. You can perform the following operation on the array any number of times:
     *
     * Increase or decrease an element of the array by 1.
     * Return an array answer of size m where answer[i] is the minimum number of operations to make all elements of
     * nums equal to queries[i].
     *
     * Note that after each query the array is reset to its original state.
     *
     * Input: nums = [3,1,6,8], queries = [1,5]
     * Output: [14,10]
     *
     * Input: nums = [2,9,6,3], queries = [10]
     * Output: [20]
     *
     * Constraints:
     *
     * n == nums.length
     * m == queries.length
     * 1 <= n, m <= 10^5
     * 1 <= nums[i], queries[i] <= 10^9
     * @param nums
     * @param queries
     * @return
     */
    // time = O((m + n) * logn), space = O(n)
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        List<Long> res = new ArrayList<>();

        for (int x : queries) {
            if (nums[n - 1] <= x) res.add((long) x * n - s[n]);
            else if (nums[0] >= x) res.add(s[n] - (long) x * n);
            else {
                int idx = helper(nums, x); // find 1st >= x pos
                long sum = (long) idx * x - s[idx] + (s[n] - s[idx] - (long) x * (n - idx));
                res.add(sum);
            }
        }
        return res;
    }

    private int helper(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}