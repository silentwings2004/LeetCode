package LC301_600;
import java.util.*;
public class LC519_RandomFlipMatrix {
    /**
     * There is an m x n binary grid matrix with all the values set 0 initially. Design an algorithm to randomly pick
     * an index (i, j) where matrix[i][j] == 0 and flips it to 1. All the indices (i, j) where matrix[i][j] == 0 should
     * be equally likely to be returned.
     *
     * Optimize your algorithm to minimize the number of calls made to the built-in random function of your language and
     * optimize the time and space complexity.
     *
     * Implement the Solution class:
     *
     * Solution(int m, int n) Initializes the object with the size of the binary matrix m and n.
     * int[] flip() Returns a random index [i, j] of the matrix where matrix[i][j] == 0 and flips it to 1.
     * void reset() Resets all the values of the matrix to be 0.
     *
     * Input
     * ["Solution", "flip", "flip", "flip", "reset", "flip"]
     * [[3, 1], [], [], [], [], []]
     * Output
     * [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
     *
     * Constraints:
     *
     * 1 <= m, n <= 10^4
     * There will be at least one free cell for each call to flip.
     * At most 1000 calls will be made to flip and reset.
     * @param m
     * @param n
     */
    // time = O(1), space = O(1)
    int r, c, k;
    HashMap<Integer, Integer> map;
    Random random;
    public LC519_RandomFlipMatrix(int m, int n) {
        r = m;
        c = n;
        k = r * c;
        map = new HashMap<>();
        random = new Random();
    }

    public int[] flip() {
        int x = random.nextInt(k);
        int y = x;
        if (map.containsKey(x)) y = map.get(x);
        if (map.containsKey(k - 1)) {
            map.put(x, map.get(k - 1));
            map.remove(k - 1);
        } else map.put(x, k - 1);
        k--;
        return new int[]{y / c, y % c};
    }

    public void reset() {
        k = r * c;
        map.clear();
    }
}
/**
 * 每次从数组里随机选一个元素，将其删掉，然后把最后一个元素覆盖到该位置继续做
 * 开哈希表去存所有剩下来的数，只存所有特殊值
 */