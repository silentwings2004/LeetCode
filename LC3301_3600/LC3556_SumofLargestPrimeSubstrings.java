package LC3301_3600;
import java.util.*;
public class LC3556_SumofLargestPrimeSubstrings {
    /**
     * Given a string s, find the sum of the 3 largest unique prime numbers that can be formed using any of its
     * substrings.
     *
     * Return the sum of the three largest unique prime numbers that can be formed. If fewer than three exist, return
     * the sum of all available primes. If no prime numbers can be formed, return 0.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Note: Each prime number should be counted only once, even if it appears in multiple substrings. Additionally,
     * when converting a substring to an integer, any leading zeros are ignored.
     *
     * Input: s = "12234"
     * Output: 1469
     *
     * Input: s = "111"
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= s.length <= 10
     * s consists of only digits.
     * @param s
     * @return
     */
    // time = O(nlogk), space = O(1)
    public long sumOfLargestPrimes(String s) {
        int n = s.length();
        TreeSet<Long> set = new TreeSet<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long x = Long.parseLong(s.substring(i, j + 1));
                if (check(x) && set.add(x)) {
                    set.add(x);
                    res += x;
                    if (set.size() > 3) {
                        res -= set.first();
                        set.remove(set.first());
                    }
                }
            }
        }
        return res;
    }

    private boolean check(long x) {
        if (x < 2) return false;
        for (long i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
