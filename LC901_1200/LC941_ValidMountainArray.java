package LC901_1200;

public class LC941_ValidMountainArray {
    /**
     * Given an array of integers arr, return true if and only if it is a valid mountain array.
     *
     * Recall that arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     *
     * Input: arr = [2,1]
     * Output: false
     *
     * Input: arr = [0,3,2,1]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^4
     * 0 <= arr[i] <= 10^4
     * @param arr
     * @return
     */
    // time = O(n), space = O(1)
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;

        int i = 0, j = n - 1;
        while (i < n - 1 && arr[i] < arr[i + 1]) i++;
        while (j > 0 && arr[j] < arr[j - 1]) j--;
        if (i == 0 || j == n - 1) return false;
        return i == j;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean validMountainArray2(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;
        int k = 0;
        while (k + 1 < n && arr[k] < arr[k + 1]) k++;
        if (k == 0 || k + 1 == n) return false;
        while (k + 1 < n && arr[k] > arr[k + 1]) k++;
        return k + 1 == n;
    }
}