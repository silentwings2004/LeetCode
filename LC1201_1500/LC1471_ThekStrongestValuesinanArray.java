package LC1201_1500;
import java.util.*;
public class LC1471_ThekStrongestValuesinanArray {
    /**
     * Given an array of integers arr and an integer k.
     *
     * A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median
     * of the array.
     * If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].
     *
     * Return a list of the strongest k values in the array. return the answer in any arbitrary order.
     *
     * Median is the middle value in an ordered integer list. More formally, if the length of the list is n, the median
     * is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
     *
     * For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and
     * the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
     * For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22] and
     * the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
     *
     * Input: arr = [1,2,3,4,5], k = 2
     * Output: [5,1]
     *
     * Input: arr = [1,1,3,5,5], k = 2
     * Output: [5,5]
     *
     * Input: arr = [6,7,11,7,6,8], k = 5
     * Output: [11,8,6,6,7]
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^5
     * -10^5 <= arr[i] <= 10^5
     * 1 <= k <= arr.length
     * @param arr
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int median = arr[(n - 1) / 2];
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{Math.abs(arr[i] - median), arr[i]};
        Arrays.sort(w, (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = w[i][1];
        return res;
    }
}