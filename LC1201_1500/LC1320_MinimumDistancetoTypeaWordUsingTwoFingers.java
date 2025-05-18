package LC1201_1500;
import java.util.*;
public class LC1320_MinimumDistancetoTypeaWordUsingTwoFingers {
    /**
     * You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at
     * some coordinate.
     *
     * For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at coordinate (0, 1), the
     * letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
     * Given the string word, return the minimum total distance to type such string using only two fingers.
     *
     * The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.
     *
     * Note that the initial positions of your two fingers are considered free so do not count towards your total
     * distance, also your two fingers do not have to start at the first letter or the first two letters.
     *
     * Input: word = "CAKE"
     * Output: 3
     *
     * Input: word = "HAPPY"
     * Output: 6
     *
     * Constraints:
     *
     * 2 <= word.length <= 300
     * word consists of uppercase English letters.
     * @param word
     * @return
     */
    // time = O(n * 26 * 26), space = O(n * 26 * 26)
    public int minimumDistance(String word) {
        int n = word.length(), INF = (int)1e8;
        int[][][] f = new int[n + 1][26][26];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                Arrays.fill(f[i][j], INF);
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                f[0][i][j] = 0;
            }
        }

        for (int i = 1; i <= n; i++) {
            int t = word.charAt(i - 1) - 'A';
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    f[i][t][k] = Math.min(f[i][t][k], f[i - 1][j][k] + get_dist(j, t));
                    f[i][j][t] = Math.min(f[i][j][t], f[i - 1][j][k] + get_dist(k, t));
                }
            }
        }

        int res = INF;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                res = Math.min(res, f[n][i][j]);
            }
        }
        return res;
    }

    private int get_dist(int x, int y) {
        return Math.abs(x / 6 - y / 6) + Math.abs(x % 6 - y % 6);
    }
}
/**
 * dp
 * 1. 状态表示f(i,j,k):
 * 集合：已经输入了前i个字符，且左手在字母j，右手在字母k的所有方案的集合
 * 属性：最小值
 * 2. 状态计算：第i+1个字符用哪个手
 * (1) 用左手输入：f(i+1,s(i+1),k) + dist
 * (2) 用右手输入: f(i+1,j,s(i+1)) + dist
 */