package LC3001_3300;
import java.util.*;
public class LC3030_FindtheGridofRegionAverage {
    /**
     * You are given a 0-indexed m x n grid image which represents a grayscale image, where image[i][j] represents a
     * pixel with intensity in the range[0..255]. You are also given a non-negative integer threshold.
     *
     * Two pixels image[a][b] and image[c][d] are said to be adjacent if |a - c| + |b - d| == 1.
     *
     * A region is a 3 x 3 subgrid where the absolute difference in intensity between any two adjacent pixels is less
     * than or equal to threshold.
     *
     * All pixels in a region belong to that region, note that a pixel can belong to multiple regions.
     *
     * You need to calculate a 0-indexed m x n grid result, where result[i][j] is the average intensity of the region
     * to which image[i][j] belongs, rounded down to the nearest integer. If image[i][j] belongs to multiple regions,
     * result[i][j] is the average of the rounded down average intensities of these regions, rounded down to the nearest
     * integer. If image[i][j] does not belong to any region, result[i][j] is equal to image[i][j].
     *
     * Return the grid result.
     *
     * Input: image = [[5,6,7,10],[8,9,10,10],[11,12,13,10]], threshold = 3
     * Output: [[9,9,9,9],[9,9,9,9],[9,9,9,9]]
     *
     * Input: image = [[10,20,30],[15,25,35],[20,30,40],[25,35,45]], threshold = 12
     * Output: [[25,25,25],[27,27,27],[27,27,27],[30,30,30]]
     *
     * Input: image = [[5,6,7],[8,9,10],[11,12,13]], threshold = 1
     * Output: [[5,6,7],[8,9,10],[11,12,13]]
     *
     * Constraints:
     *
     * 3 <= n, m <= 500
     * 0 <= image[i][j] <= 255
     * 0 <= threshold <= 255
     * @param image
     * @param threshold
     * @return
     */
    // time = O(m * n * 9), space = O(m * n * 9)
    int[][] res, cnt, image;
    int threshold;
    public int[][] resultGrid(int[][] image, int threshold) {
        this.image = image;
        this.threshold = threshold;
        int m = image.length, n = image[0].length;
        res = new int[m][n];
        cnt = new int[m][n];
        HashMap<String, int[][]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[][] g = new int[3][3];
                int idx = 0;
                boolean flag = true;
                for (int a = -1; a <= 1; a++) {
                    for (int b = -1; b <= 1; b++) {
                        int u = i + a, v = j + b;
                        if (u < 0 || u >= m || v < 0 || v >= n) {
                            flag = false;
                            break;
                        }
                        g[idx / 3][idx % 3] = image[u][v];
                        idx++;
                    }
                }
                if (flag) {
                    map.put(i + "#" + j, g);
                }
            }
        }

        for (String k : map.keySet()) {
            int[][] g = map.get(k);
            work(g, k);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt[i][j] > 0) res[i][j] /= cnt[i][j];
                else res[i][j] = image[i][j];
            }
        }
        return res;
    }

    private void work(int[][] g, String key) {
        String[] strs = key.split("#");
        int cx = Integer.parseInt(strs[0]), cy = Integer.parseInt(strs[1]);
        cx--;
        cy--;
        int s = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g[i][j] = image[cx + i][cy + j];
                s += g[i][j];
            }
        }

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int v1 = g[i][j];
                for (int d = 0; d < 4; d++) {
                    int a = i + dx[d], b = j + dy[d];
                    if (a < 0 || a >= 3 || b < 0 || b >= 3) continue;
                    int v2 = g[a][b];
                    if (Math.abs(v1 - v2) > threshold) return;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[cx + i][cy + j] += s / 9;
                cnt[cx + i][cy + j]++;
            }
        }
    }
}