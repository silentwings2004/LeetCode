package LC3001_3300;

public class LC3178_FindtheChildWhoHastheBallAfterKSeconds {
    /**
     * You are given two positive integers n and k. There are n children numbered from 0 to n - 1 standing in a queue in
     * order from left to right.
     *
     * Initially, child 0 holds a ball and the direction of passing the ball is towards the right direction. After each
     * second, the child holding the ball passes it to the child next to them. Once the ball reaches either end of the
     * line, i.e. child 0 or child n - 1, the direction of passing is reversed.
     *
     * Return the number of the child who receives the ball after k seconds.
     *
     * Input: n = 3, k = 5
     * Output: 1
     *
     * Input: n = 5, k = 6
     * Output: 2
     *
     * Input: n = 4, k = 2
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n <= 50
     * 1 <= k <= 50
     * @param n
     * @param k
     * @return
     */
    // S1: simulation
    // time = O(k), space = O(1)
    public int numberOfChild(int n, int k) {
        int res = 0;
        boolean flag = true;
        for (int i = 1; i <= k; i++) {
            if (flag) {
                if (res + 1 == n) {
                    res--;
                    flag = false;
                } else res++;
            } else {
                if (res - 1 < 0) {
                    res++;
                    flag = true;
                } else res--;
            }
        }
        return res;
    }

    // S2: math
    // time = O(1), space = O(1)
    public int numberOfChild2(int n, int k) {
        int q = k / (n - 1), r = k % (n - 1);
        return q % 2 == 0 ? r : n - 1 - r;
    }
}
/**
 * 完完整整传了一共 q = k/(n-1) 轮
 * k % (n-1)
 * q % 2 == 0 => r
 * q % 2 == 1 => n - 1 - r
 */