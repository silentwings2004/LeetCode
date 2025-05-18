package LC2700_3000;

import java.util.HashSet;

public class LC2953_CountCompleteSubstrings {
    /**
     * You are given a string word and an integer k.
     *
     * A substring s of word is complete if:
     *
     * Each character in s occurs exactly k times.
     * The difference between two adjacent characters is at most 2. That is, for any two adjacent characters c1 and c2
     * in s, the absolute difference in their positions in the alphabet is at most 2.
     * Return the number of complete substrings of word.
     *
     * A substring is a non-empty contiguous sequence of characters in a string.
     *
     * Input: word = "igigee", k = 2
     * Output: 3
     *
     * Input: word = "aaabbbccc", k = 3
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= word.length <= 10^5
     * word consists only of lowercase English letters.
     * 1 <= k <= word.length
     * @param word
     * @param k
     * @return
     */
    // time = O(26 * 26 * n), space = O(n)
    public int countCompleteSubstrings(String word, int k) {
        int n = word.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && Math.abs(word.charAt(j) - word.charAt(j - 1)) <= 2) j++;
            res += helper(word, i, j - 1, k);
            i = j - 1;
        }
        return res;
    }

    private int helper(String s, int l, int r, int k) {
        int res = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = l; i <= r; i++) set.add(s.charAt(i));
        int tot = set.size();
        for (int len = 1; len <= tot; len++) {
            int width = len * k;
            int start = l, end = start + width - 1;
            int[] cnt = new int[26];
            for (int i = start; i <= Math.min(end, r); i++) cnt[s.charAt(i) - 'a']++;
            while (end <= r) {
                if (check(cnt, k)) res++;
                cnt[s.charAt(start++) - 'a']--;
                end++;
                if (end <= r) cnt[s.charAt(end) - 'a']++;
            }
        }
        return res;
    }

    private boolean check(int[] cnt, int k) {
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] != k) return false;
        }
        return true;
    }

    // S1.2
    // time = O(26 * 26 * n), space = O(n)
    public int countCompleteSubstrings2(String word, int k) {
        int n = word.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && Math.abs(word.charAt(j) - word.charAt(j - 1)) <= 2) j++;
            res += f(word.substring(i, j), k);
            i = j - 1;
        }
        return res;
    }

    private int f(String s, int k) {
        int res = 0;
        for (int m = 1; m <= 26; m++) {
            int sz = m * k;
            if (sz > s.length()) break;

            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); i++) {
                cnt[s.charAt(i) - 'a']++;
                int j = i - sz + 1;
                if (j >= 0) {
                    boolean flag = true;
                    for (int u = 0; u < 26; u++) {
                        if (cnt[u] > 0 && cnt[u] != k) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) res++;
                    cnt[s.charAt(j) - 'a']--;
                }
            }
        }
        return res;
    }
}