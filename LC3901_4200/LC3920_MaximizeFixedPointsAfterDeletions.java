package LC3901_4200;
import java.util.*;
public class LC3920_MaximizeFixedPointsAfterDeletions {
    /**
     * You are given an integer array nums.
     *
     * A position i is called a fixed point if nums[i] == i.
     *
     * You are allowed to delete any number of elements (including zero) from the array. After each deletion, the
     * remaining elements shift left, and indices are reassigned starting from 0.
     *
     * Return an integer denoting the maximum number of fixed points that can be achieved after performing any number
     * of deletions.
     *
     * Input: nums = [0,2,1]
     * Output: 2
     *
     * Input: nums = [3,1,2]
     * Output: 2
     *
     * Input: nums = [1,0,1,2]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxFixedPoints(int[] nums) {
        List<int[]> q = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (i >= x) q.add(new int[]{x, i - x});
        }
        return maxEnvelops(q);
    }

    private int maxEnvelops(List<int[]> envelops) {
        Collections.sort(envelops, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int n = envelops.size();
        List<Integer> q = new ArrayList<>();
        for (int[] e : envelops) {
            int idx = find(q, e[1]);
            if (idx == q.size()) q.add(e[1]);
            else q.set(idx, e[1]);
        }
        return q.size();
    }

    private int find(List<Integer> q, int t) {
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
 * nums[i] 要想成为固定点，前面必须删除 i - nums[i] 个数
 * i - nums[i] < 0 => 不可能成为固定点
 * 选出一个最长的子序列
 * 满足：
 * 1. nums[i] 是严格递增的
 * 2. i - nums[i] 是递增的 (允许相邻相等)
 */