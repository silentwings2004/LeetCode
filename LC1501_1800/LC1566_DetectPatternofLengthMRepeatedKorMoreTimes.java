package LC1501_1800;

public class LC1566_DetectPatternofLengthMRepeatedKorMoreTimes {
    /**
     * Given an array of positive integers arr, find a pattern of length m that is repeated k or more times.
     *
     * A pattern is a subarray (consecutive sub-sequence) that consists of one or more values, repeated multiple times
     * consecutively without overlapping. A pattern is defined by its length and the number of repetitions.
     *
     * Return true if there exists a pattern of length m that is repeated k or more times, otherwise return false.
     *
     * Input: arr = [1,2,4,4,4,4], m = 1, k = 3
     * Output: true
     *
     * Input: arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
     * Output: true
     *
     * Input: arr = [1,2,1,2,1,3], m = 2, k = 3
     * Output: false
     *
     * Constraints:
     *
     * 2 <= arr.length <= 100
     * 1 <= arr[i] <= 100
     * 1 <= m <= 100
     * 2 <= k <= 100
     * @param arr
     * @param m
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length, cnt = 0;
        for (int i = 0; i < n - m; i++) {
            if (arr[i] != arr[i + m]) cnt = 0;
            else cnt++;
            if (cnt == (k - 1) * m) return true;
        }
        return false;
    }
}