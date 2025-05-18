package LC2401_2700;
import java.util.*;
public class LC2519_CounttheNumberofKBigIndices {
    /**
     * You are given a 0-indexed integer array nums and a positive integer k.
     *
     * We call an index i k-big if the following conditions are satisfied:
     *
     * There exist at least k different indices idx1 such that idx1 < i and nums[idx1] < nums[i].
     * There exist at least k different indices idx2 such that idx2 > i and nums[idx2] < nums[i].
     * Return the number of k-big indices.
     *
     * Input: nums = [2,3,6,5,2,3], k = 2
     * Output: 2
     *
     * Input: nums = [1,1,1], k = 3
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i], k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    int[] tr;
    public int kBigIndices(int[] nums, int k) {
        tr = new int[N];
        int n = nums.length, res = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int x = query(nums[i] - 1);
            if (x >= k) set.add(i);
            add(nums[i], 1);
        }

        tr = new int[N];
        for (int i = n - 1; i >= 0; i--) {
            int x = query(nums[i] - 1);
            if (x >= k && set.contains(i)) res++;
            add(nums[i], 1);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i < N; i += lowbit(i)) tr[i] += c;
    }

    private int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}