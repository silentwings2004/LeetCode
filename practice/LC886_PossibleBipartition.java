package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PossibleBipartition
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 886. Possible Bipartition
 */
public class LC886_PossibleBipartition {
    /**
     * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
     *
     * Each person may dislike some other people, and they should not go into the same group.
     *
     * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same
     * group.
     *
     * Return true if and only if it is possible to split everyone into two groups in this way.
     *
     *
     *
     * Example 1:
     *
     * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
     * Output: true
     * Explanation: group1 [1,4], group2 [2,3]
     *
     * Example 2:
     *
     * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
     * Output: false
     *
     * Example 3:
     *
     * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
     * Output: false
     *
     *
     * Note:
     *
     * 1 <= N <= 2000
     * 0 <= dislikes.length <= 10000
     * 1 <= dislikes[i][j] <= N
     * dislikes[i][0] < dislikes[i][1]
     * There does not exist i != j for which dislikes[i] == dislikes[j].
     * @param N
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        // corner case
        if (dislikes == null) return true;

        // 建图
        List<HashSet<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : dislikes) { // 无向图
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] colors = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (colors[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            colors[i] = 1;
            queue.add(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int color = colors[cur];
                int nextcolor = color == 1 ? 2 : 1;
                for (int nei : graph.get(cur)) {
                    if (colors[nei] == 0) {
                        colors[nei] = nextcolor;
                        queue.offer(nei);
                    } else if (colors[nei] != nextcolor) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
