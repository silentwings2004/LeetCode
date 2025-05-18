package LC3001_3300;
import java.util.*;
public class LC3171_FindSubarrayWithBitwiseANDClosesttoK {
    /**
     * You are given an array nums and an integer k. You need to find a subarray of nums such that the absolute
     * difference between k and the bitwise AND of the subarray elements is as small as possible. In other words, select
     * a subarray nums[l..r] such that |k - (nums[l] AND nums[l + 1] ... AND nums[r])| is minimum.
     *
     * Return the minimum possible value of the absolute difference.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,4,5], k = 3
     *
     * Output: 1
     *
     * Input: nums = [1,2,1,2], k = 2
     *
     * Output: 0
     *
     * Input: nums = [1], k = 10
     *
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // S1: Two Pointers (AND)
    // time = O(n), space = O(1)
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length, res = Integer.MAX_VALUE;
        int[] cnt = new int[31];
        for (int i = 0, j = 0, t = -1; i < n; i++) {
            t &= nums[i];
            res = Math.min(res, Math.abs(t - k));
            for (int u = 0; u < 31; u++) cnt[u] += nums[i] >> u & 1;
            while (t < k && j <= i) {
                for (int u = 0; u < 31; u++) {
                    cnt[u] -= nums[j] >> u & 1;
                    if (cnt[u] == 0 && (t >> u & 1) == 1) t ^= 1 << u;
                }
                j++;
                for (int u = 0; u < 31; u++) {
                    if (cnt[u] > 0 && cnt[u] == i - j + 1) t |= 1 << u;
                }
                res = Math.min(res, Math.abs(t - k));
                if (i - j + 1 == 0) t = -1;
            }
        }
        return res;
    }

    // S2: Log Trick (AND)
    // time = O(nlogU), space = O(1)  U: max val of nums
    public int minimumDifference2(int[] nums, int k) {
        int res = Integer.MAX_VALUE, n = nums.length;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, Math.abs(nums[i] - k));
            for (int j = i - 1; j >= 0; j--) {
                if ((nums[i] & nums[j]) == nums[j]) break;
                nums[j] &= nums[i];
                res = Math.min(res, Math.abs(nums[j] - k));
            }
        }
        return res;
    }
}
/**
 * 还可以解决哪些问题？
 * OR GCD LCM
 */