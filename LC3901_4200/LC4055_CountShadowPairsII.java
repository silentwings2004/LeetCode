package LC3901_4200;
import java.util.*;
public class LC4055_CountShadowPairsII {
    /**
     * You are given an integer array nums of length n.
     *
     * A pair of indices (i, j) is called a shadow pair if all of the following conditions are satisfied:
     *
     * 0 <= i < j < n
     * nums[i] < nums[j]
     * There does not exist an index k such that i < k < j and nums[i] < nums[k] < nums[j].
     * Return the total number of shadow pairs.
     *
     * Input: nums = [3,1,4,2,5]
     * Output: 5
     *
     * Input: nums = [6,7,8,9]
     * Output: 3
     *
     * Constraints:
     *
     * 3 <= n == nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n * (logn)^2), space = O(n)
    public int shadowPairs(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        List<Integer> q = new ArrayList<>();
        for (int x : nums) q.add(Arrays.binarySearch(sorted, x));
        return solve(q, 0, n - 1);
    }

    private int solve(List<Integer> q, int l, int r) {
        int n = q.size();
        if (n <= 1 || l == r) return 0;

        int[] lowSt = new int[n + 1];
        int[] highSt = new int[n + 1];
        int tt1 = 0, tt2 = 0;
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();

        int mid = l + r >> 1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int x = q.get(i);
            if (x <= mid) {
                while (tt1 > 0 && q.get(lowSt[tt1]) < x) tt1--;
                lowSt[++tt1] = i;
                b.add(x);
            } else {
                while (tt2 > 0 && q.get(highSt[tt2]) >= x) tt2--;
                res += tt1;
                if (tt2 > 0) {
                    int p = find(lowSt, 1, tt1, highSt[tt2]);
//                    if (p < 0) p = ~p;
                    res -= p;
                }
                highSt[++tt2] = i;
                c.add(x);
            }
        }
        return res + solve(b, l, mid) + solve(c, mid + 1, r);
    }

    private int find(int[] stk, int l, int r, int t) {
        if (l > r) return 0;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (stk[mid] < t) l = mid;
            else r = mid - 1;
        }
        return stk[r] < t ? r : r - 1;
    }
}
/**
 * 1. 0 <= i < j < n  枚举右维护左  枚举 j，维护 i
 * 2. nums[i] < nums[j] 值域分治，只需考虑 nums[i] 在下部，nums[j] 在上部   [1,50], [51,100]
 * 3. 不能有 k 在 [i+1,j-1] 且 nums[i] < nums[k] < nums[j]，用两个单调栈
 * 分类讨论
 * - nums[k] 在下部。用单调栈梳理出一个递减的序列 low_st，序列中的每个数都没有在下部的 nums[k] 违背要求
 * - nums[k] 在上部。找在 nums[j] 左边最近的小于 nums[j] 的数 (作为 nums[k]), 那么下部的 nums[i] 必须满足 i > k
 * 求出下部的在 low_st 中的下标个数，这些下标还必须满足 i > k，这可以在单调栈中二分
 * 按照值域去做分治
 * CDQ 分治
 */