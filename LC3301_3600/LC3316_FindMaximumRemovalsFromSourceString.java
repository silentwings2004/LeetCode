package LC3301_3600;
import java.util.*;
public class LC3316_FindMaximumRemovalsFromSourceString {
    /**
     * You are given a string source of size n, a string pattern that is a
     * subsequence
     *  of source, and a sorted integer array targetIndices that contains distinct numbers in the range [0, n - 1].
     *
     * We define an operation as removing a character at an index idx from source such that:
     *
     * idx is an element of targetIndices.
     * pattern remains a subsequence of source after removing the character.
     *
     * Performing an operation does not change the indices of the other characters in source. For example, if you
     * remove 'c' from "acb", the character at index 2 would still be 'b'.
     *
     * Return the maximum number of operations that can be performed.
     *
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * Input: source = "abbaa", pattern = "aba", targetIndices = [0,1,2]
     * Output: 1
     *
     * Input: source = "bcda", pattern = "d", targetIndices = [0,3]
     * Output: 2
     *
     * Input: source = "dda", pattern = "dda", targetIndices = [0,1,2]
     * Output: 0
     *
     * Input: source = "yeyeykyded", pattern = "yeyyd", targetIndices = [0,2,3,4]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == source.length <= 3 * 10^3
     * 1 <= pattern.length <= n
     * 1 <= targetIndices.length <= n
     * targetIndices is sorted in ascending order.
     * The input is generated such that targetIndices contains distinct elements in the range [0, n - 1].
     * source and pattern consist only of lowercase English letters.
     * The input is generated such that pattern appears as a subsequence in source.
     * @param source
     * @param pattern
     * @param targetIndices
     * @return
     */
    // time = O(n * m), space = O(n * m)
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        int n = source.length(), m = pattern.length(), inf = 0x3f3f3f3f;
        HashSet<Integer> set = new HashSet<>();
        for (int x : targetIndices) set.add(x);
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], -inf);
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j] + (set.contains(i - 1) ? 1 : 0);
                if (j > 0 && source.charAt(i - 1) == pattern.charAt(j - 1)) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1]);
                }
            }
        }
        return f[n][m];
    }
}