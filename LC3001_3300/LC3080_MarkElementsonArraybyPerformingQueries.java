package LC3001_3300;
import java.util.*;
public class LC3080_MarkElementsonArraybyPerformingQueries {
    /**
     * You are given a 0-indexed array nums of size n consisting of positive integers.
     *
     * You are also given a 2D array queries of size m where queries[i] = [indexi, ki].
     *
     * Initially all elements of the array are unmarked.
     *
     * You need to apply m queries on the array in order, where on the ith query you do the following:
     *
     * Mark the element at index indexi if it is not already marked.
     * Then mark ki unmarked elements in the array with the smallest values. If multiple such elements exist, mark the
     * ones with the smallest indices. And if less than ki unmarked elements exist, then mark all of them.
     * Return an array answer of size m where answer[i] is the sum of unmarked elements in the array after the ith query.
     *
     * Input:nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]
     * Output: [8,3,0]
     *
     * Input: nums = [1,4,2,3], queries = [[0,1]]
     * Output: [7]
     *
     * Constraints:
     *
     * n == nums.length
     * m == queries.length
     * 1 <= m <= n <= 10^5
     * 1 <= n <= 10^5
     * @param nums
     * @param queries
     * @return
     */
    // S1
    // time = O((m + n) * logn), space = O(n)
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        long[] res = new long[m];
        TreeSet<int[]> set = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        long s = 0;
        for (int i = 0; i < n; i++) {
            set.add(new int[]{nums[i], i});
            s += nums[i];
        }

        for (int i = 0; i < m; i++) {
            int idx = queries[i][0], k = queries[i][1];
            if (set.contains(new int[]{nums[idx], idx})) {
                set.remove(new int[]{nums[idx], idx});
                s -= nums[idx];
            }
            while (k-- > 0 && set.size() > 0) {
                int[] t = set.first();
                s -= t[0];
                set.remove(t);
            }
            res[i] = s;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long[] unmarkedSumArray2(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        long s = 0;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            s += nums[i];
            ids[i] = i;
        }
        Arrays.sort(ids, (o1, o2) -> nums[o1] - nums[o2]);

        long[] res = new long[m];
        int j = 0;
        for (int i = 0; i < m; i++) {
            int t = queries[i][0], k = queries[i][1];
            s -= nums[t];
            nums[t] = 0;
            while (j < n && k > 0) {
                int x = ids[j++];
                if (nums[x] > 0) {
                    s -= nums[x];
                    nums[x] = 0;
                    k--;
                }
            }
            res[i] = s;
        }
        return res;
    }
}