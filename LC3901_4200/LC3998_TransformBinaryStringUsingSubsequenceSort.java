package LC3901_4200;

public class LC3998_TransformBinaryStringUsingSubsequenceSort {
    /**
     * You are given a binary string s.
     *
     * You are also given an array of strings strs, where each strs[i] has the same length as s and consists of
     * characters '0', '1', and '?'. Each '?' can be replaced by either '0' or '1'.
     *
     * You may perform the following operation any number of times (including zero):
     *
     * Choose any subsequence sub of s.
     * Sort sub in non-decreasing order.
     * Replace the chosen subsequence in s with the sorted sub, keeping all other characters unchanged.
     * Return a boolean array ans, where ans[i] is true if it's possible to replace all '?' in strs[i] with '0' or '1'
     * and transform s into the resulting string using the allowed operation above, otherwise return false.
     *
     * Input: s = "101", strs = ["1?1","0?1","0?0"]
     * Output: [true,true,false]
     *
     * Input: s = "1100", strs = ["0011","11?1","1?1?"]
     * Output: [true,false,true]
     *
     * Input: s = "1010", strs = ["0011"]
     * Output: [true]
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 2000
     * s[i] is either '0' or '1'.
     * 1 <= strs.length <= 2000
     * strs[i].length == n
     * strs[i] is either '0', '1', or '?
     * @param s
     * @param strs
     * @return
     */
    // time = O(n * m), space = O(n)
    public boolean[] transformStr(String s, String[] strs) {
        int n = s.length(), m = strs.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + (s.charAt(i - 1) - '0');
        int tot = sum[n];
        boolean[] res = new boolean[m];

        for (int i = 0; i < m; i++) {
            String t = strs[i];
            int l = 0, r = 0;
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                char c = t.charAt(j);
                if (c == '1') {
                    l++;
                    r++;
                } else if (c == '?') r++;
                r = Math.min(r, sum[j + 1]);
                if (l > r) {
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;
            res[i] = tot >= l && tot <= r;
        }
        return res;
    }
}
/**
 * 拆分成若干次如下原子操作:
 * 选择子序列中一左一右的两个字符 x 和 y，如果 x>y，则交换 x 和 y。
 * 由于 [x,y] 也是子序列，所以实际上，我们只需考虑如下操作：
 * 选择 s[i]=1 和 s[j]=0（i<j），交换 s[i] 和 s[j]。
 * 这意味着，s 中的 0 只能向左移动。
 * 所以，为了尽量能把 s 变成 t=strs[i]，应当把 t 中靠左的 ? 变成 0，靠右的 ? 变成 1，并保证 s 和 t 中的 0 的个数相等。
 * 由于字符串长度一样，0 的个数相等了，1 的个数也就相等了。
 * 设 s 中的 0 的下标列表为 P，t 中的 0 的下标列表为 Q。由于 s 中的 0 只能向左移动，所以每个 P[j] 都要 ≥Q[j]，才能保证我们能把 s 变成 t。
 */