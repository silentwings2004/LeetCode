package LC1201_1500;

public class LC1287_ElementAppearingMoreThan25InSortedArray {
    /**
     * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more
     * than 25% of the time, return that integer.
     *
     * Input: arr = [1,2,2,6,6,6,6,7,10]
     * Output: 6
     *
     * Input: arr = [1,1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^4
     * 0 <= arr[i] <= 10^5
     * @param arr
     * @return
     */
    // time = O(n), space = O(1)
    public int findSpecialInteger(int[] arr) {
        int n = arr.length, res = arr[0];
        for (int i = 0; i < n; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                int j = i;
                while (j < n && arr[j] == arr[j - 1]) j++;
                if (j - i + 1 > n / 4) {
                    res = arr[i];
                    break;
                }
                i = j - 1;
            }
        }
        return res;
    }
}