package LC3301_3600;

public class LC3479_FruitsIntoBasketsIII {
    /**
     * You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents the
     * quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
     *
     * From left to right, place the fruits according to these rules:
     *
     * Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the
     * quantity of that fruit type.
     * Each basket can hold only one type of fruit.
     * If a fruit type cannot be placed in any basket, it remains unplaced.
     * Return the number of fruit types that remain unplaced after all possible allocations are made.
     *
     * Input: fruits = [4,2,5], baskets = [3,5,4]
     * Output: 1
     *
     * Input: fruits = [3,6,1], baskets = [6,4,7]
     * Output: 0
     *
     * Constraints:
     *
     * n == fruits.length == baskets.length
     * 1 <= n <= 10^5
     * 1 <= fruits[i], baskets[i] <= 10^9
     * @param fruits
     * @param baskets
     * @return
     */
    // time = O(nlogn), space = O(n)
    Node[] tr;
    int[] a;
    int n;
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        n = baskets.length;
        a = baskets;
        tr = new Node[n << 2];
        build(1, 1, n);

        int res = 0;
        for (int x : fruits) {
            if (tr[1].v < x) res++;
            else {
                int idx = query(1, x);
                update(1, idx, -1);
            }
        }
        return res;
    }

    private void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) tr[u].v = a[r - 1];
        else {
            int mid = l + r >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            pushup(u);
        }
    }

    private void pushup(int u) {
        tr[u].v = Math.max(tr[u << 1].v, tr[u << 1 | 1].v);
    }

    private void update(int u, int x, int v) {
        if (tr[u].l == tr[u].r && tr[u].l == x) {
            tr[u].v = v;
            return;
        }
        int mid = tr[u].l + tr[u].r >> 1;
        if (x <= mid) update(u << 1, x, v);
        else update(u << 1 | 1, x, v);
        pushup(u);
    }

    private int query(int u, int v) {
        if (tr[u].l == tr[u].r) return tr[u].r;
        if (tr[u << 1].v >= v) return query(u << 1, v);
        return query(u << 1 | 1, v);
    }

    class Node {
        int l, r;
        int v;
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    // S2: 分块
    // time = O(n^(3/2)), space = O(n^(1/2))
    public int numOfUnplacedFruits2(int[] fruits, int[] baskets) {
        int n = baskets.length;
        int m = (int)Math.sqrt(n);
        int k = (n + m - 1) / m; // total k sections, each section has length of m
        int[] a = new int[k];
        for (int i = 0; i < n; i++) {
            a[i / m] = Math.max(a[i / m], baskets[i]);
        }

        int res = 0;
        for (int x : fruits) {
            int unset = 1;
            for (int y = 0; y < k; y++) {
                if (a[y] < x) continue;
                boolean f = false;
                a[y] = 0;
                for (int i = 0; i < m; i++) {
                    int pos = y * m + i;
                    if (pos < n && baskets[pos] >= x && !f) {
                        f = true;
                        baskets[pos] = 0;
                    }
                    if (pos < n) a[y] = Math.max(a[y], baskets[pos]);
                }
                unset = 0;
                break;
            }
            res += unset;
        }
        return res;
    }
}