package LC2700_3000;

public class LC2992_NumberofSelfDivisiblePermutations {
    /**
     * Given an integer n, return the number of permutations of the 1-indexed array nums = [1, 2, ..., n], such that
     * it's self-divisible.
     *
     * Array nums is self-divisible if for every 1 <= i <= n, at least one of the following conditions holds:
     *
     * nums[i] % i == 0
     * i % nums[i] == 0
     * A permutation of an array is a rearrangement of the elements of that array, for example here are all of the
     * permutations of the array [1, 2, 3]:
     *
     * [1, 2, 3]
     * [1, 3, 2]
     * [2, 1, 3]
     * [2, 3, 1]
     * [3, 1, 2]
     * [3, 2, 1]
     *
     * Input: n = 1
     * Output: 1
     *
     * Input: n = 2
     * Output: 2
     *
     * Input: n = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 15
     * @param n
     * @return
     */
    // time = O(2^n), space = O(n)
    int n, res;
    boolean[] st;
    public int selfDivisiblePermutationCount(int n) {
        this.n = n;
        st = new boolean[n + 1];
        dfs(1);
        return res;
    }

    private void dfs(int u) {
        if (u == n + 1) res++;
        else {
            for (int i = 1; i <= n; i++) {
                if (st[i]) continue;
                if (i % u == 0 || u % i == 0) {
                    st[i] = true;
                    dfs(u + 1);
                    st[i] = false;
                }
            }
        }
    }
}