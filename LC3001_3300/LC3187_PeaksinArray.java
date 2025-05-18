package LC3001_3300;
import java.util.*;
public class LC3187_PeaksinArray {
    /**
     * A peak in an array arr is an element that is greater than its previous and next element in arr.
     *
     * You are given an integer array nums and a 2D integer array queries.
     *
     * You have to process queries of two types:
     *
     * queries[i] = [1, li, ri], determine the count of peak elements in the subarray nums[li..ri].
     * queries[i] = [2, indexi, vali], change nums[indexi] to vali.
     * Return an array answer containing the results of the queries of the first type in order.
     * Notes:
     *
     * The first and the last element of an array or a subarray cannot be a peak.
     *
     * Input: nums = [3,1,4,2,5], queries = [[2,3,4],[1,0,4]]
     *
     * Output: [0]
     *
     * Input: nums = [4,1,4,2,1,5], queries = [[2,2,4],[1,0,2],[1,0,4]]
     *
     * Output: [0,1]
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i][0] == 1 or queries[i][0] == 2
     * For all i that:
     * queries[i][0] == 1: 0 <= queries[i][1] <= queries[i][2] <= nums.length - 1
     * queries[i][0] == 2: 0 <= queries[i][1] <= nums.length - 1, 1 <= queries[i][2] <= 10^5
     * @param nums
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] tr, nums;
    boolean[] st;
    int n;
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        this.nums = nums;
        n = nums.length;
        tr = new int[n + 1];
        st = new boolean[n];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                add(i + 1, 1);
                st[i] = true;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int l = q[1], r = q[2];
                if (r < l + 1) res.add(0);
                else res.add(sum(r) - sum(l + 1));
            } else {
                int idx = q[1], v = q[2];
                if (v == nums[idx]) continue;
                nums[idx] = v;
                if (idx == 0) work(idx + 1);
                else if (idx == n - 1) work(idx - 1);
                else {
                    work(idx);
                    if (idx - 1 > 0) work(idx - 1);
                    if (idx + 1 < n - 1) work(idx + 1);
                }
            }
        }
        return res;
    }

    private void work(int x) {
        if (nums[x] > nums[x - 1] && nums[x] > nums[x + 1]) {
            if (!st[x]) {
                st[x] = true;
                add(x + 1, 1);
            }
        } else {
            if (st[x]) {
                st[x] = false;
                add(x + 1, -1);
            }
        }
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] += c;
    }

    private int sum(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}