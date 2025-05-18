package LC1501_1800;

public class LC1550_ThreeConsecutiveOdds {
    /**
     * Given an integer array arr, return true if there are three consecutive odd numbers in the array. Otherwise,
     * return false.
     *
     * Input: arr = [2,6,4,1]
     * Output: false
     *
     * Input: arr = [1,2,34,3,4,5,7,23,12]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= arr.length <= 1000
     * 1 <= arr[i] <= 1000
     * @param arr
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;
        for (int i = 0; i + 2 < n; i++) {
            if (arr[i] % 2 == 1 && arr[i + 1] % 2 == 1 && arr[i + 2] % 2 == 1) return true;
        }
        return false;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean threeConsecutiveOdds2(int[] arr) {
        int n = arr.length;
        for (int i = 0; i + 2 < n; i++) {
            if ((arr[i] & arr[i + 1] & arr[i + 2] & 1) == 1) return true;
        }
        return false;
    }
}