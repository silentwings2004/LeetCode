package LC3301_3600;

public class LC3529_CountCellsinOverlappingHorizontalandVerticalSubstrings {
    /**
     * You are given an m x n matrix grid consisting of characters and a string pattern.
     *
     * A horizontal substring is a contiguous sequence of characters read from left to right. If the end of a row is
     * reached before the substring is complete, it wraps to the first column of the next row and continues as needed.
     * You do not wrap from the bottom row back to the top.
     *
     * A vertical substring is a contiguous sequence of characters read from top to bottom. If the bottom of a column is
     * reached before the substring is complete, it wraps to the first row of the next column and continues as needed.
     * You do not wrap from the last column back to the first.
     *
     * Count the number of cells in the matrix that satisfy the following condition:
     *
     * The cell must be part of at least one horizontal substring and at least one vertical substring, where both
     * substrings are equal to the given pattern.
     * Return the count of these cells.
     *
     * Input: grid = [["a","a","c","c"],["b","b","b","c"],["a","a","b","a"],["c","a","a","c"],["a","a","c","c"]],
     * pattern = "abaca"
     * Output: 1
     *
     * Input: grid = [["c","a","a","a"],["a","a","b","a"],["b","b","a","a"],["a","a","b","a"]], pattern = "aba"
     * Output: 4
     *
     * Input: grid = [["a"]], pattern = "a"
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 10^5
     * 1 <= pattern.length <= m * n
     * grid and pattern consist of only lowercase English letters.
     * @param grid
     * @param pattern
     * @return
     */
    // S1: KMP + 差分数组
    // time = O(m * n), space = O(m * n)
    int m, n;
    public int countCells(char[][] grid, String pattern) {
        m = grid.length;
        n = grid[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]);
            }
        }
        String s1 = sb.toString();

        sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                sb.append(grid[i][j]);
            }
        }
        String s2 = sb.toString();
        boolean[][] st1 = helper(s1, pattern, 0);
        boolean[][] st2 = helper(s2, pattern, 1);

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (st1[i][j] && st2[i][j]) res++;
            }
        }
        return res;
    }

    private boolean[][] helper(String s, String p, int o) {
        int len1 = s.length(), len2 = p.length();
        s = " " + s;
        p = " " + p;
        int[] ne = new int[len2 + 1];
        for (int i = 2, j = 0; i <= len2; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (p.charAt(i) == p.charAt(j + 1)) j++;
            ne[i] = j;
        }
        int[] b = new int[len1 + 2];
        for (int i = 1, j = 0; i <= len1; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (s.charAt(i) == p.charAt(j + 1)) j++;
            if (j == len2) {
                int l = i - len2 + 1, r = i;
                b[l]++;
                b[r + 1]--;
                j = ne[j];
            }
        }
        for (int i = 1; i <= len1; i++) b[i] += b[i - 1];
        boolean[][] st = new boolean[m][n];
        for (int i = 1; i <= len1; i++) {
            if (b[i] == 0) continue;
            int v = i - 1;
            if (o == 0) {
                int x = v / n, y = v % n;
                st[x][y] = true;
            } else {
                int x = v % m, y = v / m;
                st[x][y] = true;
            }
        }
        return st;
    }
}
/**
 * 对于水平子串，把 grid 每一行首尾相接，得到一个长为 mn 的文本串 text，我们需要在文本串 text 中找到所有模式串 pattern 的出现位置，
 * 这正是 KMP 算法的标准应用
 * 标记所有在 pattern 中的单元格。假设我们在 text[i] 处匹配完成，那么用差分数组把下标区间 [i−k+1,i] 加一，其中 k 是 pattern 的长度。
 * 计算差分数组的前缀和，值大于 0 的下标就对应着在 pattern 中的单元格
 * 对于垂直子串，计算方法同理。可以把相关逻辑封装成一个函数，方便垂直子串复用
 */