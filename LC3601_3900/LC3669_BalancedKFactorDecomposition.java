package LC3601_3900;
import java.util.*;
public class LC3669_BalancedKFactorDecomposition {
    /**
     * Given two integers n and k, split the number n into exactly k positive integers such that the product of these
     * integers is equal to n.
     *
     * Return any one split in which the maximum difference between any two numbers is minimized. You may return the
     * result in any order.
     *
     * Input: n = 100, k = 2
     * Output: [10,10]
     *
     * Input: n = 44, k = 3
     * Output: [2,2,11]
     *
     * Constraints:
     *
     * 4 <= n <= 105
     * 2 <= k <= 5
     * k is strictly less than the total number of positive divisors of n.
     * @param n
     * @param k
     * @return
     */
    // time = O(D^(k/2)), space = O(k)  D <= 128 是因子个数的最大值
    List<Integer> q;
    int[] path, res;
    int k, bd;
    public int[] minDifference(int n, int k) {
        this.k = k;
        q = new ArrayList<>();
        for (int i = 1; i <= n / i; i++) {
            if (n % i == 0) q.add(i);
            if (i != n / i) q.add(n / i);
        }
        Collections.sort(q);
        path = new int[k];
        res = new int[k];
        bd = Integer.MAX_VALUE;
        dfs(n, 0, 0);
        return res;
    }

    private void dfs(int r, int start, int u) {
        if (u == k - 1) {
            int last = r, prev = u == 0 ? 1 : path[u - 1];
            if (last < prev) return;
            path[u] = last;
            int d = path[k - 1] - path[0];
            if (d < bd) {
                bd = d;
                res = path.clone();
            }
            return;
        }

        for (int i = start; i < q.size(); i++) {
            int d = q.get(i);
            if (d > r) break;
            if (r % d != 0) continue;
            path[u] = d;
            dfs(r / d, i, u + 1);
        }
    }
}