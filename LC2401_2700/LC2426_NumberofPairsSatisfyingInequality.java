package LC2401_2700;

public class LC2426_NumberofPairsSatisfyingInequality {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number
     * of pairs (i, j) such that:
     *
     * 0 <= i < j <= n - 1 and
     * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
     * Return the number of pairs that satisfy the conditions.
     *
     * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
     * Output: 3
     *
     * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
     * Output: 0
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 2 <= n <= 10^5
     * -10^4 <= nums1[i], nums2[i] <= 10^4
     * -10^4 <= diff <= 10^4
     * @param nums1
     * @param nums2
     * @param diff
     * @return
     */
    // time = O(nlogn), space = O(n)
    int n = 50050;
    int[] tr;
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        tr = new int[n];
        int m = nums1.length;
        long res = 0;

        for (int i = 0; i < m; i++) {
            int x = nums1[i] - nums2[i] + 20010;
            res += query(x + diff);
            add(x, 1);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int v) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] += v;
    }

    private long query(int x) {
        long res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }

    // S2: divide & conquer
    // time = O(nlogn), space = O(n)
    class Solution {
        long res = 0;
        int[] tmp;
        int diff;
        public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
            int n = nums1.length;
            this.diff = diff;
            tmp = new int[n];
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = nums1[i] - nums2[i];
            merge_sort(nums, 0, n - 1);
            return res;
        }

        private void merge_sort(int[] q, int l, int r) {
            if (l >= r) return;

            int mid = l + r >> 1;
            merge_sort(q, l, mid);
            merge_sort(q, mid + 1, r);

            for (int i = l; i <= mid; i++) {
                int iter = upperBound(q, mid + 1, r, q[i] - diff);
                res += r - iter + 1;
            }

            int i = l, j = mid + 1, k = 0;
            while (i <= mid && j <= r) {
                if (q[i] <= q[j]) tmp[k++] = q[i++];
                else tmp[k++] = q[j++];
            }
            while (i <= mid) tmp[k++] = q[i++];
            while (j <= r) tmp[k++] = q[j++];
            for (i = l, j = 0; i <= r; i++, j++) q[i] = tmp[j];
        }

        private int upperBound(int[] q, int l, int r, int t) {
            while (l < r) {
                int mid = l + r >> 1;
                if (q[mid] < t) l = mid + 1;
                else r = mid;
            }
            return q[r] >= t ? r : r + 1;
        }
    }
}
/**
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff
 * nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff
 * arr[k] = nums1[k] - nums2[k]
 * i < j
 * arr[i] <= arr[j] + diff
 * arr: [a,b]
 * [a,mid] [mid+1,b]
 * {i,j}: there must be an interval, s.t, i is in the first half, and j is in the 2nd half
 */