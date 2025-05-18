package LC2401_2700;

public class LC2575_FindtheDivisibilityArrayofaString {
    /**
     * You are given a 0-indexed string word of length n consisting of digits, and a positive integer m.
     *
     * The divisibility array div of word is an integer array of length n such that:
     *
     * div[i] = 1 if the numeric value of word[0,...,i] is divisible by m, or
     * div[i] = 0 otherwise.
     * Return the divisibility array of word.
     *
     * Input: word = "998244353", m = 3
     * Output: [1,1,0,0,0,1,1,0,0]
     *
     * Input: word = "1010", m = 10
     * Output: [0,1,0,1]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * word.length == n
     * word consists of digits from 0 to 9
     * 1 <= m <= 10^9
     * @param word
     * @param m
     * @return
     */
    // time = O(n), space = O(1)
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        long r = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            r = r * 10 + word.charAt(i) - '0';
            if (r % m == 0) res[i] = 1;
            r %= m;
        }
        return res;
    }
}