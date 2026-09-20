package LC3901_4200;
import java.util.*;
public class LC4053_MinimumOperationstoMakeEveryElementPalindromic {
    /**
     * You are given an integer array nums.
     *
     * In one operation, you may choose an index i and either increment or decrement nums[i] by 2.
     *
     * Return the minimum number of operations required to make every element in nums a positive palindromic integer.
     * Different elements may be changed into different palindromic integers.
     *
     * An integer is a palindrome if it reads the same forward and backward. For example, 121 is a palindrome while 123
     * is not.
     *
     * Input: nums = [10,12,14,16]
     * Output: 9
     *
     * Input: nums = [9,10,11,10]
     * Output: 2
     *
     * Input: nums = [125]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(p + nlogP), space = O(P), p < 1.1 * 10^5 是不超过九位的正回文数个数
    static final int MX = 2000000002;
    static final List<Integer>[] pal = new List[2];
    static boolean initialized = false;
    public LC4053_MinimumOperationstoMakeEveryElementPalindromic() {
        if (initialized) return;
        initialized = true;
        for (int i = 0; i < 2; i++) pal[i] = new ArrayList<>();
        pal[0].add(0);
        pal[1].add(0);

        for (int base = 1;; base *= 10) {
            for (int i = base; i < base * 10; i++) {
                int x = i;
                for (int t = i / 10; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (x > MX) return;
                pal[x % 2].add(x);
            }

            for (int i = base; i < base * 10; i++) {
                int x = i;
                for (int t = i; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (x > MX) return;
                pal[x % 2].add(x);
            }
        }
    }

    public long minOperations(int[] nums) {
        long res = 0;
        for (int x : nums) {
            List<Integer> p = pal[x % 2];
            int i = lowerBound(p, x);
            res += Math.min(p.get(i) - x, x - p.get(i - 1));
        }
        return res / 2;
    }

    private int lowerBound(List<Integer> q, int t) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= t) r = mid;
            else l = mid + 1;
        }
        return q.get(r) >= t ? r : r + 1;
    }
}
/**
 * ref LC564
 */