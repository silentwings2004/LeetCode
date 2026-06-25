package LC3901_4200;
import java.util.*;
public class LC3960_FrequencyBalanceSubarray {
    /**
     * You are given an integer array nums.
     *
     * Define a frequency balance subarray as follows:
     *
     * If the subarray contains only one element, it is frequency balanced. If the subarray contains at least two
     * elements, then every element with maximum frequency must occur exactly twice as many times as every other
     * distinct value in that subarray.
     * Return an integer denoting the length of the longest frequency balance subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * The frequency of an element x is the number of times it occurs in the array.
     *
     * Input: nums = [1,2,2,1,2,3,3,3]
     * Output: 5
     *
     * Input: nums = [5,5,5,5]
     * Output: 4
     *
     * Input: nums = [1,2,3,4]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^3
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n)
    public int getLength(int[] nums) {
        int n = nums.length, res = 1;
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            HashMap<Integer, Integer> cnt = new HashMap<>();
            for (int j = i; j < n; j++) {
                int x = nums[j];
                int of = map.getOrDefault(x, 0);
                int nf = of + 1;
                map.put(x, nf);
                cnt.put(nf, cnt.getOrDefault(nf, 0) + 1);
                if (of > 0) {
                    int c = cnt.get(of) - 1;
                    if (c == 0) cnt.remove(of);
                    else cnt.put(of, c);
                }
                if (cnt.size() == 1) {
                    int t = 0;
                    for (int v : cnt.values()) t = v;
                    if (t == 1) res = Math.max(res, j - i + 1);
                }
                else if (cnt.size() == 2) {
                    int a = n + 1, b = 0;
                    for (int k : cnt.keySet()) {
                        a = Math.min(a, k);
                        b = Math.max(b, k);
                    }
                    if (b == a * 2) res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
}
/**
 * 两种合法子数组
 * 1. 只有一种元素 [5,5,5,5]
 * 2. 恰好有两种不同的出现次数 [2,1,2,3,3]
 */