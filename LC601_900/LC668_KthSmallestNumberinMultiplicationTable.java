package LC601_900;

public class LC668_KthSmallestNumberinMultiplicationTable {
    /**
     * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix
     * mat where mat[i][j] == i * j (1-indexed).
     *
     * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
     *
     * Input: m = 3, n = 3, k = 5
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= m, n <= 3 * 10^4
     * 1 <= k <= m * n
     * @param m
     * @param n
     * @param k
     * @return
     */
    // time = O(mlog(m * n)), space = O(1)
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while (l < r) {
            int mid = l + r >> 1;
            if (get(m, n, mid) >= k) r = mid; // 可能有若干个相同的数，>= k的时候还是可能是有效解！
            else l = mid + 1;
        }
        return r;
    }

    private int get(int m, int n, int mid) {
        int res = 0;
        for (int i = 1; i <= m; i++) {
            res += Math.min(n, mid / i); // 每一行的个数不可能大于n，有可能val非常大，在行数上比较大
        }
        return res;
    }
}
/**
 * 乘法表里第k个元素
 * sorted matrix
 * [0, INT_MAX] => 32
 * 不停二分
 * <= val => x < k
 */