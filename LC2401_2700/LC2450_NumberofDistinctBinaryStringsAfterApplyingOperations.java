package LC2401_2700;

public class LC2450_NumberofDistinctBinaryStringsAfterApplyingOperations {
    /**
     * You are given a binary string s and a positive integer k.
     *
     * You can apply the following operation on the string any number of times:
     *
     * Choose any substring of size k from s and flip all its characters, that is, turn all 1's into 0's, and all 0's
     * into 1's.
     * Return the number of distinct strings you can obtain. Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Note that:
     *
     * A binary string is a string that consists only of the characters 0 and 1.
     * A substring is a contiguous part of a string.
     *
     * Input: s = "1001", k = 3
     * Output: 4
     *
     * Input: s = "10110", k = 5
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= k <= s.length <= 10^5
     * s[i] is either 0 or 1.
     * @param s
     * @param k
     * @return
     */
    // time = O(logn), space = O(1)
    public int countDistinctStrings(String s, int k) {
        int n = s.length(), cnt = n - k + 1;
        long mod = (long)(1e9 + 7);
        return (int) qmi(2, cnt, mod);
    }

    private long qmi(long a, long k, long p) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % p;
            a = a * a % p;
            k >>= 1;
        }
        return res;
    }
}
