package LC2401_2700;
import java.util.*;
public class LC2530_MaximalScoreAfterApplyingKOperations {
    /**
     * You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.
     *
     * In one operation:
     *
     * choose an index i such that 0 <= i < nums.length,
     * increase your score by nums[i], and
     * replace nums[i] with ceil(nums[i] / 3).
     * Return the maximum possible score you can attain after applying exactly k operations.
     *
     * The ceiling function ceil(val) is the least integer greater than or equal to val.
     *
     * Input: nums = [10,10,10,10,10], k = 5
     * Output: 50
     *
     * Input: nums = [1,10,3,3,3], k = 3
     * Output: 17
     *
     * Constraints:
     *
     * 1 <= nums.length, k <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(klogn), space = O(n)
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int x : nums) pq.offer(x);

        long res = 0;
        while (k-- > 0) {
            int t = pq.poll();
            res += t;
            pq.offer((t + 2) / 3);
        }
        return res;
    }
}