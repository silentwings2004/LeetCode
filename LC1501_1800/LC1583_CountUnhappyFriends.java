package LC1501_1800;
import java.util.*;
public class LC1583_CountUnhappyFriends {
    /**
     * You are given a list of preferences for n friends, where n is always even.
     *
     * For each person i, preferences[i] contains a list of friends sorted in the order of preference. In other words,
     * a friend earlier in the list is more preferred than a friend later in the list. Friends in each list are denoted
     * by integers from 0 to n-1.
     *
     * All the friends are divided into pairs. The pairings are given in a list pairs, where pairs[i] = [xi, yi] denotes
     * xi is paired with yi and yi is paired with xi.
     *
     * However, this pairing may cause some of the friends to be unhappy. A friend x is unhappy if x is paired with y
     * and there exists a friend u who is paired with v but:
     *
     * x prefers u over y, and
     * u prefers x over v.
     * Return the number of unhappy friends.
     *
     * Input: n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
     * Output: 2
     *
     * Input: n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
     * Output: 0
     *
     * Input: n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0, 2]]
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= n <= 500
     * n is even.
     * preferences.length == n
     * preferences[i].length == n - 1
     * 0 <= preferences[i][j] <= n - 1
     * preferences[i] does not contain i.
     * All values in preferences[i] are unique.
     * pairs.length == n/2
     * pairs[i].length == 2
     * xi != yi
     * 0 <= xi, yi <= n - 1
     * Each person is contained in exactly one pair.
     * @param n
     * @param preferences
     * @param pairs
     * @return
     */
    // time = O(n^2),s pace = O(n^2)
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] w = new int[n][n];
        for (int i = 0; i < preferences.length; i++) {
            for (int j = 0; j < preferences[i].length; j++) {
                w[i][preferences[i][j]] = j + 1;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < pairs.length; i++) {
            for (int j = i + 1; j < pairs.length; j++) {
                int a = pairs[i][0], b = pairs[i][1];
                int c = pairs[j][0], d = pairs[j][1];
                if (w[a][c] < w[a][b] && w[c][a] < w[c][d]) set.addAll(Arrays.asList(a, c));
                if (w[b][d] < w[b][a] && w[d][b] < w[d][c]) set.addAll(Arrays.asList(b, d));
                if (w[a][d] < w[a][b] && w[d][a] < w[d][c]) set.addAll(Arrays.asList(a, d));
                if (w[b][c] < w[b][a] && w[c][b] < w[c][d]) set.addAll(Arrays.asList(b, c));
            }
        }
        return set.size();
    }
}
