package LC2401_2700;
import java.util.*;
public class LC2565_SubsequenceWiththeMinimumScore {
    /**
     * You are given two strings s and t.
     *
     * You are allowed to remove any number of characters from the string t.
     *
     * The score string is 0 if no characters are removed from the string t, otherwise:
     *
     * Let left be the minimum index among all removed characters.
     * Let right be the maximum index among all removed characters.
     * Then the score of the string is right - left + 1.
     *
     * Return the minimum possible score to make t a subsequence of s.
     *
     * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
     * of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a
     * subsequence of "abcde" while "aec" is not).
     *
     * Input: s = "abacaba", t = "bzaa"
     * Output: 1
     *
     * Input: s = "cde", t = "xyz"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 10^5
     * s and t consist of only lowercase English letters.
     * @param s
     * @param t
     * @return
     */
    // S1: 前后缀分解
    // time = O(max(m, n)), space = O(m)
    public int minimumScore(String s, String t) {
        int m = s.length(), n = t.length();
        int[] suf = new int[m + 1];
        suf[m] = n;
        for (int i = m - 1, j = n - 1; i >= 0; i--) {
            if (j >= 0 && s.charAt(i) == t.charAt(j)) j--;
            suf[i] = j + 1;
        }
        int res = suf[0];

        for (int i = 0, j = 0; i < m && j < n; i++) {
            if (s.charAt(i) == t.charAt(j) && suf[i + 1] >= ++j) {
                res = Math.min(res, suf[i + 1] - j);
                if (j == m) break;
            }
        }
        return res;
    }

    // S2
    // time = O((m + n) * logm), space = O(m)
    public int minimumScore2(String s, String t) {
        int m = s.length(), n = t.length();
        HashMap<Character, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, new TreeSet<>());
            map.get(c).add(i);
        }

        int res = Integer.MAX_VALUE, idx = -1;
        for (int i = 0; i < n; i++) {
            int j = n - 1;
            int last = m;
            while (j >= i && map.containsKey(t.charAt(j)) && map.get(t.charAt(j)).lower(last) != null && map.get(t.charAt(j)).lower(last) > idx) {
                last = map.get(t.charAt(j)).lower(last);
                j--;
            }
            res = Math.min(res, j - i + 1);
            if (!map.containsKey(t.charAt(i)) || map.get(t.charAt(i)).higher(idx) == null) break;
            else idx = map.get(t.charAt(i)).higher(idx);
        }
        return res;
    }

    // S3: 3 pass
    // time = O(nlogn), space = O(n)
    public int minimumScore3(String s, String t) {
        int m = s.length(), n = t.length();
        int[] left = new int[n];
        Arrays.fill(left, Integer.MAX_VALUE / 2);
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && s.charAt(j) != t.charAt(i)) j++;
            if (j < m) left[i] = j++;
        }
        int[] right = new int[n];
        Arrays.fill(right, Integer.MIN_VALUE / 2);
        for (int i = n - 1, j = m - 1; i >= 0; i--) {
            while (j >= 0 && s.charAt(j) != t.charAt(i)) j--;
            if (j >= 0) right[i] = j--;
        }

        int l = 0, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid, s, t, left, right)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int len, String s, String t, int[] left, int[] right) {
        int m = s.length(), n = t.length();
        if (right[len] != Integer.MIN_VALUE / 2) return true;
        if (left[n - len - 1] != Integer.MAX_VALUE / 2) return true;
        for (int i = 1; i + len < n; i++) {
            if (left[i - 1] < right[i + len]) return true;
        }
        return false;
    }
}
/**
 * xxx [yyy] zzz
 * xxx zzz 拼接起来是s的subsequence
 * 这等价于xxx必须是s的一个前缀的subsequence，而zzz必须是s的一个后缀的subsequence，且s的前缀和后缀不能重合。
 * left[i]: the shortest prefix of s which contains t[0:i]
 * right[j]: the shortest suffix of s which contains t[j:n-1]
 * left[i] < right[j]
 * yyy 长度：单调性 => 二分
 */