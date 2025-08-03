package LC3601_3900;
import java.util.*;
public class LC3636_ThresholdMajorityQueries {
    /**
     * You are given an integer array nums of length n and an array queries, where queries[i] = [li, ri, thresholdi].
     *
     * Return an array of integers ans where ans[i] is equal to the element in the subarray nums[li...ri] that appears
     * at least thresholdi times, selecting the element with the highest frequency (choosing the smallest in case of a
     * tie), or -1 if no such element exists.
     *
     * Input: nums = [1,1,2,2,1,1], queries = [[0,5,4],[0,3,3],[2,3,2]]
     * Output: [1,-1,2]
     *
     * Input: nums = [3,2,3,2,3,2,3], queries = [[0,6,4],[1,5,2],[2,4,1],[3,3,1]]
     * Output: [3,2,3,2]
     *
     * Constraints:
     *
     * 1 <= nums.length == n <= 10^4
     * 1 <= nums[i] <= 10^9
     * 1 <= queries.length <= 5 * 10^4
     * queries[i] = [li, ri, thresholdi]
     * 0 <= li <= ri < n
     * 1 <= thresholdi <= ri - li + 1
     * @param nums
     * @param queries
     * @return
     */
    // time = O(mlogm), space = O(m)
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        Integer[] p = new Integer[m];
        for (int i = 0; i < m; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> {
            int sz = (int)Math.sqrt(n);
            int b1 = queries[o1][0] / sz, b2 = queries[o2][0] / sz;
            if (b1 != b2) return b1 - b2;
            return queries[o1][1] - queries[o2][1];
        });

        int[] res = new int[m];
        HashMap<Integer, Integer> map = new HashMap<>();
        int curL = 0, curR = -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        for (int i = 0; i < m; i++) {
            int idx = p[i];
            while (curR < queries[idx][1]) {
                curR++;
                map.put(nums[curR], map.getOrDefault(nums[curR], 0) + 1);
                pq.offer(new int[]{map.get(nums[curR]), nums[curR]});
            }
            while (curR > queries[idx][1]) {
                map.put(nums[curR], map.getOrDefault(nums[curR], 0) - 1);
                if (map.get(nums[curR]) == 0) map.remove(nums[curR]);
                curR--;
            }
            while (curL > queries[idx][0]) {
                curL--;
                map.put(nums[curL], map.getOrDefault(nums[curL], 0) + 1);
                pq.offer(new int[]{map.get(nums[curL]), nums[curL]});
            }
            while (curL < queries[idx][0]) {
                map.put(nums[curL], map.get(nums[curL]) - 1);
                if (map.get(nums[curL]) == 0) map.remove(nums[curL]);
                curL++;
            }
            while (!pq.isEmpty() && (map.get(pq.peek()[1]) == null || map.get(pq.peek()[1]) != pq.peek()[0])) pq.poll();
            if (pq.isEmpty() || pq.peek()[0] < queries[idx][2]) res[idx] = -1;
            else res[idx] = pq.peek()[1];
        }
        return res;
    }
}