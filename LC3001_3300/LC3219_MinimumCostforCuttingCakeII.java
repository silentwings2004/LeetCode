package LC3001_3300;
import java.util.*;
public class LC3219_MinimumCostforCuttingCakeII {
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
     * 1 <= m, n <= 10^5
     * horizontalCut.length == m - 1
     * verticalCut.length == n - 1
     * 1 <= horizontalCut[i], verticalCut[i] <= 10^3
     * @param m
     * @param n
     * @param horizontalCut
     * @param verticalCut
     * @return
     */
    // S1: heap
    // time = O(nlogn), space = O(n)
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        PriorityQueue<Integer> hp = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> vp = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int x : horizontalCut) hp.offer(x);
        for (int x : verticalCut) vp.offer(x);

        long res = 0;
        int hc = 1, vc = 1;
        while (!hp.isEmpty() ||!vp.isEmpty()) {
            if (hp.isEmpty()) {
                res += (long)vp.poll() * hc;
                vc++;
            } else if (vp.isEmpty()) {
                res += (long)hp.poll() * vc;
                hc++;
            } else if (hp.peek() >= vp.peek()) {
                res += (long)hp.poll() * vc;
                hc++;
            } else {
                res += (long)vp.poll() * hc;
                vc++;
            }
        }
        return res;
    }

    // S2: Sort
    // time = O(mlogm + nlogn), space = O(logm + logn)
    public long minimumCost2(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        long res = 0;
        int i = m - 2, j = n - 2;
        int cnt_h = 1, cnt_v = 1;
        while (i >= 0 || j >= 0) {
            if (j < 0 || i >= 0 && horizontalCut[i] > verticalCut[j]) {
                res += (long)horizontalCut[i] * cnt_v;
                cnt_h++;
                i--;
            } else {
                res += (long)verticalCut[j] * cnt_h;
                cnt_v++;
                j--;
            }
        }
        return res;
    }
}
/**
 * 最小总开销 = 一堆横切竖切开销的和
 * 没有一切到底的开销 = 一切到底的开销，所以下面分析就用一切到底来分析
 * cntH 表示母亲啊横切的次数, cntV 表示目前竖切的次数
 * V0 -> h0 -> h1 -> V1 -> V2
 * h1->v1: 2h1 + 3v1
 * v1->h1: 2v1 + 3h1
 * h -> v: (cv + 1) * h + (ch + 2) * v
 * v -> h: (ch + 1) * v + (cv + 2) * h
 * 横切竖切，谁的开销大，就先切谁。并且，先后次序和 cntH cntV 无关
 *
 * 另一种解释：
 * 先切竖的，那么任意(还没切)的横切都会增加"一个" h
 * 先切横的，那么任意(还没切)的竖切都会增加"一个" v
 */