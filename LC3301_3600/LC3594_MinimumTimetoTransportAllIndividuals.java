package LC3301_3600;
import java.util.*;
public class LC3594_MinimumTimetoTransportAllIndividuals {
    /**
     * You are given n individuals at a base camp who need to cross a river to reach a destination using a single boat.
     * The boat can carry at most k people at a time. The trip is affected by environmental conditions that vary
     * cyclically over m stages.
     *
     * Each stage j has a speed multiplier mul[j]:
     *
     * If mul[j] > 1, the trip slows down.
     * If mul[j] < 1, the trip speeds up.
     * Each individual i has a rowing strength represented by time[i], the time (in minutes) it takes them to cross
     * alone in neutral conditions.
     *
     * Rules:
     *
     * A group g departing at stage j takes time equal to the maximum time[i] among its members, multiplied by mul[j]
     * minutes to reach the destination.
     * After the group crosses the river in time d, the stage advances by floor(d) % m steps.
     * If individuals are left behind, one person must return with the boat. Let r be the index of the returning person,
     * the return takes time[r] × mul[current_stage], defined as return_time, and the stage advances by
     * floor(return_time) % m.
     * Return the minimum total time required to transport all individuals. If it is not possible to transport all
     * individuals to the destination, return -1.
     *
     * Input: n = 1, k = 1, m = 2, time = [5], mul = [1.0,1.3]
     * Output: 5.00000
     *
     * Input: n = 3, k = 2, m = 3, time = [2,5,8], mul = [1.0,1.5,0.75]
     * Output: 14.50000
     *
     * Input: n = 2, k = 1, m = 2, time = [10,10], mul = [2.0,2.0]
     * Output: -1.00000
     *
     * Constraints:
     *
     * 1 <= n == time.length <= 12
     * 1 <= k <= 5
     * 1 <= m <= 5
     * 1 <= time[i] <= 100
     * m == mul.length
     * 0.5 <= mul[i] <= 2.0
     *
     * @param n
     * @param k
     * @param m
     * @param time
     * @param mul
     * @return
     */
    // time = O(MlogM), space = O(M), M = n * m * 3^m
    PriorityQueue<Node> pq;
    double[][] dis;
    public double minTime(int n, int k, int m, int[] time, double[] mul) {
        int full = 1 << n;
        int[] mt = new int[full];
        // 计算每个 time 子集的最大值
        for (int i = 0; i < n; i++) {
            int b = 1 << i;
            for (int j = 0; j < b; j++) {
                mt[j | b] = Math.max(mt[j], time[i]);
            }
        }
        // 把 maxTime 中的大小大于 k 的集合改为 inf
        for (int i = 0; i < full; i++) {
            if (Integer.bitCount(i) > k) mt[i] = Integer.MAX_VALUE;
        }

        dis = new double[m][full];
        for (int i = 0; i < m; i++) Arrays.fill(dis[i], Double.MAX_VALUE);
        pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.dis, o2.dis));
        add(0, 0, full - 1); // 起点

        while (!pq.isEmpty()) {
            Node t = pq.poll();
            double d = t.dis;
            int stage = t.stage, left = t.mask; // 剩余没有过河的人
            if (left == 0) return d;  // 所有人都过河了
            if (d > dis[stage][left]) continue;

            // 枚举 sub 这群人坐一艘船
            for (int sub = left; sub > 0; sub = (sub - 1) & left) {
                if (mt[sub] == Integer.MAX_VALUE) continue;
                double cost = mt[sub] * mul[stage];
                int cur = (stage + (int)cost) % m;  // 过河后的阶段
                // 所有人都过河了
                if (sub == left) {
                    add(d + cost, cur, 0);
                    continue;
                }
                // 枚举回来的人（可以是之前过河的人）
                for (int s = (full - 1) ^ left ^ sub, lb = 0; s > 0; s ^= lb) {
                    lb = s & -s;
                    double rt = mt[lb] * mul[cur];
                    add(d + cost + rt, (cur + (int)rt) % m, left ^ sub ^ lb);
                }
            }
        }
        return -1;
    }

    private void add(double d, int stage, int mask) {
        if (d < dis[stage][mask]) {
            dis[stage][mask] = d;
            pq.offer(new Node(d, stage, mask));
        }
    }

    class Node {
        double dis;
        int stage, mask;
        public Node(double dis, int stage, int mask) {
            this.dis = dis;
            this.stage = stage;
            this.mask = mask;
        }
    }
}
/**
 * 转化成图论的问题：阶段，剩下哪些人
 * Dijkstra: 起点 -> 终点的最短路，带权有环图
 * 局部最优 !=> 全局最优
 */