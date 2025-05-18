package LC1501_1800;
import java.util.*;
public class LC1626_BestTeamWithNoConflicts {
    /**
     * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the
     * highest overall score. The score of the team is the sum of scores of all the players in the team.
     *
     * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a
     * strictly higher score than an older player. A conflict does not occur between players of the same age.
     *
     * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith
     * player, respectively, return the highest overall score of all possible basketball teams.
     *
     * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
     * Output: 34
     *
     * Input: scores = [4,5,6,5], ages = [2,1,2,1]
     * Output: 16
     *
     * Input: scores = [1,2,3,5], ages = [8,9,10,1]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= scores.length, ages.length <= 1000
     * scores.length == ages.length
     * 1 <= scores[i] <= 10^6
     * 1 <= ages[i] <= 1000
     * @param scores
     * @param ages
     * @return
     */
    // time = O(n^2), space = O(n)
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) a[i] = new int[]{ages[i], scores[i]};
        Arrays.sort(a, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int[] f = new int[n + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = a[i - 1][1];
            for (int j = 1; j < i; j++) {
                if (a[j - 1][1] <= a[i - 1][1]) {
                    f[i] = Math.max(f[i], f[j] + a[i - 1][1]);
                }
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }
}