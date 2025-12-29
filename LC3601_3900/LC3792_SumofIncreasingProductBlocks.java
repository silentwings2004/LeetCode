package LC3601_3900;

public class LC3792_SumofIncreasingProductBlocks {
    /**
     * You are given an integer n.
     *
     * A sequence is formed as follows:
     *
     * The 1st block contains 1.
     * The 2nd block contains 2 * 3.
     * The ith block is the product of the next i consecutive integers.
     * Let F(n) be the sum of the first n blocks.
     *
     * Return an integer denoting F(n) modulo 10^9 + 7.
     *
     * Input: n = 3
     * Output: 127
     *
     * Input: n = 7
     * Output: 6997165
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(n^2), space = O(1)
    public int sumOfBlocks(int n) {
        long mod = (long)(1e9 + 7), res = 0;
        for (int i = 1, st = 1; i <= n; i++) {
            long p = 1;
            for (int j = 0; j < i; j++) {
                p = p * (st + j) % mod;
            }
            res = (res + p) % mod;
            st += i;
        }
        return (int)res;
    }
}