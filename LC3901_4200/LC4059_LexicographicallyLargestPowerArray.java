package LC3901_4200;
import java.util.*;
public class LC4059_LexicographicallyLargestPowerArray {
    /**
     * You are given an integer array nums of length n. You may rearrange its elements to form any permutation perm.
     *
     * Define an array power of length 15. For each 0 <= i < 15, power[i] is the largest integer j, where 0 <= j <= n,
     * such that the first j elements of perm all have the (14 - i)th bit set.
     *
     * Bit positions are indexed from right to left, starting with the 0th bit.
     *
     * Return the lexicographically largest possible power array.
     *
     * A permutation is a rearrangement of all the elements of an array.
     *
     * A set bit is a bit whose value in the binary representation of a number is 1.
     *
     * For two arrays of the same length, an array a is lexicographically larger than an array b if, at the first index
     * where they differ, a contains the larger element.
     *
     * Input: nums = [7,5]
     * Output: [0,0,0,0,0,0,0,0,0,0,0,0,2,1,2]
     *
     * Input: nums = [3,1,7]
     * Output: [0,0,0,0,0,0,0,0,0,0,0,0,1,2,3]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 0 <= nums[i] < 2^15
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int[] largestPower(int[] nums) {
        int n = nums.length;
        int[] power = new int[15];
        boolean[] st = new boolean[n];

        int m = (1 << 15) - 1;
        int len = 0;
        while (m > 0) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (!st[i] && (nums[i] & m) == m) {
                    st[i] = true;
                    cnt++;
                }
            }
            len += cnt;
            if (len == n) break;

            int best = -1, bm = -1;
            for (int i = 0; i < n; i++) {
                if (!st[i]) {
                    int nm = m & nums[i];
                    if (nm > bm) {
                        bm = nm;
                        best = i;
                    }
                }
            }

            int pm = m;
            m = bm;
            for (int i = 0; i < 15; i++) {
                if ((pm >> i & 1) == 1 && (m >> i & 1) == 0) power[14 - i] = len;
            }
            st[best] = true;
            len++;
        }

        for (int i = 0; i < 15; i++) {
            if ((m >> i & 1) == 1) power[14 - i] = n;
        }
        return power;
    }

    // S2: Greedy
    // time = O(nlognlogU), space = O(logn)   U: max{nums}
    public int[] largestPower2(int[] nums) {
        int n = nums.length;
        int[] res = new int[15];
        Arrays.sort(nums);

        int mw = 32 - Integer.numberOfLeadingZeros(nums[n - 1]);
        for (int i = mw - 1; i >= 0; i--) {
            int j = n - 1;
            while (j >= 0 && (nums[j] >> i & 1) == 1) j--;
            res[14 - i] = n - 1 - j; // 找最长前缀连续 1

            // [j+1, n-1] 这一位都是 1，其余元素无关紧要，为方便排序，全置为 0
            for (; j >= 0; j--) nums[j] &= ~(1 << i);
            Arrays.sort(nums);
        }
        return res;
    }
}
/**
 * 从上到下看，最长的只有 1 的前缀的长度是多少
 * 保证最高位连续 1 的长度最长
 * 高位的 1 往前排
 * 最高位：从大到小排，没问题
 * 次高位：保证高位不能动，后面的位无关紧要 => 都可以改成 0，额外把后面无关的 0 和 1 都改成 0
 */