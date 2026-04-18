package LC3601_3900;

public class LC3898_FindtheDegreeofEachVertex {
    /**
     * You are given a 2D integer array matrix of size n x n representing the adjacency matrix of an undirected graph
     * with n vertices labeled from 0 to n - 1.
     *
     * matrix[i][j] = 1 indicates that there is an edge between vertices i and j.
     * matrix[i][j] = 0 indicates that there is no edge between vertices i and j.
     * The degree of a vertex is the number of edges connected to it.
     *
     * Return an integer array ans of size n where ans[i] represents the degree of vertex i.
     *
     * Input: matrix = [[0,1,1],[1,0,1],[1,1,0]]
     * Output: [2,2,2]
     *
     * Input: matrix = [[0,1,0],[1,0,0],[0,0,0]]
     * Output: [1,1,0]
     *
     * Input: matrix = [[0]]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= n == matrix.length == matrix[i].length <= 100
     * matrix[i][i] == 0
     * matrix[i][j] is either 0 or 1
     * matrix[i][j] == matrix[j][i]
     * @param matrix
     * @return
     */
    // time = O(n^2), space = O(1)
    public int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    res[i]++;
                    res[j]++;
                }
            }
        }
        return res;
    }
}