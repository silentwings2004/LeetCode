package LC3301_3600;
import java.util.*;
public class LC3478_ChooseKElementsWithMaximumSum {
    /**
     * You are given two integer arrays, nums1 and nums2, both of length n, along with a positive integer k.
     *
     * For each index i from 0 to n - 1, perform the following:
     *
     * Find all indices j where nums1[j] is less than nums1[i].
     * Choose at most k values of nums2[j] at these indices to maximize the total sum.
     * Return an array answer of size n, where answer[i] represents the result for the corresponding index i.
     *
     * Input: nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2
     * Output: [80,30,0,80,50]
     *
     * Input: nums1 = [2,2,2,2], nums2 = [3,1,2,3], k = 1
     * Output: [0,0,0,0]
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 1 <= n <= 10^5
     * 1 <= nums1[i], nums2[i] <= 10^6
     * 1 <= k <= n
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) a[i] = new int[]{nums1[i], i};
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long[] res = new long[n];
        long s = 0;

        for (int i = 0; i < n; i++) {
            int u = i;
            long t = s;
            while (u < n && a[u][0] == a[i][0]) {
                res[a[u][1]] = s;
                pq.offer(nums2[a[u][1]]);
                t += nums2[a[u][1]];
                if (pq.size() > k) t -= pq.poll();
                u++;
            }
            s = t;
            i = u - 1;
        }
        return res;
    }
}