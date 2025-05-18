package LC1201_1500;

public class LC1374_GenerateaStringWithCharactersThatHaveOddCounts {
    /**
     * Given an integer n, return a string with n characters such that each character in such string occurs an odd
     * number of times.
     *
     * The returned string must contain only lowercase English letters. If there are multiples valid strings, return
     * any of them.
     *
     * Input: n = 4
     * Output: "pppz"
     *
     * Constraints:
     *
     * 1 <= n <= 500
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 1) {
            for (int i = 0; i < n; i++) sb.append('a');
        } else {
            sb.append('a');
            for (int i = 0; i < n - 1; i++) sb.append('b');
        }
        return sb.toString();
    }
}
