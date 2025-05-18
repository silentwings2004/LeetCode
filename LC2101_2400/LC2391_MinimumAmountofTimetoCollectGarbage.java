package LC2101_2400;

public class LC2391_MinimumAmountofTimetoCollectGarbage {
    /**
     * You are given a 0-indexed array of strings garbage where garbage[i] represents the assortment of garbage at the
     * ith house. garbage[i] consists only of the characters 'M', 'P' and 'G' representing one unit of metal, paper and
     * glass garbage respectively. Picking up one unit of any type of garbage takes 1 minute.
     *
     * You are also given a 0-indexed integer array travel where travel[i] is the number of minutes needed to go from
     * house i to house i + 1.
     *
     * There are three garbage trucks in the city, each responsible for picking up one type of garbage. Each garbage
     * truck starts at house 0 and must visit each house in order; however, they do not need to visit every house.
     *
     * Only one garbage truck may be used at any given moment. While one truck is driving or picking up garbage, the
     * other two trucks cannot do anything.
     *
     * Return the minimum number of minutes needed to pick up all the garbage.
     *
     * Input: garbage = ["G","P","GP","GG"], travel = [2,4,3]
     * Output: 21
     *
     * Input: garbage = ["MMM","PGM","GP"], travel = [3,10]
     * Output: 37
     *
     * Constraints:
     *
     * 2 <= garbage.length <= 10^5
     * garbage[i] consists of only the letters 'M', 'P', and 'G'.
     * 1 <= garbage[i].length <= 10
     * travel.length == garbage.length - 1
     * 1 <= travel[i] <= 100
     * @param garbage
     * @param travel
     * @return
     */
    // S1
    // time = O(n * k), space = O(1)
    public int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length;
        int mc = 0, pc = 0, gc = 0;
        int lm = -1, lp = -1, lg = -1;

        for (int i = 0; i < n; i++) {
            for (char c : garbage[i].toCharArray()) {
                if (c == 'M') {
                    mc++;
                    lm = i;
                } else if (c == 'P') {
                    pc++;
                    lp = i;
                } else {
                    gc++;
                    lg = i;
                }
            }
        }

        if (lm > 0) {
            for (int i = 0; i < lm; i++) mc += travel[i];
        }
        if (lp > 0) {
            for (int i = 0; i < lp; i++) pc += travel[i];
        }
        if (lg > 0) {
            for (int i = 0; i < lg; i++) gc += travel[i];
        }
        return mc + pc + gc;
    }

    // S2
    // time = O(n * k), space = O(1)
    public int garbageCollection2(String[] garbage, int[] travel) {
        int n = garbage.length, res = 0;
        String op = "MPG";
        for (char c : op.toCharArray()) {
            int k = 0;
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (char x : garbage[i].toCharArray()) {
                    if (x == c) cnt++;
                    if (cnt > 0) k = i;
                }
                res += cnt;
            }
            for (int i = 0; i < k; i++) res += travel[i];
        }
        return res;
    }
}