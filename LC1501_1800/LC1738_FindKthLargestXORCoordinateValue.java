package LC1501_1800;
import java.util.*;
public class LC1738_FindKthLargestXORCoordinateValue {
    /**
     * You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.
     *
     * The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and
     * 0 <= j <= b < n (0-indexed).
     *
     * Find the kth largest value (1-indexed) of all the coordinates of matrix.
     *
     * Input: matrix = [[5,2],[1,6]], k = 1
     * Output: 7
     *
     * Input: matrix = [[5,2],[1,6]], k = 2
     * Output: 5
     *
     * Input: matrix = [[5,2],[1,6]], k = 3
     * Output: 4
     *
     * 
     * @param matrix
     * @param k
     * @return
     */
    // S1: quick select
    // time = O(m * n), space = O(m * n)
    int[][] s;
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] ^ s[i][j - 1] ^ s[i - 1][j - 1] ^ matrix[i - 1][j - 1];
            }
        }

        int[] q = new int[m * n];
        int cnt = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                q[cnt++] = get(1, 1, i, j);
            }
        }
        return quick_select(q, 0, m * n - 1, m * n + 1 - k);
    }

    private int quick_select(int[] q, int l, int r, int k) {
        if (l >= r) return q[r];

        int x = q[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (q[i] < x);
            do j--; while (q[j] > x);
            if (i < j) {
                int t = q[i];
                q[i] = q[j];
                q[j] = t;
            }
        }
        int sl = j - l + 1;
        if (k <= sl) return quick_select(q, l, j, k);
        return quick_select(q, j + 1, r, k - sl);
    }

    private int get(int x1, int y1, int x2, int y2) {
        return s[x2][y2] ^ s[x1 - 1][y2] ^ s[x2][y1 - 1] ^ s[x1 - 1][y1 - 1];
    }

    // S2: heap
    // time = O(m * n * log(k)), space = O(m * n + k)
    public int kthLargestValue2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] ^ s[i][j - 1] ^ s[i - 1][j - 1] ^ matrix[i - 1][j - 1];
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pq.offer(s[i][j] ^ s[0][j] ^ s[i][0] ^ s[0][0]);
                if (pq.size() > k) pq.poll();
            }
        }
        return pq.peek();
    }
}