package LC2700_3000;
import java.util.*;
public class LC2766_RelocateMarbles {
    /**
     * You are given a 0-indexed integer array nums representing the initial positions of some marbles. You are also
     * given two 0-indexed integer arrays moveFrom and moveTo of equal length.
     *
     * Throughout moveFrom.length steps, you will change the positions of the marbles. On the ith step, you will move
     * all marbles at position moveFrom[i] to position moveTo[i].
     *
     * After completing all the steps, return the sorted list of occupied positions.
     *
     * Notes:
     *
     * We call a position occupied if there is at least one marble in that position.
     * There may be multiple marbles in a single position.
     *
     * Input: nums = [1,6,7,8], moveFrom = [1,7,2], moveTo = [2,9,5]
     * Output: [5,6,8,9]
     *
     * Input: nums = [1,1,3,3], moveFrom = [1,3], moveTo = [2,2]
     * Output: [2]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= moveFrom.length <= 10^5
     * moveFrom.length == moveTo.length
     * 1 <= nums[i], moveFrom[i], moveTo[i] <= 10^9
     * The test cases are generated such that there is at least a marble in moveFrom[i] at the moment we want to apply
     * the ith move.
     * @param nums
     * @param moveFrom
     * @param moveTo
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int n = moveFrom.length;
        for (int i = 0; i < n; i++) {
            int a = moveFrom[i], b = moveTo[i];
            set.remove(a);
            set.add(b);
        }
        List<Integer> res = new ArrayList<>();
        for (int x : set) res.add(x);
        Collections.sort(res);
        return res;
    }
}