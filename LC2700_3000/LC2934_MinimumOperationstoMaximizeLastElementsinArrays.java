package LC2700_3000;

public class LC2934_MinimumOperationstoMaximizeLastElementsinArrays {
    /**
     * You are given two 0-indexed integer arrays, nums1 and nums2, both having length n.
     *
     * You are allowed to perform a series of operations (possibly none).
     *
     * In an operation, you select an index i in the range [0, n - 1] and swap the values of nums1[i] and nums2[i].
     *
     * Your task is to find the minimum number of operations required to satisfy the following conditions:
     *
     * nums1[n - 1] is equal to the maximum value among all elements of nums1, i.e., nums1[n - 1] = max(nums1[0],
     * nums1[1], ..., nums1[n - 1]).
     * nums2[n - 1] is equal to the maximum value among all elements of nums2, i.e., nums2[n - 1] = max(nums2[0],
     * nums2[1], ..., nums2[n - 1]).
     * Return an integer denoting the minimum number of operations needed to meet both conditions, or -1 if it is
     * impossible to satisfy both conditions.
     *
     * Input: nums1 = [1,2,7], nums2 = [4,5,3]
     * Output: 1
     *
     * Input: nums1 = [2,3,4,5,9], nums2 = [8,8,4,4,4]
     * Output: 2
     *
     * Input: nums1 = [1,5,4], nums2 = [2,5,3]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums1.length == nums2.length <= 1000
     * 1 <= nums1[i] <= 10^9
     * 1 <= nums2[i] <= 10^9
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n), space = O(1)
    int[] nums1, nums2;
    int n;
    public int minOperations(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        n = nums1.length;
        int res = Math.min(check(nums1[n - 1], nums2[n - 1]), 1 + check(nums2[n - 1], nums1[n - 1]));
        return res > n ? -1 : res;
    }

    private int check(int a, int b) {
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            int x = nums1[i], y = nums2[i];
            if (x > a || y > b) {
                if (x > b || y > a) return n + 1;
                res++;
            }
        }
        return res;
    }
}
/**
 * 同一列的2个值交换，只有2种情况：
 * 1.最后一对数没有交换
 * 2.最后一对数交换了
 */