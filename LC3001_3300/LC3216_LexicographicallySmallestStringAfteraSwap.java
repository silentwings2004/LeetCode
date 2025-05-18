package LC3001_3300;

public class LC3216_LexicographicallySmallestStringAfteraSwap {
    /**
     * Given a string s containing only digits, return the lexicographically smallest string that can be obtained after
     * swapping adjacent digits in s with the same parity at most once.
     *
     * Digits have the same parity if both are odd or both are even. For example, 5 and 9, as well as 2 and 4, have the
     * same parity, while 6 and 9 do not.
     *
     * Input: s = "45320"
     * Output: "43520"
     *
     * Input: s = "001"
     * Output: "001"
     *
     * Constraints:
     *
     * 2 <= s.length <= 100
     * s consists only of digits.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String getSmallestString(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        for (int i = 0; i + 1 < n; i++) {
            int a = t[i] - '0', b = t[i + 1] - '0';
            if (a % 2 != b % 2 || a <= b) continue;
            char c = t[i];
            t[i] = t[i + 1];
            t[i + 1] = c;
            return String.valueOf(t);
        }
        return s;
    }
}