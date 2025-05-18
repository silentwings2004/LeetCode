package LC001_300;
import java.util.*;
public class LC233_NumberofDigitOne {
    /**
     * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal
     * to n.
     *
     * Input: n = 13
     * Output: 6
     *
     * Constraints:
     *
     * 0 <= n <= 2 * 10^9
     * @param n
     * @return
     */
    // S1
    // time = O(logn), space = O(1)
    public int countDigitOne(int n) {
        int res = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n / m;
            long b = n % m;
            res += (a + 8) / 10 * m;
            if (a % 10 == 1) res += b + 1;
        }
        return res;
    }

    // S2: 根据数位来找多少数
    // time = O(logn), space = O(1)
    public int countDigitOne2(int n) {
        String s = "" + n;
        int m = s.length();
        long count = 0;

        for (int i = 1; i <= m; i++) {
            long a = n / (long) Math.pow(10, i);
            count += a * (long) Math.pow(10, i - 1);

            if (s.charAt(m - i) - '0' > 1) {
                count += (long) Math.pow(10, i - 1);
            } else if (s.charAt(m - i) - '0' == 1) {
                count += n % (long) Math.pow(10, i - 1) + 1;
            }
        }
        return (int) count;
    }

    // S3: 拆分三段
    // time = O(logn), space = O(1)
    public int countDigitOne3(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }
        Collections.reverse(nums);

        int m = nums.size(), res = 0;
        for (int i = 0; i < m; i++) {
            int d = nums.get(i);
            int left = 0, right = 0, p = 1;
            for (int j = 0; j < i; j++) left = left * 10 + nums.get(j);
            for (int j = i + 1; j < m; j++) {
                right = right * 10 + nums.get(j);
                p *= 10;
            }
            if (d == 0) res += left * p;
            else if (d == 1) res += left * p + right + 1;
            else if (d > 1) res += (left + 1) * p;
        }
        return res;
    }
}
/**
 * x 1
 * xx 11~19, 21, 31, ...91
 * xxx xx1, x1x, 1xx, 11x, x11, 1x1, 111
 * xxxxx
 * 对于任何一个位上的1，有多少整数在这1位上是有1的
 * xx 1 xx
 * xxx 1 x
 * xxxx 1
 * a + b + c + ...
 * 2 3 6 4 7
 * x x 1 x x
 * 0 0   0 0
 * 2 2   9 9
 * 2 3 1 0 0
 *       9 9
 * 23 * 100
 * 2 3 1 4 7
 * [2 3] 1 x x => 00 ~ 47
 *
 * abc d efg
 * d = 0 => abc * 1000
 * d = 1 => abc * 1000 + efg + 1
 * d > 1 => (abc + 1) * 1000
 */
