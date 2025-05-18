package LC3001_3300;

public class LC3271_HashDividedString {
    /**
     * You are given a string s of length n and an integer k, where n is a multiple of k. Your task is to hash the
     * string s into a new string called result, which has a length of n / k.
     *
     * First, divide s into n / k
     * substrings
     * , each with a length of k. Then, initialize result as an empty string.
     *
     * For each substring in order from the beginning:
     *
     * The hash value of a character is the index of that character in the English alphabet (e.g., 'a' → 0, 'b' → 1,
     * ..., 'z' → 25).
     * Calculate the sum of all the hash values of the characters in the substring.
     * Find the remainder of this sum when divided by 26, which is called hashedChar.
     * Identify the character in the English lowercase alphabet that corresponds to hashedChar.
     * Append that character to the end of result.
     * Return result.
     *
     * Input: s = "abcd", k = 2
     * Output: "bf"
     *
     * Input: s = "mxz", k = 3
     * Output: "i"
     *
     * Constraints:
     *
     * 1 <= k <= 100
     * k <= s.length <= 1000
     * s.length is divisible by k.
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public String stringHash(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, sum = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            sum += u;
            if ((i + 1) % k == 0) {
                sum %= 26;
                sb.append((char)(sum + 'a'));
                sum = 0;
            }
        }
        return sb.toString();
    }
}