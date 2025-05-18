package LC2101_2400;
import java.util.*;
public class LC2382_MaximumSegmentSumAfterRemovals {
    /**
     * You are given two 0-indexed integer arrays nums and removeQueries, both of length n. For the ith query, the
     * element in nums at the index removeQueries[i] is removed, splitting nums into different segments.
     *
     * A segment is a contiguous sequence of positive integers in nums. A segment sum is the sum of every element in a
     * segment.
     *
     * Return an integer array answer, of length n, where answer[i] is the maximum segment sum after applying the ith
     * removal.
     *
     * Note: The same index will not be removed more than once.
     *
     * Input: nums = [1,2,5,6,1], removeQueries = [0,3,2,4,1]
     * Output: [14,7,2,2,0]
     *
     * Input: nums = [3,2,11,1], removeQueries = [3,2,1,0]
     * Output: [16,5,3,0]
     *
     * Constraints:
     *
     * n == nums.length == removeQueries.length
     * 1 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= removeQueries[i] < n
     * All the values of removeQueries are unique.
     * @param nums
     * @param removeQueries
     * @return
     */
    // S1: PQ
    // time = O(nlogn), space = O(n)
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + nums[i - 1];

        TreeSet<Integer> set = new TreeSet<>();
        set.add(-1);
        set.add(n);

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2[2], o1[2]));
        pq.offer(new long[]{1, n, presum[n]});

        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            int x = removeQueries[i];
            int lk = set.lower(x), hk = set.higher(x);
            if (lk + 1 <= x - 1) pq.offer(new long[]{(long) lk + 1, (long) x - 1, presum[x] - presum[lk + 1]});
            if (x + 1 <= hk - 1) pq.offer(new long[]{(long) x + 1, (long) hk - 1, presum[hk] - presum[x + 1]});
            set.add(x);

            while (!pq.isEmpty()) {
                long[] cur = pq.poll();
                long a = cur[0], b = cur[1], sum = cur[2];
                int l = set.ceiling((int) a), r = set.floor((int) b);
                if (l <= b || r >= a) continue;
                res[i] = sum;
                pq.offer(cur);
                break;
            }
        }
        return res;
    }

    // S2: Union Find
    // time = O(n), space = O(n)
    int[] p;
    long[] s;
    public long[] maximumSegmentSum2(int[] nums, int[] removeQueries) {
        int n = nums.length;
        p = new int[n];
        s = new long[n];

        for (int i = 0; i < n; i++) p[i] = i;

        long[] res = new long[n];
        long max = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = removeQueries[i];
            s[x] = nums[x];
            for (int j = x - 1; j <= x + 1; j += 2) {
                if (j >= 0 && j < n && s[j] > 0) {
                    s[x] += s[find(j)];
                    p[find(j)] = x;
                }
            }
            res[i] = max;
            max = Math.max(max, s[x]);
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * reverse union find
 * 一个个加，把它加回来
 * 倒着做并查集，就是一个不断集合合并的过程
 * 时间复杂度降到线性
 */