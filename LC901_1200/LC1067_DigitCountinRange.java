package LC901_1200;

public class LC1067_DigitCountinRange {
    /**
     * Given a single-digit integer d and two integers low and high, return the number of times that d occurs as a digit
     * in all integers in the inclusive range [low, right].
     *
     * Input: d = 1, low = 1, high = 13
     * Output: 6
     *
     * Input: d = 3, low = 100, high = 250
     * Output: 35
     *
     * Constraints:
     *
     * 0 <= d <= 9
     * 1 <= low <= high <= 2 * 10^8
     * @param d
     * @param low
     * @param high
     * @return
     */
    // time = O(logn), space = O(1)
    public int digitsCount(int d, int low, int high) {
        return helper(d, high) - helper(d, low - 1);
    }

    private int helper(int d, int n) { // how many ds in [1 ~ n]
        String s = n + "";
        int m = s.length(), res = 0;
        if (d != 0) {
            for (int i = 1; i <= m; i++) {
                int a = n / (int) Math.pow(10, i);
                res += a * (int) Math.pow(10, i - 1);

                if (s.charAt(m - i) - '0' > d) res += (int) Math.pow(10, i - 1);
                else if (s.charAt(m - i) - '0' == d) res += n % (int) Math.pow(10, i - 1) + 1;
            }
        } else {
            for (int i = 1; i < m; i++) {
                int a = n / (int) Math.pow(10, i);
                res += (a - 1) * (int) Math.pow(10, i - 1);

                if (s.charAt(m - i) - '0' > d) res += (int) Math.pow(10, i - 1);
                else if (s.charAt(m - i) - '0' == d) res += n % (int) Math.pow(10, i - 1) + 1;
            }
        }
        return res;
    }
}
/**
 * similar to LC233
 * LC1067的扩展
 * 有一个range
 * 区间和
 * x x x [x x x x] x
 * 这个1从哪个位数上来 => 按照位数来分类
 * n = 2 3 5 4 7
 *     x x 1 x x
 *         ^
 *     0 0   x x
 * how many numbers whose ba wei is 1
 * how many numbers whose shi wei is 1
 * how many numbers whose ge wei is 1
 * 1) 00        x x -> 00 ~ 99
 *    22
 * ret += 23 * 100
 * 2) 23 d x x -> 0 ~ 99  or 0
 * ret += 1 * 100
 *
 * 0 x x x x
 */