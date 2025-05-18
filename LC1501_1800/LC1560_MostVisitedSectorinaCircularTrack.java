package LC1501_1800;
import java.util.*;
public class LC1560_MostVisitedSectorinaCircularTrack {
    /**
     * Given an integer n and an integer array rounds. We have a circular track which consists of n sectors labeled
     * from 1 to n. A marathon will be held on this track, the marathon consists of m rounds. The ith round starts at
     * sector rounds[i - 1] and ends at sector rounds[i]. For example, round 1 starts at sector rounds[0] and ends at
     * sector rounds[1]
     *
     * Return an array of the most visited sectors sorted in ascending order.
     *
     * Notice that you circulate the track in ascending order of sector numbers in the counter-clockwise direction (See
     * the first example).
     *
     * Input: n = 4, rounds = [1,3,1,2]
     * Output: [1,2]
     *
     * Input: n = 2, rounds = [2,1,2,1,2,1,2,1,2]
     * Output: [2]
     *
     * Input: n = 7, rounds = [1,3,5,7]
     * Output: [1,2,3,4,5,6,7]
     *
     *
     Constraints:

     2 <= n <= 100
     1 <= m <= 100
     rounds.length == m + 1
     1 <= rounds[i] <= n
     rounds[i] != rounds[i + 1] for 0 <= i < m
     * @param n
     * @param rounds
     * @return
     */
    // S1
    // time = O(n * m), space = O(n)
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int m = rounds.length;
        int[] s = new int[n + 1];
        for (int i = 0; i + 1 < m; i++) {
            int a = rounds[i], b = rounds[i + 1];
            while (a != b) {
                s[a]++;
                a++;
                if (a > n) a -= n;
            }
        }
        s[rounds[m - 1]]++;

        int c = 0;
        for (int x : s) c = Math.max(x, c);
        for (int i = 1; i <= n; i++) {
            if (s[i] == c) res.add(i);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public List<Integer> mostVisited2(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int m = rounds.length;
        int a = rounds[0], b = rounds[m - 1];
        if (a <= b) {
            for (int i = a; i <= b; i++) res.add(i);
        } else {
            for (int i = 1; i <= b; i++) res.add(i);
            for (int i = a; i <= n; i++) res.add(i);
        }
        return res;
    }
}