package LC3301_3600;
import java.util.*;
public class LC3542_MinimumOperationstoConvertAllElementstoZero {
    /**
     * You are given an array nums of size n, consisting of non-negative integers. Your task is to apply some (possibly
     * zero) operations on the array so that all elements become 0.
     *
     * In one operation, you can select a subarray [i, j] (where 0 <= i <= j < n) and set all occurrences of the minimum
     * non-negative integer in that subarray to 0.
     *
     * Return the minimum number of operations required to make all elements in the array 0.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [0,2]
     * Output: 1
     *
     * Input: nums = [3,1,2,1]
     * Output: 3
     *
     * Input: nums = [1,2,1,2,1,2]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1: monotonic stack
    // time = O(n), space = O(n)
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] stk = new int[n + 1];
        int tt = 0, res = 0;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();
            while (tt > 0 && stk[tt] > nums[i]) set.add(stk[tt--]);
            res += set.size();
            stk[++tt] = nums[i];
        }
        for (int i = tt; i > 0; i--) {
            if (stk[i] == 0) break;
            int j = i - 1;
            while (j > 0 && stk[j] == stk[i]) j--;
            res++;
            i = j + 1;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int minOperations2(int[] nums) {
        int res = 0, tt = -1;
        for (int x : nums) {
            while (tt >= 0 && x < nums[tt]) {
                tt--;
                res++;
            }
            if (tt < 0 || x > nums[tt]) nums[++tt] = x;
        }
        return res + tt + (nums[0] > 0 ? 1 : 0);
    }
}
/**
 * 回顾示例 3 nums=[1,2,1,2,1,2] 的操作过程
 * 首先，只需要一次操作（选择整个数组），就可以把所有的最小值 1 都变成 0。现在数组是 [0,2,0,2,0,2]
 * 这些被 0 分割开的 2，无法合在一起操作（因为子数组会包含 0，导致 2 无法变成 0），只能一个一个操作
 * 先通过一次操作，把 nums 的最小值都变成 0（如果最小值已经是 0 则跳过这步）。
 * 此时 nums 被这些 0 划分成了若干段，后续操作只能在每段内部，不能跨段操作（否则子数组会包含 0）。
 * 每一段是规模更小的子问题，可以用第一步的方法解决。这样我们可以写一个递归去处理。递归边界：如果操作后全为 0，直接返回
 * 找最小值可以用 ST 表或者线段树，但这种做法很麻烦。
 * 单调栈
 * 从左往右遍历数组，只在「必须要操作」的时候，才把答案加一
 * 遍历数组的同时，把遍历过的元素用栈记录：
 * 如果当前元素比栈顶大（或者栈为空），那么直接入栈。
 * 如果当前元素比栈顶小，那么对于栈顶来说，左边（栈顶倒数第二个数）比栈顶小（原因后面解释），右边（当前元素）也比栈顶小，所以栈顶必须操作一次。
 * 然后弹出栈顶。
 * 如果当前元素等于栈顶，可以在同一次操作中把当前元素与栈顶都变成 0，所以无需入栈。注意这保证了栈中没有重复元素。
 * 如果当前元素比栈顶小，就弹出栈顶，我们会得到一个底小顶大的单调栈，这就保证了「对于栈顶来说，左边（栈顶倒数第二个数）比栈顶小」。
 * 遍历结束后，因为栈是严格递增的，所以栈中每个非零数字都需要操作一次
 */