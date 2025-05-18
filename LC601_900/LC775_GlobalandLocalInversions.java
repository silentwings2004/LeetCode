package LC601_900;
import java.util.*;
public class  LC775_GlobalandLocalInversions {
    /**
     * You are given an integer array nums of length n which represents a permutation of all the integers in the
     * range [0, n - 1].
     *
     * The number of global inversions is the number of the different pairs (i, j) where:
     *
     * 0 <= i < j < n
     * nums[i] > nums[j]
     * The number of local inversions is the number of indices i where:
     *
     * 0 <= i < n - 1
     * nums[i] > nums[i + 1]
     * Return true if the number of global inversions is equal to the number of local inversions.
     *
     * Input: nums = [1,0,2]
     * Output: true
     *
     * Input: nums = [1,2,0]
     * Output: false
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * 0 <= nums[i] < n
     * All the integers of nums are unique.
     * nums is a permutation of all the numbers in the range [0, n - 1].
     * @param A
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean isIdealPermutation(int[] A) {
        // corner case
        if (A == null || A.length == 0) return true;

        int curMax = A[0];
        for (int i = 0; i < A.length; i++) {
            if (i >= 2) curMax = Math.max(curMax, A[i - 2]);
            if (i >= 2 && A[i] < curMax) return false;
        }
        return true;
    }

    // S2: Math
    // time = O(n), space = O(1)
    public boolean isIdealPermutation2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (Math.abs(nums[i] - i) > 1) return false;
        }
        return true;
    }

    // S3: BIT
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    int[] tr;
    public boolean isIdealPermutation3(int[] nums) {
        tr = new int[N];
        int n = nums.length, s1 = 0, s2 = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (nums[i] > nums[i + 1]) s1++;
        }

        for (int i = 0; i < n; i++) {
            s2 += i - sum(nums[i] + 1); // BIT 必须是1-index，所以可能为0的时候要+1
            add(nums[i] + 1, 1);
        }
        return s1 == s2;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i < N; i += lowbit(i)) tr[i] += c;
    }

    private long sum(int x) {
        long res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}
/**
 * curMax[i - 1] < A[i + 1]
 * curMin[i + 2] > A[i]
 * one pass
 * 本质上就是判断逆序对是否相邻 => 0 只能放在第0位和第1位
 * 0 在0位 -> 递归做
 * 0 在1位 -> 前2位必为10
 * n-2子问题
 * 必要条件：|ai - i| <= 1
 * 是否充分？
 *
 * 核心：一个局部倒置肯定也是一个全局倒置!
 * 要使得两者相等，那必须不能有额外的全局倒置产生，即不能有非相邻的倒置出现，也就是说对于两个数，不能出现
 * j - i >= 2 && A[j] < A[i]
 *
 * 那么我们的思路就变成，从左到右扫一遍，然后一直maintain一个[0, I - 2]之间的一个curMax，那么只要出现curMax > A[i]，一定就会出现额外的
 * 全局倒置，因为此时i和curMax之间一定 >= 2，也就是肯定不相邻。
 */
