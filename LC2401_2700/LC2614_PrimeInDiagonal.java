package LC2401_2700;

public class LC2614_PrimeInDiagonal {
    /**
     *You are given a 0-indexed two-dimensional integer array nums.
     *
     * Return the largest prime number that lies on at least one of the diagonals of nums. In case, no prime is present
     * on any of the diagonals, return 0.
     *
     * Note that:
     *
     * An integer is prime if it is greater than 1 and has no positive integer divisors other than 1 and itself.
     * An integer val is on one of thediagonals of nums if there exists an integer i for which nums[i][i] = val or an i
     * for which nums[i][nums.length - i - 1]= val.
     *
     * In the above diagram, one diagonal is [1,5,9] and another diagonal is [3,5,7].
     *
     * Input: nums = [[1,2,3],[5,6,7],[9,10,11]]
     * Output: 11
     *
     * Input: nums = [[1,2,3],[5,17,7],[9,11,10]]
     * Output: 17
     *
     * Constraints:
     *
     * 1 <= nums.length <= 300
     * nums.length == numsi.length
     * 1 <= nums[i][j] <= 4*10^6
     * @param nums
     * @return
     */
    // time = O(n^2* logk), space = O(1)
    public int diagonalPrime(int[][] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int a = nums[i][i], b = nums[i][n - 1 - i];
            if (isPrime(a)) res = Math.max(res, a);
            if (isPrime(b)) res = Math.max(res, b);
        }
        return res;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}