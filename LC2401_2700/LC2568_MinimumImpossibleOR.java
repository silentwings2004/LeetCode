package LC2401_2700;
import java.util.*;
public class LC2568_MinimumImpossibleOR {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * We say that an integer x is expressible from nums if there exist some integers 0 <= index1 < index2 < ... <
     * indexk < nums.length for which nums[index1] | nums[index2] | ... | nums[indexk] = x. In other words, an integer
     * is expressible if it can be written as the bitwise OR of some subsequence of nums.
     *
     * Return the minimum positive non-zero integer that is not expressible from nums.
     *
     * Input: nums = [2,1]
     * Output: 4
     *
     * Input: nums = [5,3,2]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minImpossibleOR(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (!set.contains(1 << i)) {
                res = 1 << i;
                break;
            }
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public int minImpossibleOR2(int[] nums) {
        Arrays.sort(nums);
        int mx = 0;
        for (int x : nums) {
            if (x > mx + 1) return mx + 1;
            mx |= x;
        }
        return mx + 1;
    }
}
/**
 * 我们将数组从小到大排列。
 * 假设前i-1个元素里，我们能构造连续的自然数[1,mx]，那么如果nums[i]<=mx+1，那么意味着前i个元素里，我们可以任意构造[1,mx+num[i]]里的元素。
 * 反之，如果nums[i]>mx+1，那么我们如论如何都无法构造出mx+1来。
 * 同理，本题里的或运算和加法运算有着相同的性质：越搞数越大。
 * 假设前i-1个元素里，我们能构造连续的自然数[1,mx]，那么如果nums[i]<=mx+1，那么意味着前i个元素里，我们可以任意构造[1,mx|num[i]]里的元素。
 * 反之，如果nums[i]>mx+1，那么将nums[i]与任何[1,mx]里面的元素进行操作，得到的都会比nums[i]还大，我们如论也无法构造出mx+1来。
 */