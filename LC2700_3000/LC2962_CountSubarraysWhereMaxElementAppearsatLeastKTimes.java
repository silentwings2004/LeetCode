package LC2700_3000;

public class LC2962_CountSubarraysWhereMaxElementAppearsatLeastKTimes {
    /**
     * You are given an integer array nums and a positive integer k.
     *
     * Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [1,3,2,3,3], k = 2
     * Output: 6
     *
     * Input: nums = [1,4,2,1], k = 3
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length, last = -1;
        long res = 0;
        int maxv = nums[0];
        for (int x : nums) maxv = Math.max(maxv, x);
        for (int i = 0, j = 0, t = -1; i < n; i++) {
            if (nums[i] == maxv) {
                if (t == -1) {
                    t = 1;
                    j = i;
                } else t++;
            }
            if (t == k) {
                res += 1L * (j - last) * (n - i);
                last = j;
                j++;
                while (j < n && nums[j] != maxv) j++;
                t--;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long countSubarrays2(int[] nums, int k) {
        int maxv = nums[0];
        for (int x : nums) maxv = Math.max(maxv, x);
        int res = 0, cnt_mx = 0, left = 0;
        for (int x : nums) {
            if (x == maxv) cnt_mx++;
            while (cnt_mx == k) {
                if (nums[left] == maxv) cnt_mx--;
                left++;
            }
            res += left;
        }
        return res;
    }
}