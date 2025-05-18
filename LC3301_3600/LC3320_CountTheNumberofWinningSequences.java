package LC3301_3600;
import java.util.*;
public class LC3320_CountTheNumberofWinningSequences {
    /**
     * Alice and Bob are playing a fantasy battle game consisting of n rounds where they summon one of three magical
     * creatures each round: a Fire Dragon, a Water Serpent, or an Earth Golem. In each round, players simultaneously
     * summon their creature and are awarded points as follows:
     *
     * If one player summons a Fire Dragon and the other summons an Earth Golem, the player who summoned the Fire
     * Dragon is awarded a point.
     * If one player summons a Water Serpent and the other summons a Fire Dragon, the player who summoned the Water
     * Serpent is awarded a point.
     * If one player summons an Earth Golem and the other summons a Water Serpent, the player who summoned the Earth
     * Golem is awarded a point.
     * If both players summon the same creature, no player is awarded a point.
     * You are given a string s consisting of n characters 'F', 'W', and 'E', representing the sequence of creatures
     * Alice will summon in each round:
     *
     * If s[i] == 'F', Alice summons a Fire Dragon.
     * If s[i] == 'W', Alice summons a Water Serpent.
     * If s[i] == 'E', Alice summons an Earth Golem.
     * Bob’s sequence of moves is unknown, but it is guaranteed that Bob will never summon the same creature in two
     * consecutive rounds. Bob beats Alice if the total number of points awarded to Bob after n rounds is strictly
     * greater than the points awarded to Alice.
     *
     * Return the number of distinct sequences Bob can use to beat Alice.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: s = "FFF"
     * Output: 3
     *
     * Input: s = "FWEFW"
     * Output: 18
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s[i] is either 'F', 'W', or 'E'.
     * @param s
     * @return
     */
    // time = O(n^2 * K^2), space = O(n^2 * K), K = 3
    long mod = (long)(1e9 + 7);
    long[][][] f;
    int n;
    String s;
    public int countWinningSequences(String s) {
        this.s = s;
        n = s.length();
        f = new long[n][3][2 * n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }
        return (int)dfs(0, 3, n);
    }

    private long dfs(int u, int last, int d) {
        if (u == n) return d > n ? 1 : 0;
        if (n - u + d - n < 0) return 0;
        if (d - n - (n - u) > 0) return qmi(2, n - u);
        if (last < 3 && f[u][last][d] != -1) return f[u][last][d];

        long res = 0;
        int t = 0;
        if (s.charAt(u) == 'F') t = 0;
        else if (s.charAt(u) == 'W') t = 1;
        else t = 2;
        for (int i = 0; i < 3; i++) {
            if (i == last) continue;
            if (i == t) res = (res + dfs(u + 1, i, d)) % mod;
            else {
                int v = (i - t + 3) % 3;
                if (v == 1) res = (res + dfs(u + 1, i, d + 1)) % mod;
                else res = (res + dfs(u + 1, i, d - 1)) % mod;
            }
        }
        if (last < 3) f[u][last][d] = res;
        return res;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}