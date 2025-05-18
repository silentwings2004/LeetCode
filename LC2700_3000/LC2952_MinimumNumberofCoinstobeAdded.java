package LC2700_3000;
import java.util.*;
public class LC2952_MinimumNumberofCoinstobeAdded {
    /**
     * You are given a 0-indexed integer array coins, representing the values of the coins available, and an integer
     * target.
     *
     * An integer x is obtainable if there exists a subsequence of coins that sums to x.
     *
     * Return the minimum number of coins of any value that need to be added to the array so that every integer in the
     * range [1, target] is obtainable.
     *
     * A subsequence of an array is a new non-empty array that is formed from the original array by deleting some
     * (possibly none) of the elements without disturbing the relative positions of the remaining elements.
     *
     * Input: coins = [1,4,10], target = 19
     * Output: 2
     *
     * Input: coins = [1,4,10,5,7,19], target = 19
     * Output: 1
     *
     * Input: coins = [1,1,1], target = 20
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= target <= 10^5
     * 1 <= coins.length <= 10^5
     * 1 <= coins[i] <= target
     * @param coins
     * @param target
     * @return
     */
    // time = O(nlogn + logk), space = O(logn)
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int n = coins.length;
        int res = 0, i = 0, s = 1; // 一开始只能组成[0,s-1]的所有数
        while (s <= target) { // 当 s - 1 >= target 的时候，就表示 [1, target] 都有了
            if (i < n && coins[i] <= s) s += coins[i++]; // 现在可以组成 [0, s+coins[i]-1] 的所有数
            else {
                s *= 2; // 必须添加一个数 s
                res++;
            }
        }
        return res;
    }
}
/**
 * [0, S-1]   x
 * [x, S+x-1] => [0, S+x-1]
 * when x <= s
 * if x > s => cannot get s
 * [0,1] + 4 => [4,5]  miss 2 => add 2
 * => [0,1] + 2 => [2,3] => [0,3] + 4 =>
 * [4,7] => [0,7] + 10  => [10,17] miss 8
 * => [0,7] + 8 => [8,15] => [0,15] + 10
 * => [10,25] => [0,25]
 * 在遍历过程中能够构造出哪些数字，如果新生成的区间不能和之前的区间合并起来，那么一定要添加一个数字
 *
 * 归纳法：
 * 假设[0,s-1]我都能组成
 * 新来了一个数 x [x, s+x-1]
 * 想一想，能否组成[0,s+x-1]的所有数？
 */