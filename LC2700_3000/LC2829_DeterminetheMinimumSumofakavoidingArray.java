package LC2700_3000;
import java.util.*;
public class LC2829_DeterminetheMinimumSumofakavoidingArray {
    /**
     * You are given two integers, n and k.
     *
     * An array of distinct positive integers is called a k-avoiding array if there does not exist any pair of distinct
     * elements that sum to k.
     *
     * Return the minimum possible sum of a k-avoiding array of length n.
     *
     * Input: n = 5, k = 4
     * Output: 18
     *
     * Input: n = 2, k = 6
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n, k <= 50
     * @param n
     * @param k
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int minimumSum(int n, int k) {
        int[] w = new int[n];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            w[i] = i + 1;
            set.add(w[i]);
        }
        int s = w[n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (w[i] + w[j] == k) {
                    set.remove(w[j]);
                    for (int u = s + 1;; u++) {
                        if (set.contains(k - u)) continue;
                        w[j] = u;
                        set.add(u);
                        s = u;
                        break;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) res += w[i];
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int minimumSum2(int n, int k) {
        HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 1, j = 0; j < n; j++) {
            while (set.contains(i)) i++;
            res += i;
            set.add(i);
            if (i < k) set.add(k - i);
        }
        return res;
    }

    // S3: Math
    // time = O(1), space = O(1)
    public int minimumSum3(int n, int k) {
        int m = Math.min(k / 2, n);
        return (1 + m) * m / 2 + (k + k + n - m - 1) * (n - m) / 2;
    }
}