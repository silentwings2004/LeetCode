package LC901_1200;

public class LC978_LongestTurbulentSubarray {
    /**
     * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
     *
     * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
     *
     * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
     *
     * For i <= k < j:
     * arr[k] > arr[k + 1] when k is odd, and
     * arr[k] < arr[k + 1] when k is even.
     * Or, for i <= k < j:
     * arr[k] > arr[k + 1] when k is even, and
     * arr[k] < arr[k + 1] when k is odd.
     *
     * Input: arr = [9,4,2,10,7,8,8,1,9]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= arr.length <= 4 * 10^4
     * 0 <= arr[i] <= 10^9
     * @param arr
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int maxTurbulenceSize(int[] arr) {
        // corner case
        if (arr == null || arr.length == 0) return 0;

        int n = arr.length;
        int[] q = new int[n];
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) q[i] = -1;
            else if (arr[i] < arr[i + 1]) q[i] = 1;
        }

        if (n == 1) return 1;
        if (n == 2) return q[0] == 0 ? 1 : 2;

        int count = 1, start = 0;
        for (int i = 0; i + 1 < n - 1; i++) {
            if (q[i] != 0) {
                count = Math.max(count, 2);
            }
            start = i;
            while (i + 1 < n - 1 && q[i] * q[i + 1] == -1) {
                i++;
                count = Math.max(count, i - start + 2);
            }
        }
        return count;
    }

    // S2
    // time = O(n), space = O(1)
    public int maxTurbulenceSize2(int[] arr) {
        int n = arr.length, res = 1;
        for (int i = 1, up = 1, down = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                up = down + 1;
                down = 1;
            } else if (arr[i] < arr[i - 1]) {
                down = up + 1;
                up = 1;
            } else up = down = 1;
            res = Math.max(res, Math.max(up, down));
        }
        return res;
    }
}
/**
 * up[i]: 以i结尾且i-1 i 递增的所有区间中的最大值
 * down[i]:             递减
 * => 递推
 * 1. a[i-1] < a[i] => up[i] = down[i-1] + 1, down[i] = 1;
 * 2. a[i-1] > a[i] => down[i] = up[i-1] + 1, up[i] = 1;
 * 3. a[i-1] = a[i] => up[i] = down[i] = 1
 */