package LC2401_2700;

public class LC2571_MinimumOperationstoReduceanIntegerto0 {
    /**
     * You are given a positive integer n, you can do the following operation any number of times:
     *
     * Add or subtract a power of 2 from n.
     * Return the minimum number of operations to make n equal to 0.
     *
     * A number x is power of 2 if x == 2i where i >= 0.
     *
     * Input: n = 39
     * Output: 3
     *
     * Input: n = 54
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public int minOperations(int n) {
        char[] s = Integer.toBinaryString(n).toCharArray();
        int m = s.length, res = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (s[i] == '0') continue;
            int j = i - 1;
            while (j >= 0 && s[j] == '1') j--;
            int len = i - j;
            if (len == 1) res++;
            else {
                if (j >= 0) s[j] = '1';
                else res++;
                res++;
            }
            i = j + 1;
        }
        return res;
    }
}