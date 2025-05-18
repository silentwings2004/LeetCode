package LC3001_3300;
import java.util.*;
public class LC3159_FindOccurrencesofanElementinanArray {
    /**
     * You are given an integer array nums, an integer array queries, and an integer x.
     *
     * For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array. If there
     * are fewer than queries[i] occurrences of x, the answer should be -1 for that query.
     *
     * Return an integer array answer containing the answers to all queries.
     *
     * Input: nums = [1,3,1,7], queries = [1,3,2,4], x = 1
     *
     * Output: [0,-1,2,-1]
     *
     * Input: nums = [1,2,3], queries = [10], x = 5
     *
     * Output: [-1]
     *
     * Constraints:
     *
     * 1 <= nums.length, queries.length <= 10^5
     * 1 <= queries[i] <= 10^5
     * 1 <= nums[i], x <= 10^4
     * @param nums
     * @param queries
     * @param x
     * @return
     */
    // time = O(n + m), space = O(n)
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> q = new ArrayList<>();
        int n = nums.length, m = queries.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == x) q.add(i);
        }

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int y = queries[i] - 1;
            if (q.size() <= y) res[i] = -1;
            else res[i] = q.get(y);
        }
        return res;
    }
}