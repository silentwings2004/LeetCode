package LC2700_3000;
import java.util.*;
public class LC2901_LongestUnequalAdjacentGroupsSubsequenceII {
    /**
     * You are given an integer n, a 0-indexed string array words, and a 0-indexed array groups, both arrays having
     * length n.
     *
     * The hamming distance between two strings of equal length is the number of positions at which the corresponding
     * characters are different.
     *
     * You need to select the longest subsequence from an array of indices [0, 1, ..., n - 1], such that for the
     * subsequence denoted as [i0, i1, ..., ik - 1] having length k, the following holds:
     *
     * For adjacent indices in the subsequence, their corresponding groups are unequal, i.e.,
     * groups[ij] != groups[ij + 1], for each j where 0 < j + 1 < k.
     * words[ij] and words[ij + 1] are equal in length, and the hamming distance between them is 1, where 0 < j + 1 < k,
     * for all indices in the subsequence.
     * Return a string array containing the words corresponding to the indices (in order) in the selected subsequence.
     * If there are multiple answers, return any of them.
     *
     * A subsequence of an array is a new array that is formed from the original array by deleting some (possibly none)
     * of the elements without disturbing the relative positions of the remaining elements.
     *
     * Note: strings in words may be unequal in length.
     *
     * Input: n = 3, words = ["bab","dab","cab"], groups = [1,2,2]
     * Output: ["bab","cab"]
     *
     * Input: n = 4, words = ["a","b","c","d"], groups = [1,2,3,4]
     * Output: ["a","b","c","d"]
     *
     * Constraints:
     *
     * 1 <= n == words.length == groups.length <= 1000
     * 1 <= words[i].length <= 10
     * 1 <= groups[i] <= n
     * words consists of distinct strings.
     * words[i] consists of lowercase English letters.
     * @param n
     * @param words
     * @param groups
     * @return
     */
    // time = O(n^2), space = O(n)
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[] f = new int[n], path = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (groups[i] != groups[j] && check(words[i], words[j]) && f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                    path[i] = j;
                }
            }
        }
        int ans = 0, k = 0;
        for (int i = 0; i < n; i++) {
            if (ans < f[i]) {
                ans = f[i];
                k = i;
            }
        }
        List<String> res = new LinkedList<>();
        int t = f[k] + 1;
        while (t > 0) {
            res.add(0, words[k]);
            k = path[k];
            t--;
        }
        return res;
    }

    private boolean check(String s, String t) {
        if (s.length() != t.length() || s.equals(t)) return false;
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) cnt++;
            if (cnt > 1) return false;
        }
        return true;
    }
}
