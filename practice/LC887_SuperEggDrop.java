package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SuperEggDrop
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 887. Super Egg Drop
 */
public class LC887_SuperEggDrop {
    /**
     * You are given K eggs, and you have access to a building with N floors from 1 to N.
     *
     * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
     *
     * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will
     * break, and any egg dropped at or below floor F will not break.
     *
     * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
     *
     * Your goal is to know with certainty what the value of F is.
     *
     * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial
     * value of F?
     *
     *
     *
     * Example 1:
     *
     * Input: K = 1, N = 2
     * Output: 2
     * Explanation:
     * Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
     * Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
     * If it didn't break, then we know with certainty F = 2.
     * Hence, we needed 2 moves in the worst case to know what F is with certainty.
     *
     * Example 2:
     *
     * Input: K = 2, N = 6
     * Output: 3
     *
     * Example 3:
     *
     * Input: K = 3, N = 14
     * Output: 4
     *
     *
     * Note:
     *
     * 1 <= K <= 100
     * 1 <= N <= 10000
     *
     * @param K
     * @param N
     * @return
     */
    /**
     * 鸡蛋掉落，鹰蛋（Leetcode 887）：（经典dp）
     * 有 K 个鸡蛋，有 N 层楼，用最少的操作次数 F 检查出鸡蛋的质量。
     *
     * 思路：
     * 本题应该逆向思维，若你有 K 个鸡蛋，你最多操作 F 次，求 N 最大值。
     *
     * dp[k][f] = dp[k][f-1] + dp[k-1][f-1] + 1;
     * 解释：
     * 0.dp[k][f]：如果你还剩 k 个蛋，且只能操作 f 次了，所能确定的楼层。
     * 1.dp[k][f-1]：蛋没碎，因此该部分决定了所操作楼层的上面所能容纳的楼层最大值
     * 2.dp[k-1][f-1]：蛋碎了，因此该部分决定了所操作楼层的下面所能容纳的楼层最大值
     * 又因为第 f 次操作结果只和第 f-1 次操作结果相关，因此可以只用一维数组。
     *
     * time = O(K*(N)^(1/2))
     */
    public int superEggDrop(int K, int N) {
        int[] dp = new int[K + 1];
        int ans = 0;    // 操作的次数
        while (dp[K] < N){
            for (int i = K; i > 0; i--) // 从后往前计算
                dp[i] = dp[i] + dp[i-1] + 1;
            ans++;
        }
        return ans;
    }
}
