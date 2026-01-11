package LC3601_3900;
import java.util.*;
public class LC3796_FindMaximumValueinaConstrainedSequence {
    /**
     * You are given an integer n, a 2D integer array restrictions, and an integer array diff of length n - 1. Your task
     * is to construct a sequence of length n, denoted by a[0], a[1], ..., a[n - 1], such that it satisfies the
     * following conditions:
     *
     * a[0] is 0.
     * All elements in the sequence are non-negative.
     * For every index i (0 <= i <= n - 2), abs(a[i] - a[i + 1]) <= diff[i].
     * For each restrictions[i] = [idx, maxVal], the value at position idx in the sequence must not exceed maxVal
     * (i.e., a[idx] <= maxVal).
     * Your goal is to construct a valid sequence that maximizes the largest value within the sequence while satisfying
     * all the above conditions.
     *
     * Return an integer denoting the largest value present in such an optimal sequence.
     *
     * Input: n = 10, restrictions = [[3,1],[8,1]], diff = [2,2,3,1,4,5,1,1,2]
     * Output: 6
     *
     * Input: n = 8, restrictions = [[3,2]], diff = [3,5,2,4,2,3,1]
     * Output: 12
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= restrictions.length <= n - 1
     * restrictions[i].length == 2
     * restrictions[i] = [idx, maxVal]
     * 1 <= idx < n
     * 1 <= maxVal <= 10^6
     * diff.length == n - 1
     * 1 <= diff[i] <= 10
     * The values of restrictions[i][0] are unique.
     * @param n
     * @param restrictions
     * @param diff
     * @return
     */
    // time = O(n), space = O(n)
    public int findMaxVal(int n, int[][] restrictions, int[] diff) {
        int[] a = new int[n];
        Arrays.fill(a, Integer.MAX_VALUE);
        a[0] = 0;
        for (int[] r : restrictions) a[r[0]] = Math.min(a[r[0]], r[1]);
        int[] l = new int[n], r = new int[n];
        l[0] = a[0];
        for (int i = 1; i < n; i++) l[i] = Math.min(a[i], l[i - 1] + diff[i - 1]);
        r[n - 1] = l[n - 1];
        int res = r[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            r[i] = Math.min(l[i], r[i + 1] + diff[i]);
            res = Math.max(res, r[i]);
        }
        return res;
    }
}
/**
 * ref: LC1840
 * 最后一个数的约束只来自于左边
 * 约束从左到右传递到了最后一个数
 * 最后一个数的约束只关心左边，所以第一次遍历后最后一个数就是它最终的最大值
 * => 从最后一个数往左再扫描一遍，把约束更新到最左端
 * => 二次扫描法
 */