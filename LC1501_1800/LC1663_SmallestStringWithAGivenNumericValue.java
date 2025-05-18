package LC1501_1800;

public class LC1663_SmallestStringWithAGivenNumericValue {
    /**
     * The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric
     * value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.
     *
     * The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric
     * values. For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
     *
     * You are given two integers n and k. Return the lexicographically smallest string with length equal to n and
     * numeric value equal to k.
     *
     * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is,
     * either x is a prefix of y, or if i i`s the first position such that x[i] != y[i], then x[i] comes before y[i] in
     * alphabetic order.
     *
     * Input: n = 3, k = 27
     * Output: "aay"
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * n <= k <= 26 * n
     * @param n
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String getSmallestString(int n, int k) {
        char[] chars = new char[n];
        int i = 0;

        while (i < n) {
            if (k - i <= 26 * (n - i)) chars[i++] = 'a';
            else break;
        }
        k -= i;
        i--;
        chars[i++] = (char)('a' + k % 26);
        while (i < n) chars[i++] = 'z';
        return String.valueOf(chars);
    }

    // S2
    // time = O(n), space = O(n)
    public String getSmallestString2(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, s = 0; i < n; i++) {
            for (int j = 1; j <= 26; j++) {
                int last = k - s - j, m = n - i - 1;
                if (last >= m && last <= m * 26) {
                    sb.append((char)(j - 1 + 'a'));
                    s += j;
                    break;
                }
            }
        }
        return sb.toString();
    }

    // S3
    // time = O(n), space = O(n)
    public String getSmallestString3(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int t = k / 26;
        k -= t * 26;
        n -= t;
        while (n > k) {
            t--;
            k += 26;
            n++;
        }

        if (n > 0) {
            for (int i = 0; i < n - 1; i++) sb.append('a');
            sb.append((char)(k - n + 'a'));
        }
        for (int i = 0; i < t; i++) sb.append('z');
        return sb.toString();
    }
}