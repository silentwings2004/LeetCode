package LC3301_3600;
import java.util.*;
public class LC3458_SelectKDisjointSpecialSubstrings {
    /**
     * Given a string s of length n and an integer k, determine whether it is possible to select k disjoint special
     * substrings.
     *
     * A special substring is a substring where:
     *
     * Any character present inside the substring should not appear outside it in the string.
     * The substring is not the entire string s.
     * Note that all k substrings must be disjoint, meaning they cannot overlap.
     *
     * Return true if it is possible to select k such disjoint special substrings; otherwise, return false.
     *
     * Input: s = "abcdbaefab", k = 2
     * Output: true
     *
     * Input: s = "cdefdc", k = 3
     * Output: false
     *
     * Input: s = "abeabe", k = 0
     * Output: true
     *
     * Constraints:
     *
     * 2 <= n == s.length <= 5 * 10^4
     * 0 <= k <= 26
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public boolean maxSubstringLength(String s, int k) {
        int n = s.length();
        int[] first = new int[26], last = new int[26];
        Arrays.fill(first, n);
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            first[u] = Math.min(first[u], i);
            last[u] = i;
        }

        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] == n) continue;
            int l = first[i], r = last[i];
            int p = l;
            while (p <= r) {
                int v = s.charAt(p) - 'a';
                r = Math.max(r, last[v]);
                p++;
            }
            if (l == 0 && r == n - 1) continue;
            boolean f = true;
            for (int j = l; j <= r; j++) {
                int v = s.charAt(j) - 'a';
                if (first[v] < l) {
                    f = false;
                    break;
                }
            }
            if (f) q.add(new int[]{l, r});
        }
        Collections.sort(q, (o1, o2) -> o1[1] - o2[1]);
        int cnt = 0, ed = -1;
        for (int[] x : q) {
            if (x[0] > ed) {
                cnt++;
                ed = x[1];
            }
        }
        return cnt >= k;
    }
}