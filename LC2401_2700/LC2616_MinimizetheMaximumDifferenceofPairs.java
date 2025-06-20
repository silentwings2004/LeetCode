package LC2401_2700;
import java.util.*;
public class LC2616_MinimizetheMaximumDifferenceofPairs {
    /**
     * You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the
     * maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the
     * p pairs.
     *
     * Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where
     * |x| represents the absolute value of x.
     *
     * Return the minimum maximum difference among all p pairs.
     *
     * Input: nums = [10,1,2,7,1,3], p = 2
     * Output: 1
     *
     * Input: nums = [4,2,1,2], p = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 0 <= p <= (nums.length)/2
     * @param nums
     * @param p
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums[n - 1] - nums[0];
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, p, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int p, int mid) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (nums[i + 1] - nums[i] <= mid) {
                cnt++;
                i++;
            }
        }
        return cnt >= p;
    }
}