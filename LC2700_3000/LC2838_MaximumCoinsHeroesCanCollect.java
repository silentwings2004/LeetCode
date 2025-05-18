package LC2700_3000;
import java.util.*;
public class LC2838_MaximumCoinsHeroesCanCollect {
    /**
     * There is a battle and n heroes are trying to defeat m monsters. You are given two 1-indexed arrays of positive
     * integers heroes and monsters of length n and m, respectively. heroes[i] is the power of ith hero, and monsters[i]
     * is the power of ith monster.
     *
     * The ith hero can defeat the jth monster if monsters[j] <= heroes[i].
     *
     * You are also given a 1-indexed array coins of length m consisting of positive integers. coins[i] is the number
     * of coins that each hero earns after defeating the ith monster.
     *
     * Return an array ans of length n where ans[i] is the maximum number of coins that the ith hero can collect from
     * this battle.
     *
     * Notes
     *
     * The health of a hero doesn't get reduced after defeating a monster.
     * Multiple heroes can defeat a monster, but each monster can be defeated by a given hero only once.
     *
     * Input: heroes = [1,4,2], monsters = [1,1,5,2,3], coins = [2,3,4,5,6]
     * Output: [5,16,10]
     *
     * Input: heroes = [5], monsters = [2,3,1,2], coins = [10,6,5,2]
     * Output: [23]
     *
     * Input: heroes = [4,4], monsters = [5,7,8], coins = [1,1,1]
     * Output: [0,0]
     *
     * Constraints:
     *
     * 1 <= n == heroes.length <= 10^5
     * 1 <= m == monsters.length <= 10^5
     * coins.length == m
     * 1 <= heroes[i], monsters[i], coins[i] <= 10^9
     * @param heroes
     * @param monsters
     * @param coins
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long[] maximumCoins(int[] heroes, int[] monsters, int[] coins) {
        int n = heroes.length, m = monsters.length;
        int[][] w = new int[m][2];
        for (int i = 0; i < m; i++) w[i] = new int[]{monsters[i], coins[i]};
        Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);
        long[] s = new long[m + 1];
        for (int i = 1; i <= m; i++) s[i] = s[i - 1] + w[i - 1][1];

        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            int pos = find(w, heroes[i]);
            res[i] = s[pos + 1];
        }
        return res;
    }

    private int find(int[][] w, int x) {
        int l = 0, r = w.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (w[mid][0] <= x) l = mid;
            else r = mid - 1;
        }
        return w[r][0] <= x ? r : r - 1;
    }
}