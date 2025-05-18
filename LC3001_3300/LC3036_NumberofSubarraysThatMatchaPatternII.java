package LC3001_3300;

public class LC3036_NumberofSubarraysThatMatchaPatternII {
    /**
     * You are given a 0-indexed integer array nums of size n, and a 0-indexed integer array pattern of size m
     * consisting of integers -1, 0, and 1.
     *
     * A subarray nums[i..j] of size m + 1 is said to match the pattern if the following conditions hold for each
     * element pattern[k]:
     *
     * nums[i + k + 1] > nums[i + k] if pattern[k] == 1.
     * nums[i + k + 1] == nums[i + k] if pattern[k] == 0.
     * nums[i + k + 1] < nums[i + k] if pattern[k] == -1.
     * Return the count of subarrays in nums that match the pattern.
     *
     * Input: nums = [1,2,3,4,5,6], pattern = [1,1]
     * Output: 4
     *
     * Input: nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 10^6
     * 1 <= nums[i] <= 10^9
     * 1 <= m == pattern.length < n
     * -1 <= pattern[i] <= 1
     * @param nums
     * @param pattern
     * @return
     */
    final int P = 131;
    long[] h, p;
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length, m = pattern.length, res = 0;
        char[] w = new char[n];
        for (int i = 0; i + 1 < n; i++) {
            if (nums[i] < nums[i + 1]) w[i] = 'a';
            else if (nums[i] == nums[i + 1]) w[i] = 'b';
            else w[i] = 'c';
        }

        h = new long[n + 1];
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + w[i - 1];
        }


        long hs = 0;
        for (int x : pattern) {
            char c = 'a';
            if (x == 1) c = 'a';
            else if (x == 0) c = 'b';
            else c = 'c';
            hs = hs * P + c;
        }

        for (int i = 0; i + m + 1 - 1 < n; i++) {
            if (get(i + 1, i + m) == hs) res++;
        }
        return res;
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }

    // S2: z 函数
    // time = O(n + m), space = O(n + m)
    public int countMatchingSubarrays2(int[] nums, int[] pattern) {
        int m = pattern.length, n = nums.length;
        int[] w = new int[m + n - 1];
        for (int i = 0; i < w.length; i++) {
            if (i < m) w[i] = pattern[i];
            else {
                int a = nums[i - m], b = nums[i - m + 1];
                if (a < b) w[i] = 1;
                else if (a == b) w[i] = 0;
                else w[i] = -1;
            }
        }

        n = w.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (i + z[i] < n && w[z[i]] == w[i + z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
        }
        int res = 0;
        for (int i = m; i < n; i++) {
            if (z[i] >= m) res++;
        }
        return res;
    }
}
/**
 * zi = LCP(s, t) 这两个字符串的最长公共前缀的长度
 * s = aaba
 * t = aabcd
 * zi = |LCP(s, s[i:])|
 * s = ababc
 * 文本串放到模式串的右边 => 就可以做匹配了
 * z 函数是整个字符串和它的后缀做最长公共前缀的匹配 => 文本串可以看做整个拼接后字符串的“后缀”
 * => 只需要统计从文本串的位置开始往后有多少个 z 函数的值是 >= 模式串长度的位置即可
 */