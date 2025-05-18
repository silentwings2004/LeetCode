package LC1201_1500;

public class LC1394_FindLuckyIntegerinanArray {
    /**
     * Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
     *
     * Return the largest lucky integer in the array. If there is no lucky integer return -1.
     *
     * Input: arr = [2,2,3,4]
     * Output: 2
     *
     * Input: arr = [1,2,2,3,3,3]
     * Output: 3
     *
     * Input: arr = [2,2,2,3,3]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= arr.length <= 500
     * 1 <= arr[i] <= 500
     * @param arr
     * @return
     */
    // time = O(n), space = O(1)
    public int findLucky(int[] arr) {
        int[] cnt = new int[501];
        for (int x : arr) cnt[x]++;
        for (int i = 500; i >= 1; i--) {
            if (cnt[i] == i) return i;
        }
        return -1;
    }
}