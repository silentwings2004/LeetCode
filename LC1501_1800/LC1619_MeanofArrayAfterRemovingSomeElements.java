package LC1501_1800;
import java.util.*;
public class LC1619_MeanofArrayAfterRemovingSomeElements {
    /**
     * Given an integer array arr, return the mean of the remaining integers after removing the smallest 5% and the
     * largest 5% of the elements.
     *
     * Answers within 10-5 of the actual answer will be considered accepted.
     *
     * Input: arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
     * Output: 2.00000
     *
     * Input: arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
     * Output: 4.00000
     *
     * Constraints:
     *
     * 20 <= arr.length <= 1000
     * arr.length is a multiple of 20.
     * 0 <= arr[i] <= 10^5
     * @param arr
     * @return
     */
    // time = O(nlogn), space = O(1)
    public double trimMean(int[] arr) {
        int n = arr.length, k = n / 20;
        double sum = 0;
        Arrays.sort(arr);
        for (int i = k; i <= n - 1 - k; i++) sum += arr[i];
        return sum / (n - 2 * k);
    }
}
