package LC3601_3900;

public class LC3602_HexadecimalandHexatrigesimalConversion {
    /**
     * You are given an integer n.
     *
     * Return the concatenation of the hexadecimal representation of n2 and the hexatrigesimal representation of n3.
     *
     * A hexadecimal number is defined as a base-16 numeral system that uses the digits 0 – 9 and the uppercase letters
     * A - F to represent values from 0 to 15.
     *
     * A hexatrigesimal number is defined as a base-36 numeral system that uses the digits 0 – 9 and the uppercase
     * letters A - Z to represent values from 0 to 35.
     *
     * Input: n = 13
     * Output: "A91P1"
     *
     * Input: n = 36
     * Output: "5101000"
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public String concatHex36(int n) {
        return get(n * n, 16) + get(n * n * n, 36);
    }

    private String get(int x, int y) {
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            int r = x % y;
            if (r < 10) sb.append(r);
            else sb.append((char)('A' + (r - 10)));
            x /= y;
        }
        return sb.reverse().toString();
    }
}