package LC3301_3600;
import java.util.*;
public class LC3377_DigitOperationstoMakeTwoIntegersEqual {
    /**
     * You are given two integers n and m that consist of the same number of digits.
     *
     * You can perform the following operations any number of times:
     *
     * Choose any digit from n that is not 9 and increase it by 1.
     * Choose any digit from n that is not 0 and decrease it by 1.
     * The integer n must not be a prime number at any point, including its original value and after each operation.
     *
     * The cost of a transformation is the sum of all values that n takes throughout the operations performed.
     *
     * Return the minimum cost to transform n into m. If it is impossible, return -1.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Input: n = 10, m = 12
     * Output: 85
     *
     * Input: n = 4, m = 8
     * Output: -1
     *
     * Input: n = 6, m = 2
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n, m < 10^4
     * n and m consist of the same number of digits.
     * @param n
     * @param m
     * @return
     */
    // S1
    // time = O(mlogm), space = O(m)
    final int inf = 0x3f3f3f3f;
    public int minOperations(int n, int m) {
        if (isPrime(n) || isPrime(m)) return -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{n, n});
        int[] dist = new int[10010];
        Arrays.fill(dist, inf);
        dist[n] = n;

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int cost = t[0], x = t[1];
            if (x == m) return cost;

            int[] a = convert(x);
            for (int i = 0; i < a.length; i++) {
                if (a[i] != 9) {
                    a[i]++;
                    int v = get(a);
                    if (dist[v] > cost + v && !isPrime(v)) {
                        dist[v] = cost + v;
                        pq.offer(new int[]{dist[v], v});
                    }
                    a[i]--;
                }
                if (a[i] != 0) {
                    a[i]--;
                    int v = get(a);
                    if (dist[v] > cost + v && !isPrime(v)) {
                        dist[v] = cost + v;
                        pq.offer(new int[]{dist[v], v});
                    }
                    a[i]++;
                }
            }
        }
        return -1;
    }

    private int[] convert(int x) {
        List<Integer> q = new ArrayList<>();
        while (x > 0) {
            q.add(x % 10);
            x /= 10;
        }
        Collections.reverse(q);
        int[] a = new int[q.size()];
        for (int i = 0; i < q.size(); i++) a[i] = q.get(i);
        return a;
    }

    private int get(int[] a) {
        int v = 0;
        for (int x : a) v = v * 10 + x;
        return v;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    // S2
    // time = O(mlogm), space = O(m)
    class Solution {
        final int N = 10010, inf = 0x3f3f3f3f;
        int[] primes;
        int cnt;
        boolean[] st, isPrime;
        public int minOperations(int n, int m) {
            primes = new int[N];
            st = new boolean[N];
            st[0] = st[1] = true;
            init();

            if (!st[n] || !st[m]) return -1;

            int sz = String.valueOf(n).length();
            int[] dist = new int[(int)Math.pow(10, sz)];
            Arrays.fill(dist, inf);
            dist[n] = n;

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
            pq.offer(new int[]{n, n});
            while (!pq.isEmpty()) {
                int[] t = pq.poll();
                int d = t[0], x = t[1];
                if (x == m) return d;
                if (d > dist[x]) continue;

                int pow10 = 1;
                for (int v = x; v > 0; v /= 10) {
                    int u = v % 10;
                    if (u > 0) {
                        int y = x - pow10;
                        int nd = d + y;
                        if (st[y] && nd < dist[y]) {
                            dist[y] = nd;
                            pq.offer(new int[]{nd, y});
                        }
                    }
                    if (u < 9) {
                        int y = x + pow10;
                        int nd = d + y;
                        if (st[y] && nd < dist[y]) {
                            dist[y] = nd;
                            pq.offer(new int[]{nd, y});
                        }
                    }
                    pow10 *= 10;
                }
            }
            return -1;
        }

        private void init() {
            for (int i = 2; i < N; i++) {
                if (!st[i]) primes[cnt++] = i;
                for (int j = 0; i * primes[j] < N; j++) {
                    st[i * primes[j]] = true;
                    if (i % primes[j] == 0) break;
                }
            }
        }
    }
}