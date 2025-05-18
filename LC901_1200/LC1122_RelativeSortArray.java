package LC901_1200;
import java.util.*;
public class LC1122_RelativeSortArray {
    /**
     * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
     *
     * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that
     * do not appear in arr2 should be placed at the end of arr1 in ascending order.
     *
     * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * Output: [2,2,2,1,4,3,3,9,6,7,19]
     *
     * Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
     * Output: [22,28,8,6,17,44]
     *
     * Constraints:
     *
     * 1 <= arr1.length, arr2.length <= 1000
     * 0 <= arr1[i], arr2[i] <= 1000
     * All the elements of arr2 are distinct.
     * Each arr2[i] is in arr1.
     * @param arr1
     * @param arr2
     * @return
     */
    // time = O(n), space = O(n)
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] cnt = new int[1001];
        for (int x : arr1) cnt[x]++;

        int idx = 0;
        for (int x : arr2) {
            while (cnt[x] > 0) {
                arr1[idx++] = x;
                cnt[x]--;
            }
        }
        for (int i = 0; i <= 1000; i++) {
            while (cnt[i] > 0) {
                arr1[idx++] = i;
                cnt[i]--;
            }
        }
        return arr1;
    }
}