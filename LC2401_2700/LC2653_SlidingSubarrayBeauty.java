package LC2401_2700;

public class LC2653_SlidingSubarrayBeauty {
    /**
     * Given an integer array nums containing n integers, find the beauty of each subarray of size k.
     *
     * The beauty of a subarray is the xth smallest integer in the subarray if it is negative, or 0 if there are fewer
     * than x negative integers.
     *
     * Return an integer array containing n - k + 1 integers, which denote the beauty of the subarrays in order from the
     * first index in the array.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,-1,-3,-2,3], k = 3, x = 2
     * Output: [-1,-2,-2]
     *
     * Input: nums = [-1,-2,-3,-4,-5], k = 2, x = 2
     * Output: [-1,-2,-3,-4]
     *
     * Input: nums = [-3,1,2,-3,0,-3], k = 2, x = 1
     * Output: [-3,0,-3,-3,-3]
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * 1 <= k <= n
     * 1 <= x <= k
     * -50 <= nums[i] <= 50
     * @param nums
     * @param k
     * @param x
     * @return
     */
    // time = O(n), space = O(1)
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length, idx = 0;
        int[] cnt = new int[110];
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            int val = nums[i] + 50;
            cnt[val]++;
            if (i >= k - 1) {
                int t = 0;
                for (int j = 0; j <= 100; j++) {
                    t += cnt[j];
                    if (t >= x) {
                        res[idx++] = j - 50 < 0 ? j - 50 : 0;
                        break;
                    }
                }
                int j = i - k + 1;
                cnt[nums[j++] + 50]--;
            }
        }
        return res;
    }
}