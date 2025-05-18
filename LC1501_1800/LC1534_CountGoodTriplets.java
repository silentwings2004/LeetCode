package LC1501_1800;

public class LC1534_CountGoodTriplets {
    /**
     * Given an array of integers arr, and three integers a, b and c. You need to find the number of good triplets.
     *
     * A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
     *
     * 0 <= i < j < k < arr.length
     * |arr[i] - arr[j]| <= a
     * |arr[j] - arr[k]| <= b
     * |arr[i] - arr[k]| <= c
     * Where |x| denotes the absolute value of x.
     *
     * Return the number of good triplets.
     *
     * Input: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
     * Output: 4
     *
     * Input: arr = [1,1,2,2,3], a = 0, b = 0, c = 1
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= arr.length <= 100
     * 0 <= arr[i] <= 1000
     * 0 <= a, b, c <= 1000
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    // time = O(n^3), space = O(1)
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(arr[i] - arr[j]) > a) continue;
                for (int k = j + 1; k < n; k++) {
                    if (Math.abs(arr[j] - arr[k]) > b) continue;
                    if (Math.abs(arr[i] - arr[k]) <= c) res++;
                }
            }
        }
        return res;
    }
}
