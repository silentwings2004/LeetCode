package LC601_900;
import java.util.*;
public class LC842_SplitArrayintoFibonacciSequence {
    /**
     * You are given a string of digits num, such as "123456579". We can split it into a Fibonacci-like sequence
     * [123, 456, 579].
     *
     * Formally, a Fibonacci-like sequence is a list f of non-negative integers such that:
     *
     * 0 <= f[i] < 231, (that is, each integer fits in a 32-bit signed integer type),
     * f.length >= 3, and
     * f[i] + f[i + 1] == f[i + 2] for all 0 <= i < f.length - 2.
     * Note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the
     * piece is the number 0 itself.
     *
     * Return any Fibonacci-like sequence split from num, or return [] if it cannot be done.
     *
     * Input: num = "1101111"
     * Output: [11,0,11,11]
     *
     * Input: num = "112358130"
     * Output: []
     *
     * Input: num = "0123"
     * Output: []
     *
     * Constraints:
     *
     * 1 <= num.length <= 200
     * num contains only digits.
     * @param num
     * @return
     */
    // time = O(100*n), space = O(n)
    public List<Integer> splitIntoFibonacci(String num) {
        int n = num.length();
        for (int i = 1; i <= 10 && i < n; i++) {
            for (int j = 1; j <= 10 && i + j < n; j++) {
                long a = Long.parseLong(num.substring(0, i));
                long b = Long.parseLong(num.substring(i, i + j));
                List<Integer> res = get(num, a, b);
                if (res.size() > 0) return res;
            }
        }
        return new ArrayList<>();
    }

    private List<Integer> get(String s, long a, long b) {
        List<Integer> res = new ArrayList<>();
        res.addAll(Arrays.asList((int)a, (int)b));
        String t = String.valueOf(a) + String.valueOf(b);
        while (t.length() < s.length()) {
            long c = a + b;
            if (c > Integer.MAX_VALUE) return new ArrayList<>();
            t += c;
            res.add((int)c);
            a = b;
            b = c;
        }
        if (!t.equals(s)) return new ArrayList<>();
        return res;
    }
}
/**
 * 确定前面2个数字后，第3个数字就确定了 => 枚举a和b
 * 构造出这个序列，一直往后算，算到生成的序列>=给定序列的长度
 */