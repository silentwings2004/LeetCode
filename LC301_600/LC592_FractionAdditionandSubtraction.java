package LC301_600;

public class LC592_FractionAdditionandSubtraction {
    /**
     * Given a string expression representing an expression of fraction addition and subtraction, return the calculation
     * result in string format.
     *
     * The final result should be an irreducible fraction. If your final result is an integer, change it to the format
     * of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.
     *
     * Input: expression = "-1/2+1/2"
     * Output: "0/1"
     *
     * Input: expression = "-1/2+1/2+1/3"
     * Output: "1/3"
     *
     * Input: expression = "1/3-1/2"
     * Output: "-1/6"
     *
     * Constraints:
     *
     * The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
     * Each fraction (input and output) has the format ±numerator/denominator. If the first input fraction or the output
     * is positive, then '+' will be omitted.
     * The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will
     * always be in the range [1, 10]. If the denominator is 1, it means this fraction is actually an integer in a
     * fraction format defined above.
     * The number of given fractions will be in the range [1, 10].
     * The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
     * @param expression
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public String fractionAddition(String expression) {
        int n = expression.length();
        int a = 0, b = 1, c = 0, d = 0;
        int i = 0;
        char e = '+';

        while (i < n) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                e = expression.charAt(i++);
            }
            while (i < n && expression.charAt(i) != '/') {
                c = c * 10 + expression.charAt(i++) - '0';
            }
            i++;
            while (i < n && Character.isDigit(expression.charAt(i))) {
                d = d * 10 + expression.charAt(i++) - '0';
            }
            if (e == '-') c = -c;

            a = a * d + b * c;
            b = b * d;
            c = 0;
            d = 0;
            int t = Math.abs(gcd(a, b));
            a /= t;
            b /= t;
        }
        return a + "/" + b;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2
    // time = O(n), space = O(n)
    public String fractionAddition2(String expression) {
        int n = expression.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            if (expression.charAt(i) == '/') cnt++;
        }
        expression = "+" + expression;

        int a = 0, b = 1, offset = 0;
        for (int i = 0; i < cnt; i++) {
            char e = expression.charAt(offset);
            int j = offset + 1;
            while (j <= n && expression.charAt(j) != '/') j++;
            int c = Integer.parseInt(expression.substring(offset + 1, j));
            int k = j + 1;
            while (k <= n && Character.isDigit(expression.charAt(k))) k++;
            int d = Integer.parseInt(expression.substring(j + 1, k));
            offset = k;
            if (e == '-') c = -c;
            int x = a * d + b * c, y = b * d;
            int z = gcd(x, y);
            a = x / z;
            b = y / z;
        }
        if (b < 0) {
            a = -a;
            b = -b;
        }
        return a + "/" + b;
    }
}
/**
 * 每次处理2个分数的加和
 * 如何能读进来每个分数
 */