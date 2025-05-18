package LC1201_1500;

public class LC1395_CountNumberofTeams {
    /**
     * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
     *
     * You have to form a team of 3 soldiers amongst them under the following rules:
     *
     * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
     * A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where
     * (0 <= i < j < k < n).
     * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
     *
     * Input: rating = [2,5,3,4,1]
     * Output: 3
     *
     * Constraints:
     *
     * n == rating.length
     * 3 <= n <= 1000
     * 1 <= rating[i] <= 10^5
     * All the integers in rating are unique.
     * @param rating
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int numTeams(int[] rating) {
        Fenwick f1 = new Fenwick(100010);
        Fenwick f2 = new Fenwick(100010);
        int n = rating.length;
        int[] l = new int[n], r = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = f1.sum(rating[i] - 1);
            f1.add(rating[i] - 1, 1);
        }
        for (int i = n - 1; i >= 0; i--) {
            r[i] = f2.sum(rating[i] - 1);
            f2.add(rating[i] - 1, 1);
        }

        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            res += l[i] * (n - 1 - i - r[i]) + (i - l[i]) * r[i];
        }
        return res;
    }

    private class Fenwick {
        private int n;
        private int[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new int[n + 1];
        }

        private void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = a[i - 1] + v;
            }
        }

        private int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = ans + a[i - 1];
            }
            return ans;
        }

        private int rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }
    }
}
