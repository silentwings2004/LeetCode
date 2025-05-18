package LC3301_3600;
import java.util.*;
public class LC3362_ZeroArrayTransformationIII {
    /**
     * You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri].
     *
     * Each queries[i] represents the following action on nums:
     *
     * Decrement the value at each index in the range [li, ri] in nums by at most 1.
     * The amount by which the value is decremented can be chosen independently for each index.
     * A Zero Array is an array with all its elements equal to 0.
     *
     * Return the maximum number of elements that can be removed from queries, such that nums can still be converted to
     * a zero array using the remaining queries. If it is not possible to convert nums to a zero array, return -1.
     *
     * Input: nums = [2,0,2], queries = [[0,2],[0,2],[1,1]]
     * Output: 1
     *
     * Input: nums = [1,1,1,1], queries = [[1,3],[0,2],[1,3],[1,2]]
     * Output: 2
     *
     * Input: nums = [1,2,3,4], queries = [[0,3]]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= li <= ri < nums.length
     * @param nums
     * @param queries
     * @return
     */
    // time = O(nlogm + m), space = O(n + m)
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] b = new int[n + 1];
        Arrays.sort(queries, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int res = 0;
        for (int i = 0, j = 0, s = 0; i < n; i++) {
            s += b[i];
            while (j < m && queries[j][0] <= i) pq.offer(queries[j++][1]);
            while (nums[i] + s > 0 && !pq.isEmpty()) {
                int r = pq.poll();
                if (r >= i) {
                    s--;
                    res++;
                }
                b[r + 1]++;
            }
            if (nums[i] + s > 0) return -1;
        }
        return m - res;
    }
}