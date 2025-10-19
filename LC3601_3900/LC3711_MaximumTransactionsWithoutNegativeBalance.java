package LC3601_3900;
import java.util.*;
public class LC3711_MaximumTransactionsWithoutNegativeBalance {
    /**
     * You are given an integer array transactions, where transactions[i] represents the amount of the ith transaction:
     *
     * A positive value means money is received.
     * A negative value means money is sent.
     * The account starts with a balance of 0, and the balance must never become negative. Transactions must be
     * considered in the given order, but you are allowed to skip some transactions.
     *
     * Return an integer denoting the maximum number of transactions that can be performed without the balance ever g
     * oing negative.
     *
     * Input: transactions = [2,-5,3,-1,-2]
     * Output: 4
     *
     * Input: transactions = [-1,-2,-3]
     * Output: 0
     *
     * Input: transactions = [3,-2,3,-2,1,-1]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= transactions.length <= 10^5
     * -10^9 <= transactions[i] <= 10^9
     * @param transactions
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxTransactions(int[] transactions) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long s = 0;
        int res = 0;
        for (int x : transactions) {
            s += x;
            res++;
            if (x < 0) pq.offer(x);
            while (s < 0 && !pq.isEmpty()) {
                s -= pq.poll();
                res--;
            }
        }
        return res;
    }
}