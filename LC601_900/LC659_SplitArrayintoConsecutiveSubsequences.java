package LC601_900;
import java.util.*;
public class LC659_SplitArrayintoConsecutiveSubsequences {
    /**
     * You are given an integer array nums that is sorted in non-decreasing order.
     *
     * Determine if it is possible to split nums into one or more subsequences such that both of the following
     * conditions are true:
     *
     * Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous
     * integer).
     * All subsequences have a length of 3 or more.
     * Return true if you can split nums according to the above conditions, or false otherwise.
     *
     * A subsequence of an array is a new array that is formed from the original array by deleting some (can be none)
     * of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a
     * subsequence of [1,2,3,4,5] while [1,3,2] is not).
     *
     * Input: nums = [1,2,3,3,4,5]
     * Output: true
     *
     * Input: nums = [1,2,3,3,4,4,5,5]
     * Output: true
     *
     * Input: nums = [1,2,3,4,4,5]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -1000 <= nums[i] <= 1000
     * nums is sorted in non-decreasing order.
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); // key: ending number, val: how many seqs
        HashMap<Integer, Integer> count = new HashMap<>(); // key: number, val: how many of key unchecked
        for (int x : nums) count.put(x, count.getOrDefault(x, 0) + 1);

        for (int x : nums) {
            if (count.getOrDefault(x, 0) == 0) continue;
            if (map.getOrDefault(x - 1, 0) > 0) {
                map.put(x - 1, map.get(x - 1) - 1);
                map.put(x, map.getOrDefault(x, 0) + 1);
                count.put(x, count.get(x) - 1);
            } else {
                if (count.getOrDefault(x + 1, 0) == 0 || count.getOrDefault(x + 2, 0) == 0) {
                    return false;
                }
                count.put(x, count.get(x) - 1);
                count.put(x + 1, count.get(x + 1) - 1);
                count.put(x + 2, count.get(x + 2) - 1);
                map.put(x + 2, map.getOrDefault(x + 2, 0) + 1);
            }
        }
        return true;
    }

    // S2
    // time = O(n), space = O(n)
    public boolean isPossible2(int[] nums) {
        HashMap<Integer, Integer> m1 = new HashMap<>();
        HashMap<Integer, Integer> m2 = new HashMap<>();
        for (int x : nums) m1.put(x, m1.getOrDefault(x, 0) + 1);
        for (int x : nums) {
            if (!m1.containsKey(x)) continue;
            if (m2.containsKey(x - 1)) {
                m2.put(x - 1, m2.get(x - 1) - 1);
                if (m2.get(x - 1) == 0) m2.remove(x - 1);
                m2.put(x, m2.getOrDefault(x, 0) + 1);
                m1.put(x, m1.get(x) - 1);
                if (m1.get(x) == 0) m1.remove(x);
            } else if (m1.containsKey(x + 1) && m1.containsKey(x + 2)) {
                m2.put(x + 2, m2.getOrDefault(x + 2, 0) + 1);
                m1.put(x, m1.get(x) - 1);
                m1.put(x + 1, m1.get(x + 1) - 1);
                m1.put(x + 2, m1.get(x + 2) - 1);
                if (m1.get(x) == 0) m1.remove(x);
                if (m1.get(x + 1) == 0) m1.remove(x + 1);
                if (m1.get(x + 2) == 0) m1.remove(x + 2);
            } else return false;
        }
        return true;
    }
}
/**
 * 贪心法
 * 3 x 4 x x 5 x 6  x x 7 x 8
 * x x x x x x x 6 7 8  -> 预先把后面的7和8看一下，要单干的话必须后面要7和8
 * x x 4 x 5 x x 6 -> 只抱一个已经长度至少为3的大腿
 * 如果4拿出来单干，5和6势必会找到，既然4，5，6都check过了，这时候再来1个6是不可能插在4和5后面的
 */