package LC901_1200;
import java.util.*;
public class LC967_NumbersWithSameConsecutiveDifferences {
    /**
     * Return all non-negative integers of length n such that the absolute difference between every two consecutive
     * digits is k.
     *
     * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is
     * invalid.
     *
     * You may return the answer in any order.
     *
     * Input: n = 3, k = 7
     * Output: [181,292,707,818,929]
     *
     * Input: n = 2, k = 1
     * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
     *
     * Constraints:
     *
     * 2 <= n <= 9
     * 0 <= k <= 9
     * @param n
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    int n, k;
    List<Integer> res;
    public int[] numsSameConsecDiff(int n, int k) {
        res = new ArrayList<>();
        this.n = n;
        this.k = k;
        for (int i = 1; i <= 9; i++) dfs(1, i);

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private void dfs(int u, int x) {
        if (u == n) res.add(x);
        else {
            if (x % 10 - k >= 0) dfs(u + 1, x * 10 + x % 10 - k);
            if (k != 0 && x % 10 + k < 10) dfs(u + 1, x * 10 + x % 10 + k);
        }
    }
}