package LC1801_2100;

public class LC1954_MinimumGardenPerimetertoCollectEnoughApples {
    /**
     * In a garden represented as an infinite 2D grid, there is an apple tree planted at every integer coordinate.
     * The apple tree planted at an integer coordinate (i, j) has |i| + |j| apples growing on it.
     *
     * You will buy an axis-aligned square plot of land that is centered at (0, 0).
     *
     * Given an integer neededApples, return the minimum perimeter of a plot such that at least neededApples apples are
     * inside or on the perimeter of that plot.
     *
     * The value of |x| is defined as:
     *
     * x if x >= 0
     * -x if x < 0
     *
     * Input: neededApples = 1
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= neededApples <= 10^15
     * @param neededApples
     * @return
     */
    // S1: Cardano's formula + BS
    // time = O(logn), space = O(n)
    public long minimumPerimeter(long neededApples) {
        final int N = 100010;
        long[] s = new long[N];
        for (int i = 1; i < N; i++) s[i] = s[i - 1] + get(i);
        int l = 1, r = N;
        while (l < r) {
            int mid = l + r >> 1;
            if (s[mid] >= neededApples) r = mid;
            else l = mid + 1;
        }
        return 8L * r;
    }

    private long get(int k) {
        long a = k, b = 2L * k;
        return (a + b) * (b - a + 1) * 4 - 4 * (a + b);
    }

    // S2: Enumeration
    // time = O(n^(1/2)), space = O(1)
    public long minimumPerimeter2(long neededApples) {
        long r = 1, total = 0;
        while (total < neededApples) {
            total += 12 * r * r;
            r++;
        }
        return 8 * (r - 1);
    }

    // S3: Math
    // time = O(1), space = O(1)
    public long minimumPerimeter3(long neededApples) {
        long n = (long)Math.cbrt(neededApples / 4.0);
        if (2 * n * (n + 1) * (2 * n + 1) < neededApples) n++;
        return 8L * n;
    }
}
/**
 * total apples = (i * 4 + 2 * i * 4) + ((i + 1) + (i + 2) + ... + (i + i - 1)) * 8
 * = 12i + (i + 1 + 2i - 1) * (i - 1) / 2 * 8 = 12i + 3i * (i - 1) * 4 = 12i^2
 * square sum = 12 * (1^2 + 2^2 + ... + r^2) = 2 * n * (n + 1) * (2n + 1)  ~ n^3 => n ~ 10^5
 *
 * 第1圈上的苹果分布为 2 1 2
 * 第2圈上的苹果分布为 4 3 2 3 4
 * 第2圈上的苹果分布为 6 5 4 3 4 5 6
 * ...
 * 2k 2k-1 ... k+1 k k+1 ... 2k-1, 2k
 */
