package LC3301_3600;
import java.util.*;
public class LC3306_CountofSubstringsContainingEveryVowelandKConsonantsII {
    /**
     * You are given a string word and a non-negative integer k.
     *
     * Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least
     * once and exactly k consonants.
     *
     * Input: word = "aeioqq", k = 1
     * Output: 0
     *
     * Input: word = "aeiou", k = 0
     * Output: 1
     *
     * Input: word = "ieaouqqieaouqq", k = 1
     * Output: 3
     *
     * Constraints:
     *
     * 5 <= word.length <= 2 * 10^5
     * word consists only of lowercase English letters.
     * 0 <= k <= word.length - 5
     * @param word
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long countOfSubstrings(String word, int k) {
        int n = word.length(), full = (1 << 5) - 1;
        long res = 0;
        String vowel = "aeiou";
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            char c = word.charAt(i - 1);
            s[i] = s[i - 1] + (vowel.indexOf(c) != -1 ? 0 : 1);
        }

        int j = 0, state = 0;
        int[] w = new int[5];
        Arrays.fill(w, -1);
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int p = vowel.indexOf(c);
            if (p != -1) {
                w[p] = i;
                state |= 1 << p;
            }
            while (s[i + 1] - s[j] > k) {
                c = word.charAt(j);
                p = vowel.indexOf(c);
                if (p != -1) {
                    if (w[p] == j) {
                        w[p] = -1;
                        state ^= 1 << p;
                    }
                }
                j++;
            }
            if (state == full && s[i + 1] - s[j] == k) {
                int l = j, r = i;
                while (l < r) {
                    int mid = l + r + 1 >> 1;
                    boolean flag = true;
                    for (int u = 0; u < 5; u++) {
                        if (w[u] < mid) flag = false;
                    }
                    if (s[i + 1] - s[mid] == k && flag) l = mid;
                    else r = mid - 1;
                }
                res += r - j + 1;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long countOfSubstrings2(String word, int k) {
        return helper(word, k) - helper(word, k + 1);
    }

    private long helper(String s, int k) {
        String vowel = "aeiou";
        int n = s.length(), full = (1 << 5) - 1;
        int[] cnt = new int[5];
        int tc = 0, state = 0;
        long res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            int p = vowel.indexOf(c);
            if (p == -1) tc++;
            else {
                cnt[p]++;
                state |= 1 << p;
            }
            while (state == full && tc >= k) {
                res += n - i;
                p = vowel.indexOf(s.charAt(j++));
                if (p == -1) tc--;
                else {
                    cnt[p]--;
                    if (cnt[p] == 0) state ^= 1 << p;
                }
            }
        }
        return res;
    }
}
/**
 * == k => >= k - >= k + 1
 */