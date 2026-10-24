package LC3901_4200;
import java.util.*;
public class LC4090_CountMagicalSubstringPairsI {
    /**
     * You are given a string s of length n consisting of lowercase English letters.
     *
     * For every triplet of indices (i, j, k) satisfying 0 <= i <= j < k < n, define:
     *
     * s1 = s[i..j]
     * s2 = s[j + 1..k]
     * A pair of substrings (s1, s2) is called magical if the number of distinct characters in s1 is equal to the number
     * of distinct characters in s2.
     *
     * Return the number of triplets (i, j, k) for which (s1, s2) is a magical pair.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "aba"
     * Output: 2
     *
     * Input: s = "kkk"
     * Output: 4
     *
     * Input: s = "abcac"
     * Output: 7
     *
     * Constraints:
     *
     * 2 <= s.length <= 2000
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int countMagicalPairs(String s) {
        int n = s.length();
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int state = 0;
            for (int j = i; j < n; j++) {
                int u = s.charAt(j) - 'a';
                state |= 1 << u;
                int m = Integer.bitCount(state);
                int h1 = i << 12 | j;
                int h2 = m << 12 | i;
                map.put(h1, m);
                cnt.put(h2, cnt.getOrDefault(h2, 0) + 1);
            }
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                int h1 = i << 12 | j;
                int m = map.get(h1);
                int h2 = m << 12 | (j + 1);
                res += cnt.getOrDefault(h2, 0);
            }
        }
        return res;
    }
}