package LC1501_1800;

public class LC1528_ShuffleString {
    /**
     * You are given a string s and an integer array indices of the same length. The string s will be shuffled such that
     * the character at the ith position moves to indices[i] in the shuffled string.
     *
     * Return the shuffled string.
     *
     * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
     * Output: "leetcode"
     *
     * Input: s = "abc", indices = [0,1,2]
     * Output: "abc"
     *
     * Constraints:
     *
     * s.length == indices.length == n
     * 1 <= n <= 100
     * s consists of only lowercase English letters.
     * 0 <= indices[i] < n
     * All values of indices are unique.
     * @param s
     * @param indices
     * @return
     */
    // time = O(n), space = O(n)
    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] t = new char[n];
        for (int i = 0; i < n; i++) t[indices[i]] = s.charAt(i);
        return String.valueOf(t);
    }
}