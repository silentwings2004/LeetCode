package LC3001_3300;
import java.util.*;
public class LC3035_MaximumPalindromesAfterOperations {
    /**
     * You are given a 0-indexed string array words having length n and containing 0-indexed strings.
     *
     * You are allowed to perform the following operation any number of times (including zero):
     *
     * Choose integers i, j, x, and y such that 0 <= i, j < n, 0 <= x < words[i].length, 0 <= y < words[j].length, and
     * swap the characters words[i][x] and words[j][y].
     * Return an integer denoting the maximum number of palindromes words can contain, after performing some operations.
     *
     * Note: i and j may be equal during an operation.
     *
     * Input: words = ["abbb","ba","aa"]
     * Output: 3
     *
     * Input: words = ["abc","ab"]
     * Output: 2
     *
     * Input: words = ["cd","ef","a"]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 100
     * words[i] consists only of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(nlogn + n * k), space = O(logn)
    public int maxPalindromesAfterOperations(String[] words) {
        int[] cnt = new int[26];
        int n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int u = words[i].charAt(j) - 'a';
                cnt[u]++;
            }
        }

        int even = 0, odd = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                if (cnt[i] % 2 == 1) {
                    odd++;
                    cnt[i]--;
                    if (cnt[i] > 0) even += cnt[i];
                } else even += cnt[i];
            }
        }

        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        int res = 0;
        for (int i = 0; i < n; i++) {
            int m = words[i].length();
            if (m % 2 == 0) {
                if (even >= m) {
                    even -= m;
                    res++;
                }
            } else {
                if (odd > 0) {
                    odd--;
                    m--;
                } else {
                    if (even > 0) {
                        even -= 2;
                        odd++;
                        m--;
                    }
                }
                if (even >= m) {
                    even -= m;
                    res++;
                }
            }
        }
        return res;
    }

    // S2
    // time = O(nlogn + n * k), space O(logn)
    public int maxPalindromesAfterOperations2(String[] words) {
        int[] cnt = new int[26];
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                cnt[w.charAt(i) - 'a']++;
            }
        }

        int left = 0, res = 0;
        for (int i = 0; i < 26; i++) left += cnt[i] / 2;
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        for (String w : words) {
            int m = w.length() / 2;
            if (left < m) break;
            left -= m;
            res++;
        }
        return res;
    }
}
/**
 * 偶回文串的特点：左边一半的每种字母个数 = 右边一半的每种字母个数
 * 奇回文串的特点：偶回文串的特点 + 正中间的位置，填什么都可以 -> 留到最后再填
 * 优先填短的字符串
 */