package LC901_1200;

public class LC1013_PartitionArrayIntoThreePartsWithEqualSum {
    /**
     * Given an array of integers arr, return true if we can partition the array into three non-empty parts with equal
     * sums.
     *
     * Formally, we can partition the array if we can find indexes i + 1 < j with
     * (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... +
     * arr[arr.length - 1])
     *
     * Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1]
     * Output: true
     *
     * Input: arr = [0,2,1,-6,6,7,9,-1,2,0,1]
     * Output: false
     *
     * Input: arr = [3,3,6,5,-2,2,5,1,-9,4]
     * Output: true
     *
     * Constraints:
     *
     * 3 <= arr.length <= 5 * 10^4
     * -10^4 <= arr[i] <= 10^4
     * @param arr
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0, n = arr.length;
        for (int x : arr) sum += x;
        if (sum % 3 != 0) return false;
        sum /= 3;

        int i = 0, j = n - 1;
        for (int s = 0; i < n; i++) {
            s += arr[i];
            if (s == sum) break;
        }
        for (int s = 0; j >= 0; j--) {
            s += arr[j];
            if (s == sum) break;
        }
        return i + 1 <= j - 1;
    }
}