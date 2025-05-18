package LC1201_1500;

public class LC1304_FindNUniqueIntegersSumuptoZero {
    /**
     * Given an integer n, return any array containing n unique integers such that they add up to 0.
     *
     * Input: n = 5
     * Output: [-7,-1,1,3,4]
     *
     * Input: n = 3
     * Output: [-1,0,1]
     *
     * Input: n = 1
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int[] sumZero(int n) {
        int[] res = new int[n];
        for (int i = 1, j = 0; i <= n / 2; i++, j += 2) {
            res[j] = i;
            res[j + 1] = -i;
        }
        if (n % 2 == 1) res[n - 1] = 0;
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] sumZero2(int n) {
        int[] res = new int[n];
        for (int i = 1; i <= n - 1; i++) res[i - 1] = i;
        res[n - 1] = -(1 + n - 1) * (n - 1) / 2;
        return res;
    }
}