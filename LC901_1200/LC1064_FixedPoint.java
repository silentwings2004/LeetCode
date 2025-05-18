package LC901_1200;

public class LC1064_FixedPoint {
    /**
     * Given an array of distinct integers arr, where arr is sorted in ascending order, return the smallest index i that
     * satisfies arr[i] == i. If there is no such index, return -1.
     *
     * Input: arr = [-10,-5,0,3,7]
     * Output: 3
     *
     * Input: arr = [0,2,5,8,17]
     * Output: 0
     *
     * Input: arr = [-10,-5,3,4,7,9]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= arr.length < 10^4
     * -10^9 <= arr[i] <= 10^9
     *
     * Follow up: The O(n) solution is very straightforward. Can we do better?
     * @param arr
     * @return
     */
    // time = O(logn), space = O(1)
    public int fixedPoint(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (arr[mid] >= mid) r = mid;
            else l = mid + 1;
        }
        return arr[r] == r ? r : -1;
    }
}