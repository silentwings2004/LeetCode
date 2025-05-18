package LC901_1200;
import java.util.*;
public class LC1033_MovingStonesUntilConsecutive {
    /**
     * There are three stones in different positions on the X-axis. You are given three integers a, b, and c, the
     * positions of the stones.
     *
     * In one move, you pick up a stone at an endpoint (i.e., either the lowest or highest position stone), and move it
     * to an unoccupied position between those endpoints. Formally, let's say the stones are currently at positions x, y,
     * and z with x < y < z. You pick up the stone at either position x or position z, and move that stone to an integer
     * position k, with x < k < z and k != y.
     *
     * The game ends when you cannot make any more moves (i.e., the stones are in three consecutive positions).
     *
     * Return an integer array answer of length 2 where:
     *
     * answer[0] is the minimum number of moves you can play, and
     * answer[1] is the maximum number of moves you can play.
     *
     * Input: a = 1, b = 2, c = 5
     * Output: [1,2]
     *
     * Input: a = 4, b = 3, c = 2
     * Output: [0,0]
     *
     * Input: a = 3, b = 5, c = 1
     * Output: [1,2]
     *
     * Constraints:
     *
     * 1 <= a, b, c <= 100
     * a, b, and c have different values.
     * @param a
     * @param b
     * @param c
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        int x = arr[1] - arr[0] - 1, y = arr[2] - arr[1] - 1;
        int[] res = new int[2];
        if (x == 0 && y == 0) res[0] = 0;
        else if (x <= 1 || y <= 1) res[0] = 1;
        else res[0] = 2;
        res[1] = x + y;
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] numMovesStones2(int a, int b, int c) {
        int[] w = new int[]{a, b, c};
        Arrays.sort(w);
        a = w[0];
        b = w[1];
        c = w[2];
        int[] res = new int[2];
        if (c - a == 2) res[0] = 0;
        else if (b - a <= 2 || c - b <= 2) res[0] = 1;
        else res[0] = 2;
        res[1] = c - a - 2;
        return res;
    }
}