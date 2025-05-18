package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RandomPickwithWeight
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 528. Random Pick with Weight
 */
public class LC528_RandomPickwithWeight {
    /**
     * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex
     * which randomly picks an index in proportion to its weight.
     *
     * Note:
     *
     * 1 <= w.length <= 10000
     * 1 <= w[i] <= 10^5
     * pickIndex will be called at most 10000 times.
     * Example 1:
     *
     * Input:
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * Output: [null,0]
     *
     * Example 2:
     *
     * Input:
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * Output: [null,0,1,1,1,0]
     * Explanation of Input Syntax:
     *
     * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the
     * array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
     * @param w
     */
    List<Integer> psum = new ArrayList<>();
    int total = 0;
    Random random = new Random();
    public LC528_RandomPickwithWeight(int[] w) {
        for (int n : w) {
            total += n;
            psum.add(total);
        }
    }

    public int pickIndex() {
        int rand = random.nextInt(total);

        int left = 0, right = psum.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (psum.get(mid) <= rand) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
