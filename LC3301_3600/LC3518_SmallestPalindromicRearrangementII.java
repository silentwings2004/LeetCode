package LC3301_3600;

public class LC3518_SmallestPalindromicRearrangementII {
    /**
     * You are given a palindromic string s and an integer k.
     *
     * Return the k-th lexicographically smallest palindromic permutation of s. If there are fewer than k distinct
     * palindromic permutations, return an empty string.
     *
     * Note: Different rearrangements that yield the same palindromic string are considered identical and are counted once.
     *
     * A string is palindromic if it reads the same forward and backward.
     *
     * A permutation is a rearrangement of all the characters of a string.
     *
     * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a
     * has a letter that appears earlier in the alphabet than the corresponding letter in b.
     * If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically
     * smaller one.
     *
     * Input: s = "abba", k = 2
     * Output: "baab"
     *
     * Input: s = "aa", k = 2
     * Output: ""
     *
     * Input: s = "bacab", k = 1
     * Output: "abcba"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of lowercase English letters.
     * s is guaranteed to be palindromic.
     * 1 <= k <= 10^6
     * @param s
     * @param k
     * @return
     */
    // time = O(n * 26 * (26 + logk)), space = O(n + 26)
    public String smallestPalindrome(String s, int k) {
        int n = s.length(), m = n / 2;
        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }

        if (perm(m, cnt, k) < k) return "";

        char[] l = new char[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 26; j++) {
                if (cnt[j] == 0) continue;
                cnt[j]--;
                int p = perm(m - i - 1, cnt, k);
                if (p >= k) {
                    l[i] = (char)('a' + j);
                    break;
                }
                k -= p;
                cnt[j]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(l);
        if (n % 2 > 0) sb.append(s.charAt(n / 2));
        for (int i = m - 1; i >= 0; i--) sb.append(l[i]);
        return sb.toString();
    }

    private int comb(int a, int b, int k) {
        b = Math.min(b, a - b);
        long res = 1;
        for (int i = a, j = 1; j <= b; i--, j++) {
            res = res * i / j;
            if (res >= k) return k;
        }
        return (int)res;
    }

    private int perm(int n, int[] cnt, int k) {
        long res = 1;
        for (int x : cnt) {
            if (x == 0) continue;
            res *= comb(n, x, k);
            if (res >= k) return k;
            n -= x;
        }
        return (int)res;
    }
}