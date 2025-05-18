package LC1201_1500;
import java.util.*;
public class LC1215_SteppingNumbers {
    /**
     * A stepping number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.
     *
     * For example, 321 is a stepping number while 421 is not.
     * Given two integers low and high, return a sorted list of all the stepping numbers in the inclusive range
     * [low, high].
     *
     * Input: low = 0, high = 21
     * Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
     *
     * Input: low = 10, high = 15
     * Output: [10,12]
     *
     * Constraints:
     *
     * 0 <= low <= high <= 2 * 10^9
     * @param low
     * @param high
     * @return
     */
    TreeSet<Integer> set;
    int low, high;
    public List<Integer> countSteppingNumbers(int low, int high) {
        this.low = low;
        this.high = high;
        set = new TreeSet<>();
        dfs(0, -1);
        return new ArrayList<>(set);
    }

    private void dfs(long s, int last) {
        if (s > high) return;
        if (last == -1) {
            for (int i = 0; i < 10; i++) {
                if (i >= low && i <= high) set.add(i);
                dfs(i, i);
            }
        } else {
            if (last + 1 < 10) {
                long ns = s * 10 + last + 1;
                if (ns >= low && ns <= high) set.add((int)ns);
                dfs(ns, last + 1);
            }
            if (last - 1 >= 0) {
                long ns = s * 10 + last - 1;
                if (ns >= low && ns <= high) set.add((int)ns);
                dfs(ns, last - 1);
            }
        }
    }
}