package LC901_1200;
import java.util.*;
public class LC1058_MinimizeRoundingErrortoMeetTarget {
    /**
     * Given an array of prices [p1,p2...,pn] and a target, round each price pi to Roundi(pi) so that the rounded array
     * [Round1(p1),Round2(p2)...,Roundn(pn)] sums to the given target. Each operation Roundi(pi) could be either
     * Floor(pi) or Ceil(pi).
     *
     * Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest
     * rounding error, which is defined as Σ |Roundi(pi) - (pi)| for i from 1 to n, as a string with three places after
     * the decimal.
     *
     * Input: prices = ["0.700","2.800","4.900"], target = 8
     * Output: "1.000"
     *
     * Input: prices = ["1.500","2.500","3.500"], target = 10
     * Output: "-1"
     *
     * Input: prices = ["1.500","2.500","3.500"], target = 9
     * Output: "1.500"
     *
     * Constraints:
     *
     * 1 <= prices.length <= 500
     * Each string prices[i] represents a real number in the range [0.0, 1000.0] and has exactly 3 decimal places.
     * 0 <= target <= 10^6
     * @param prices
     * @param target
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String minimizeError(String[] prices, int target) {
        double res = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (String p : prices) {
            double t = Double.valueOf(p);
            double l = Math.floor(t);
            double h = Math.ceil(t);

            if (l != h) pq.offer((h - t) - (t - l));
            res += t - l;
            target -= l;
        }
        if (target < 0 || target > pq.size()) return "-1";
        while (target-- > 0) res += pq.poll();
        return String.format("%.3f", res);
    }
}