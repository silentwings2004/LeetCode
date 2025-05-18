package LC3001_3300;

public class LC3258_CountSubstringsThatSatisfyKConstraintI {
    /**
     * You are given a binary string s and an integer k.
     *
     * A binary string satisfies the k-constraint if either of the following conditions holds:
     *
     * The number of 0's in the string is at most k.
     * The number of 1's in the string is at most k.
     * Return an integer denoting the number of substrings of s that satisfy the k-constraint.
     *
     * Input: s = "10101", k = 1
     *
     * Output: 12
     *
     * Input: s = "1010101", k = 2
     *
     * Output: 25
     *
     * Input: s = "11111", k = 1
     *
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= s.length <= 50
     * 1 <= k <= s.length
     * s[i] is either '0' or '1'.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length(), res = 0;
        int[] cnt = new int[2];
        for (int i = 0, j = 0; i < n; i++) {
            int x = s.charAt(i) - '0';
            cnt[x]++;
            while (cnt[0] > k && cnt[1] > k) cnt[s.charAt(j++) - '0']--;
            res += i - j + 1;
        }
        return res;
    }
}