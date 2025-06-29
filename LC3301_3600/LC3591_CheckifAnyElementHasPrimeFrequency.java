package LC3301_3600;

public class LC3591_CheckifAnyElementHasPrimeFrequency {
    /**
     * You are given an integer array nums.
     *
     * Return true if the frequency of any element of the array is prime, otherwise, return false.
     *
     * The frequency of an element x is the number of times it occurs in the array.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Input: nums = [1,2,3,4,5,4]
     * Output: true
     *
     * Input: nums = [1,2,3,4,5]
     * Output: false
     *
     * Input: nums = [2,2,2,4,4]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    //S1
    // time = O(nlogk), space = O(1)
    public boolean checkPrimeFrequency(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) cnt[x]++;
        for (int i = 0; i <= 100; i++) {
            if (cnt[i] == 0) continue;
            if (check(cnt[i])) return true;
        }
        return false;
    }

    private boolean check(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
    // S2
    // time = O(n), space = O(n)
    final int N = 101;
    boolean[] st = new boolean[N];
    boolean f = false;
    private void init() {
        if (f) return;
        f = true;
        st[0] = st[1] = true;
        for (int i = 2; i < N; i++) {
            if (st[i]) continue;
            for (int j = i * i; j < N; j += i) {
                st[j] = true;
            }
        }
    }

    public boolean checkPrimeFrequency2(int[] nums) {
        init();
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);
        int[] cnt = new int[mx + 1];
        for (int x : nums) cnt[x]++;
        for (int x : cnt) {
            if (!st[x]) return true;
        }
        return false;
    }
}