package LC301_600;

public class LC410_SplitArrayLargestSum {
    /**
     * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m
     * non-empty continuous subarrays.
     *
     * Write an algorithm to minimize the largest sum among these m subarrays.
     *
     * Input: nums = [7,2,5,10,8], m = 2
     * Output: 18
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 10^6
     * 1 <= m <= min(50, nums.length)
     * @param nums
     * @param m
     * @return
     */
    // S1: BS
    // time = O(nlogS), space = O(1)  S: sum of array
    public int splitArray(int[] nums, int k) {
        int sum = 0;
        for (int x : nums) sum += x;
        int l = 0, r = sum;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, mid, k)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int t, int k) {
        int sum = 0, cnt = 1;
        for (int x : nums) {
            if (x > t) return false;
            if (sum + x <= t) sum += x;
            else {
                sum = x;
                cnt++;
            }
        }
        return cnt <= k;
    }

    // S2: DP
    public int splitArray2(int[] nums, int m) {
        // corner case
        if (nums == null || nums.length == 0 || m <= 0) return 0;

        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) dp[i][1] = dp[i - 1][1] + nums[i - 1];

        for (int i = 1; i <= n; i++) {
            for (int k = 2; k <= Math.min(i, m); k++) {
                dp[i][k] = Math.max(dp[i - 1][k - 1], nums[i - 1]); // 自成一派
                for (int j = 1; j < i; j++) {
                    if (j >= k - 1) {
                        int temp = Math.max(dp[j][k - 1], dp[i][1] - dp[j][1]);
                        dp[i][k] = Math.min(dp[i][k], temp);
                    }
                }
            }
        }
        return dp[n][m];
    }
}
/**
 * 尽量让它们平均分
 * BS: search by value
 * DP比较常规
 * dp[i][k]: nums[0:i] divide k subarrays, minimized maximum subarray sum
 *                                         maximized minimum subarray sum
 *           min{max{dp[j][k - 1], sum[j + 1 : i]}}
 * dp[i][k]: max{min{dp[j][k - 1], sum[j + 1 : i]}} over j  considering nums[j:i] belong to the last subarray
 * i = 0,1,2,3,...,nums.length
 * j = 0,1,2,...,i-1
 * k = 1,2,3,...,i
 * => O(n^3)
 * return dp[nums.length - 1][m] 区间型dp
 *
 * S2: BS
 * val = [0, INT_MAX] => 32
 *
 * 判断是否有一种方案，使每一段总和<= mid，最少能分成多少段
 * 贪心
 * 1. 贪心解 >= 最优解
 * 2. 贪心解 <= 最优解
 */