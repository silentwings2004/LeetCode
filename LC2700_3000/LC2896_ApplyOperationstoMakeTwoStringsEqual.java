package LC2700_3000;
import java.util.*;
public class LC2896_ApplyOperationstoMakeTwoStringsEqual {
    /**
     * You are given two 0-indexed binary strings s1 and s2, both of length n, and a positive integer x.
     *
     * You can perform any of the following operations on the string s1 any number of times:
     *
     * Choose two indices i and j, and flip both s1[i] and s1[j]. The cost of this operation is x.
     * Choose an index i such that i < n - 1 and flip both s1[i] and s1[i + 1]. The cost of this operation is 1.
     * Return the minimum cost needed to make the strings s1 and s2 equal, or return -1 if it is impossible.
     *
     * Note that flipping a character means changing it from 0 to 1 or vice-versa.
     *
     * Input: s1 = "1100011000", s2 = "0101001010", x = 2
     * Output: 4
     *
     * Input: s1 = "10110", s2 = "00011", x = 4
     * Output: -1
     *
     * Constraints:
     *
     * n == s1.length == s2.length
     * 1 <= n, x <= 500
     * s1 and s2 consist only of the characters '0' and '1'.
     * @param s1
     * @param s2
     * @param x
     * @return
     */
    // time = O(n), space = O(n)
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) q.add(i);
        }
        int m = q.size();
        if (m % 2 == 1) return -1;
        if (x == 1) return m / 2;

        int[] f = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            f[i] = f[i - 1] + x;
            if (i >= 2) {
                f[i] = Math.min(f[i], f[i - 2] + (q.get(i - 1) - q.get(i - 2)) * 2);
            }
        }
        return f[m] / 2;
    }
}
/**
 * dp的本质就是暴力
 * 方法1. O(n^2)
 * 无论如何操作，也不会影响1的奇偶性
 * 只要奇偶性一样，就一定可以操作把s1变成s2
 *
 * 方法2. O(n)
 * 花费x / 2的代价 => x是奇数的话，不好操作 => 都 x 2
 *
 */