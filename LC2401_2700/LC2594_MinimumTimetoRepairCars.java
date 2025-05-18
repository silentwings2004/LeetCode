package LC2401_2700;

public class LC2594_MinimumTimetoRepairCars {
    /**
     * You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith
     * mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.
     *
     * You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.
     *
     * Return the minimum time taken to repair all the cars.
     *
     * Note: All the mechanics can repair the cars simultaneously.
     *
     * Input: ranks = [4,2,3,1], cars = 10
     * Output: 16
     *
     * Input: ranks = [5,1,8], cars = 6
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= ranks.length <= 10^5
     * 1 <= ranks[i] <= 100
     * 1 <= cars <= 10^6
     * @param ranks
     * @param cars
     * @return
     */
    // time = O(nlogn), space = O(1)
    public long repairCars(int[] ranks, int cars) {
        long l = 0, r = (long) 1e14;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(ranks, cars, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] a, int k, long t) {
        long sum = 0;
        for (int i = 0; i < a.length; i++) sum += (long) Math.sqrt(t / a[i]);
        return sum >= k;
    }
}