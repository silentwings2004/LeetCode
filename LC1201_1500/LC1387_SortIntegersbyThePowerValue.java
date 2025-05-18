package LC1201_1500;
import java.util.*;
public class LC1387_SortIntegersbyThePowerValue {
    /**
     * The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
     *
     * if x is even then x = x / 2
     * if x is odd then x = 3 * x + 1
     * For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1
     * (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).
     *
     * Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value
     * in ascending order, if two or more integers have the same power value sort them by ascending order.
     *
     * Return the kth integer in the range [lo, hi] sorted by the power value.
     *
     * Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps
     * and that the power of x is will fit in a 32-bit signed integer.
     *
     * Input: lo = 12, hi = 15, k = 2
     * Output: 13
     *
     * Input: lo = 7, hi = 11, k = 4
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= lo <= hi <= 1000
     * 1 <= k <= hi - lo + 1
     * @param lo
     * @param hi
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int getKth(int lo, int hi, int k) {
        List<int[]> nums = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            int x = helper(i);
            nums.add(new int[]{x, i});
        }
        Collections.sort(nums, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        return nums.get(k - 1)[1];
    }

    private int helper(int x) {
        int step = 0;
        while (x != 1) {
            x = x % 2 == 0 ? x / 2 : 3 * x + 1;
            step++;
        }
        return step;
    }
}
/**
 * 冰雹猜想
 */
