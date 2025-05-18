package LC2401_2700;
import java.util.*;
public class LC2561_RearrangingFruits {
    /**
     * You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and
     * basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can
     * use the following operation as many times as you want:
     *
     * Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
     * The cost of the swap is min(basket1[i],basket2[j]).
     * Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
     *
     * Return the minimum cost to make both the baskets equal or -1 if impossible.
     *
     * Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
     * Output: 1
     *
     * Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
     * Output: -1
     *
     * Constraints:
     *
     * basket1.length == bakste2.length
     * 1 <= basket1.length <= 10^5
     * 1 <= basket1[i],basket2[i] <= 10^9
     * @param basket1
     * @param basket2
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long minCost(int[] basket1, int[] basket2) {
        int n = basket1.length, mk = (int) 2e9;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = basket1[i], b = basket2[i];
            mk = Math.min(mk, Math.min(a, b));
            map.put(a, map.getOrDefault(a, 0) + 1);
            map.put(b, map.getOrDefault(b, 0) - 1);
            if (map.get(b) == 0) map.remove(b);
        }
        int tot = 0;
        List<int[]> q1 = new ArrayList<>();
        List<int[]> q2 = new ArrayList<>();
        for (int x : map.keySet()) {
            int v = map.get(x);
            tot += v;
            if (Math.abs(v) % 2 != 0) return -1;
            if (v > 0) q1.add(new int[]{x, v / 2});
            else q2.add(new int[]{x, -v / 2});
        }
        if (tot != 0) return -1;

        Collections.sort(q1, (o1, o2) -> o1[0] - o2[0]);
        Collections.sort(q2, (o1, o2) -> o1[0] - o2[0]);
        int i = 0, j = q2.size() - 1;
        long res = 0;
        while (i < q1.size() && j >= 0) {
            int t = Math.min(q1.get(i)[1], q2.get(j)[1]);
            long v1 = (long) Math.min(q1.get(i)[0], q2.get(j)[0]) * t;
            long v2 = (long) t * (mk == q1.get(i)[0] ? 0 : mk) + (long) t * (mk == q2.get(j)[0] ? 0 : mk);
            res += Math.min(v1, v2);
            q1.get(i)[1] -= t;
            q2.get(j)[1] -= t;
            if (q1.get(i)[1] == 0) i++;
            else j--;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long minCost2(int[] basket1, int[] basket2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : basket1) map.put(x, map.getOrDefault(x, 0) + 1);
        for (int x : basket2) map.put(x, map.getOrDefault(x, 0) - 1);
        int fk = map.firstKey();

        List<Integer> q = new ArrayList<>();
        for (int k : map.keySet()) {
            int v = map.get(k);
            if (v % 2 != 0) return -1;
            for (int i = 0; i < Math.abs(v) / 2; i++) q.add(k);
        }
        Collections.sort(q);
        long res = 0;
        for (int i = 0; i < q.size() / 2; i++) res += Math.min(q.get(i), fk * 2);
        return res;
    }
}
/**
 * 尽量少交换
 * map[v] = {x, y}  abs(x- y)/2
 * A: a c d e d d d
 * B: b b f f e g h
 *
 * the total cost is the sum of the minimum N elements among A & B
 * {a, h} => a;
 * {a, m}, {h, m} => m * 2
 * 贪心法里的构造题
 */