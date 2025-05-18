package LC2401_2700;
import java.util.*;
public class LC2542_MaximumSubsequenceScore {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must
     * choose a subsequence of indices from nums1 of length k.
     *
     * For chosen indices i0, i1, ..., ik - 1, your score is defined as:
     *
     * The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
     * It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,
     * nums2[ik - 1]).
     * Return the maximum possible score.
     *
     * A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting
     * some or no elements.
     *
     * Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
     * Output: 12
     *
     * Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
     * Output: 30
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 1 <= n <= 10^5
     * 0 <= nums1[i], nums2[j] <= 10^5
     * 1 <= k <= n
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{nums2[i], i};
        Arrays.sort(w, (o1, o2) -> o2[0] - o1[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            int x = w[i][0], idx = w[i][1];
            pq.offer(nums1[idx]);
            sum += nums1[idx];
            if (pq.size() > k) sum -= pq.poll();
            if (i >= k - 1) res = Math.max(res, sum * x);
        }
        return res;
    }
}