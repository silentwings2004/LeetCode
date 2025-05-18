package LC3001_3300;
import java.util.*;
public class LC3135_EqualizeStringsbyAddingorRemovingCharactersatEnds {
    /**
     * Given two strings initial and target, your task is to modify initial by performing a series of operations to make
     * it equal to target.
     *
     * In one operation, you can add or remove one character only at the beginning or the end of the string initial.
     *
     * Return the minimum number of operations required to transform initial into target.
     *
     * Input: initial = "abcde", target = "cdef"
     * Output: 3
     *
     * Input: initial = "axxy", target = "yabx"
     * Output: 6
     *
     * Input: initial = "xyz", target = "xyz"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= initial.length, target.length <= 1000
     * initial and target consist only of lowercase English letters.
     * @param initial
     * @param target
     * @return
     */
    // S1
    // time = O(n^2 * logn), space = O(n)
    public int minOperations(String initial, String target) {
        int m = initial.length(), n = target.length();
        int maxLen = 0;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(initial, target, mid)) l = mid;
            else r = mid - 1;
        }
        return m + n - r * 2;
    }

    private boolean check(String a, String b, int mid) {
        int m = a.length(), n = b.length();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i + mid - 1 < m; i++) set.add(a.substring(i, i + mid));
        for (int i = 0; i + mid - 1 < n; i++) {
            if (set.contains(b.substring(i, i + mid))) return true;
        }
        return false;
    }

    // S2: dp
    // time = O(m * n), space = O(n)
    public int minOperations2(String initial, String target) {
        int m = initial.length(), n = target.length();
        int[] f = new int[n + 1];
        int maxLen = 0;
        for (int i = 0; i < m; i++) {
            int[] g = new int[n];
            for (int j = 0; j < n; j++) {
                if (initial.charAt(i) == target.charAt(j)) g[j] = (j > 0 ? f[j - 1] : 0) + 1;
                maxLen = Math.max(maxLen, g[j]);
            }
            f = g.clone();
        }
        return m + n - 2 * maxLen;
    }
}