package LC3001_3300;

public class LC3274_CheckifTwoChessboardSquaresHavetheSameColor {
    /**
     * You are given two strings, coordinate1 and coordinate2, representing the coordinates of a square on an 8 x 8
     * chessboard.
     *
     * Below is the chessboard for reference.
     *
     * Return true if these two squares have the same color and false otherwise.
     *
     * The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first
     * (indicating its column), and the number second (indicating its row).
     *
     * Input: coordinate1 = "a1", coordinate2 = "c3"
     * Output: true
     *
     * Input: coordinate1 = "a1", coordinate2 = "h3"
     * Output: false
     *
     * Constraints:
     *
     * coordinate1.length == coordinate2.length == 2
     * 'a' <= coordinate1[0], coordinate2[0] <= 'h'
     * '1' <= coordinate1[1], coordinate2[1] <= '8'
     * @param coordinate1
     * @param coordinate2
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int[][] g = new int[8][8];
        int t = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g[i][j] = t;
                t ^= 1;
            }
            t ^= 1;
        }

        int u = coordinate1.charAt(0) - 'a', v = coordinate2.charAt(0) - 'a';
        int x = coordinate1.charAt(1) - '0', y = coordinate2.charAt(1) - '0';
        x = 8 - x;
        y = 8 - y;
        return g[x][u] == g[y][v];
    }

    // S2
    // time = O(1), space = O(1)
    public boolean checkTwoChessboards2(String coordinate1, String coordinate2) {
        return (coordinate1.charAt(0) + coordinate1.charAt(1)) % 2 == (coordinate2.charAt(0) + coordinate2.charAt(1)) % 2;
    }
}
/**
 * 如果英文字母和数字对应的ASCII值都是奇数或者都是偶数
 * 奇偶性相同 => 那么坐标所对应的格子就是黑色
 * 奇偶性不同 => 那么坐标所对应的格子就是白色
 * 奇数 + 奇数 = 偶数
 * 偶数 + 偶数 = 偶数
 */