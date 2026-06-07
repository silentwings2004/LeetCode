package LC3901_4200;

public class LC3952_MaximumTotalValueofCoveredIndices {
    /**
     * You are given an integer array nums of length n and a binary string s of length n, where s[i] == '1' means index
     * i initially contains a token and s[i] == '0' means it does not.
     *
     * You may perform the following operation any number of times:
     *
     * Choose a token currently located at index i, where i > 0, such that this token has not been moved before.
     * Move this token from index i to index i - 1.
     * An index is considered covered if it contains a token after all moves.
     *
     * Return an integer denoting the maximum total value of nums at the covered indices after optimally performing the
     * operations.
     *
     * Input: nums = [9,2,6,1], s = "0101"
     * Output: 15
     *
     * Input: nums = [5,1,4], s = "001"
     * Output: 4
     *
     * Input: nums = [9,3,5], s = "011"
     * Output: 14
     *
     * Constraints:
     *
     * 1 <= n == nums.length == s.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * s[i] is either '0' or '1'
     * @param nums
     * @param s
     * @return
     */
    // S1: greedy
    // time = O(n), space = O(1)
    public long maxTotal(int[] nums, String s) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i + 1 == n || s.charAt(i + 1) == '0') continue;
                long v = nums[i];
                int minv = nums[i];
                int j = i + 1;
                while (j < n && s.charAt(j) == '1') {
                    v += nums[j];
                    minv = Math.min(minv, nums[j]);
                    j++;
                }
                res += v - minv;
                i = j - 1;
            } else res += nums[i];
        }
        return res;
    }

    // S2: dp
    // time = O(n), space = O(1)
    public long maxTotal2(int[] nums, String s) {
        long[] f = new long[2];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (s.charAt(i) == '0') f[1] = f[0] + x;
            else {
                f[0] = Math.max(f[0] + x, f[1]);
                f[1] += x;
            }
        }
        return f[0];
    }
}