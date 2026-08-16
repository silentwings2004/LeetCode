package LC3901_4200;

public class LC4020_ElevatorRequestsI {
    /**
     * You are given an integer n denoting the number of floors in a building, where the floors are numbered from 0 to
     * n - 1.
     *
     * You are also given an integer array requests, where requests represents the sequence of floor requests.
     *
     * An elevator starts at floor 0, and follows these rules:
     *
     * The elevator moves one floor per second.
     * The elevator serves requests in the given order.
     * If the elevator is already on the requested floor, no movement is needed.
     * After serving a request, the elevator immediately starts moving toward the next request.
     * Return the total time (in seconds) required to serve all requests.
     *
     * Input: n = 5, requests = [2,1,4,3]
     * Output: 7
     *
     * Input: n = 3, requests = [2,0,0]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 1 <= requests.length <= 100
     * 0 <= requests[i] <= n - 1
     * @param n
     * @param requests
     * @return
     */
    // time = O(n), space = O(1)
    public int elevatorRequests(int n, int[] requests) {
        int res = 0, cur = 0;
        for (int r : requests) {
            res += Math.abs(r - cur);
            cur = r;
        }
        return res;
    }
}