package LC1201_1500;

public class LC1385_FindtheDistanceValueBetweenTwoArrays {
    /**
     * Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.
     *
     * The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where
     * |arr1[i]-arr2[j]| <= d.
     *
     * Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= arr1.length, arr2.length <= 500
     * -1000 <= arr1[i], arr2[j] <= 1000
     * 0 <= d <= 100
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    // time = O(m * n), space = O(1)
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int m = arr1.length, n = arr2.length, res = 0;
        for (int i = 0; i < m; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    flag = false;
                    break;
                }
            }
            if (flag) res++;
        }
        return res;
    }
}
