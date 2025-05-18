package LC901_1200;

public class LC955_DeleteColumnstoMakeSortedII {
    /**
     * You are given an array of n strings strs, all of the same length.
     *
     * We may choose any deletion indices, and we delete all the characters in those indices for each string.
     *
     * For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after
     * deletions is ["bef", "vyz"].
     *
     * Suppose we chose a set of deletion indices answer such that after deletions, the final array has its elements in
     * lexicographic order (i.e., strs[0] <= strs[1] <= strs[2] <= ... <= strs[n - 1]). Return the minimum possible
     * value of answer.length.
     *
     * Input: strs = ["ca","bb","ac"]
     * Output: 1
     *
     * Input: strs = ["xc","yb","za"]
     * Output: 0
     *
     * Input: strs = ["zyx","wvu","tsr"]
     * Output: 3
     *
     * Constraints:
     *
     * n == strs.length
     * 1 <= n <= 100
     * 1 <= strs[i].length <= 100
     * strs[i] consists of lowercase English letters.
     * @param strs
     * @return
     */
    // time = O(m * n), space = O(n)
    public int minDeletionSize(String[] strs) {
        int n = strs.length, m = strs[0].length();
        boolean[] f = new boolean[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            boolean flag = true;
            for (int j = 1; j < n; j++) {
                if (!f[j] && strs[j - 1].charAt(i) > strs[j].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) res++;
            else {
                for (int j = 1; j < n; j++) {
                    if (!f[j] && strs[j - 1].charAt(i) < strs[j].charAt(i)) f[j] = true;
                }
            }
        }
        return res;
    }
}