package LC901_1200;

public class LC1131_MaximumofAbsoluteValueExpression {
    /**
     * Given two arrays of integers with equal lengths, return the maximum value of:
     *
     * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
     *
     * where the maximum is taken over all 0 <= i, j < arr1.length.
     *
     * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
     * Output: 13
     *
     * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
     * Output: 20
     *
     * Constraints:
     *
     * 2 <= arr1.length == arr2.length <= 40000
     * -10^6 <= arr1[i], arr2[i] <= 10^6
     * @param arr1
     * @param arr2
     * @return
     */
    // time = O(n), space = O(1)
    int[] a, b;
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        a = arr1;
        b = arr2;
        return Math.max(work(1, 1), Math.max(work(1, -1), Math.max(work(-1, 1), work(-1, -1))));
    }

    private int work(int x, int y) {
        int n = a.length, res = 0, maxv = (int)-1e8;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, a[i] * x + b[i] * y + i + maxv);
            maxv = Math.max(maxv, -a[i] * x - b[i] * y - i);
        }
        return res;
    }
}