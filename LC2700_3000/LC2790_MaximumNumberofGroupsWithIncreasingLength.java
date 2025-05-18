package LC2700_3000;
import java.util.*;
public class LC2790_MaximumNumberofGroupsWithIncreasingLength {
    /**
     * You are given a 0-indexed array usageLimits of length n.
     *
     * Your task is to create groups using numbers from 0 to n - 1, ensuring that each number, i, is used no more than
     * usageLimits[i] times in total across all groups. You must also satisfy the following conditions:
     *
     * Each group must consist of distinct numbers, meaning that no duplicate numbers are allowed within a single group.
     * Each group (except the first one) must have a length strictly greater than the previous group.
     * Return an integer denoting the maximum number of groups you can create while satisfying these conditions.
     *
     * Input: usageLimits = [1,2,5]
     * Output: 3
     *
     * Input: usageLimits = [2,1,2]
     * Output: 2
     *
     * Input: usageLimits = [1,1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= usageLimits.length <= 10^5
     * 1 <= usageLimits[i] <= 10^9
     * @param usageLimits
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        Collections.sort(usageLimits, (o1, o2) -> o2 - o1);
        int n = usageLimits.size();
        int l = 1, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(usageLimits, mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(List<Integer> w, int mid) {
        int t = 0, n = w.size();
        for (int i = 0; i < n; i++) {
            if (w.get(i) >= Math.max(0, mid - i) + t) t = 0;
            else t = Math.max(0, mid - i) + t - w.get(i);
        }
        return t == 0;
    }
}