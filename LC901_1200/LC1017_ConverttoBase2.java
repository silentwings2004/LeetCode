package LC901_1200;

public class LC1017_ConverttoBase2 {
    /**
     * Given an integer n, return a binary string representing its representation in base -2.
     *
     * Note that the returned string should not have leading zeros unless the string is "0".
     *
     * Input: n = 2
     * Output: "110"
     *
     * Input: n = 3
     * Output: "111"
     *
     * Input: n = 4
     * Output: "100"
     *
     * Constraints:
     *
     * 0 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(logn)
    public String baseNeg2(int n) {
        if (n == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int r = n & 1;
            sb.append(r);
            n = (n - r) / -2;
        }
        return sb.reverse().toString();
    }
}
/**
 * n = ak*(-2)^k+ak-1*(-2)^(k-1)...+a0*(-2)^0
 * a0 = n % (-2)
 * (n-a0)/(-2) = ak(-2)^(k-1) + ... + a1(-2)^0
 * 短除法
 */