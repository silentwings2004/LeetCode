package LC3301_3600;
import java.util.*;
public class LC3318_FindXSumofAllKLongSubarraysI {
    /**
     * You are given an array nums of n integers and two integers k and x.
     *
     * The x-sum of an array is calculated by the following procedure:
     *
     * Count the occurrences of all elements in the array.
     * Keep only the occurrences of the top x most frequent elements. If two elements have the same number of
     * occurrences, the element with the bigger value is considered more frequent.
     * Calculate the sum of the resulting array.
     * Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
     *
     * Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray
     * nums[i..i + k - 1].
     *
     * Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
     *
     * Output: [6,10,12]
     *
     * Input: nums = [3,8,7,8,7,5], k = 2, x = 2
     *
     * Output: [11,15,15,15,12]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 50
     * 1 <= nums[i] <= 50
     * 1 <= x <= k <= nums.length
     * @param nums
     * @param k
     * @param x
     * @return
     */
    // time = O(n * (x + 50) * log50, space = O(50)
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int[] cnt = new int[55];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0, j = 0; i < n; i++) {
            cnt[nums[i]]++;
            if (i - j + 1 == k) {
                pq.clear();
                for (int u = 1; u <= 50; u++) {
                    if (cnt[u] > 0) pq.offer(new int[]{cnt[u], u});
                }
                for (int u = 0; u < x; u++) {
                    if (pq.isEmpty()) break;
                    res[j] += pq.peek()[0] * pq.poll()[1];
                }
                cnt[nums[j++]]--;
            }
        }
        return res;
    }
}