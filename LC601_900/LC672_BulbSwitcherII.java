package LC601_900;

import java.util.HashSet;

public class LC672_BulbSwitcherII {
    /**
     * There is a room with n bulbs labeled from 1 to n that all are turned on initially, and four buttons on the wall.
     * Each of the four buttons has a different functionality where:
     *
     * Button 1: Flips the status of all the bulbs.
     * Button 2: Flips the status of all the bulbs with even labels (i.e., 2, 4, ...).
     * Button 3: Flips the status of all the bulbs with odd labels (i.e., 1, 3, ...).
     * Button 4: Flips the status of all the bulbs with a label j = 3k + 1 where k = 0, 1, 2, ... (i.e., 1, 4, 7, 10, ...).
     * You must make exactly presses button presses in total. For each press, you may pick any of the four buttons to
     * press.
     *
     * Given the two integers n and presses, return the number of different possible statuses after performing all
     * presses button presses.
     *
     * Input: n = 1, presses = 1
     * Output: 2
     *
     * Input: n = 2, presses = 1
     * Output: 3
     *
     * Input: n = 3, presses = 1
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 0 <= presses <= 1000
     * @param n
     * @param presses
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    int[][] state;
    public int flipLights(int n, int presses) {
        state = new int[][]{
                {1, 1, 1, 1, 1, 1}, // 不按
                {0, 0, 0, 0, 0, 0}, // 1
                {1, 0, 1, 0, 1, 0}, // 2
                {0, 1, 0, 1, 0, 1}, // 3
                {0, 1, 1, 0, 1, 1}, // 4
                {1, 0, 0, 1, 0, 0}, // 14
                {0, 0, 1, 1, 1, 0}, // 24
                {1, 1, 0, 0, 0, 1}, // 34
        };

        n = Math.min(n, 6);
        if (presses == 0) return work(n, new int[]{0});
        else if (presses == 1) return work(n, new int[]{1, 2, 3, 4});
        else if (presses == 2) return work(n, new int[]{0, 1, 2, 3, 5, 6, 7});
        else return work(n, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
    }

    private int work(int n, int[] ops) {
        HashSet<Integer> set = new HashSet<>();
        for (int op : ops) {
            int t = 0;
            for (int i = 0; i < n; i++) {
                t = t * 2 + state[op][i];
            }
            set.add(t);
        }
        return set.size();
    }

    // S2
    public int flipLights2(int n, int presses) {
        if (n == 0 || presses == 0) return 1;
        if (n == 1) return 2;
        else if (n == 2) {
            if (presses == 1) return 3;
            if (presses >= 2) return 4;
        } else if (n >= 3) {
            if (presses == 1) return 4;
            if (presses == 2) return 7;
            if (presses >= 3) return 8;
        }
        return 1;
    }
}
/**
 * 1 2 3 4 5 6
 * 最小公倍数 = 6 => 每6个一循环
 * m = k1 + k2 + k3 + k4
 * m1: k1 + k3 + k4
 * m2: k1 + k2
 * m3: k1 + k3
 *
 * m4: k1 + k2 + k4  => m1 % 2 + m2 % 2 - m3 % 2 = m4 % 2   4号灯的状态由1，2，3决定，不是一个独立状态
 * m5: k1 + k3   x  same as 3
 * m6: k1 + k2   x  same as 2
 *
 * 循环节
 * 对于任何一个灯而言，看前3个操作 => 2 + 3 => 1 / 1 + 2 => 3 / 1 + 3 => 2
 * 前3个操作，任意做2个，等效于第三个 => 可以抵消，化简后只可能存在1，2，3中的1个
 * 1. the same operation cannot appear twice
 * 2. 1, 2, 3 can appear only at most one (exclusively)
 * m >= 3: null, 1, 2, 3, 4, 1 + 4, 2 + 4, 3 + 4   if m 足够大，最多只有这8种情况   m >= 3
 * m = 2: null, 1, 2, 3, 1 + 4, 2 + 4, 3 + 4
 * m = 1: 1, 2, 3, 4
 *
 * n >= 3: result = 8
 * n = 2:
 *  m = 1 => result = 3
 *  m = 2 => result = 4 // 全开，全灭，一半开一半灭(1+2 or 1+3)
 *  m = 3 => result = 4
 * n = 1, result = 2
 *
 */