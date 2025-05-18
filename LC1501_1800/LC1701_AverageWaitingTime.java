package LC1501_1800;

public class LC1701_AverageWaitingTime {
    /**
     * There is a restaurant with a single chef. You are given an array customers, where customers[i] = [arrivali, timei]:
     *
     * arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
     * timei is the time needed to prepare the order of the ith customer.
     * When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. The
     * customer waits till the chef finishes preparing his order. The chef does not prepare food for more than one
     * customer at a time. The chef prepares food for customers in the order they were given in the input.
     *
     * Return the average waiting time of all customers. Solutions within 10-5 from the actual answer are considered
     * accepted.
     *
     * Input: customers = [[1,2],[2,5],[4,3]]
     * Output: 5.00000
     *
     * Input: customers = [[5,2],[5,4],[10,3],[20,1]]
     * Output: 3.25000
     *
     * Constraints:
     *
     * 1 <= customers.length <= 10^5
     * 1 <= arrivali, timei <= 10^4
     * arrivali <= arrivali+1
     * @param customers
     * @return
     */
    // time = O(n), space = O(1)
    public double averageWaitingTime(int[][] customers) {
        int n = customers.length, t = 0;
        double res = 0;
        for (int i = 0; i < n; i++) {
            t = Math.max(t, customers[i][0]) + customers[i][1];
            res += t - customers[i][0];
        }
        return res / n;
    }
}