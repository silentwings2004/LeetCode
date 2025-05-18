package LC301_600;

public class LC522_LongestUncommonSubsequenceII {
    /**
     * Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the
     * longest uncommon subsequence does not exist, return -1.
     *
     * An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not
     * the others.
     *
     * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
     *
     * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to
     * get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
     *
     * Input: strs = ["aba","cdc","eae"]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= strs.length <= 50
     * 1 <= strs[i].length <= 10
     * strs[i] consists of lowercase English letters.
     * @param strs
     * @return
     */
    // time = O(n^2 * k), space = O(1)  k: avg length of each string
    public int findLUSlength(String[] strs) {
        int n = strs.length, res = -1;
        for (int i = 0; i < n; i++) {
            boolean is_sub = false;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (check(strs[i], strs[j])) {
                    is_sub = true;
                    break;
                }
            }
            if (!is_sub) res = Math.max(res, strs[i].length());
        }
        return res;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        int i, j;
        for (i = 0, j = 0; i < m && j < n;) {
            if (s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == m;
    }
}
/**
 * 核心：如果某个序列的子序列是特殊序列的话，那么这个序列整体一定也是特殊序列
 * 答案必然是某个序列本身
 * 依次枚举每个序列，然后判断该序列是否是其他序列的子序列
 * 如何快速判断一个序列是另一个序列的子序列呢？
 */