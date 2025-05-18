package LC601_900;
import java.util.*;
public class LC781_RabbitsinForest {
    /**
     * There is a forest with an unknown number of rabbits. We asked n rabbits "How many rabbits have the same color as
     * you?" and collected the answers in an integer array answers where answers[i] is the answer of the ith rabbit.
     *
     * Given the array answers, return the minimum number of rabbits that could be in the forest.
     *
     * Input: answers = [1,1,2]
     * Output: 5
     *
     * Input: answers = [10,10,10]
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= answers.length <= 1000
     * 0 <= answers[i] < 1000
     * @param answers
     * @return
     */
    // time = O(n), space = O(n)
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : answers) map.put(x, map.getOrDefault(x, 0) + 1);
        int res = 0;
        for (int k : map.keySet()) {
            int v = map.get(k);
            res += (v + k) / (k + 1) * (k + 1);
        }
        return res;
    }
}