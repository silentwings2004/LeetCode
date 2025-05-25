package LC3301_3600;

public class LC3555_SmallestSubarraytoSortinEverySlidingWindow {
    /**
     * You are given an integer array nums and an integer k.
     *
     * For each contiguous subarray of length k, determine the minimum length of a continuous segment that must be
     * sorted so that the entire window becomes non‑decreasing; if the window is already sorted, its required length is
     * zero.
     *
     * Return an array of length n − k + 1 where each element corresponds to the answer for its window.
     *
     * Input: nums = [1,3,2,4,5], k = 3
     * Output: [2,2,0]
     *
     * Input: nums = [5,4,3,2,1], k = 4
     * Output: [4,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= k <= nums.length
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * k), space = O(1)
    public int[] minSubarraySort(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = k - 1, j = 0; i < n; i++, j++) {
            int l = -1, r = -1;
            for (int u = j; u + 1 <= i; u++) {
                if (nums[u] > nums[u + 1]) {
                    l = u;
                    break;
                }
            }
            if (l == -1) continue;
            for (int u = i; u - 1 >= j; u--) {
                if (nums[u - 1] > nums[u]) {
                    r = u;
                    break;
                }
            }

            int minv = Integer.MAX_VALUE, maxv = Integer.MIN_VALUE;
            for (int u = l; u <= r; u++) {
                minv = Math.min(minv, nums[u]);
                maxv = Math.max(maxv, nums[u]);
            }

            while (l > j && nums[l - 1] > minv) l--;
            while (r < i && nums[r + 1] < maxv) r++;
            res[j] = r - l + 1;
        }
        return res;
    }
}