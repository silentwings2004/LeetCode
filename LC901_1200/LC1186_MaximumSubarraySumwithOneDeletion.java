package LC901_1200;

public class LC1186_MaximumSubarraySumwithOneDeletion {
    /**
     * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most
     * one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so
     * that there is still at least one element left and the sum of the remaining elements is maximum possible.
     *
     * Note that the subarray needs to be non-empty after deleting one element.
     *
     * Input: arr = [1,-2,0,3]
     * Output: 4
     *
     * Input: arr = [1,-2,-2,3]
     * Output: 3
     *
     * Input: arr = [-1,-1,-1,-1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^5
     * -10^4 <= arr[i] <= 10^4
     * @param arr
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] f = new int[n], g = new int[n];
        f[0] = arr[0];
        g[n - 1] = arr[n - 1];
        int res = arr[0];

        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1], 0) + arr[i];
            res = Math.max(res, f[i]);
        }

        for (int i = n - 2; i >= 0; i--) g[i] = Math.max(g[i + 1], 0) + arr[i];
        for (int i = 1; i < n - 1; i++) res = Math.max(res, f[i - 1] + g[i + 1]);
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int maximumSum2(int[] arr) {
        int n = arr.length, INF = (int)2e9;
        int[] f = new int[n], g = new int[n];
        f[0] = arr[0];
        g[0] = -INF;
        int res = arr[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1], 0) + arr[i];
            g[i] = g[i - 1] + arr[i];
            if (i >= 2) g[i] = Math.max(g[i], f[i - 2] + arr[i]);
            res = Math.max(res, Math.max(f[i], g[i]));
        }
        return res;
    }
}