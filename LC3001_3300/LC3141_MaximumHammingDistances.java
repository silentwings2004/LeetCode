package LC3001_3300;
import java.util.*;
public class LC3141_MaximumHammingDistances {
    /**
     * Given an array nums and an integer m, with each element nums[i] satisfying 0 <= nums[i] < 2m, return an array
     * answer. The answer array should be of the same length as nums, where each element answer[i] represents the
     * maximum Hamming distance between nums[i] and any other element nums[j] in the array.
     *
     * The Hamming distance between two binary integers is defined as the number of positions at which the corresponding
     * bits differ (add leading zeroes if needed).
     *
     * Input: nums = [9,12,9,11], m = 4
     * Output: [2,3,2,3]
     *
     * Input: nums = [3,4,6,10], m = 4
     * Output: [3,3,2,3]
     *
     * Constraints:
     *
     * 1 <= m <= 17
     * 2 <= nums.length <= 2^m
     * 0 <= nums[i] < 2^m
     * @param nums
     * @param m
     * @return
     */
    // time = O(n * m), space = O(2^m)
    public int[] maxHammingDistances(int[] nums, int m) {
        int n = nums.length;
        int[] res = new int[n];
        HashSet<Integer> set = new HashSet<>();
        int[] dist = new int[1 << m];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        for (int x : nums) {
            int y = x;
            for (int i = 0; i < m; i++) {
                y ^= 1 << i;
            }
            dist[y] = 0;
            q.offer(y);
        }

        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = 0; i < m; i++) {
                int y = t ^ (1 << i);
                if (dist[y] == -1) {
                    dist[y] = dist[t] + 1;
                    q.offer(y);
                }
            }
        }

        for (int i = 0; i < n; i++) res[i] = m - dist[nums[i]];
        return res;
    }
}