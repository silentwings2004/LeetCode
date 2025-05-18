package LC3001_3300;

public class LC3228_MaximumNumberofOperationstoMoveOnestotheEnd {
    /**
     * You are given a binary string s.
     *
     * You can perform the following operation on the string any number of times:
     *
     * Choose any index i from the string where i + 1 < s.length such that s[i] == '1' and s[i + 1] == '0'.
     * Move the character s[i] to the right until it reaches the end of the string or another '1'. For example, for
     * s = "010010", if we choose i = 1, the resulting string will be s = "000110".
     * Return the maximum number of operations that you can perform.
     *
     * Input: s = "1001101"
     * Output: 4
     *
     * Input: s = "00111"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int maxOperations(String s) {
        int n = s.length(), res = 0, last = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                int j = i + 1;
                while (j < n && s.charAt(j) == '1') j++;
                if (j == n) break;
                int len = j - i;
                res += last + len;
                last += len;
                i = j - 1;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maxOperations2(String s) {
        int n = s.length(), res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') cnt++;
            else if (i > 0 && s.charAt(i - 1) == '1') res += cnt;
        }
        return res;
    }
}