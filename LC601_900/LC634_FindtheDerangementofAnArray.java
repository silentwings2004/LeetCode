package LC601_900;

public class LC634_FindtheDerangementofAnArray {
    /**
     * In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element
     * appears in its original position.
     *
     * You are given an integer n. There is originally an array consisting of n integers from 1 to n in ascending order,
     * return the number of derangements it can generate. Since the answer may be huge, return it modulo 10^9 + 7.
     *
     * Input: n = 3
     * Output: 2
     *
     * Input: n = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^6
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public int findDerangement(int n) {
        if (n == 1) return 0;
        int mod = (int) 1e9 + 7;
        long[] f = new long[n + 1];
        f[1] = 0;
        f[2] = 1;
        for (int i = 3; i <= n; i++) {
            f[i] = (i - 1) * (f[i - 1] + f[i - 2]) % mod;
        }
        return (int)f[n];
    }
}