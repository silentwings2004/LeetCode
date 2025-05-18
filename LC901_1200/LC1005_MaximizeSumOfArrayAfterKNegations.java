package LC901_1200;
import java.util.*;
public class LC1005_MaximizeSumOfArrayAfterKNegations {
    /**
     * Given an integer array nums and an integer k, modify the array in the following way:
     *
     * choose an index i and replace nums[i] with -nums[i].
     * You should apply this process exactly k times. You may choose the same index i multiple times.
     *
     * Return the largest possible sum of the array after modifying it in this way.
     *
     * Input: nums = [4,2,3], k = 1
     * Output: 5
     *
     * Input: nums = [3,-1,0,2], k = 3
     * Output: 6
     *
     * Input: nums = [2,-3,-1,5,-4], k = 2
     * Output: 13
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -100 <= nums[i] <= 100
     * 1 <= k <= 10^4
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int largestSumAfterKNegations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int t = (int)1e8;
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            t = Math.min(t, Math.abs(x));
        }

        for (int i = -100; i < 0 && k > 0; i++) {
            while (map.getOrDefault(i, 0) > 0 && k > 0) {
                map.put(-i, map.getOrDefault(-i, 0) + 1);
                map.put(i, map.get(i) - 1);
                k--;
            }
        }

        int res = 0;
        for (int i = -100; i <= 100; i++) res += i * map.getOrDefault(i, 0);
        if (k % 2 == 1) res -= 2 * t;
        return res;
    }

    // S2: Heap
    // time = O(nlogn), space = O(n)
    public int largestSumAfterKNegations2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : nums) pq.offer(x);
        while (k > 0) {
            int t = pq.poll();
            if (t == 0) break;
            else {
                pq.offer(-t);
                k--;
            }
        }

        int res = 0;
        while (!pq.isEmpty()) res += pq.poll();
        return res;
    }
}