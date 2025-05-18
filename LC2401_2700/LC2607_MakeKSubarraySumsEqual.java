package LC2401_2700;
import java.util.*;
public class LC2607_MakeKSubarraySumsEqual {
    /**
     * You are given a 0-indexed integer array arr and an integer k. The array arr is circular. In other words, the
     * first element of the array is the next element of the last element, and the last element of the array is the
     * previous element of the first element.
     *
     * You can do the following operation any number of times:
     *
     * Pick any element from arr and increase or decrease it by 1.
     * Return the minimum number of operations such that the sum of each subarray of length k is equal.
     *
     * A subarray is a contiguous part of the array.
     *
     * Input: arr = [1,4,1,3], k = 2
     * Output: 1
     *
     * Input: arr = [2,5,5,7], k = 3
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= k <= arr.length <= 10^5
     * 1 <= arr[i] <= 10^9
     * @param arr
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        long res = 0;
        k = gcd(n, k);
        for (int i = 0; i < k; i++) {
            List<Integer> q = new ArrayList<>();
            for (int j = i; j < n; j += k) {
                q.add(arr[j]);
            }
            Collections.sort(q);
            int median = q.get(q.size() / 2);
            for (int j = 0; j < q.size(); j++) {
                res += Math.abs(q.get(j) - median);
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}