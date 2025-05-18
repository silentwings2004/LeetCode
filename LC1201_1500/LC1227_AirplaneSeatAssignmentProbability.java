package LC1201_1500;

public class LC1227_AirplaneSeatAssignmentProbability {
    /**
     * n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat
     * randomly. But after that, the rest of the passengers will:
     *
     * Take their own seat if it is still available, and
     * Pick other seats randomly when they find their seat occupied
     * Return the probability that the nth person gets his own seat.
     *
     * Input: n = 1
     * Output: 1.00000
     *
     * Input: n = 2
     * Output: 0.50000
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public double nthPersonGetsNthSeat(int n) {
        return n > 1 ? 0.5 : 1;
    }
}
/**
 * 第1个人
 * 1. 1 -> 1/n
 * 2. n (x)
 * 3. 2 ~ n - 1 => 设坐到了第j个位置上：
 * 1 =>  1 / (n - j + 1)
 * n => (x)
 * j + 1 ~ n - 1
 * f(n) = 1/n + 1/n * (f(2) + ... + f(n-1)) = 1/n * (f(1) + f(2) + ... + f(n - 1))
 * nf(n) = f(1) + ... + f(n - 1)
 * (n+1)f(n + 1) = f(1) + ... + f(n)
 * (n+1)f(n+1) - nf(n) = f(n)
 * f(n+1) = f(n)  n >= 2
 * 第k个人登机的时候，第1到第k-1个人已经登机了。
 * 除了第一个人的位置未知，其他第2到第k-1个人的位置肯定是已经被占据了（either被本人占据，or被乱坐的人占据）。
 * 此时未占据的位置有n-(k-1)个，另外还有一个座位是不知道主人的（它的主人肯定不是编号2到k-1的乘客）。
 * 所以这n-k+2个位置，只有一份的概率是k号乘客的位置。
 * 所以第k个乘客能坐到自己的位置的概率就是(n-k+1)/(n-k+2)
 * 当n=k时，上面的公式就是1/2.
 * 第k个人坐到自己位置上的概率 = 1/(n-(k-2))  when k = n
 */