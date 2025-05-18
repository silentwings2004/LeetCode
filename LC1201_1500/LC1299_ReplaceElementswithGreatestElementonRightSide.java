package LC1201_1500;

public class LC1299_ReplaceElementswithGreatestElementonRightSide {
    /**
     * Given an array arr, replace every element in that array with the greatest element among the elements to its
     * right, and replace the last element with -1.
     *
     * After doing so, return the array.
     *
     * Input: arr = [17,18,5,4,6,1]
     * Output: [18,6,6,6,1,-1]
     *
     * Input: arr = [400]
     * Output: [-1]
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^4
     * 1 <= arr[i] <= 10^5
     * @param arr
     * @return
     */
    // time = O(n), space = O(1)
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int maxv = -1;
        for (int i = n - 1; i >= 0; i--) {
            int t = arr[i];
            arr[i] = maxv;
            maxv = Math.max(maxv, t);
        }
        return arr;
    }
}