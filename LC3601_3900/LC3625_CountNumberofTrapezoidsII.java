package LC3601_3900;
import java.util.*;
public class LC3625_CountNumberofTrapezoidsII {
    /**
     * You are given a 2D integer array points where points[i] = [xi, yi] represents the coordinates of the ith point
     * on the Cartesian plane.
     *
     * Return the number of unique trapezoids that can be formed by choosing any four distinct points from points.
     *
     * A trapezoid is a convex quadrilateral with at least one pair of parallel sides. Two lines are parallel if and
     * only if they have the same slope.
     *
     * Input: points = [[-3,2],[3,0],[2,3],[3,2],[2,-3]]
     * Output: 2
     *
     * Input: points = [[0,0],[1,0],[0,1],[2,1]]
     * Output: 1
     *
     * Constraints:
     *
     * 4 <= points.length <= 500
     * –1000 <= xi, yi <= 1000
     * All points are pairwise distinct.
     * @param points
     * @return
     */
    // S1
    // time = O(n^2), space = O(n^2)
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(points[i][0], points[i][1]);

        HashMap<String, HashMap<Long, Long>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point d = pts[j].subtract(pts[i]);
                long[] slope = normalize(d.x, d.y);
                long c = slope[1] * pts[i].x - slope[0] * pts[i].y;
                String h = slope[0] + "#" + slope[1];
                map.putIfAbsent(h, new HashMap<>());
                map.get(h).put(c,map.get(h).getOrDefault(c, 0L) + 1);
            }
        }

        long s = 0;
        for (HashMap<Long, Long> lines : map.values()) {
            long ts = 0;
            for (long t : lines.values()) {
                s += t * ts;
                ts += t;
            }
        }

        HashMap<Long, Entry> cnt = new HashMap<>();
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++) {
                long mx = pts[i].x + pts[j].x;
                long my = pts[i].y + pts[j].y;
                long mh = (mx << 32) ^ (my & 0xffffffffL);

                Point d = pts[j].subtract(pts[i]);
                long[] slope = normalize(d.x, d.y);
                long sh = (slope[0] << 32) ^ (slope[1] & 0xffffffffL);

                Entry ent = cnt.getOrDefault(mh, new Entry());
                ent.tot++;
                ent.sl.put(sh, ent.sl.getOrDefault(sh, 0) + 1);
                cnt.put(mh, ent);
            }
        }

        long p = 0;
        for (Entry e : cnt.values()) {
            if (e.tot < 2) continue;
            long t = 1L * e.tot * (e.tot - 1) / 2, r = 0;
            for (int v : e.sl.values()) {
                if (v > 1) r += 1L * v * (v - 1) / 2;
            }
            p += t - r;
        }
        long res = s - p;
        return (int)res;
    }

    class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        private Point subtract(Point o) {
            return new Point(x - o.x, y - o.y);
        }
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long[] normalize(long dx, long dy) {
        if (dx < 0) {
            dx = -dx;
            dy = -dy;
        } else if (dx == 0 && dy < 0) dy = -dy;
        long g = gcd(Math.abs(dx), Math.abs(dy));
        return new long[]{dx / g, dy / g};
    }

    class Entry {
        int tot;
        HashMap<Long, Integer> sl;
        public Entry() {
            this.tot = 0;
            this.sl = new HashMap<>();
        }
    }

    // S2
    // time = O(n^2), space = O(n^2)
    public int countTrapezoids2(int[][] points) {
        HashMap<Double, HashMap<Double, Integer>> mp = new HashMap<>(); // {斜率: {截距:个数}}
        HashMap<Integer, HashMap<Double, Integer>> mp2 = new HashMap<>(); // {中点: {截距:个数}}

        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x1 - x2, dy = y1 - y2;
                double k = dx != 0 ? 1.0 * dy / dx : Double.MAX_VALUE;
                double b = dx != 0 ? 1.0 * (y1 * dx - dy * x1) / dx : x1;

                if (k == -0.0) k = 0.0;
                if (b == -0.0) b = 0.0;

                mp.putIfAbsent(k, new HashMap<>());
                mp.get(k).put(b, mp.get(k).getOrDefault(b, 0) + 1);
                int mask = (x1 + x2 + 2000) << 16 | (y1 + y2 + 2000);
                mp2.putIfAbsent(mask, new HashMap<>());
                mp2.get(mask).put(k, mp2.get(mask).getOrDefault(k, 0) + 1);
            }
        }

        int res = 0;
        for (HashMap<Double, Integer> e : mp.values()) {
            int s = 0;
            for (int c : e.values()) {
                res += s * c;
                s += c;
            }
        }

        for (HashMap<Double, Integer> e : mp2.values()) {
            int s = 0;
            for (int c : e.values()) {
                res -= s * c;
                s += c;
            }
        }
        return res;
    }
}
/**
 * O(n^2) 先分组 按斜率
 * k = 0
 * k = inf
 * 斜率相同的就可以按LC3623来做
 * 注意：保证选出的线段不能共线
 * 怎么区分？ => 把截距算出来 => 截距不同一定不共线, b = y - k * x
 * map[k:{map[b, int]}] 哈希表套哈希表
 * 还有一种情况：平行四边形，两对平行的线段 => 统计2次，重复！
 * => 必须减去平行四边形的个数
 * 平行四边形的两条对角线的交点，刚好是这两条对角线的中点
 * 多点共线
 * 只要斜率不同就不会共线
 */