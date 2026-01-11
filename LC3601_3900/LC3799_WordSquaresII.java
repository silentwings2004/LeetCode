package LC3601_3900;
import java.util.*;
public class LC3799_WordSquaresII {
    /**
     * You are given a string array words, consisting of distinct 4-letter strings, each containing lowercase English
     * letters.
     *
     * A word square consists of 4 distinct words: top, left, right and bottom, arranged as follows:
     *
     * top forms the top row.
     * bottom forms the bottom row.
     * left forms the left column (top to bottom).
     * right forms the right column (top to bottom).
     * It must satisfy:
     *
     * top[0] == left[0], top[3] == right[0]
     * bottom[0] == left[3], bottom[3] == right[3]
     * Return all valid distinct word squares, sorted in ascending lexicographic order by the 4-tuple (top, left, right,
     * bottom).
     *
     * Input: words = ["able","area","echo","also"]
     * Output: [["able","area","echo","also"],["area","able","also","echo"]]
     *
     * Input: words = ["code","cafe","eden","edge"]
     * Output: []
     *
     * Constraints:
     *
     * 4 <= words.length <= 15
     * words[i].length == 4
     * words[i] consists of only lowercase English letters.
     * All words[i] are distinct.
     * @param words
     * @return
     */
    // S1
    // time = O(nlogn + n^4), space = O(logn)
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        Arrays.sort(words);
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String top = words[i];
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                String left = words[j];
                if (left.charAt(0) != top.charAt(0)) continue;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;
                    String right = words[k];
                    if (right.charAt(0) != top.charAt(3)) continue;
                    for (int u = 0; u < n; u++) {
                        if (u == i || u == j || u == k) continue;
                        String bottom = words[u];
                        if (bottom.charAt(0) == left.charAt(3) && bottom.charAt(3) == right.charAt(3)) {
                            res.add(Arrays.asList(top, left, right, bottom));
                        }
                    }
                }
            }
        }
        return res;
    }

    // S2
    // time = O(n^4), sapce = O(n)
    List<List<String>> res;
    public List<List<String>> wordSquares2(String[] words) {
        Arrays.sort(words);
        int[] path = new int[4];
        int n = words.length;
        boolean[] st = new boolean[n];
        res = new ArrayList<>();
        dfs(words, path, st, 0);
        return res;
    }

    private void dfs(String[] words, int[] path, boolean[] st, int u) {
        if (u == 4) {
            String top = words[path[0]];
            String left = words[path[1]];
            String right = words[path[2]];
            String bottom = words[path[3]];
            if (top.charAt(0) == left.charAt(0) && top.charAt(3) == right.charAt(0)
                    && bottom.charAt(0) == left.charAt(3) && bottom.charAt(3) == right.charAt(3)) {
                res.add(Arrays.asList(top, left, right, bottom));
            }
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!st[i]) {
                path[u] = i;
                st[i] = true;
                dfs(words, path, st, u + 1);
                st[i] = false;

            }
        }
    }
}