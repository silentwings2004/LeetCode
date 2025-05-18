package LC1501_1800;

public class LC1529_MinimumSuffixFlips {
    /**
     * You are given a 0-indexed binary string target of length n. You have another binary string s of length n that is
     * initially set to all zeros. You want to make s equal to target.
     *
     * In one operation, you can pick an index i where 0 <= i < n and flip all bits in the inclusive range [i, n - 1].
     * Flip means changing '0' to '1' and '1' to '0'.
     *
     * Return the minimum number of operations needed to make s equal to target.
     *
     * Input: target = "10111"
     * Output: 3
     *
     * Input: target = "101"
     * Output: 3
     *
     * Input: target = "00000"
     * Output: 0
     *
     * Constraints:
     *
     * n == target.length
     * 1 <= n <= 10^5
     * target[i] is either '0' or '1'.
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public int minFlips(String target) {
        int n = target.length();
        int t = target.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < n; i++) {
            if (target.charAt(i) != target.charAt(i - 1)) t++;
        }
        return t;
    }
}