package LC601_900;
import java.util.*;
public class LC756_PyramidTransitionMatrix {
    /**
     * You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter. Each
     * row of blocks contains one less block than the row beneath it and is centered on top.
     *
     * To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed. A
     * triangular pattern consists of a single block stacked on top of two blocks. The patterns are given as a list of
     * three-letter strings allowed, where the first two characters of a pattern represent the left and right bottom
     * blocks respectively, and the third character is the top block.
     *
     * For example, "ABC" represents a triangular pattern with a 'C' block stacked on top of an 'A' (left) and 'B'
     * (right) block. Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
     * You start with a bottom row of blocks bottom, given as a single string, that you must use as the base of the pyramid.
     *
     * Given bottom and allowed, return true if you can build the pyramid all the way to the top such that every
     * triangular pattern in the pyramid is in allowed, or false otherwise.
     *
     * Input: bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
     * Output: true
     *
     * Input: bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= bottom.length <= 6
     * 0 <= allowed.length <= 216
     * allowed[i].length == 3
     * The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E', 'F'}.
     * All the values of allowed are unique.
     * @param bottom
     * @param allowed
     * @return
     */
    // time = O(A^n), space = O(n^2)
    List<Character>[][] g;
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        g = new List[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                g[i][j] = new ArrayList<>();
            }
        }

        for (String s : allowed) {
            int a = s.charAt(0) - 'A', b = s.charAt(1) - 'A';
            char c = s.charAt(2);
            g[a][b].add(c);
        }
        return dfs(bottom, "", 0);
    }

    private boolean dfs(String bottom, String up, int u) {
        if (bottom.length() == 1) return true;
        if (u == bottom.length() - 1) return dfs(up, "", 0);

        int a = bottom.charAt(u) - 'A', b = bottom.charAt(u + 1) - 'A';
        for (char c : g[a][b]) {
            if (dfs(bottom, up + c, u + 1)) return true;
        }
        return false;
    }
}