package LC2401_2700;
import java.util.*;
public class LC2411_SmallestSubarraysWithMaximumBitwiseOR {
    /**
     * You are given a 0-indexed array nums of length n, consisting of non-negative integers. For each index i from 0
     * to n - 1, you must determine the size of the minimum sized non-empty subarray of nums starting at i (inclusive)
     * that has the maximum possible bitwise OR.
     *
     * In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You need to find the smallest subarray
     * starting at i, such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
     * The bitwise OR of an array is the bitwise OR of all the numbers in it.
     *
     * Return an integer array answer of size n where answer[i] is the length of the minimum sized subarray starting at
     * i with maximum bitwise OR.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,0,2,1,3]
     * Output: [3,3,2,2,1]
     *
     * Input: nums = [1,2]
     * Output: [2,1]
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] p = new int[30], res = new int[n];
        Arrays.fill(p, n);

        for (int i = n - 1; i >= 0; i--) {
            int t = i;
            for (int j = 0; j < 30; j++) {
                if ((nums[i] >> j & 1) == 1) p[j] = i; // 这一位1可以通过nums[i]自己提供
                else if (p[j] < n) t = Math.max(t, p[j]); // 这一位上之前有数字提供给了1，所以至少要延伸到那个数为止
            }
            res[i] = t - i + 1; // 双闭区间
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] smallestSubarrays2(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[32], res = new int[n];

        for (int i = n - 1, j = n - 1; i >= 0; i--) {
            for (int k = 0; k < 32; k++) cnt[k] += nums[i] >> k & 1;
            while (j > i && ok2remove(nums[j], cnt)) {
                for (int k = 0; k < 32; k++) cnt[k] -= nums[j] >> k & 1;
                j--;
            }
            res[i] = j - i + 1;
        }
        return res;
    }

    private boolean ok2remove(int num, int[] cnt) {
        for (int k = 0; k < 32; k++) {
            if (cnt[k] == 1 && (num >> k & 1) == 1) return false;
        }
        return true;
    }

    // S3
    // time = O(nlogn), space = O(n)
    public int[] smallestSubarrays3(int[] nums) {
        int n = nums.length;
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = 0; j < 31; j++) {
                if ((x >> j & 1) == 1) {
                    map.putIfAbsent(j, new TreeSet<>());
                    map.get(j).add(i);
                }
            }
        }

        int[] res = new int[n];
        res[n - 1] = 1;
        int s = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            s |= nums[i];
            int x = s - nums[i];
            if (x == 0) {
                res[i] = 1;
                continue;
            }
            int max = i;
            for (int j = 0; j < 31; j++) {
                if ((x >> j & 1) == 1) {
                    Integer hk = map.get(j).higher(i);
                    max = Math.max(max, hk);
                }
            }
            res[i] = max - i + 1;
        }
        return res;
    }
}
/**
 * 求每一位最小在什么位置
 * 在满足每一位下界的情况下，求所有下界的最大值
 * 只需要记录每一位最靠前的位置即可
 *
 * x x x [x x x x x] [n-1]
 *    i-1 i            j
 * bit x:     4        1
 * OrSum[i:n-1] = OrSum[i:j]
 * 维护1个32位计数器
 * count[32]
 * for (int k = 0; k < 32; k++) {
 *     if (count[k] == 1 && nums[j] >> k & 1)
 *          return false;
 * }
 * return true;
 * 对于i-1，完全可以继承i所得到的j，j是单调向左移动的 => 双指针 O(n)
 *
 */