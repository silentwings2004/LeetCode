package LC1501_1800;

public class LC1556_ThousandSeparator {
    /**
     * Given an integer n, add a dot (".") as the thousands separator and return it in string format.
     *
     * Input: n = 987
     * Output: "987"
     *
     * Input: n = 1234
     * Output: "1.234"
     *
     * Constraints:
     *
     * 0 <= n <= 2^31 - 1
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String thousandSeparator(int n) {
        String s = String.valueOf(n);
        n = s.length();
        if (n < 3) return s;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 3; i >= 0; i -= 3) {
            sb.insert(0, "." + s.substring(i, i + 3));
        }
        int r = n % 3;
        if (r > 0) {
            sb.insert(0, s.substring(0, r));
            return sb.toString();
        }
        return sb.toString().substring(1);
    }

    // S2
    // time = O(n), space = O(n)
    public String thousandSeparator2(int n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        int pos = sb.length() % 3 == 0 ? 3 : sb.length() % 3;
        while (pos < sb.length()) {
            sb.insert(pos, '.');
            pos += 4;
        }
        return sb.toString();
    }

    // S3
    // time = O(n), space = O(n)
    public String thousandSeparator3(int n) {
        String num = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        n = num.length();
        for (int i = n - 1, j = 0; i >= 0; i--, j++) {
            if (j % 3 == 0 && j > 0) sb.append('.');
            sb.append(num.charAt(i));
        }
        return sb.reverse().toString();
    }
}