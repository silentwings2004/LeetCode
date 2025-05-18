package LC3301_3600;
import java.util.*;
public class LC3506_FindTimeRequiredtoEliminateBacterialStrainsII {
    /**
     * You are given an integer array timeReq and an integer splitTime.
     *
     * In the microscopic world of the human body, the immune system faces an extraordinary challenge: combatting a
     * rapidly multiplying bacterial colony that threatens the body's survival.
     *
     * Initially, only one white blood cell (WBC) is deployed to eliminate the bacteria. However, the lone WBC quickly
     * realizes it cannot keep up with the bacterial growth rate.
     *
     * The WBC devises a clever strategy to fight the bacteria:
     *
     * The ith bacterial strain takes timeReq[i] units of time to be eliminated.
     * A single WBC can eliminate only one bacterial strain. Afterwards, the WBC is exhausted and cannot perform any
     * other tasks.
     * A WBC can split itself into two WBCs, but this requires splitTime units of time. Once split, the two WBCs can
     * work in parallel on eliminating the bacteria.
     * Only one WBC can work on a single bacterial strain. Multiple WBCs cannot attack one strain in parallel.
     * You must determine the minimum time required to eliminate all the bacterial strains.
     *
     * Note that the bacterial strains can be eliminated in any order.
     *
     * Input: timeReq = [10,4,5], splitTime = 2
     * Output: 12
     *
     * Input: timeReq = [10,4], splitTime = 5
     * Output:15
     *
     * Constraints:
     *
     * 2 <= timeReq.length <= 10^5
     * 1 <= timeReq[i] <= 10^9
     * 1 <= splitTime <= 10^9
     * @param timeReq
     * @param splitTime
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long minEliminationTime(int[] timeReq, int splitTime) {
        long res = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int x : timeReq) pq.offer(1L * x);
        int n = timeReq.length;
        for (int i = 0; i < n - 1; i++) {
            long a = pq.poll(), b = pq.poll();
            pq.offer(Math.max(a, b) + splitTime);
        }
        return pq.peek();
    }
}