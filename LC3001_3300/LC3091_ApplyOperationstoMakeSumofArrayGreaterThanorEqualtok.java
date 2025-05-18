package LC3001_3300;

public class LC3091_ApplyOperationstoMakeSumofArrayGreaterThanorEqualtok {
    /**
     * You are given a positive integer k. Initially, you have an array nums = [1].
     *
     * You can perform any of the following operations on the array any number of times (possibly zero):
     *
     * Choose any element in the array and increase its value by 1.
     * Duplicate any element in the array and add it to the end of the array.
     * Return the minimum number of operations required to make the sum of elements of the final array greater than or
     * equal to k.
     *
     * Input: k = 11
     * Output: 5
     *
     * Input: k = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= k <= 10^5
     * @param k
     * @return
     */
    // S1
    // time = O(k), space = O(1)
    public int minOperations(int k) {
        if (k == 1) return 0;
        int res = k;
        for (int i = 2; i <= k; i++) {
            int t = (k + i - 1) / i - 1;
            res = Math.min(res, i - 1 + t);
        }
        return res;
    }

    // S2
    // time = O(k), space = O(1)
    public int minOperations2(int k) {
        int rt = Math.max(1, (int)Math.sqrt(k - 1));
        return Math.min(rt + (k - 1) / rt - 1, rt + (k - 1) / (rt + 1));
    }
}
/**
 * 先+1，再复制更好
 * 假设+1操作发生在复制操作的后面，那么就交换一下这2个操作
 * => +1 操作都发生在复制操作之前
 * 设若干次 +1 操作后，最大值为 m, 此时数组中只有一个数 m
 * m * x >= k
 * x = ceil(k / m) = floor((k - 1) / m) + 1
 * m - 1 + (k - 1) // m
 */