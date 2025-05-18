package LC3001_3300;
import java.util.*;
public class LC3119_MaximumNumberofPotholesThatCanBeFixed {
    /**
     * You are given a string road, consisting only of characters "x" and ".", where each "x" denotes a pothole and
     * each "." denotes a smooth road, and an integer budget.
     *
     * In one repair operation, you can repair n consecutive potholes for a price of n + 1.
     *
     * Return the maximum number of potholes that can be fixed such that the sum of the prices of all of the fixes
     * doesn't go over the given budget.
     *
     * Input: road = "..", budget = 5
     *
     * Output: 0
     *
     * Input: road = "..xxxxx", budget = 4
     *
     * Output: 3
     *
     * Input: road = "x.x.xxx...x", budget = 14
     *
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= road.length <= 10^5
     * 1 <= budget <= 10^5 + 1
     * road consists only of characters '.' and 'x'.
     * @param road
     * @param budget
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxPotholes(String road, int budget) {
        int n = road.length(), res = 0;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (road.charAt(i) == 'x') {
                int j = i + 1;
                while (j < n && road.charAt(j) == 'x') j++;
                int len = j - i;
                q.add(len);
                i = j - 1;
            }
        }
        Collections.sort(q, (o1, o2) -> o2 - o1);
        for (int x : q) {
            if (x + 1 <= budget) {
                budget -= x + 1;
                res += x;
                if (budget == 0) break;
            } else {
                res += budget - 1;
                break;
            }
        }
        return res;
    }
}