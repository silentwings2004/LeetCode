package LC901_1200;
import java.util.*;
public class LC1086_HighFive {
    /**
     * Given a list of the scores of different students, items, where items[i] = [IDi, scorei] represents one score from
     * a student with IDi, calculate each student's top five average.
     *
     * Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] represents the student
     * with IDj and their top five average. Sort result by IDj in increasing order.
     *
     * A student's top five average is calculated by taking the sum of their top five scores and dividing it by 5 using
     * integer division.
     *
     * Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
     * Output: [[1,87],[2,88]]
     *
     * Input: items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
     * Output: [[1,100],[7,100]]
     *
     * Constraints:
     *
     * 1 <= items.length <= 1000
     * items[i].length == 2
     * 1 <= IDi <= 1000
     * 0 <= scorei <= 100
     * For each IDi, there will be at least five scores.
     * @param items
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[][] highFive(int[][] items) {
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int[] x : items) {
            int id = x[0], score = x[1];
            map.putIfAbsent(id, new ArrayList<>());
            map.get(id).add(score);
        }

        List<int[]> res = new ArrayList<>();
        for (int key : map.keySet()) {
            List<Integer> q = map.get(key);
            Collections.sort(q, (o1, o2) -> o2 - o1);
            int sum = 0;
            for (int i = 0; i < 5; i++) sum += q.get(i);
            res.add(new int[]{key, sum / 5});
        }
        Collections.sort(res, (o1, o2) -> o1[0] - o2[0]);
        return res.toArray(new int[res.size()][]);
    }
}