package LC3001_3300;
import java.util.*;
public class LC3086_MinimumMovestoPickKOnes {
    /**
     * You are given a 0-indexed binary array nums of length n, a positive integer k and a non-negative integer
     * maxChanges.
     *
     * Dylan Smith plays a game, where the goal is for Dylan to pick up k ones from nums using the minimum number of
     * moves. When the game starts, Dylan picks up any index dylanIndex in the range [0, n - 1] and stands there. If
     * nums[dylanIndex] == 1 , Dylan picks up the one and nums[dylanIndex] becomes 0(this does not count as a move).
     * After this, Dylan can make any number of moves (including zero) where in each move Dylan must perform exactly
     * one of the following actions:
     *
     * Select any index j != dylanIndex such that nums[j] == 0 and set nums[j] = 1. This action can be performed at
     * most maxChanges times.
     * Select any two adjacent indices x and y (|x - y| == 1) such that nums[x] == 1, nums[y] == 0, then swap their
     * values (set nums[y] = 1 and nums[x] = 0). If y == dylanIndex, Dylan picks up the one after this move and nums[y]
     * becomes 0.
     * Return the minimum number of moves required by Dylan to pick exactly k ones.
     *
     * Input: nums = [1,1,0,0,0,1,1,0,0,1], k = 3, maxChanges = 1
     * Output: 3
     *
     * Input: nums = [0,0,0,0], k = 2, maxChanges = 3
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 0 <= nums[i] <= 1
     * 1 <= k <= 10^5
     * 0 <= maxChanges <= 10^5
     * maxChanges + sum(nums) >= k
     * @param nums
     * @param k
     * @param maxChanges
     * @return
     */
    // time = O(n), space = O(n)
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        List<Integer> pos = new ArrayList<>();
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                pos.add(i);
                cnt = Math.max(cnt, 1);
                if (i > 0 && nums[i - 1] == 1) {
                    if (i > 1 && nums[i - 2] == 1) {
                        cnt = 3;
                    } else cnt = Math.max(cnt, 2);
                }
            }
        }
        cnt = Math.min(k, cnt);
        if (maxChanges >= k - cnt) return Math.max(0, cnt - 1) + 2 * (k - cnt);

        int d = k - maxChanges;
        n = pos.size();
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + pos.get(i - 1);

        long res = Long.MAX_VALUE;
        for (int l = 0, r = d - 1; r < n; l++, r++) {
            int mid = l + r >> 1;
            long s1 = (long)pos.get(mid) * (mid - l + 1) - (s[mid + 1] - s[l]);
            long s2 = (s[r + 1] - s[mid]) - (long)pos.get(mid) * (r - mid + 1);
            res = Math.min(res, s1 + s2);
        }
        return res + maxChanges * 2;
    }
}
/**
 * 操作次数的分析：
 * 1. 当前位置的1操作0次
 * 2. 当前位置左右相邻位置的1操作1次
 * 3. 第一种操作，生成1个1，第二种操作，把这个1移动过来 => 操作2次
 * 4. 只用第二种操作，把在下标j的1，移动到当前下标index => abs(idx - j)
 *
 * 优先做哪些操作：
 * 1. 先把index, index-1, index+1 这三个位置，至多有3个1收集好
 * 2. 用第一种操作+第二种操作，得到maxChanges个1
 * 3. 如果还有需要得到1，就用第二种操作，次数为abs（index-j)
 *
 * 先把 maxChanges 比较大的情况单独处理了
 * 如果只有操作2 => 货仓选址问题(中位数贪心)
 * 先把 macChanges个1，每个1用两次操作得到
 * 其余 k- maxChanges个1，就套用货仓选址问题解决(因为你只能用操作2了)
 * 先把nums中的所有1的位置，保存到一个pos数组中
 * pos的大小为 k - maxChanges 子数组的货仓选址问题
 */