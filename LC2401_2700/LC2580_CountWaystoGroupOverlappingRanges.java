package LC2401_2700;
import java.util.*;
public class LC2580_CountWaystoGroupOverlappingRanges {
    /**
     * You are given a 2D integer array ranges where ranges[i] = [starti, endi] denotes that all integers between starti
     * and endi (both inclusive) are contained in the ith range.
     *
     * You are to split ranges into two (possibly empty) groups such that:
     *
     * Each range belongs to exactly one group.
     * Any two overlapping ranges must belong to the same group.
     * Two ranges are said to be overlapping if there exists at least one integer that is present in both ranges.
     *
     * For example, [1, 3] and [2, 5] are overlapping because 2 and 3 occur in both ranges.
     * Return the total number of ways to split ranges into two groups. Since the answer may be very large, return it
     * modulo 10^9 + 7.
     *
     * Input: ranges = [[6,10],[5,15]]
     * Output: 2
     *
     * Input: ranges = [[1,3],[10,20],[2,5],[4,8]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= ranges.length <= 10^5
     * ranges[i].length == 2
     * 0 <= starti <= endi <= 10^9
     * @param ranges
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (o1, o2) -> o1[0] - o2[0]);
        int st = -1, ed = -1, k = 0;
        for (int[] x : ranges) {
            if (ed < x[0]) {
                if (st != -1) k++;
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) k++;
        long mod =(long)(1e9 + 7);
        long res = 1;
        for (int i = 0; i < k; i++) res = res * 2 % mod;
        return (int) res;
    }
}
/**
 * [a,b,c], [d][e,f]
 * 2^3 => 2^k
 */