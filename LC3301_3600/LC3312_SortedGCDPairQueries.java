package LC3301_3600;

public class LC3312_SortedGCDPairQueries {
    /**
     * You are given an integer array nums of length n and an integer array queries.
     *
     * Let gcdPairs denote an array obtained by calculating the GCD of all possible pairs (nums[i], nums[j]), where
     * 0 <= i < j < n, and then sorting these values in ascending order.
     *
     * For each query queries[i], you need to find the element at index queries[i] in gcdPairs.
     *
     * Return an integer array answer, where answer[i] is the value at gcdPairs[queries[i]] for each query.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * Input: nums = [2,3,4], queries = [0,2,2]
     *
     * Output: [1,2,2]
     *
     * Input: nums = [4,4,2,1], queries = [5,3,1,0]
     *
     * Output: [4,2,1,1]
     *
     * Input: nums = [2,2], queries = [0,0]
     *
     * Output: [2,2]
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 5 * 10^4
     * 1 <= queries.length <= 10^5
     * 0 <= queries[i] < n * (n - 1) / 2
     * @param nums
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int[] cnt = new int[mx + 1];
        for (int x : nums) cnt[x]++;

        long[] p = new long[mx + 1];
        for (int i = mx; i > 0; i--) {
            int c = 0;
            for (int j = i; j <= mx; j += i) {
                c += cnt[j];
                p[i] -= p[j];
            }
            p[i] += 1L * c * (c - 1) / 2;
        }
        long[] s = new long[mx + 1];
        for (int i = 1; i <= mx; i++) s[i] = s[i - 1] + p[i];

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            long x = queries[i] + 1;
            int l = 1, r = mx;
            while (l < r) {
                int mid = l + r >> 1;
                if (s[mid] >= x) r = mid;
                else l = mid + 1;
            }
            res[i] = r;
        }
        return res;
    }
}