package LC2401_2700;
import java.util.*;
public class LC2599_MakethePrefixSumNonnegative {
    /**
     * You are given a 0-indexed integer array nums. You can apply the following operation any number of times:
     *
     * Pick any element from nums and put it at the end of nums.
     * The prefix sum array of nums is an array prefix of the same length as nums such that prefix[i] is the sum of all
     * the integers nums[j] where j is in the inclusive range [0, i].
     *
     * Return the minimum number of operations such that the prefix sum array does not contain negative integers. The
     * test cases are generated such that it is always possible to make the prefix sum array non-negative.
     *
     * Input: nums = [2,3,-5,4]
     * Output: 0
     *
     * Input: nums = [3,-5,-2,6]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -109 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int makePrefSumNonNegative(int[] nums) {
        int n = nums.length, res = 0;
        int[] s = new int[n + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
            if (nums[i - 1] < 0) pq.offer(nums[i - 1]);
            while (!pq.isEmpty() && s[i] < 0) {
                s[i] -= pq.poll();
                res++;
            }
        }
        return res;
    }
}