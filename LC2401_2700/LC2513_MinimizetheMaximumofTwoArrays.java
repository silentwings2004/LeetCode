package LC2401_2700;

public class LC2513_MinimizetheMaximumofTwoArrays {
    /**
     * We have two arrays arr1 and arr2 which are initially empty. You need to add positive integers to them such that
     * they satisfy all the following conditions:
     *
     * arr1 contains uniqueCnt1 distinct positive integers, each of which is not divisible by divisor1.
     * arr2 contains uniqueCnt2 distinct positive integers, each of which is not divisible by divisor2.
     * No integer is present in both arr1 and arr2.
     * Given divisor1, divisor2, uniqueCnt1, and uniqueCnt2, return the minimum possible maximum integer that can be
     * present in either array.
     *
     * Input: divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
     * Output: 4
     *
     * Input: divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
     * Output: 3
     *
     * Input: divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
     * Output: 15
     *
     * Constraints:
     *
     * 2 <= divisor1, divisor2 <= 10^5
     * 1 <= uniqueCnt1, uniqueCnt2 < 10^9
     * 2 <= uniqueCnt1 + uniqueCnt2 <= 10^9
     * @param divisor1
     * @param divisor2
     * @param uniqueCnt1
     * @param uniqueCnt2
     * @return
     */
    // time = O((logn)^2), space = O(1)
    int d1, d2, uc1, uc2;
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        d1 = divisor1;
        d2 = divisor2;
        uc1 = uniqueCnt1;
        uc2 = uniqueCnt2;

        long l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return (int) r;
    }

    private boolean check(long mid) {
        long x1 = mid / d1, x2 = mid / d2, common = mid / lcm(d1, d2);
        return mid - x1 >= uc1 && mid - x2 >= uc2 && mid - common >= uc1 + uc2;
    }

    private long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}