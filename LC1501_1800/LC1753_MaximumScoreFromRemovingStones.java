package LC1501_1800;
import java.util.*;
public class LC1753_MaximumScoreFromRemovingStones {
    /**
     * You are playing a solitaire game with three piles of stones of sizes a, b, and c respectively. Each turn you
     * choose two different non-empty piles, take one stone from each, and add 1 point to your score. The game stops
     * when there are fewer than two non-empty piles (meaning there are no more available moves).
     *
     * Given three integers a, b, and c, return the maximum score you can get.
     *
     * Input: a = 2, b = 4, c = 6
     * Output: 6
     *
     * Input: a = 4, b = 4, c = 6
     * Output: 7
     *
     * Input: a = 1, b = 8, c = 8
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= a, b, c <= 10^5
     * @param a
     * @param b
     * @param c
     * @return
     */
    // S1: brute-force
    // time = O(nlogn), space = O(1)
    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        pq.offer(a);
        pq.offer(b);
        pq.offer(c);

        int res = 0;
        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();
            res++;
            x--;
            y--;
            if (x > 0) pq.offer(x);
            if (y > 0) pq.offer(y);
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public int maximumScore2(int a, int b, int c) {
        int[] d = new int[]{a, b, c};
        Arrays.sort(d);
        int x = 0;
        if (d[0] + d[1] < d[2]) x = d[2] - (d[0] + d[1]);
        else x = (a + b + c) % 2;
        return (a + b + c - x) / 2;
    }
}
