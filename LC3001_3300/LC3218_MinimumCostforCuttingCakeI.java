package LC3001_3300;
import java.util.*;
public class LC3218_MinimumCostforCuttingCakeI {
    /**
     * There is an m x n cake that needs to be cut into 1 x 1 pieces.
     *
     * You are given integers m, n, and two arrays:
     *
     * horizontalCut of size m - 1, where horizontalCut[i] represents the cost to cut along the horizontal line i.
     * verticalCut of size n - 1, where verticalCut[j] represents the cost to cut along the vertical line j.
     * In one operation, you can choose any piece of cake that is not yet a 1 x 1 square and perform one of the
     * following cuts:
     *
     * Cut along a horizontal line i at a cost of horizontalCut[i].
     * Cut along a vertical line j at a cost of verticalCut[j].
     * After the cut, the piece of cake is divided into two distinct pieces.
     *
     * The cost of a cut depends only on the initial cost of the line and does not change.
     *
     * Return the minimum total cost to cut the entire cake into 1 x 1 pieces.
     *
     * Input: m = 3, n = 2, horizontalCut = [1,3], verticalCut = [5]
     * Output: 13
     *
     * Input: m = 2, n = 2, horizontalCut = [7], verticalCut = [4]
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= m, n <= 20
     * horizontalCut.length == m - 1
     * verticalCut.length == n - 1
     * 1 <= horizontalCut[i], verticalCut[i] <= 10^3
     * @param m
     * @param n
     * @param horizontalCut
     * @param verticalCut
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        PriorityQueue<Integer> hp = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> vp = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int x : horizontalCut) hp.offer(x);
        for (int x : verticalCut) vp.offer(x);

        int res = 0, hc = 1, vc = 1;
        while (!hp.isEmpty() ||!vp.isEmpty()) {
            if (hp.isEmpty()) {
                res += vp.poll() * hc;
                vc++;
            } else if (vp.isEmpty()) {
                res += hp.poll() * vc;
                hc++;
            } else if (hp.peek() >= vp.peek()) {
                res += hp.poll() * vc;
                hc++;
            } else {
                res += vp.poll() * hc;
                vc++;
            }
        }
        return res;
    }
}