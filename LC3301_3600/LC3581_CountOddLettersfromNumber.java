package LC3301_3600;

public class LC3581_CountOddLettersfromNumber {
    /**
     * You are given an integer n perform the following steps:
     *
     * Convert each digit of n into its lowercase English word (e.g., 4 → "four", 1 → "one").
     * Concatenate those words in the original digit order to form a string s.
     * Return the number of distinct characters in s that appear an odd number of times.
     *
     * Input: n = 41
     * Output: 5
     *
     * Input: n = 20
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public int countOddLetters(int n) {
        String[] dict = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int state = 0;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            String y = dict[x];
            for (int i = 0; i < y.length(); i++) {
                int u = y.charAt(i) - 'a';
                state ^= 1 << u;
            }
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if ((state >> i & 1) == 1) res++;
        }
        return res;
    }
}