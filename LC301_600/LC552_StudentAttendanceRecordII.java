package LC301_600;

public class LC552_StudentAttendanceRecordII {
    /**
     * An attendance record for a student can be represented as a string where each character signifies whether the
     * student was absent, late, or present on that day. The record only contains the following three characters:
     *
     * 'A': Absent.
     * 'L': Late.
     * 'P': Present.
     * Any student is eligible for an attendance award if they meet both of the following criteria:
     *
     * The student was absent ('A') for strictly fewer than 2 days total.
     * The student was never late ('L') for 3 or more consecutive days.
     * Given an integer n, return the number of possible attendance records of length n that make a student eligible for
     * an attendance award. The answer may be very large, so return it modulo 10^9 + 7.
     *
     * Input: n = 2
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010, mod = (int) 1e9 + 7;
    public int checkRecord(int n) {
        int[][][] f = new int[N][2][3];
        f[0][0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == 0) f[i + 1][j + 1][0] = (f[i + 1][j + 1][0] + f[i][j][k]) % mod;
                    if (k + 1 <= 2) f[i + 1][j][k + 1] = (f[i + 1][j][k + 1] + f[i][j][k]) % mod;
                    f[i + 1][j][0] = (f[i + 1][j][0] + f[i][j][k]) % mod;
                }
            }
        }

        int res = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                res = (res + f[n][j][k]) % mod;
            }
        }
        return res;
    }
}
/**
 * A的数量 <= 1
 * 连续L的数量<=2
 * f(i,j,k)
 * i:1~n
 * j:0~1
 * k:0~2
 * f[i][j][k] 枚举下一位填什么？ A， L， P
 * A: f[i+1][j+1][0]  j+1<=1?
 * L: f[i+1][j][k+1]  k+1<=2?
 * P: f[i+1][j][0]  一定合法！
 * init: 0个A，0个L => f[0][0][0]=1
 * time = O(n)
 *
 * f(i,j,k)
 * i: 0~n-1
 * j: 0~1 A
 * k: 0~2 L
 *  假设当前状态，然后枚举下一位可以填什么比较好
 *  下一位填A,L,P分别对应转移到到什么状态
 *  A: f(i+1, j+1,0) -> j+1<=1 => j <= 0 -> j = 0
 *  L: f(i+1,j,k+1) -> k+1<=2
 *  P: f(i+1,j,0) -> 一定合法
 *  init: f(0,0,0) = 1
 *  递推一遍 => O(n)
 */
