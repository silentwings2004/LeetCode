package LC2401_2700;
import java.util.*;
public class LC2638_CounttheNumberofKFreeSubsets {
    /**
     * You are given an integer array nums, which contains distinct elements and an integer k.
     *
     * A subset is called a k-Free subset if it contains no two elements with an absolute difference equal to k. Notice
     * that the empty set is a k-Free subset.
     *
     * Return the number of k-Free subsets of nums.
     *
     * A subset of an array is a selection of elements (possibly none) of the array.
     *
     * Input: nums = [5,4,6], k = 1
     * Output: 5
     *
     * Input: nums = [2,3,5,8], k = 5
     * Output: 12
     *
     * Input: nums = [10,5,9,11], k = 20
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 1000
     * 1 <= k <= 1000
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2*logn), space = O(n)
    int[] p, sz;
    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        p = new int[n];
        sz = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i;
            sz[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] - nums[i] == k) {
                    int a = find(i), b = find(j);
                    sz[b] += sz[a];
                    p[a] = b;
                    break;
                }
            }
        }

        long res = 1;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) res *= cal(sz[i]);
        }
        return res;
    }

    private long cal(int n) {
        long[] f = new long[n + 1];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}