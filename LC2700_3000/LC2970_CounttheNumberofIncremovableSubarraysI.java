package LC2700_3000;

public class LC2970_CounttheNumberofIncremovableSubarraysI {
    /**
     * You are given a 0-indexed array of positive integers nums.
     *
     * A subarray of nums is called incremovable if nums becomes strictly increasing on removing the subarray. For
     * example, the subarray [3, 4] is an incremovable subarray of [5, 3, 4, 6, 7] because removing this subarray
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
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // S1: brute-force
    // time = O(n^3), space = O(1)
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length, res = 0;
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                if (check(nums, i, i + len - 1)) res++;
            }
        }
        return res;
    }

    private boolean check(int[] nums, int l, int r) {
        int n = nums.length;
        for (int i = 0, last = -1; i < n; i++) {
            if (i >= l && i <= r) continue;
            if (nums[i] > last) last = nums[i];
            else return false;
        }
        return true;
    }

    // S2
    // time = O(n), space = O(1)
    public int incremovableSubarrayCount2(int[] nums) {
        int n = nums.length, i = 0;
        for (; i + 1 < n; i++) {
            if (nums[i] < nums[i + 1]) continue;
            else break;
        }
        if (i == n - 1) return (n + 1) * n / 2;
        int res = i + 2; // 包含最长 i + 1 的数组 + 1 个 可以为空的数组
        int j = n - 1;
        while (j == n - 1 || nums[j] < nums[j + 1]) { // 枚举递增上升的后缀里的每一个可以作为子数组右边界的端点
            while (i >= 0 && nums[i] >= nums[j]) i--; // 收缩不符合要求的前缀
            res += i + 2; // 长度为 i + 1 的前缀 + j 所在的元素作为一个后缀 => 一共 i + 2 个元素
            j--;
        }
        return res;
    }
}