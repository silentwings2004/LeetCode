package LC3301_3600;
import java.util.*;
public class LC3592_InverseCoinChange {
    /**
     * You are given a 1-indexed integer array numWays, where numWays[i] represents the number of ways to select a total
     * amount i using an infinite supply of some fixed coin denominations. Each denomination is a positive integer with
     * value at most numWays.length.
     *
     * However, the exact coin denominations have been lost. Your task is to recover the set of denominations that could
     * have resulted in the given numWays array.
     *
     * Return a sorted array containing unique integers which represents this set of denominations.
     *
     * If no such set exists, return an empty array.
     *
     * Input: numWays = [0,1,0,2,0,3,0,4,0,5]
     * Output: [2,4,6]
     *
     * Input: numWays = [1,2,2,3,4]
     * Output: [1,2,5]
     *
     * Input: numWays = [1,2,3,4,15]
     * Output: []
     *
     * Constraints:
     *
     * 1 <= numWays.length <= 100
     * 0 <= numWays[i] <= 2 * 10^8
     * @param numWays
     * @return
     */
    // time = O(n), space = O(n)
    public List<Integer> findCoins(int[] numWays) {
        int n = numWays.length;
        long[] f = new long[n + 1];
        f[0] = 1;
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int x = numWays[i - 1];
            if (x == f[i]) continue; // res 中没有 i
            if (x - 1 != f[i]) return new ArrayList<>(); // 无解
            res.add(i);
            // 现在得到了一个大小为 i 的物品，用 i 计算完全背包 (空间优化写法)
            for (int j = i; j <= n; j++) f[j] += f[j - i];
        }
        return res;
    }
}
/**
 * ref: LC518
 */