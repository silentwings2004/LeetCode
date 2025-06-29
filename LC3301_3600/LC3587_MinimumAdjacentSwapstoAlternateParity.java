package LC3301_3600;
import java.util.*;
public class LC3587_MinimumAdjacentSwapstoAlternateParity {
    /**
     * You are given an array nums of distinct integers.
     *
     * In one operation, you can swap any two adjacent elements in the array.
     *
     * An arrangement of the array is considered valid if the parity of adjacent elements alternates, meaning every pair
     * of neighboring elements consists of one even and one odd number.
     *
     * Return the minimum number of adjacent swaps required to transform nums into any valid arrangement.
     *
     * If it is impossible to rearrange nums such that no two adjacent elements have the same parity, return -1.
     *
     * Input: nums = [2,4,6,5,7]
     * Output: 3
     *
     * Input: nums = [2,4,5,7]
     * Output: 1
     *
     * Input: nums = [1,2,3]
     * Output: 0
     *
     * Input: nums = [4,5,6,8]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * All elements in nums are distinct.
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int minSwaps(int[] nums) {
        int a = helper(nums, 0), b = helper(nums, 1);
        if (a == -1 && b == -1) return -1;
        if (a == -1) return b;
        if (b == -1) return a;
        return Math.min(a, b);
    }

    private int helper(int[] nums, int v) {
        int n = nums.length, cnt = 0;
        TreeSet<Integer> odd = new TreeSet<>();
        TreeSet<Integer> even = new TreeSet<>();
        boolean f = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 1) odd.add(i);
            else even.add(i);
            if (i + 1 < n && nums[i] % 2 == nums[i + 1] % 2) f = false;
        }
        if (f) return 0;
        if (Math.abs(odd.size() - even.size()) > 1) return -1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == v) {
                if (odd.contains(i)) continue;
                Integer hk = odd.higher(i);
                if (hk == null) return -1;
                odd.remove(hk);
                odd.add(i);
                even.remove(i);
                even.add(hk);
                cnt += hk - i;
            } else {
                if (even.contains(i)) continue;
                Integer hk = even.higher(i);
                if (hk == null) return -1;
                even.remove(hk);
                even.add(i);
                odd.remove(i);
                odd.add(hk);
                cnt += hk - i;
            }
        }
        return cnt;
    }

    // S2
    // time = O(n), space = O(n)
    public int minSwaps2(int[] nums) {
        int n = nums.length;
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0) p.add(i);
        }

        int res = Math.min(cal(0, p, n), cal(1, p, n));
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int cal(int start, List<Integer> p, int n) {
        if ((n - start + 1) / 2 != p.size()) return Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < p.size(); i++) {
            res += Math.abs(i * 2 + start - p.get(i));
        }
        return res;
    }
}