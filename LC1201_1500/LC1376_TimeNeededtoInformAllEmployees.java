package LC1201_1500;
import java.util.*;
public class LC1376_TimeNeededtoInformAllEmployees {
    /**
     * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one
     * with headID.
     *
     * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the
     * i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree
     * structure.
     *
     * The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his
     * direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent
     * news.
     *
     * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i]
     * minutes, all his direct subordinates can start spreading the news).
     *
     * Return the number of minutes needed to inform all the employees about the urgent news.
     *
     * Input: n = 1, headID = 0, manager = [-1], informTime = [0]
     * Output: 0
     *
     * Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 0 <= headID < n
     * manager.length == n
     * 0 <= manager[i] < n
     * manager[headID] == -1
     * informTime.length == n
     * 0 <= informTime[i] <= 1000
     * informTime[i] == 0 if employee i has no subordinates.
     * It is guaranteed that all the employees can be informed.
     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    int[] h, e, ne, w;
    int idx;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        h = new int[N];
        e = new int[N];
        ne = new int[N];
        w = new int[N];
        Arrays.fill(h, -1);
        idx = 0;

        for (int i = 0; i < n; i++) {
            int a = i, b = manager[i];
            if (b != -1) add(b, a, informTime[b]);
        }
        return dfs(headID);
    }

    private int dfs(int u) {
        int t = 0;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            t = Math.max(t, dfs(j) + w[i]);
        }
        return t;
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}