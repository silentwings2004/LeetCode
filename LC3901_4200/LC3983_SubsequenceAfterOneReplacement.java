package LC3901_4200;

public class LC3983_SubsequenceAfterOneReplacement {
    /**
     * You are given two strings s and t consisting of lowercase English letters.
     *
     * You may choose at most one index in s and replace the character at that index with any lowercase English letter.
     *
     * Return true if it is possible to make s a subsequence of t; otherwise, return false.
     *
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * Input: s = "cat", t = "chat"
     * Output: true
     *
     * Input: s = "plane", t = "apple"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 10^5
     * s and t consist only of lowercase English letters.
     * @param s
     * @param t
     * @return
     */
    // S1: 前后缀分解
    // time = O(n), space = O(n)
    public boolean canMakeSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        if (n > m) return false;

        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int p = pre[i - 1];
            char c = s.charAt(i - 1);
            while (p < m && t.charAt(p) != c) p++;
            pre[i] = p == m ? m + 1 : p + 1; // m + 1 表示不可能
        }
        if (pre[n] <= m) return true; // 不改就成功

        int[] suf = new int[n + 1];
        suf[n] = m;
        for (int i = n - 1; i >= 0; i--) {
            int p = suf[i + 1] - 1;
            char c = s.charAt(i);
            while (p >= 0 && t.charAt(p) != c) p--;
            suf[i] = p; // -1 代表不可能
        }

        for (int k = 0; k < n; k++) {
            if (pre[k] > m) continue; // 前缀都配不上
            if (k == n - 1) { // 只剩最后一位要改，后面不需要后缀
                if (pre[k] < m) return true; // t 里还有空位
            } else {
                if (suf[k + 1] == -1) continue; // 后缀配不上
                if (pre[k] < suf[k + 1]) return true; // 中间需要至少一个位置给被改的字符
            }
        }
        return false;
    }

    // S2: 三指针
    // time = O(n), space = O(1)
    public boolean canMakeSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int j0 = 0; // 在不修改的情况下，s 的前缀 [0, j0-1] 是 t 的当前前缀的子序列
        int j1 = 0; // 在改过一次的情况下，s 的前缀 [0, j1-1] 是 t 的当前前缀的子序列
        for (int i = 0; i < m; i++) {
            char c = t.charAt(i);
            if (s.charAt(j1) == c) j1++; // j1 普通匹配
            j1 = Math.max(j1, j0 + 1); // 也可以修改 s[j0] 为 ch, 强行匹配，只能修改一次
            if (s.charAt(j0) == c) j0++;
            if (j0 == n || j1 == n) return true;
        }
        return false;
    }
}