package LC901_1200;
import java.util.*;
public class LC923_3SumWithMultiplicity {
    /**
     * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and
     * arr[i] + arr[j] + arr[k] == target.
     *
     * As the answer can be very large, return it modulo 10^9 + 7.
     *
     * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
     * Output: 20
     *
     * Constraints:
     *
     * 3 <= arr.length <= 3000
     * 0 <= arr[i] <= 100
     * 0 <= target <= 300
     * @param arr
     * @param target
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int threeSumMulti(int[] arr, int target) {
        // corner case
        if (arr == null || arr.length == 0) return 0;
        long res = 0;
        int MOD = 1000000007;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) { // O(n)
            int t = target - arr[i];
            int j = i + 1, k = arr.length - 1;
            while (j < k) { // O(n)
                if (arr[j] + arr[k] < t) j++;
                else if (arr[j] + arr[k] > t) k--;
                else { // arr[j] + arr[k] == t
                    if (arr[j] != arr[k]) {
                        int left = j + 1, right = k - 1;
                        while (arr[left] == arr[j] && left < k) left++;
                        while (arr[right] == arr[k] && right > j) right--;
                        res += (left - j) * (k - right);
                        res %= MOD;
                        j = left;
                        k = right;
                    } else {
                        res += (k - j + 1) * (k - j) / 2;
                        res %= MOD;
                        j++;
                        k--;
                        break;
                    }
                }
            }
        }
        return (int) res;
    }

    // S2
    // time = O(n^2), space = O(1)
    int[] cnt;
    int mod = (int)(1e9 + 7);
    public int threeSumMulti2(int[] arr, int target) {
        cnt = new int[310];
        for (int x : arr) cnt[x]++;
        int res = 0, mod = (int)1e9 + 7;
        for (int i = 0; i <= target; i++) {
            for (int j = i; j <= target - i - j; j++) {
                res = (res + calc(i, j, target - i - j)) % mod;
            }
        }
        return res;
    }

    private int calc(int a, int b, int c) {
        if (a == b && b == c) return C(cnt[a], 3);
        if (a == b) return C(cnt[a], 2) * cnt[c] % mod;
        if (b == c) return cnt[a] * C(cnt[b], 2) % mod;
        return cnt[a] * cnt[b] % mod * cnt[c] % mod;
    }

    private int C(int a, int b) {
        long res = 1;
        for (int i = a, j = 1; j <= b; i--, j++) {
            res = res * i / j;
        }
        return (int)(res % mod);
    }
}
/**
 * 0 ~ 100 => 按数值来枚举
 * 分类讨论 => Ca * Cb * Cc
 * T^3
 */