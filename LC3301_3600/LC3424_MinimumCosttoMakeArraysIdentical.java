package LC3301_3600;
import java.util.*;
public class LC3424_MinimumCosttoMakeArraysIdentical {
    /**
     * You are given two integer arrays arr and brr of length n, and an integer k. You can perform the following
     * operations on arr any number of times:
     *
     * Split arr into any number of contiguous subarrays and rearrange these subarrays in any order. This operation has
     * a fixed cost of k.
     * Choose any element in arr and add or subtract a positive integer x to it. The cost of this operation is x.
     *
     * Return the minimum total cost to make arr equal to brr.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: arr = [-7,9,5], brr = [7,-2,-5], k = 2
     *
     * Output: 13
     *
     * Input: arr = [2,1], brr = [2,1], k = 0
     *
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= arr.length == brr.length <= 10^5
     * 0 <= k <= 2 * 10^10
     * -10^5 <= arr[i] <= 10^5
     * -10^5 <= brr[i] <= 10^5
     * @param arr
     * @param brr
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long minCost(int[] arr, int[] brr, long k) {
        int n = arr.length;
        long s1 = 0, s2 = 0;
        int[][] a = new int[n][2], b = new int[n][2];
        for (int i = 0; i < n; i++) {
            s1 += Math.abs(arr[i] - brr[i]);
            a[i] = new int[]{arr[i], i};
            b[i] = new int[]{brr[i], i};
        }
        Arrays.sort(a, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        Arrays.sort(b, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        boolean f = false;
        for (int i = 0; i < n; i++) {
            s2 += Math.abs(a[i][0] - b[i][0]);
            if (a[i][1] != b[i][1]) f = true;
        }
        if (f) s2 += k;
        return Math.min(s1, s2);
    }
}