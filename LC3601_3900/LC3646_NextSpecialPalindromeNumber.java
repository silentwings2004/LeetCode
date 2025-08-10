package LC3601_3900;
import java.util.*;
public class LC3646_NextSpecialPalindromeNumber {
    /**
     * You are given an integer n.
     *
     * A number is called special if:
     *
     * It is a palindrome.
     * Every digit k in the number appears exactly k times.
     * Return the smallest special number strictly greater than n.
     *
     * An integer is a palindrome if it reads the same forward and backward. For example, 121 is a palindrome, while 123
     * is not.
     *
     * Input: n = 2
     * Output: 22
     *
     * Input: n = 33
     * Output: 212
     *
     * Constraints:
     *
     * 0 <= n <= 10^15
     * @param n
     * @return
     */
    // time = O(2^9 * 9! * 9), space = O(2^9 + 9)
    int[] sz;
    public long specialPalindrome(long n) {
        if (n == 0) return 1;
        sz = new int[1 << 9];
        init();
        int m = String.valueOf(n).length();
        long res = Long.MAX_VALUE;
        for (int mask = 1; mask < 1 << 9; mask++) {
            int len = sz[mask];
            if (len != m && len != m + 1) continue;
            StringBuilder sb = new StringBuilder();
            int odd = 0;
            for (int x = 1; x <= 9; x++) {
                if (((mask >> (x - 1)) & 1) != 0) {
                    for (int i = 0; i < x / 2; i++) sb.append(x);
                    if (x % 2 == 1) odd = x;
                }
            }
            if (sb.length() == 0) continue;
            char[] s = sb.toString().toCharArray();
            Arrays.sort(s);
            do {
                long x = 0;
                for (char c : s) x = x * 10 + c - '0';
                long v = x;
                if (odd != 0) x = x * 10 + odd;
                while (v > 0){
                    long d = v % 10;
                    v /= 10;
                    x = x * 10 + d;
                }
                if (x > res) break;
                if (x > n) {
                    res = x;
                    break;
                }
            } while (nextPermutation(s));
        }
        return res;
    }

    private void init() {
        for (int mask = 1; mask < 1 << 9; mask++) {
            int t = mask & 0x155;
            if ((t & (t - 1)) != 0) continue; // 有超过1个奇数
            for (int i = 0; i < 9; i++) {
                if ((mask >> i & 1) != 0) sz[mask] += i + 1; // 计算该子集里总共多少个数
            }
        }
    }

    public boolean nextPermutation(char[] nums) {
        if (nums.length == 0) return false;
        int n = nums.length, i = n - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) i--;
        if (i == 0) return false;
        else {
            int j = n - 1;
            while (j >= i && nums[j] <= nums[i - 1]) j--;
            swap(nums, j, i - 1);
            reverse(nums, i, n - 1);
        }
        return true;
    }

    private void reverse(char[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }

    private void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
/**
 * 1. 回文数不能有0，只能1~9
 * 2. 如果有2个奇数，就不对称了，只能放正中间，至多一个
 * [2,4,6,8] + 1 odd => 2^4 * 6 = 96 - 1 (empty set) = 95
 * 全选，1个2，2个4，3个6，4个8，4个9 => 当数字长度 = 16 一定能找到一个方案
 * O(95 * 8! * 8) => 计算量不大
 * 暴力枚举
 */