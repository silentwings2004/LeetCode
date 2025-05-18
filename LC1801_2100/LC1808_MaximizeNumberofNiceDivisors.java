package LC1801_2100;

public class LC1808_MaximizeNumberofNiceDivisors {
    /**
     * You are given a positive integer primeFactors. You are asked to construct a positive integer n that satisfies the
     * following conditions:
     *
     * The number of prime factors of n (not necessarily distinct) is at most primeFactors.
     * The number of nice divisors of n is maximized. Note that a divisor of n is nice if it is divisible by every prime
     * factor of n. For example, if n = 12, then its prime factors are [2,2,3], then 6 and 12 are nice divisors, while
     * 3 and 4 are not.
     * Return the number of nice divisors of n. Since that number can be too large, return it modulo 10^9 + 7.
     *
     * Note that a prime number is a natural number greater than 1 that is not a product of two smaller natural numbers.
     * The prime factors of a number n is a list of prime numbers such that their product equals n.
     *
     * Input: primeFactors = 5
     * Output: 6
     *
     * Input: primeFactors = 8
     * Output: 18
     *
     * Constraints:
     *
     * 1 <= primeFactors <= 10^9
     * @param primeFactors
     * @return
     */
    // time = O(logn), space = O(1)
    final long MOD = (int)(1e9 + 7);
    public int maxNiceDivisors(int primeFactors) {
        int m = primeFactors;
        if (m <= 3) return m;
        if (m % 3 == 0) return (int) qmi(3, m / 3);
        if (m % 3 == 1) return (int)(qmi(3, (m - 4) / 3) * 4 % MOD);
        return (int)(qmi(3, (m - 2) / 3) * 2 % MOD);
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            k >>= 1;
        }
        return res;
    }
}
/**
 * n = p1^a1 * p2^a2 * ... * pk^ak
 * => f(n) = a1 * a2 * ... * ak
 * a1 + a2 + ... + ak = m => math problem 要让这些正整数乘积最大 =>
 * 尽可能拆分出尽量多的3，其余的用2表示
 * ai >= 5  拆分成3和ai-3 => 3 * (ai - 3) - ai = 2ai - 9 >= 1 > 0
 * => 1 <= ai <= 4 有5的话必然不是最优解，因为肯定可以继续拆来得到更好的结果
 * 4 = 2 + 2 => 1 <= w <= 3
 * 如果有3个2 => 2 * 2 * 2 < 3 * 3 => 所以3个2没有拆成2个3好
 * 最优解里必然最多只包含2个2
 * 由2和3组成，并且最多包含2个2
 * => m % 3 == 0 => m / 3 个3
 * => m % 3 == 1 => (m - 4) / 3 个3和2个2
 * => m % 3 == 2 => (m - 2) / 3 个3和1个2
 * 如果 m <= 3的话，直接返回m即可
 * 乘积用快速幂来算
 */