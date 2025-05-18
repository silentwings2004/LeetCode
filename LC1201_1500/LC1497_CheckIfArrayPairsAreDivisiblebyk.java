package LC1201_1500;
import java.util.*;
public class LC1497_CheckIfArrayPairsAreDivisiblebyk {
    /**
     * Given an array of integers arr of even length n and an integer k.
     *
     * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
     *
     * Return true If you can find a way to do that or false otherwise.
     *
     * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
     * Output: true
     *
     * Input: arr = [1,2,3,4,5,6], k = 7
     * Output: true
     *
     * Input: arr = [1,2,3,4,5,6], k = 10
     * Output: false
     *
     * Constraints:
     *
     * arr.length == n
     * 1 <= n <= 10^5
     * n is even.
     * -109 <= arr[i] <= 10^9
     * 1 <= k <= 10^5
     * @param arr
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public boolean canArrange(int[] arr, int k) {
        int[] cnt = new int[k + 1];
        for (int x : arr) cnt[(x % k + k) % k]++;
        for (int i = 0; i < k; i++) {
            if (cnt[i] > 0) {
                if (i == (k - i) % k) {
                    if (cnt[i] % 2 != 0) return false;
                } else {
                    if (cnt[i] != cnt[k - i]) return false;
                }
            }
        }
        return true;
    }
}