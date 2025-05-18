package LC601_900;

public class LC738_MonotoneIncreasingDigits {
    /**
     * An integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.
     *
     * Given an integer n, return the largest number that is less than or equal to n with monotone increasing digits.
     *
     * Input: n = 10
     * Output: 9
     *
     * Input: n = 1234
     * Output: 1234
     *
     * Input: n = 332
     * Output: 299
     *
     * Constraints:
     *
     * 0 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public int monotoneIncreasingDigits(int n) {
        char[] str = String.valueOf(n).toCharArray();
        int m = str.length, k = 0;
        while (k + 1 < m && str[k] <= str[k + 1]) k++;
        if (k == m - 1) return n;
        while (k > 0 && str[k - 1] == str[k]) k--;
        str[k]--;
        for (int i = k + 1; i < m; i++) str[i] = '9';
        return Integer.parseInt(String.valueOf(str));
    }
}