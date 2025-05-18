package LC3301_3600;
import java.util.*;
public class LC3301_MaximizetheTotalHeightofUniqueTowers {
    /**
     * You are given an array maximumHeight, where maximumHeight[i] denotes the maximum height the ith tower can be
     * assigned.
     *
     * Your task is to assign a height to each tower so that:
     *
     * The height of the ith tower is a positive integer and does not exceed maximumHeight[i].
     * No two towers have the same height.
     * Return the maximum possible total sum of the tower heights. If it's not possible to assign heights, return -1.
     *
     * Input: maximumHeight = [2,3,4,3]
     * Output: 10
     *
     * Input: maximumHeight = [15,10]
     * Output: 25
     *
     * Input: maximumHeight = [2,2,1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= maximumHeight.length <= 10^5
     * 1 <= maximumHeight[i] <= 10^9
     * @param maximumHeight
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long maximumTotalSum(int[] maximumHeight) {
        Arrays.sort(maximumHeight);
        int n = maximumHeight.length;
        long res = maximumHeight[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maximumHeight[i] = Math.min(maximumHeight[i], maximumHeight[i + 1] - 1);
            if (maximumHeight[i] <= 0) return -1;
            res += maximumHeight[i];
        }
        return res;
    }
}
/**
 * 从特殊到一半
 * 1. maximumHeight 的所有数都不同 => 不改
 * 1. maximumHeight 的所有数都相同
 *
 * 证明：一定有一个数是不需要减1的
 * 最大的那个数是不需要减1的
 * 次大的数怎么变？
 * 如果次大的数 < 最大的数，次大不变
 * 如果次大的数 == 最大的数，次大 - 1
 *
 * 从大到小考虑，
 * 当前数 = min(当前数，前1个数 - 1)
 */