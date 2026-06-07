package LC3901_4200;

public class LC3954_SumofCompatibleNumbersinRangeI {
    /**
     * You are given two integers n and k.
     *
     * A positive integer x is called compatible if it satisfies both of the following conditions:
     *
     * abs(n - x) <= k
     * (n & x) == 0
     * Return the sum of all compatible integers x.
     *
     * Note:
     *
     * Here, & denotes the bitwise AND operator.
     * The absolute difference between integers i and j is defined as abs(i - j).
     *
     * Input: n = 2, k = 3
     * Output: 10
     *
     * Input: n = 5, k = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 1 <= k <= 100
     * @param n
     * @param k
     * @return
     */
    // time = O(k), space = O(1)
    public int sumOfGoodIntegers(int n, int k) {
        int res = 0;
        for (int i = Math.max(1, n - k); i <= n + k; i++) {
            if ((n & i) == 0) res += i;
        }
        return res;
    }
}