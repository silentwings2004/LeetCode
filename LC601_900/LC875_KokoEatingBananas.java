package LC601_900;

public class LC875_KokoEatingBananas {
    /**
     * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone
     * and will come back in h hours.
     *
     * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k
     * bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any
     * more bananas during this hour.
     *
     * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
     *
     * Return the minimum integer k such that she can eat all the bananas within h hours.
     *
     * Input: piles = [3,6,7,11], h = 8
     * Output: 4
     *
     * Input: piles = [30,11,23,4,20], h = 5
     * Output: 30
     *
     * Input: piles = [30,11,23,4,20], h = 6
     * Output: 23
     *
     * Constraints:
     *
     * 1 <= piles.length <= 10^4
     * piles.length <= h <= 10^9
     * 1 <= piles[i] <= 10^9
     * @param piles
     * @param h
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = (int)1e9;
        while (l < r) {
            int mid = l + r >> 1;
            if (get(piles, mid) <= h) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private int get(int[] a, int t) {
        int res = 0;
        for (int x : a) res += (x + t - 1) / t;
        return res;
    }
}