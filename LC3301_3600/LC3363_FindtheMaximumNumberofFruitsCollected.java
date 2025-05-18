package LC3301_3600;
import java.util.*;
public class LC3363_FindtheMaximumNumberofFruitsCollected {
    /**
     * There is a game dungeon comprised of n x n rooms arranged in a grid.
     *
     * You are given a 2D array fruits of size n x n, where fruits[i][j] represents the number of fruits in the room
     * (i, j). Three children will play in the game dungeon, with initial positions at the corner rooms (0, 0),
     * (0, n - 1), and (n - 1, 0).
     *
     * The children will make exactly n - 1 moves according to the following rules to reach the room (n - 1, n - 1):
     *
     * The child starting from (0, 0) must move from their current room (i, j) to one of the rooms (i + 1, j + 1),
     * (i + 1, j), and (i, j + 1) if the target room exists.
     * The child starting from (0, n - 1) must move from their current room (i, j) to one of the rooms (i + 1, j - 1),
     * (i + 1, j), and (i + 1, j + 1) if the target room exists.
     * The child starting from (n - 1, 0) must move from their current room (i, j) to one of the rooms (i - 1, j + 1),
     * (i, j + 1), and (i + 1, j + 1) if the target room exists.
     * When a child enters a room, they will collect all the fruits there. If two or more children enter the same room,
     * only one child will collect the fruits, and the room will be emptied after they leave.
     *
     * Return the maximum number of fruits the children can collect from the dungeon.
     *
     * Input: fruits = [[1,2,3,4],[5,6,8,7],[9,10,11,12],[13,14,15,16]]
     * Output: 100
     *
     * Input: fruits = [[1,1],[1,1]]
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= n == fruits.length == fruits[i].length <= 1000
     * 0 <= fruits[i][j] <= 1000
     * @param fruits
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], Integer.MIN_VALUE);
        f[0][n - 1] = fruits[0][n - 1];
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
                if (j + 1 < n) f[i][j] = Math.max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
            }
        }
        f[n - 1][0] = fruits[n - 1][0];
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                f[i][j] = Math.max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
                if (i + 1 < n) f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
            }
        }
        int res = f[n - 1][n - 2] + f[n - 2][n - 1];
        for (int i = 0; i < n; i++) res += fruits[i][i];
        return res;
    }
}