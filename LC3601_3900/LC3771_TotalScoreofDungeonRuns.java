package LC3601_3900;

import java.util.Arrays;

public class LC3771_TotalScoreofDungeonRuns {
    /**
     * You are given a positive integer hp and two positive 1-indexed integer arrays damage and requirement.
     *
     * There is a dungeon with n trap rooms numbered from 1 to n. Entering room i reduces your health points by
     * damage[i]. After that reduction, if your remaining health points are at least requirement[i], you earn 1 point
     * for that room.
     *
     * Let score(j) be the number of points you get if you start with hp health points and enter the rooms j, j + 1,
     * ..., n in this order.
     *
     * Return the integer score(1) + score(2) + ... + score(n), the sum of scores over all starting rooms.
     *
     * Note: You cannot skip rooms. You can finish your journey even if your health points become non-positive.
     *
     * Input: hp = 11, damage = [3,6,7], requirement = [4,2,5]
     * Output: 3
     *
     * Input: hp = 2, damage = [10000,1], requirement = [1,1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= hp <= 10^9
     * 1 <= n == damage.length == requirement.length <= 10^5
     * 1 <= damage[i], requirement[i] <= 10^4
     * @param hp
     * @param damage
     * @param requirement
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public long totalScore(int hp, int[] damage, int[] requirement) {
        int n = damage.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + damage[i - 1];

        long res = 0;
        for (int i = 1; i <= n; i++) {
            long d = hp - requirement[i - 1];
            long v = s[i] - d;
            int l = 0, r = i - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (s[mid] >= v) r = mid;
                else l = mid + 1;
            }
            if (s[r] < v) r++;
            res += i - r;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long totalScore2(int hp, int[] damage, int[] requirement) {
        int n = damage.length;
        int[] s = new int[n + 1];
        long res = 0;
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + damage[i];
            int t = s[i + 1] + requirement[i] - hp;
            int j = Arrays.binarySearch(s, 0, i + 1, t);
            if (j < 0) j = ~j; // 如果没找到 = t 的数，把返回值取反就是 > t 的第一个数的下标
            res += i - j + 1;
        }
        return res;
    }
}
/**
 * hp - S(i+1) + S(j) >= r(i)
 * => S(j) >= S(i+1) + r(i) - hp = C
 * damage[i] > 0 => 前缀和 > 0 => 找到 >= C 的第一个数
 */