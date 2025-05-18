package LC3301_3600;
import java.util.*;
public class LC3357_MinimizetheMaximumAdjacentElementDifference {
    /**
     * You are given an array of integers nums. Some values in nums are missing and are denoted by -1.
     *
     * You can choose a pair of positive integers (x, y) exactly once and replace each missing element with either x
     * r y.
     *
     * You need to minimize the maximum absolute difference between adjacent elements of nums after replacements.
     *
     * Return the minimum possible difference.
     *
     * Input: nums = [1,2,-1,10,8]
     * Output: 4
     *
     * Input: nums = [-1,-1,-1]
     * Output: 0
     *
     * Input: nums = [-1,10,-1,8]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * nums[i] is either -1 or in the range [1, 10^9].
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int inf = (int)1e9;
    public int minDifference(int[] nums) {
        int n = nums.length, l = 0, r = inf;
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0 && nums[i - 1] > 0) l = Math.max(l, Math.abs(nums[i] - nums[i - 1]));
        }
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int mid) {
        int n = nums.length;
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != -1) continue;
            if (i > 0 && nums[i - 1] > 0 && i + 1 < n && nums[i + 1] > 0) {
                int l = Math.max(nums[i - 1] - mid, nums[i + 1] - mid);
                int r = Math.min(nums[i - 1] + mid, nums[i + 1] + mid);
                q.add(new int[]{l, r});
            } else if (i > 0 && nums[i - 1] > 0) q.add(new int[]{nums[i - 1] - mid, nums[i - 1] + mid});
            else if (i + 1 < n && nums[i + 1] > 0) q.add(new int[]{nums[i + 1] - mid, nums[i + 1] + mid});
        }

        if (q.size() == 0) return true;
        Collections.sort(q, (o1, o2) -> o1[1] - o2[1]);
        int x = q.get(0)[1], y = -inf;
        for (int[] v : q) y = Math.max(y, v[0]);
        for (int[] v : q) {
            if ((x < v[0] || x > v[1]) && (y < v[0] || y > v[1])) return false;
        }

        for (int i = 0, j = -1; i < n; i++) {
            if (nums[i] != -1) {
                if (!isValid(nums, i, j, x, y, mid)) return false;
                j = i;
            }
        }
        return true;
    }

    private boolean isValid(int[] nums, int i, int j, int x, int y, int mid) {
        if (j == -1 || j + 1 == i) return true;
        if (Math.abs(nums[j] - x) <= mid && Math.abs(nums[i] - x) <= mid || Math.abs(nums[j] - y) <= mid && Math.abs(nums[i] - y) <= mid) {
            return true;
        }
        if (y - x > mid) return false;
        return true;
    }
}