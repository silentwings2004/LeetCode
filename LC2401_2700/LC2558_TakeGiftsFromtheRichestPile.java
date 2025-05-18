package LC2401_2700;
import java.util.*;
public class LC2558_TakeGiftsFromtheRichestPile {
    /**
     * You are given an integer array gifts denoting the number of gifts in various piles. Every second, you do the
     * following:
     *
     * Choose the pile with the maximum number of gifts.
     * If there is more than one pile with the maximum number of gifts, choose any.
     * Leave behind the floor of the square root of the number of gifts in the pile. Take the rest of the gifts.
     * Return the number of gifts remaining after k seconds.
     *
     * Input: gifts = [25,64,9,4,100], k = 4
     * Output: 29
     *
     * Input: gifts = [1,1,1,1], k = 4
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= gifts.length <= 10^3
     * 1 <= gifts[i] <= 10^9
     * 1 <= k <= 10^3
     * @param gifts
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long pickGifts(int[] gifts, int k) {
        long sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int x : gifts) {
            sum += x;
            pq.offer(x);
        }

        while (k-- > 0) {
            int t = pq.poll();
            sum -= t - (int) Math.sqrt(t);
            pq.offer((int) Math.sqrt(t));
        }
        return sum;
    }
}