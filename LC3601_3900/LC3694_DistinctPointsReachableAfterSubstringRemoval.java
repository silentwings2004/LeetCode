package LC3601_3900;
import java.util.*;
public class LC3694_DistinctPointsReachableAfterSubstringRemoval {
    /**
     * You are given a string s consisting of characters 'U', 'D', 'L', and 'R', representing moves on an infinite 2D
     * Cartesian grid.
     *
     * 'U': Move from (x, y) to (x, y + 1).
     * 'D': Move from (x, y) to (x, y - 1).
     * 'L': Move from (x, y) to (x - 1, y).
     * 'R': Move from (x, y) to (x + 1, y).
     * You are also given a positive integer k.
     *
     * You must choose and remove exactly one contiguous substring of length k from s. Then, start from coordinate
     * (0, 0) and perform the remaining moves in order.
     *
     * Return an integer denoting the number of distinct final coordinates reachable.
     *
     * Input: s = "LUL", k = 1
     * Output: 2
     *
     * Input: s = "UDLR", k = 4
     * Output: 1
     *
     * Input: s = "UU", k = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only 'U', 'D', 'L', and 'R'.
     * 1 <= k <= s.length
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int distinctPoints(String s, int k) {
        int n = s.length();
        int[] sx = new int[n + 1], sy = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int[] t = get(s.charAt(i - 1));
            sx[i] = sx[i - 1] + t[0];
            sy[i] = sy[i - 1] + t[1];
        }

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i + k - 1 < n; i++) {
            int l = i + 1, r = i + k;
            int[] v = cal(sx, sy, l, r);
            int[] t = new int[]{sx[n] - v[0], sy[n] - v[1]};
            set.add(t[0] + "#" + t[1]);
        }
        return set.size();
    }

    private int[] cal(int[] sx, int[] sy, int l, int r) {
        return new int[]{sx[r] - sx[l - 1], sy[r] - sy[l - 1]};
    }

    private int[] get(char c) {
        int x = 0, y = 0;
        if (c == 'U') y++;
        if (c == 'D') y--;
        if (c == 'L') x--;
        if (c == 'R') x++;
        return new int[]{x, y};
    }
}