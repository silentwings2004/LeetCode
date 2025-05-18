package LC2401_2700;

public class LC2432_TheEmployeeThatWorkedontheLongestTask {
    /**
     * There are n employees, each with a unique id from 0 to n - 1.
     *
     * You are given a 2D integer array logs where logs[i] = [idi, leaveTimei] where:
     *
     * idi is the id of the employee that worked on the ith task, and
     * leaveTimei is the time at which the employee finished the ith task. All the values leaveTimei are unique.
     * Note that the ith task starts the moment right after the (i - 1)th task ends, and the 0th task starts at time 0.
     *
     * Return the id of the employee that worked the task with the longest time. If there is a tie between two or more
     * employees, return the smallest id among them.
     *
     * Input: n = 10, logs = [[0,3],[2,5],[0,9],[1,15]]
     * Output: 1
     *
     * Input: n = 26, logs = [[1,1],[3,7],[2,12],[7,17]]
     * Output: 3
     *
     * Input: n = 2, logs = [[0,10],[1,20]]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n <= 500
     * 1 <= logs.length <= 500
     * logs[i].length == 2
     * 0 <= idi <= n - 1
     * 1 <= leaveTimei <= 500
     * idi != idi+1
     * leaveTimei are sorted in a strictly increasing order.
     * @param n
     * @param logs
     * @return
     */
    // time = O(n), space = O(1)
    public int hardestWorker(int n, int[][] logs) {
        int max = 0, last = 0, res = 0;
        for (int[] x : logs) {
            int id = x[0], t = x[1];
            int d = t - last;
            if (d > max) {
                max = d;
                res = id;
            } else if (d == max && id < res) res = id;
            last = t;
        }
        return res;
    }
}
