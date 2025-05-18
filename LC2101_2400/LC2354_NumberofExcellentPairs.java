package LC2101_2400;
import java.util.*;
public class LC2354_NumberofExcellentPairs {
    /**
     * You are given a 0-indexed positive integer array nums and a positive integer k.
     *
     * A pair of numbers (num1, num2) is called excellent if the following conditions are satisfied:
     *
     * Both the numbers num1 and num2 exist in the array nums.
     * The sum of the number of set bits in num1 OR num2 and num1 AND num2 is greater than or equal to k, where OR is
     * the bitwise OR operation and AND is the bitwise AND operation.
     * Return the number of distinct excellent pairs.
     *
     * Two pairs (a, b) and (c, d) are considered distinct if either a != c or b != d. For example, (1, 2) and (2, 1)
     * are distinct.
     *
     * Note that a pair (num1, num2) such that num1 == num2 can also be excellent if you have at least one occurrence
     * of num1 in the array.
     *
     * Input: nums = [1,2,3,1], k = 3
     * Output: 5
     *
     * Input: nums = [5,1,1], k = 10
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 60
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long countExcellentPairs(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int[] cnt = new int[30];
        for (int x : set) {
            int s = Integer.bitCount(x);
            cnt[s]++;
        }

        long res = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (i + j >= k) res += (long) cnt[i] * cnt[j];
            }
        }
        return res;
    }

    // S2: Two Pointers
    // time = O(nlogn), space = O(n)
    public long countExcellentPairs2(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int n = set.size(), idx = 0;
        int[] arr = new int[n];
        for (int x : set) arr[idx++] = Integer.bitCount(x);
        Arrays.sort(arr);

        long res = 0;
        for (int i = 0, j = n - 1; i < n; i++) {
            while (j >= 0 && arr[i] + arr[j] >= k) j--; // 注意：这里是 j >= 0
            if (j >= i) res += n - j - 1;
            else res += n - i - 1;
        }
        res *= 2;

        for (int x : arr) {
            if (x * 2 >= k) res++;
        }
        return res;
    }
}
/**
 * & | 每一位都是独立的
 * 如果2个都是1，统计2次
 * 如果1和0，统计1次
 * 如果0和1，统计1次
 * 如果0和0，统计0次
 * & + | => 两个数里所有1的个数总和
 * 统计下所有数里1的个数 C1,C2,...,Ck
 * 二分 / 双指针
 * 开一个30的数组，cnt[i]
 */