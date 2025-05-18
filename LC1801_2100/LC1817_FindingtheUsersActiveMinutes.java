package LC1801_2100;
import java.util.*;
public class LC1817_FindingtheUsersActiveMinutes {
    /**
     * You are given the logs for users' actions on LeetCode, and an integer k. The logs are represented by a 2D integer
     * array logs where each logs[i] = [IDi, timei] indicates that the user with IDi performed an action at the minute
     * timei.
     *
     * Multiple users can perform actions simultaneously, and a single user can perform multiple actions in the same
     * minute.
     *
     * The user active minutes (UAM) for a given user is defined as the number of unique minutes in which the user
     * performed an action on LeetCode. A minute can only be counted once, even if multiple actions occur during it.
     *
     * You are to calculate a 1-indexed array answer of size k such that, for each j (1 <= j <= k), answer[j] is the
     * number of users whose UAM equals j.
     *
     * Return the array answer as described above.
     *
     * Input: logs = [[0,5],[1,2],[0,2],[0,5],[1,3]], k = 5
     * Output: [0,2,0,0,0]
     *
     * Input: logs = [[1,1],[2,2],[2,3]], k = 4
     * Output: [1,1,0,0]
     *
     * Constraints:
     *
     * 1 <= logs.length <= 10^4
     * 0 <= IDi <= 10^9
     * 1 <= timei <= 10^5
     * k is in the range [The maximum UAM for a user, 105].
     * @param logs
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] res = new int[k];
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] x : logs) {
            int id = x[0], t = x[1];
            map.putIfAbsent(id, new HashSet<>());
            map.get(id).add(t);
        }

        for (int key : map.keySet()) res[map.get(key).size() - 1]++;
        return res;
    }
}
