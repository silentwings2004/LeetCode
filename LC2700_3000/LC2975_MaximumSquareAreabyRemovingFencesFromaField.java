package LC2700_3000;
import java.util.*;
public class LC2975_MaximumSquareAreabyRemovingFencesFromaField {
    /**
     * There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1) and (m, n) containing some horizontal
     * and vertical fences given in arrays hFences and vFences respectively.
     *
     * Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i], n) and vertical fences are from the
     * coordinates (1, vFences[i]) to (m, vFences[i]).
     *
     * Return the maximum area of a square field that can be formed by removing some fences (possibly none) or -1 if it
     * is impossible to make a square field.
     *
     * Since the answer may be large, return it modulo 109 + 7.
     *
     * Note: The field is surrounded by two horizontal fences from the coordinates (1, 1) to (1, n) and (m, 1) to (m, n)
     * and two vertical fences from the coordinates (1, 1) to (m, 1) and (1, n) to (m, n). These fences cannot be removed.
     *
     * Input: m = 4, n = 3, hFences = [2,3], vFences = [2]
     * Output: 4
     *
     * Input: m = 6, n = 7, hFences = [2], vFences = [4]
     * Output: -1
     *
     * Constraints:
     *
     * 3 <= m, n <= 10^9
     * 1 <= hFences.length, vFences.length <= 600
     * 1 < hFences[i] < m
     * 1 < vFences[i] < n
     * hFences and vFences are unique.
     * @param m
     * @param n
     * @param hFences
     * @param vFences
     * @return
     */
    // time = O(m^2 + n^2), space = O(m + n)
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int hl = hFences.length, wl = vFences.length;
        int[] h = new int[hl + 2], w = new int[wl + 2];
        h[0] = 1;
        h[h.length - 1] = m;
        w[0] = 1;
        w[w.length - 1] = n;
        for (int i = 1; i < h.length - 1; i++) h[i] = hFences[i - 1];
        for (int i = 1; i < w.length - 1; i++) w[i] = vFences[i - 1];
        Arrays.sort(h);
        Arrays.sort(w);

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                set.add(h[j] - h[i]);
            }
        }

        int res = 0;
        for (int i = 0; i < w.length; i++) {
            for (int j = i + 1; j < w.length; j++) {
                int x = w[j] - w[i];
                if (set.contains(x)) {
                    res = Math.max(res, x);
                }
            }
        }
        if (res == 0) return -1;
        long mod = (long)(1e9 + 7);
        return (int)(1L * res * res % mod);
    }
}