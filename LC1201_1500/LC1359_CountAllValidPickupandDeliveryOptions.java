package LC1201_1500;

public class LC1359_CountAllValidPickupandDeliveryOptions {
    /**
     * Given n orders, each order consist in pickup and delivery services.
     *
     * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
     *
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Input: n = 1
     * Output: 1
     * Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
     *
     * Constraints:
     *
     * 1 <= n <= 500
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int countOrders(int n) {
        long res = 1;
        long M = (long)(1e9 + 7);
        for (int i = 1; i <= n; i++) res = res * i % M;
        for (int i = 2 * n - 1; i >= 1; i -= 2) res = res * i % M;
        return (int) res;
    }

    // S2: Math
    // time = O(n), space = O(1)
    public int countOrders2(int n) {
        long mod = (long)(1e9 + 7);
        long res = 1;
        for (int i = 1; i <= n * 2; i++) {
            if (i % 2 == 1) res = res * i % mod;
            else res = i / 2 * res % mod;
        }
        return (int) res;
    }
}
/**
 * 此题乍看像卡特兰数，但其实没有那么复杂。我们首先将所有的订单排列一下，表示下单的顺序，这样就有A(n,n)种可能。
 * 然后对于给定的下单顺序，那么我们如果确定递送顺序和位置呢？
 * 我们这么考虑。第一单的下单只能在idx=0的地方；那么第一个单的递送可以在之后的任何一个idx，也就是2*n-1种可能。
 * 然后在考虑第二单的下单位置，注意它只可能是在当前一个未被占据的idx，所以并没有选择的空间；
 * 然后第二单的递送安排在什么位置呢？只要安排在当前任意一个未被占据的位置即可，也就是有2*n-3种可能。
 * 以此类推，最后一单的下单和递送位置其实都是没有选择余地的。
 * 所以综上，答案应该是A(n,n)*(2n-1)*(2n-3)*...*1
 *
 * (2n)! / (2^n)
 */