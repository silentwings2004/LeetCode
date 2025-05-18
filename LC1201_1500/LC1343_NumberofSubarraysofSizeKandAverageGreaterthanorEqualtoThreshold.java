package LC1201_1500;

public class LC1343_NumberofSubarraysofSizeKandAverageGreaterthanorEqualtoThreshold {
    /**
     * Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and
     * average greater than or equal to threshold.
     *
     * Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
     * Output: 3
     *
     * Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^5
     * 1 <= arr[i] <= 10^4
     * 1 <= k <= arr.length
     * 0 <= threshold <= 10^4
     * @param arr
     * @param k
     * @param threshold
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        for (int i = 0; i < n; i++) arr[i] += i == 0 ? 0 : arr[i - 1];

        int res = 0;
        for (int i = 0, j = k - 1; j < n; j++) {
            if (arr[j] - (i == 0 ? 0 : arr[i - 1]) >= threshold * k) res++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int numOfSubarrays2(int[] arr, int k, int threshold) {
        int n = arr.length, res = 0, j = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (i - j + 1 > k) sum -= arr[j++];
            if (i - j + 1 == k && sum >= k * threshold) res++;
        }
        return res;
    }
}
