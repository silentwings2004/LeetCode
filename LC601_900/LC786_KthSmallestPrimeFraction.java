package LC601_900;

public class LC786_KthSmallestPrimeFraction {
    /**
     * You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique.
     * You are also given an integer k.
     *
     * For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
     *
     * Return the kth smallest fraction considered. Return your answer as an array of integers of size 2,
     * where answer[0] == arr[i] and answer[1] == arr[j].
     *
     * Input: arr = [1,2,3,5], k = 3
     * Output: [2,5]
     *
     * Constraints:
     *
     * 2 <= arr.length <= 1000
     * 1 <= arr[i] <= 3 * 10^4
     * arr[0] == 1
     * arr[i] is a prime number for i > 0.
     * All the numbers of arr are unique and sorted in strictly increasing order.
     * 1 <= k <= arr.length * (arr.length - 1) / 2
     * @param arr
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(1)
    final double eps = 1e-8;
    int[] ans;
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        ans = new int[2];
        double l = 0, r = 1;
        while (r - l > eps) {
            double mid = (l + r) / 2;
            if (get(arr, mid) >= k) r = mid;
            else l = mid;
        }
        get(arr, r);
        return ans;
    }

    private int get(int[] arr, double mid) {
        int n = arr.length, res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j + 1 < n && arr[j + 1] * 1.0 / arr[i] <= mid) j++;
            if (arr[j] * 1.0 / arr[i] <= mid) res += j + 1;
            if (Math.abs(arr[j] * 1.0 / arr[i] - mid) < eps) {
                ans = new int[]{arr[j], arr[i]};
            }
        }
        return res;
    }
}
/**
 * kth smallest -> search by value
 * 用bs来猜这个数，然后来判断是猜大还是猜小，试错！逆向思维非常典型的做法！
 * nums[i] / nums[j] <= mid =>
 * nums[j] >= nums[i] / mid
 * 找出来的mid未必在这个arr里
 * 3/5 = 0.6 => mid = 0.625
 * 如果是double,永远是开区间，得通过手动设置条件跳出loop
 * 分母越大，整个fraction就越小，所以找的j是后半段 => n - idx
 *
 * 二分：满足 <= 它的数的个数 >= k 的最小数
 */
