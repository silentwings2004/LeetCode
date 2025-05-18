package LC1801_2100;
import java.util.*;
public class LC1964_FindtheLongestValidObstacleCourseatEachPosition {
    /**
     * You want to build some obstacle courses. You are given a 0-indexed integer array obstacles of length n, where
     * obstacles[i] describes the height of the ith obstacle.
     *
     * For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course in obstacles
     * such that:
     *
     * You choose any number of obstacles between 0 and i inclusive.
     * You must include the ith obstacle in the course.
     * You must put the chosen obstacles in the same order as they appear in obstacles.
     * Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
     * Return an array ans of length n, where ans[i] is the length of the longest obstacle course for index i as
     * described above.
     *
     * Input: obstacles = [1,2,3,2]
     * Output: [1,2,3,3]
     *
     * Constraints:
     *
     * n == obstacles.length
     * 1 <= n <= 10^5
     * 1 <= obstacles[i] <= 10^7
     * @param obstacles
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] res = new int[n];
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = obstacles[i];
            int pos = upper_bound(q, x);
            res[i] = pos + 1;
            if (pos == q.size()) q.add(x);
            else q.set(pos, x);
        }
        return res;
    }

    private int upper_bound(List<Integer> q, int t) {
        if (q.size() == 0) return 0;
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) > t) r = mid;
            else l = mid + 1;
        }
        return q.get(r) > t ? r : r + 1;
    }
}
/**
 * ref: LC300 -> strictly increasing => b.s needs to find the 1st pos >= target
 * ref: LC300, LC354, LC1713
 * while for this problem, it can be increasing or equal => b.s. needs to find the 1st pos strictly > target
 * 要仔细体会下两者的差别！
 * 找一个最长的非递减序列！
 * LIS：longest increasing subsequence
 * nums:[x x x x x x x] 9 5  -> 在序列里找到第一个 >= 5的位置
 * arr:  2 4 7 9
 * if (nums[i] > arr.back()) {
 *     arr.push_back(nums[i])
 *     ret.push_back(arr.size());
 * } else {
 *     auto iter = upper_bound(arr.begin(), arr.end());
 *     ret.push_back(iter - arr.begin());
 *     *iter = nums[i];
 * }
 */