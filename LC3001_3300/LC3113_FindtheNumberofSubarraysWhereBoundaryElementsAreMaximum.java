package LC3001_3300;
import java.util.*;
public class LC3113_FindtheNumberofSubarraysWhereBoundaryElementsAreMaximum {
    /**
     * You are given an array of positive integers nums.
     *
     * Return the number of subarrays of nums, where the first and the last elements of the subarray are equal to the
     * largest element in the subarray.
     *
     * Input: nums = [1,4,3,3,2]
     *
     * Output: 6
     *
     * Input: nums = [3,3,3]
     *
     * Output: 6
     *
     * Input: nums = [1]
     *
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    HashMap<Integer, List<Integer>> map;
    public long numberOfSubarrays(int[] nums) {
        int n = nums.length;
        int[] stk = new int[n + 10];
        int tt = 0;
        int[] r = new int[n];
        Arrays.fill(r, n);
        for (int i = 0; i < n; i++) {
            while (tt > 0 && nums[stk[tt]] < nums[i]) r[stk[tt--]] = i;
            stk[++tt] = i;
        }

        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            int left = find(nums[i], i), right = find2(nums[i], r[i]);
            res += right - left + 1;
        }
        return res;
    }

    private int find(int x, int t) {
        List<Integer> q = map.get(x);
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= t) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private int find2(int x, int t) {
        List<Integer> q = map.get(x);
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q.get(mid) < t) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    // S2
    // time = O(n), space = O(n)
    public long numberOfSubarrays2(int[] nums) {
        int n = nums.length;
        int[][] stk = new int[n + 1][2];
        int tt = 0;
        long res = n;
        for (int i = 0; i < n; i++) {
            while (tt > 0 && stk[tt][0] < nums[i]) tt--;
            if (tt > 0 && stk[tt][0] == nums[i]) {
                res += stk[tt][1];
                stk[tt][1]++;
            } else stk[++tt] = new int[]{nums[i], 1};
        }
        return res;
    }
}
/**
 * 子数组问题：通用的思考方式
 * 枚举右端点
 * 变形题：第一个 或 最后一个 是这个子数组中的最大值。
 * 考虑容斥原理
 * +计算出第一个是最大值的子数组个数
 * +最后一个是最大值的子数组个数
 * -第一个和最后一个是这个自述中的最大值个数
 * 更复杂：第一个是最大值，最后一个是最小值
 */