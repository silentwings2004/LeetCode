package LC1501_1800;

public class LC1588_SumofAllOddLengthSubarrays {
    /**
     * Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.
     *
     * A subarray is a contiguous subsequence of the array.
     *
     * Input: arr = [1,4,2,5,3]
     * Output: 58
     *
     * Input: arr = [1,2]
     * Output: 3
     *
     * Input: arr = [10,11,12]
     * Output: 66
     *
     * Constraints:
     *
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= 1000
     * @param arr
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length, res = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + arr[i - 1];
        for (int len = 1; len <= n; len += 2) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                res += s[j + 1] - s[i];
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int sumOddLengthSubarrays2(int[] arr) {
        int n = arr.length, res = 0;
        for (int i = 0; i < n; i++) {
            int l = i + 1, r = n - i;
            int leftEven = l + 1 >> 1, leftOdd = l >> 1;
            int rightEven = r + 1 >> 1, rightOdd = r >> 1;
            res += (leftEven * rightEven + leftOdd * rightOdd) * arr[i];
        }
        return res;
    }
}