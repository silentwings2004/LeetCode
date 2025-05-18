package LC301_600;

public class LC367_ValidPerfectSquare {
    /**
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.
     *
     * Follow up: Do not use any built-in library function such as sqrt.
     *
     * Input: num = 16
     * Output: true
     *
     * Input: num = 14
     * Output: false
     *
     * Constraints:
     *
     * 1 <= num <= 2^31 - 1
     * @param num
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean isPerfectSquare(int num) {
        long l = 1, r = num;
        while (l < r) {
            long mid = l + r >> 1;
            if (mid < num / mid) l = mid + 1;
            else r = mid;
        }
        return r * r == num;
    }
}
