package LC3001_3300;
import java.util.*;
public class LC3066_MinimumOperationstoExceedThresholdValueII {
    /**
     * You are given a 0-indexed integer array nums, and an integer k.
     *
     * In one operation, you will:
     *
     * Take the two smallest integers x and y in nums.
     * Remove x and y from nums.
     * Add min(x, y) * 2 + max(x, y) anywhere in the array.
     * Note that you can only apply the described operation if nums contains at least two elements.
     *
     * Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
     *
     * Input: nums = [2,11,10,1,3], k = 10
     * Output: 2
     *
     * Input: nums = [1,1,2,4,9], k = 20
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= nums.length <= 2 * 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * The input is generated such that an answer always exists. That is, there exists some sequence of operations after
     * which all elements of the array are greater than or equal to k.
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int x : nums) pq.offer((long)x);
        int res = 0;

        while (pq.size() > 1) {
            if (pq.peek() >= k) break;
            res++;
            long x = pq.poll(), y = pq.poll();
            long t = 2L * Math.min(x, y) + Math.max(x, y);
            pq.offer(t);
        }
        return res;
    }
}