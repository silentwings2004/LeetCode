package LC3601_3900;
import java.util.*;
public class LC3605_MinimumStabilityFactorofArray {
    /**
     * You are given an integer array nums and an integer maxC.
     *
     * A subarray is called stable if the highest common factor (HCF) of all its elements is greater than or equal to 2.
     *
     * The stability factor of an array is defined as the length of its longest stable subarray.
     *
     * You may modify at most maxC elements of the array to any integer.
     *
     * Return the minimum possible stability factor of the array after at most maxC modifications. If no stable subarray
     * remains, return 0.
     *
     * Note:
     *
     * A subarray is a contiguous sequence of elements within an array.
     * The highest common factor (HCF) of an array is the largest integer that evenly divides all the array elements.
     * A subarray of length 1 is stable if its only element is greater than or equal to 2, since HCF([x]) = x.
     *
     * Input: nums = [3,5,10], maxC = 1
     * Output: 1
     *
     * Input: nums = [2,6,8], maxC = 2
     * Output: 1
     *
     * Input: nums = [2,4,9,6], maxC = 1
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= maxC <= n
     * @param nums
     * @param maxC
     * @return
     */
    // S1: ST table
    // time = O(nlogn), space = O(n)
    int[][] f;
    public int minStable(int[] nums, int maxC) {
        int n = nums.length, m = 32 - Integer.numberOfLeadingZeros(n);
        f = new int[n + 1][m];
        init(nums); // 预处理 ST 表

        int l = 0, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, maxC, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    // 检查能否通过至多 maxC 次修改，使得最长的稳定子数组长度不超过 mid
    private boolean check(int[] nums, int maxC, int mid) {
        if (mid == nums.length) return true;
        int cnt = 0;
        // 枚举每个长度为 mid + 1 的子数组，它们的 gcd 都需要是 1
        for (int i = mid; i < nums.length;) {
            int t = query(i - mid, i);
            if (t > 1) {
                cnt++; // 如果 gcd 不是 1，把子数组最右边的元素改成 1，然后跳过所有包含这个元素的子数组
                i += mid + 1;
            } else i++;
        }
        return cnt <= maxC;
    }

    private void init(int[] nums) {
        int n = nums.length, m = 32 - Integer.numberOfLeadingZeros(n);
        for (int j = 0; j < m; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                if (j == 0) f[i][j] = nums[i - 1];
                else f[i][j] = gcd(f[i][j - 1], f[i + (1 << j - 1)][j - 1]);
            }
        }
    }

    private int query(int l, int r) { // 求 nums[l..r] 的 gcd
        l++;
        r++;
        int len = r - l + 1;
        int k = (int)(Math.log(len) / Math.log(2));
        return gcd(f[l][k], f[r - (1 << k) + 1][k]);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2: log trick
    // time = O(nlogU * logM), space = O(logU)
    public int minStable2(int[] nums, int maxC) {
        int n = nums.length;
        int l = 0, r = n / (maxC + 1);
        while (l < r) {
            int mid = l + r >> 1;
            if (check2(nums, maxC, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check2(int[] nums, int maxC, int upper) {
        List<int[]> intervals = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int[] interval : intervals) interval[0] = gcd(interval[0], x);
            intervals.add(new int[]{x, i});
            int idx = 1;
            for (int j = 1; j < intervals.size(); j++) {
                if (intervals.get(j)[0] != intervals.get(j - 1)[0]) {
                    intervals.set(idx++, intervals.get(j));
                }
            }
            intervals.subList(idx, intervals.size()).clear();
            if (intervals.get(0)[0] == 1) intervals.remove(0);
            if (!intervals.isEmpty() && i - intervals.get(0)[1] + 1 > upper) {
                if (maxC == 0) return false;
                maxC--;
                intervals.clear();
            }
        }
        return true;
    }

    // S2.5: log trick (optimized)
    // time = O(nlogU + nlogM), space = O(n)
    public int minStable3(int[] nums, int maxC) {
        int n = nums.length;
        int[] leftMin = new int[n];
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{1, 0}); // 哨兵
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int[] interval : intervals) interval[0] = gcd(interval[0], x);
            intervals.add(new int[]{x, i});
            int idx = 1;
            for (int j = 1; j < intervals.size(); j++) {
                if (intervals.get(j)[0] != intervals.get(j - 1)[0]) {
                    intervals.set(idx++, intervals.get(j));
                }
            }
            intervals.subList(idx, intervals.size()).clear();
            leftMin[i] = intervals.size() > 1 ? intervals.get(1)[1] : n;
        }

        int l = 0, r = n / (maxC + 1);
        while (l < r) {
            int mid = l + r >> 1;
            if (check3(leftMin, maxC, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check3(int[] leftMin, int maxC, int upper) {
        int i = upper, n = leftMin.length;
        while (i < n) {
            if (i - leftMin[i] + 1 > upper) {
                if (maxC == 0) return false;
                maxC--;
                i += upper + 1;
            } else i++;
        }
        return true;
    }
}
/**
 * 子数组 GCD logTrick / 栈 + 滑动窗口  ST 表 / 线段树
 * 最小化最大值 => 二分答案  二分上界
 * 线性 子数组 GCD => GCD[L,i] >= 2, minL => i - L + 1 > upper
 *
 * 如果随着运算的数越多，运算的结果在减半 (或者说倍增)
 * 运算的结果只有 O(logU) 种
 * GCD AND OR
 * LCM *
 */