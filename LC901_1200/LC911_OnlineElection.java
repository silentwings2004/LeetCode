package LC901_1200;
import java.util.*;
public class LC911_OnlineElection {
    /**
     * You are given two integer arrays persons and times. In an election, the ith vote was cast for persons[i] at time
     * times[i].
     *
     * For each query at a time t, find the person that was leading the election at time t. Votes cast at time t will
     * count towards our query. In the case of a tie, the most recent vote (among tied candidates) wins.
     *
     * Implement the TopVotedCandidate class:
     *
     * TopVotedCandidate(int[] persons, int[] times) Initializes the object with the persons and times arrays.
     * int q(int t) Returns the number of the person that was leading the election at time t according to the mentioned
     * rules.
     *
     * Input
     * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
     * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
     * Output
     * [null, 0, 1, 1, 0, 0, 1]
     *
     * Constraints:
     *
     * 1 <= persons.length <= 5000
     * times.length == persons.length
     * 0 <= persons[i] < persons.length
     * 0 <= times[i] <= 109
     * times is sorted in a strictly increasing order.
     * times[0] <= t <= 109
     * At most 104 calls will be made to q.
     * @param persons
     * @param times
     */
    // time = O(nlogn), space = O(n)
    int[] win, times;
    public LC911_OnlineElection(int[] persons, int[] times) {
        this.times = times;
        int n = persons.length;
        win = new int[n];
        int[] sum = new int[n];

        int maxc = 0, maxp = 0;
        for (int i = 0; i < n; i++) {
            int p = persons[i];
            if (++sum[p] >= maxc) {
                maxc = sum[p];
                maxp = p;
            }
            win[i] = maxp;
        }
    }
    // time = O(logn), space = O(1)
    public int q(int t) {
        int k = lower_bound(times, t);
        return win[k];
    }

    private int lower_bound(int[] a, int t) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (a[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return r;
    }
}