package LC3901_4200;
import java.util.*;
public class LC3934_SmallestUniqueSubarray {
    /**
     * You are given an integer array nums.
     *
     * Find the minimum length of a subarray that is not identical to any other subarray in nums.
     *
     * Return an integer denoting the minimum possible length of such a subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Two subarrays are considered identical if they have the same length and the same elements in corresponding
     * positions.
     *
     * Input: nums = [3,3,3]
     *
     * Output: 3
     *
     * Input: nums = [2,1,2,3,3]
     *
     * Output: 1
     *
     * Input: nums = [1,1,2,2,1]
     *
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int P = 131;
    long[] h, p;
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        h = new long[n + 1];
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + nums[i - 1];
        }
        int l = 1, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, mid)) r = mid;
            else l = mid + 1;
        }
        return check(nums, r) ? r : 0;
    }

    private boolean check(int[] nums, int len) {
        int n = nums.length, m = n - len + 1;
        long[] hs = new long[m];
        for (int i = 0; i < m; i++) hs[i] = get(i, i + len - 1);
        HashMap<Long, Integer> map = new HashMap<>();
        for (long x : hs) map.put(x, map.getOrDefault(x, 0) + 1);
        for (long x : hs) {
            if (map.get(x) == 1) return true;
        }
        return false;
    }

    private long get(int l, int r) {
        l++;
        r++;
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
/**
 * 二分答案 + 哈希
 */