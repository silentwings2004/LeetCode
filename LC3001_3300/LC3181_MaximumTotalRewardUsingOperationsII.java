package LC3001_3300;
import java.math.BigInteger;
import java.util.*;
public class LC3181_MaximumTotalRewardUsingOperationsII {
    /**
     * You are given an integer array rewardValues of length n, representing the values of rewards.
     *
     * Initially, your total reward x is 0, and all indices are unmarked. You are allowed to perform the following
     * operation any number of times:
     *
     * Choose an unmarked index i from the range [0, n - 1].
     * If rewardValues[i] is greater than your current total reward x, then add rewardValues[i] to x (i.e., x = x +
     * rewardValues[i]), and mark the index i.
     * Return an integer denoting the maximum total reward you can collect by performing the operations optimally.
     *
     * Input: rewardValues = [1,1,3,3]
     *
     * Output: 4
     *
     * Input: rewardValues = [1,6,4,3,2]
     *
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= rewardValues.length <= 5 * 10^4
     * 1 <= rewardValues[i] <= 5 * 10^4
     * @param rewardValues
     * @return
     */
    // S1
    // time = O(n^2), space = O(k)
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length;
        int mx = rewardValues[n - 1];
        boolean[] f = new boolean[mx + 1];
        f[0] = true;

        for (int i = 0; i < n; i++) {
            if (i > 0 && rewardValues[i] == rewardValues[i - 1]) continue;
            for (int j = 0; j < rewardValues[i]; j++) {
                if (f[j]) {
                    if (j + rewardValues[i] <= mx) f[j + rewardValues[i]] = true;
                    else break;
                }
            }
        }

        int res = mx;
        for (int i = mx - 1; i >= 0; i--) {
            if (f[i]) {
                res += i;
                break;
            }
        }
        return res;
    }

    // S2: BigInteger
    // time = O(nm/w), space = O(n + m/w)
    public int maxTotalReward2(int[] rewardValues) {
        int mx = rewardValues[0];
        for (int x : rewardValues) mx = Math.max(mx, x);
        HashSet<Integer> set = new HashSet<>();
        for (int v : rewardValues) {
            if (v == mx - 1) return mx * 2 - 1;
            if (set.contains(v)) continue;
            if (set.contains(mx - 1 - v)) return mx * 2 - 1; // 如果有两个"不同"元素之和等于 m − 1，也可以直接返回 2m - 1
            set.add(v);
        }

        Arrays.sort(rewardValues);
        int last = 0;
        BigInteger f = BigInteger.ONE;
        for (int v : rewardValues) {
            if (v == last) continue;
            BigInteger mask = BigInteger.ONE.shiftLeft(v).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(v));
            last = v;
        }
        return f.bitLength() - 1;
    }
}
/**
 * 0-1 背包
 * 布尔数组转化成二进制
 * bitset 优化
 * 优化：去重，不可能选重复的数字
 */