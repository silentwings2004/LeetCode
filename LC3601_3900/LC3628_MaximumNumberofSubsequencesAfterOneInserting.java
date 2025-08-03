package LC3601_3900;

public class LC3628_MaximumNumberofSubsequencesAfterOneInserting {
    /**
     * You are given a string s consisting of uppercase English letters.
     *
     * You are allowed to insert at most one uppercase English letter at any position (including the beginning or end)
     * of the string.
     *
     * Return the maximum number of "LCT" subsequences that can be formed in the resulting string after at most one
     * insertion.
     *
     * A subsequence is a non-empty string that can be derived from another string by deleting some or no characters
     * without changing the order of the remaining characters.
     *
     * Note: Please do not copy the description during the contest to maintain the integrity of your submissions.
     *
     * Input: s = "LMCT"
     * Output: 2
     *
     * Input: s = "LCCT"
     * Output: 4
     *
     * Input: s = "L"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of uppercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public long numOfSubsequences(String s) {
        int n = s.length();
        int[] pre = new int[n + 1], suf = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + (s.charAt(i) == 'L' ? 1 : 0);
        for (int i = n - 1; i >= 0; i--) suf[i] = suf[i + 1] + (s.charAt(i) == 'T' ? 1 : 0);
        int[] lc = new int[n + 1], ct = new int[n + 1];
        for (int i = 0, cnt = 0, t = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'L') cnt++;
            else if (c == 'C') t += cnt;
            lc[i + 1] = t;
        }
        for (int i = n - 1, cnt = 0, t = 0; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == 'T') cnt++;
            else if (c == 'C') t += cnt;
            ct[i] = t;
        }

        long base = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'C') base += 1L * pre[i] * suf[i];
        }
        long tl = 0, tc = 0, tt = 0;
        for (int i = 0; i <= n; i++) tl = Math.max(tl, ct[i]);
        for (int i = 0; i <= n; i++) tc = Math.max(tc, 1L * pre[i] * suf[i]);
        for (int i = 0; i <= n; i++) tt = Math.max(tt, lc[i]);
        return base + Math.max(tl, Math.max(tc, tt));
    }

    // S2
    // time = O(n), space = O(1)
    public long numOfSubsequences2(String s) {
        int n = s.length(), t = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'T') t++;
        }

        long l = 0, lc = 0, lct = 0, c = 0, ct = 0, lt = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'L') l++;
            else if (ch == 'C') {
                lc += l;
                c++;
            } else if (ch == 'T') {
                lct += lc;
                ct += c;
                t--;
            }
            lt = Math.max(lt, l * t);
        }
        return lct + Math.max(Math.max(ct, lc), lt);
    }
}