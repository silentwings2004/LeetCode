package LC901_1200;

public class LC960_DeleteColumnstoMakeSortedIII {
    /**
     * You are given an array of n strings strs, all of the same length.
     *
     * We may choose any deletion indices, and we delete all the characters in those indices for each string.
     *
     * For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after
     * deletions is ["bef", "vyz"].
     *
     * Suppose we chose a set of deletion indices answer such that after deletions, the final array has every string
     * (row) in lexicographic order. (i.e., (strs[0][0] <= strs[0][1] <= ... <= strs[0][strs[0].length - 1]), and
     * (strs[1][0] <= strs[1][1] <= ... <= strs[1][strs[1].length - 1]), and so on). Return the minimum possible value
     * of answer.length.
     *
     * Input: strs = ["babca","bbazb"]
     * Output: 3
     *
     * Input: strs = ["edcba"]
     * Output: 4
     *
     * Input: strs = ["ghi","def","abc"]
     * Output: 0
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
    // time = O(m^2 * n), space = O(m)
    public int minDeletionSize(String[] strs) {
        int n = strs.length, m = strs[0].length();
        int[] f = new int[m];
        int res = m;
        for (int i = 0; i < m; i++) {
            f[i] = i;
            for (int j = 0; j < i; j++) {
                if (check(strs, j, i)) f[i] = Math.min(f[i], f[j] + i - j - 1);
            }
            res = Math.min(res, f[i] + m - 1 - i);
        }
        return res;
    }

    private boolean check(String[] strs, int i, int j) {
        for (int k = 0; k < strs.length; k++) {
            if (strs[k].charAt(i) > strs[k].charAt(j)) return false;
        }
        return true;
    }
}
/**
 * 序列型dp
 * 状态表示：f(i)
 * 集合：只看前i列且保留第i列所有合法方案的集合
 * 属性：最少删除的列数
 * 状态计算：看倒数第二个元素是第几列
 * f(i) = f(j) + (i - j - 1)
 * 答案：枚举最后一列 f(i) + m - 1 - i
 */