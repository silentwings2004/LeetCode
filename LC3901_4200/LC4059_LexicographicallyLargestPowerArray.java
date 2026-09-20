package LC3901_4200;

public class LC4059_LexicographicallyLargestPowerArray {
    /**
     * You are given an integer array nums of length n. You may rearrange its elements to form any permutation perm.
     *
     * Define an array power of length 15. For each 0 <= i < 15, power[i] is the largest integer j, where 0 <= j <= n,
     * such that the first j elements of perm all have the (14 - i)th bit set.
     *
     * Bit positions are indexed from right to left, starting with the 0th bit.
     *
     * Return the lexicographically largest possible power array.
     *
     * A permutation is a rearrangement of all the elements of an array.
     *
     * A set bit is a bit whose value in the binary representation of a number is 1.
     *
     * For two arrays of the same length, an array a is lexicographically larger than an array b if, at the first index
     * where they differ, a contains the larger element.
     *
     * Input: nums = [7,5]
     * Output: [0,0,0,0,0,0,0,0,0,0,0,0,2,1,2]
     *
     * Input: nums = [3,1,7]
     * Output: [0,0,0,0,0,0,0,0,0,0,0,0,1,2,3]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 0 <= nums[i] < 2^15
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int[] largestPower(int[] nums) {
        int n = nums.length;
        int[] power = new int[15];
        boolean[] st = new boolean[n];

        int m = (1 << 15) - 1;
        int len = 0;
        while (m > 0) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (!st[i] && (nums[i] & m) == m) {
                    st[i] = true;
                    cnt++;
                }
            }
            len += cnt;
            if (len == n) break;

            int best = -1, bm = -1;
            for (int i = 0; i < n; i++) {
                if (!st[i]) {
                    int nm = m & nums[i];
                    if (nm > bm) {
                        bm = nm;
                        best = i;
                    }
                }
            }

            int pm = m;
            m = bm;
            for (int i = 0; i < 15; i++) {
                if ((pm >> i & 1) == 1 && (m >> i & 1) == 0) power[14 - i] = len;
            }
            st[best] = true;
            len++;
        }

        for (int i = 0; i < 15; i++) {
            if ((m >> i & 1) == 1) power[14 - i] = n;
        }
        return power;
    }
}