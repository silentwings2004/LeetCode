package LC901_1200;

public class LC972_EqualRationalNumbers {
    /**
     * Given two strings s and t, each of which represents a non-negative rational number, return true if and only if
     * they represent the same number. The strings may use parentheses to denote the repeating part of the rational
     * number.
     *
     * A rational number can be represented using up to three parts: <IntegerPart>, <NonRepeatingPart>, and a
     * <RepeatingPart>. The number will be represented in one of the following three ways:
     *
     * <IntegerPart>
     * For example, 12, 0, and 123.
     * <IntegerPart><.><NonRepeatingPart>
     * For example, 0.5, 1., 2.12, and 123.0001.
     * <IntegerPart><.><NonRepeatingPart><(><RepeatingPart><)>
     * For example, 0.1(6), 1.(9), 123.00(1212).
     * The repeating portion of a decimal expansion is conventionally denoted within a pair of round brackets. For
     * example:
     *
     * 1/6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66).
     *
     * Input: s = "0.(52)", t = "0.5(25)"
     * Output: true
     *
     * Input: s = "0.1666(6)", t = "0.166(66)"
     * Output: true
     *
     * Input: s = "0.9(9)", t = "1."
     * Output: true
     *
     * Constraints:
     *
     * Each part consists only of digits.
     * The <IntegerPart> does not have leading zeros (except for the zero itself).
     * 1 <= <IntegerPart>.length <= 4
     * 0 <= <NonRepeatingPart>.length <= 4
     * 1 <= <RepeatingPart>.length <= 4
     * @param s
     * @param t
     * @return
     */
    // time = O(1), space = O(1)
    public boolean isRationalEqual(String s, String t) {
        return get(s)[0] == get(t)[0] && get(s)[1] == get(t)[1];
    }

    private long[] get(String s) {
        long a = 0, b = 1;
        int n = s.length(), k = 0;
        while (k < n && s.charAt(k) != '.') {
            a = a * 10 + s.charAt(k) - '0';
            k++;
        }
        long[] r = new long[]{a, b};

        a = 0;
        b = 1;
        k++;
        while (k < n && s.charAt(k) != '(') {
            a = a * 10 + s.charAt(k) - '0';
            b *= 10;
            k++;
        }
        r = add(r, new long[]{a, b});
        k++;
        long t = b;
        a = 0;
        b = 0;
        while (k < n && s.charAt(k) != ')') {
            a = a * 10 + s.charAt(k) - '0';
            b = b * 10 + 9;
            k++;
        }
        r = add(r, new long[]{a, b * t});
        return r;
    }

    private long[] add(long[] a, long[] b) {
        if (b[1] == 0) return a;
        long[] r = new long[]{a[0] * b[1] + a[1] * b[0], a[1] * b[1]};
        long d = gcd(r[0], r[1]);
        r[0] /= d;
        r[1] /= d;
        return r;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * 思路：先将小数化成最简分数，然后直接判断2个分数是否相同即可
 * 如何将一个小数转化成分数？
 * 整数部分： 3 => 3 / 1
 * 小数部分: 0.5 => 5 / 10
 * 循环部分：(25) => 25 / 99 => 0.0(25) = 25/99/10 = 25/990
 * => 将这三部分合并起来
 */