package LC601_900;
import java.util.*;
public class LC839_SimilarStringGroups {
    /**
     * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
     * Also two strings X and Y are similar if they are equal.
     *
     * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar,
     * but "star" is not similar to "tars", "rats", or "arts".
     *
     * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that
     * "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a
     * word is in the group if and only if it is similar to at least one other word in the group.
     *
     * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How
     * many groups are there?
     *
     * Input: strs = ["tars","rats","arts","star"]
     * Output: 2
     *
     * Input: strs = ["omv","ovm"]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= strs.length <= 300
     * 1 <= strs[i].length <= 300
     * strs[i] consists of lowercase letters only.
     * All words in strs have the same length and are anagrams of each other.
     * @param strs
     * @return
     */
    // time = O(n^2 * k), space = O(n)
    int[] p;
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;

        int cnt = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(strs[i], strs[j])) {
                    int pa = find(i), pb = find(j);
                    if (pa != pb) {
                        p[pa] = pb;
                        cnt--;
                    }
                }
            }
        }
        return cnt;
    }

    private boolean check(String s, String t) {
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) cnt++;
            if (cnt > 2) return false;
        }
        return true;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}