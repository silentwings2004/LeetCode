package LC3301_3600;
import java.util.*;
public class LC3344_MaximumSizedArray {
    /**
     * Given a positive integer s, let A be a 3D array of dimensions n × n × n, where each element A[i][j][k] is defined
     * as:
     *
     * A[i][j][k] = i * (j OR k), where 0 <= i, j, k < n.
     * Return the maximum possible value of n such that the sum of all elements in array A does not exceed s.
     *
     * Input: s = 10
     * Output: 2
     *
     * Input: s = 0
     * Output: 1
     *
     * Constraints:
     *
     * 0 <= s <= 10^15
     * @param s
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int maxSizedArray(long s) {
        long t1 = 0, t2 = 0;
        int res = 0;
        for (int n = 1;; n++) {
            t1 += n - 1;
            for (int j = 0; j < n - 1; j++) t2 += (n - 1 | j) * 2L;
            t2 += n - 1;
            if (t1 * t2 > s) {
                res = n - 1;
                break;
            }
        }
        return res;
    }

    // S2
    // time = O((logn)^2), space = O(1)
    public int maxSizedArray2(long s) {
        int l = 1, r = 1200;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(s, mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(long s, int n) {
        int p = n * n;
        long sor = 0, sidx = (n - 1) * n / 2;
        for (int i = 0; (1 << i) < n; i++) {
            int c1 = cal(n - 1, i), c0 = n - c1;
            int anyOne = p - c0 * c0;
            sor += anyOne * (1L << i);
        }
        return sor * sidx <= s;
    }

    private int cal(int limit, int k) {
        int res = limit / (1 << (k + 1)) * (1 << k);
        limit %= (1 << (k + 1));
        if (limit >= 1 << k) res += limit - (1 << k) + 1;
        return res;
    }

    // S3
    // time = O((logn)^2), space = O(1)
    public int maxSizedArray3(long s) {
        int l = 1, r = 1200;
        long[] f = new long[1210];
        Arrays.fill(f, -1);
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (f[mid] == -1) {
                HashMap<Integer, Integer> cnt = new HashMap<>();
                for (int i = 1; i < mid; i++) {
                    int j = i;
                    while (j > 0) {
                        int x = j & -j;
                        cnt.put(x, cnt.getOrDefault(x, 0) + 1);
                        j ^= x;
                    }
                }
                f[mid] = 0;
                for (int k : cnt.keySet()) {
                    f[mid] += 1L * k * (mid * mid - (mid - cnt.get(k)) * (mid - cnt.get(k)));
                }
                f[mid] *= (mid - 1) * mid / 2;
            }
            if (f[mid] <= s) l = mid;
            else r = mid - 1;
        }
        return r;
    }
}