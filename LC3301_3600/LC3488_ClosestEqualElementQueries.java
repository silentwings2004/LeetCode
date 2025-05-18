package LC3301_3600;
import java.util.*;
public class LC3488_ClosestEqualElementQueries {
    /**
     * You are given a circular array nums and an array queries.
     *
     * For each query i, you have to find the following:
     *
     * The minimum distance between the element at index queries[i] and any other index j in the circular array, where
     * nums[j] == nums[queries[i]]. If no such index exists, the answer for that query should be -1.
     * Return an array answer of the same size as queries, where answer[i] represents the result for query i.
     *
     * Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
     * Output: [2,-1,3]
     *
     * Input: nums = [1,2,3,4], queries = [0,1,2,3]
     * Output: [-1,-1,-1,-1]
     *
     * Constraints:
     *
     * 1 <= queries.length <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * 0 <= queries[i] < nums.length
     * @param nums
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length, m = queries.length;
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new TreeSet<>());
            map.get(nums[i]).add(i);
            map.get(nums[i]).add(i - n);
            map.get(nums[i]).add(i + n);
        }

        for (int q : queries) {
            TreeSet<Integer> set = map.get(nums[q]);
            int v = Integer.MAX_VALUE;
            Integer lk = set.lower(q), hk = set.higher(q);
            if (lk != null && q - lk != n) v = Math.min(v, q - lk);
            if (hk != null && hk - q != n) v = Math.min(v, hk - q);
            if (v == Integer.MAX_VALUE) res.add(-1);
            else res.add(v);
        }
        return res;
    }
}