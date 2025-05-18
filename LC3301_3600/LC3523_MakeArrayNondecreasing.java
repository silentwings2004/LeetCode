package LC3301_3600;

public class LC3523_MakeArrayNondecreasing {
    /**
     * You are given an integer array nums. In one operation, you can select a subarray and replace it with a single
     * element equal to its maximum value.
     *
     * Return the maximum possible size of the array after performing zero or more operations such that the resulting
     * array is non-decreasing.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [4,2,5,3,5]
     * Output: 3
     *
     * Input: nums = [1,2,3]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^5
     * 1 <= nums[i] <= 2 * 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int maximumPossibleSize(int[] nums) {
        int res = 0, mx = nums[0];
        for (int x : nums) {
            if (x >= mx) {
                mx = x;
                res++;
            }
        }
        return res;
    }

    // S2: monotonic stack
    // time = O(n), space = O(n)
    public int maximumPossibleSize2(int[] nums) {
        int n = nums.length;
        int[] stk = new int[n + 1];
        int tt = 0;
        for (int i = 0; i < n; i++) {
            int mx = nums[i];
            while (tt > 0 && stk[tt] > mx) mx = Math.max(mx, stk[tt--]);
            stk[++tt] = mx;
        }
        return tt;
    }
}
/**
 * 无论怎么操作，剩下的都是子数组中的最大值 => 最大值是去不掉的
 */