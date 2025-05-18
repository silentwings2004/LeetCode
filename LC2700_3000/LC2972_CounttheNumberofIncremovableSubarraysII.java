package LC2700_3000;
import java.util.*;
public class LC2972_CounttheNumberofIncremovableSubarraysII {
    /**
     * You are given a 0-indexed array of positive integers nums.
     *
     * A subarray of nums is called incremovable if nums becomes strictly increasing on removing the subarray.
     * For example, the subarray [3, 4] is an incremovable subarray of [5, 3, 4, 6, 7] because removing this subarray
     * changes the array [5, 3, 4, 6, 7] to [5, 6, 7] which is strictly increasing.
     *
     * Return the total number of incremovable subarrays of nums.
     *
     * Note that an empty array is considered strictly increasing.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,3,4]
     * Output: 10
     *
     * Input: nums = [6,5,7,8]
     * Output: 7
     *
     * Input: nums = [8,7,6,6]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1: 前后缀分解
    // time = O(nlogn), space = O(n)
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        List<Integer> l = new ArrayList<>(), r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) l.add(i);
            else break;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == l.get(l.size() - 1)) break;
            if (i == n - 1 || nums[i] < nums[i + 1]) r.add(i);
            else break;
        }
        Collections.reverse(r);

        int a = l.size(), b = r.size();
        if (b == 0) return (1 + a) * a / 2;
        if (a == 0) return (1 + b) * b / 2;
        long res = a + b + 1;
        for (int x : l) {
            int k = find(nums, x, r);
            res += b - k;
        }
        return res;
    }

    private int find(int[] nums, int t, List<Integer> q) {
        if (q.size() == 0) return 0;
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[q.get(mid)] > nums[t]) r = mid;
            else l = mid + 1;
        }
        return nums[q.get(r)] > nums[t] ? r : r + 1;
    }

    // S2: 前后缀分解
    // time = O(n), space = O(1)
    public long incremovableSubarrayCount2(int[] nums) {
        int n = nums.length, i = 0;
        for (; i + 1 < n; i++) {
            if (nums[i] < nums[i + 1]) continue;
            else break;
        }
        if (i == n - 1) return 1L * (n + 1) * n / 2;
        long res = i + 2; // 包含最长 i + 1 的数组 + 1 个 可以为空的数组
        int j = n - 1;
        while (j == n - 1 || nums[j] < nums[j + 1]) { // 枚举递增上升的后缀里的每一个可以作为子数组右边界的端点
            while (i >= 0 && nums[i] >= nums[j]) i--; // 收缩不符合要求的前缀
            res += i + 2; // 长度为 i + 1 的前缀 + j 所在的元素作为一个后缀 => 一共 i + 2 个元素
            j--;
        }
        return res;
    }

    // S3: TreeMap
    // time = O(nlogn), space = O(n)
    public long incremovableSubarrayCount3(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) map.put(nums[i], i);
            else {
                res += i;
                break;
            }
        }
        if (map.size() == n) return 1L * (n + 1) * n / 2;

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1 || nums[i] < nums[i + 1]) {
                Integer lk = map.lowerKey(nums[i]);
                res += (lk != null ? map.get(lk) + 1 : 0) + 1;
            } else break;
        }
        return res + 1;
    }
}