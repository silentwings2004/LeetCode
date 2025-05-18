package LC3001_3300;

public class LC3162_FindtheNumberofGoodPairsI {
    /**
     * You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively. You are also given a positive
     * integer k.
     *
     * A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).
     *
     * Return the total number of good pairs.
     *
     * Input: nums1 = [1,3,4], nums2 = [1,3,4], k = 1
     * Output: 5
     *
     * Input: nums1 = [1,2,4,12], nums2 = [2,4], k = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n, m <= 50
     * 1 <= nums1[i], nums2[j] <= 50
     * 1 <= k <= 50
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    // time = O(n * m), space = O(1)
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums1[i] % (nums2[j] * k) == 0) res++;
            }
        }
        return res;
    }
}