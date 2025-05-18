package LC2401_2700;

public class LC2560_HouseRobberIV {
    /**
     * There are several consecutive houses along a street, each of which has some money inside. There is also a robber,
     * who wants to steal money from the homes, but he refuses to steal from adjacent homes.
     *
     * The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.
     *
     * You are given an integer array nums representing how much money is stashed in each house. More formally, the ith
     * house from the left has nums[i] dollars.
     *
     * You are also given an integer k, representing the minimum number of houses the robber will steal from. It is
     * always possible to steal at least k houses.
     *
     * Return the minimum capability of the robber out of all the possible ways to steal at least k houses.
     *
     * Input: nums = [2,3,5,9], k = 2
     * Output: 5
     *
     * Input: nums = [2,7,9,3,1], k = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= (nums.length + 1)/2
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int minCapability(int[] nums, int k) {
        int l = (int) 1e9, r = 1;
        for (int x : nums) {
            l = Math.min(l, x);
            r = Math.max(r, x);
        }

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, mid, k)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int t, int k) {
        int n = nums.length, res = 0, last = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > t) continue;
            else {
                if (last == -1) last = i;
                else {
                    if (last + 1 == i) continue;
                    else last = i;
                }
                res++;
            }
        }
        return res >= k;
    }
}
/**
 * capability: 打劫的上限
 * 上限越小，可以选择的House越少
 * 扩大capability，你可以抢劫的上限变大，越容易满足k houses => 单调性
 * c: => how many houses we can rob
 * x x x x x i
 * dp[i][0]: how many houses we can rob if we do not rob the i-th house
 * dp[i][1]: how many houses we can rob if we do rob the i-th house
 * if (nums[i] > c) {
 *     dp[i][0] = max{dp[i-1][0], dp[i-1][1]};
 *     dp[i][1] = INT_MIN/2;
 * }
 * else {
 *     dp[i][0] = max{dp[i-1][0], d[i-1][1]};
 *     dp[1][1] = dp[i-1][0] + 1;
 * }
 * dp[n-1][x] >= k
 */