package LC601_900;
import java.util.*;
public class LC833_FindAndReplaceinString {
    /**
     * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations
     * are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
     *
     * To complete the ith replacement operation:
     *
     * Check if the substring sources[i] occurs at index indices[i] in the original string s.
     * If it does not occur, do nothing.
     * Otherwise if it does occur, replace that substring with targets[i].
     * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this
     * replacement will be "eeecd".
     *
     * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the
     * indexing of each other. The testcases will be generated such that the replacements will not overlap.
     *
     * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because
     * the "ab" and "bc" replacements overlap.
     * Return the resulting string after performing all replacement operations on s.
     *
     * A substring is a contiguous sequence of characters in a string.
     *
     * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
     * Output: "eeebffff"
     *
     * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
     * Output: "eeecd"
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * k == indices.length == sources.length == targets.length
     * 1 <= k <= 100
     * 0 <= indexes[i] < s.length
     * 1 <= sources[i].length, targets[i].length <= 50
     * s consists of only lowercase English letters.
     * sources[i] and targets[i] consist of only lowercase English letters.
     * @param s
     * @param indices
     * @param sources
     * @param targets
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = indices.length, m = s.length();
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) id[i] = i;
        Arrays.sort(id, (o1, o2) -> indices[o1] - indices[o2]);
        for (int i = n - 1; i >= 0; i--) {
            int k = id[i];
            int j = indices[k], len = sources[k].length();
            if (j + len > m) continue;
            if (s.substring(j, j + len).equals(sources[k])) {
                s = s.substring(0, j) + targets[k] + s.substring(j + len);
            }
        }
        return s;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length(), m = indices.length;
        Integer[] id = new Integer[m];
        for (int i = 0; i < m; i++) id[i] = i;
        Arrays.sort(id, (o1, o2) -> indices[o1] - indices[o2]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < n; i++) {
            if (j >= m || i != indices[id[j]]) sb.append(s.charAt(i));
            else {
                int k = id[j];
                int l = indices[k], r = l + sources[k].length();
                if (r <= n) {
                    String str = s.substring(l, r);
                    if (str.equals(sources[k])) {
                        sb.append(targets[k]);
                        i = r - 1;
                    } else sb.append(s.charAt(i));
                } else sb.append(s.charAt(i));
                j++;
            }
        }
        return sb.toString();
    }
}