package LC2401_2700;

public class LC2672_NumberofAdjacentElementsWiththeSameColor {
    /**
     * There is a 0-indexed array nums of length n. Initially, all elements are uncolored (has a value of 0).
     *
     * You are given a 2D integer array queries where queries[i] = [indexi, colori].
     *
     * For each query, you color the index indexi with the color colori in the array nums.
     *
     * Return an array answer of the same length as queries where answer[i] is the number of adjacent elements with the
     * same color after the ith query.
     *
     * More formally, answer[i] is the number of indices j, such that 0 <= j < n - 1 and nums[j] == nums[j + 1] and
     * nums[j] != 0 after the ith query.
     *
     * Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
     * Output: [0,1,1,0,2]
     *
     * Input: n = 1, queries = [[0,100000]]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= indexi <= n - 1
     * 1 <=  colori <= 10^5
     * @param n
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public int[] colorTheArray(int n, int[][] queries) {
        int m = queries.length;
        int[] s = new int[n], color = new int[n];
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int x = queries[i][0], c = queries[i][1];
            if (i > 0) s[x] = res[i - 1];
            if (color[x] == 0) {
                if (x - 1 >= 0 && color[x - 1] == c) s[x]++;
                if (x + 1 < n && color[x + 1] == c) s[x]++;
                res[i] = s[x];
            } else {
                if (color[x] == c) res[i] = res[i - 1];
                else {
                    if (x - 1 >= 0 && color[x - 1] == color[x]) s[x]--;
                    if (x + 1 < n && color[x + 1] == color[x]) s[x]--;
                    if (x - 1 >= 0 && color[x - 1] == c) s[x]++;
                    if (x + 1 < n && color[x + 1] == c) s[x]++;
                    res[i] = s[x];
                }
            }
            color[x] = c;
        }
        return res;
    }
}