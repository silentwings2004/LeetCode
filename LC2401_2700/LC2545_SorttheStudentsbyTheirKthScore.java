package LC2401_2700;
import java.util.*;
public class LC2545_SorttheStudentsbyTheirKthScore {
    /**
     * There is a class with m students and n exams. You are given a 0-indexed m x n integer matrix score, where each
     * row represents one student and score[i][j] denotes the score the ith student got in the jth exam. The matrix
     * score contains distinct integers only.
     *
     * You are also given an integer k. Sort the students (i.e., the rows of the matrix) by their scores in the kth
     * (0-indexed) exam from the highest to the lowest.
     *
     * Return the matrix after sorting it.
     *
     * Input: score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]], k = 2
     * Output: [[7,5,11,2],[10,6,9,1],[4,8,3,15]]
     *
     * Input: score = [[3,4],[5,6]], k = 0
     * Output: [[5,6],[3,4]]
     *
     * Constraints:
     *
     * m == score.length
     * n == score[i].length
     * 1 <= m, n <= 250
     * 1 <= score[i][j] <= 10^5
     * score consists of distinct integers.
     * 0 <= k < n
     * @param score
     * @param k
     * @return
     */
    // S1
    // time = O(mlogm), space = O(m)
    public int[][] sortTheStudents(int[][] score, int k) {
        int m = score.length, n = score[0].length;
        Integer[] t = new Integer[m];
        for (int i = 0; i < m; i++) t[i] = i;
        Arrays.sort(t, (o1, o2) -> score[o2][k] - score[o1][k]);
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) res[i] = score[t[i]];
        return res;
    }

    // S2
    // time = O(mlogm), space = O(1)
    public int[][] sortTheStudents2(int[][] score, int k) {
        Arrays.sort(score, (o1, o2) -> o2[k] - o1[k]);
        return score;
    }
}
