package LC3601_3900;
import java.util.*;
public class LC3886_SumofSortableIntegers {
    /**
     * You are given an integer array nums of length n.
     *
     * An integer k is called sortable if k divides n and you can sort nums in non-decreasing order by sequentially
     * performing the following operations:
     *
     * Partition nums into consecutive subarrays of length k.
     * Cyclically rotate each subarray independently any number of times to the left or to the right.
     * Return an integer denoting the sum of all possible sortable integers k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [3,1,2]
     * Output: 3
     *
     * Input: nums = [7,6,5]
     * Output: 0
     *
     * Input: nums = [5,8]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn + d * n), space = O(n)
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] w = nums.clone();
        Arrays.sort(w);
        List<Integer> q = get(n);

        int res = 0;
        for (int k : q) {
            if (check(nums, w, k)) res += k;
        }
        return res;
    }

    private boolean kmp(int[] s, int[] p) {
        int m = s.length - 1, n = p.length - 1;
        int[] ne = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            while (j > 0 && p[i] != p[j + 1]) j = ne[j];
            if (p[i] == p[j + 1]) j++;
            ne[i] = j;
        }

        for (int i = 1, j = 0; i <= m; i++) {
            while (j > 0 && s[i] != p[j + 1]) j = ne[j];
            if (s[i] == p[j + 1]) j++;
            if (j == n) return true;
        }
        return false;
    }

    private boolean isRotate(int[] nums, int[] w, int start, int k) {
        int[] s = new int[k * 2 + 1];
        int[] p = new int[k + 1];
        for (int i = 0; i < k; i++) {
            s[i + 1] = s[i + k + 1] = nums[start + i];
            p[i + 1] = w[start + i];
        }
        return kmp(s, p);
    }

    private boolean check(int[] nums, int[] w, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i += k) {
            if (!isRotate(nums, w, i, k)) return false;
        }
        return true;
    }

    private List<Integer> get(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n / i; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i != n / i) res.add(n / i);
            }
        }
        return res;
    }

    // S2
    // time = O(nloglogn), space = O(n)
    public int sortableIntegers2(int[] nums) {
        int n = nums.length;
        int[] nxt = new int[n];
        nxt[n - 1] = n;
        int p = n;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) p = i;
            nxt[i] = p;
        }

        int res = 0;
        for (int k = 1; k <= n / k; k++) {
            if (n % k == 0) {
                res += solve(k, nums, nxt);
                if (k < n / k) res += solve(n / k, nums, nxt);
            }
        }
        return res;
    }

    private int solve(int k, int[] nums, int[] nxt) {
        int last = 0;
        for (int r = k - 1; r < nums.length; r += k) {
            int l = r - k + 1;
            int m = nxt[l];
            if (m >= r) {
                // [l, r] 是递增的，最小值为 nums[l]，最大值为 nums[r]
                // 最小值必须 >= 上一段的最大值
                if (nums[l] < last) return 0;
                last = nums[r];
            } else {
                // [l, m] 是第一段，[m+1, r] 是第二段
                // 第二段必须是递增的，且第二段的最小值必须 >= 上一段的最大值，且第二段的最大值必须 <= 第一段的最小值
                if (nxt[m + 1] < r || nums[m + 1] < last || nums[r] > nums[l]) return 0;
                last = nums[m];
            }
        }
        return k;
    }
}
/**
 * 要么整段是递增的
 * 要么有两个递增段，且第一段的最小值，大于第二段的最大值
 * a[m] > a[m+1] => 找一个最近的递减的位置 => 预处理 倒着遍历
 * 只要发现递减了，就把 p 记录下来,保存下来 => 分类讨论
 * 每一段最小值 >= 上一段最大值
 *
 */