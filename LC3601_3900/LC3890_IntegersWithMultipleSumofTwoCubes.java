package LC3601_3900;
import java.util.*;
public class LC3890_IntegersWithMultipleSumofTwoCubes {
    /**
     * You are given an integer n.
     *
     * An integer x is considered good if there exist at least two distinct pairs (a, b) such that:
     *
     * a and b are positive integers.
     * a <= b
     * x = a^3 + b^3
     * Return an array containing all good integers less than or equal to n, sorted in ascending order.
     *
     * Input: n = 4104
     * Output: [1729,4104]
     *
     * Input: n = 578
     * Output: []
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(n^(2/3)), space = O(n^(2/3))
    public List<Integer> findGoodIntegers(int n) {
        int[] cube = new int[1001];
        int t = 0;
        for (int i = 1; i <= 1000; i++) {
            int v = i * i * i;
            if (v > n) break;
            cube[i] = v;
            t = i;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= t; i++) {
            for (int j = i; j <= t; j++) {
                int x = i * i * i + j * j * j;
                if (x > n) break;
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k) >= 2) res.add(k);
        }
        Collections.sort(res);
        return res;
    }
}