package LC3901_4200;

public class LC4035_MaximumValidSplitPositionsI {
    /**
     * You are given an integer array nums.
     *
     * You may remove at most one element from nums. Let arr be the array of remaining elements in their original order,
     * and let m be its length.
     *
     * A split position i of arr is valid if:
     *
     * 0 <= i < m - 1, and
     * gcd(arr[0..i]) == gcd(arr[i + 1..m - 1]).
     * An array of length 1 has no valid split positions.
     *
     * The score of arr is the number of valid split positions in it.
     *
     * Return the maximum possible score of arr.
     *
     * Here, gcd(a) denotes the greatest common divisor of all elements in the array a.
     *
     * Input: nums = [10,30,15,10]
     * Output: 2
     *
     * Input: nums = [2,10,14]
     * Output: 1
     *
     * Input: nums = [2,4]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n^2 * log(max(nums)), space = O(n)
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int res = helper(nums, -1);
        if (n == 2) return res;
        for (int i = 0; i < n; i++) res = Math.max(res, helper(nums, i));
        return res;
    }

    private int helper(int[] nums, int idx) {
        int n = nums.length;
        int m = idx == -1 ? n : n - 1;
        int[] w = new int[m];
        for (int i = 0, p = 0; i < n; i++) {
            if (i == idx) continue;
            w[p++] = nums[i];
        }

        int[] suf = new int[m];
        suf[m - 1] = w[m - 1];
        for (int i = m - 2; i >= 0; i--) suf[i] = gcd(suf[i + 1], w[i]);
        int cnt = 0;
        for (int i = 0, pre = 0; i < m - 1; i++) {
            pre = gcd(pre, w[i]);
            if (pre == suf[i + 1]) cnt++;
        }
        return cnt;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}