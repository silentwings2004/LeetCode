package LC3001_3300;
import java.util.*;
public class LC3290_MaximumMultiplicationScore {
    /**
     * You are given an integer array a of size 4 and another integer array b of size at least 4.
     *
     * You need to choose 4 indices i0, i1, i2, and i3 from the array b such that i0 < i1 < i2 < i3. Your score will
     * be equal to the value a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3].
     *
     * Return the maximum score you can achieve.
     *
     * Input: a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]
     *
     * Output: 26
     *
     * Input: a = [-1,4,5,-2], b = [-5,-1,-3,-2,-4]
     *
     * Output: -1
     *
     * Constraints:
     *
     * a.length == 4
     * 4 <= b.length <= 10^5
     * -10^5 <= a[i], b[i] <= 10^5
     * @param a
     * @param b
     * @return
     */
    // time = O(n), space = O(n)
    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        long inf = (long)1e18;
        long[][] f = new long[n][5];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -inf);
        for (int i = 0; i < n; i++) f[i][0] = 0;
        f[0][1] = (long)a[0] * b[0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 4; j++) {
                if (i > 0) f[i][j] = f[i - 1][j];
                f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + (long)a[j - 1] * b[i]);
            }
        }
        return f[n - 1][4];
    }
}