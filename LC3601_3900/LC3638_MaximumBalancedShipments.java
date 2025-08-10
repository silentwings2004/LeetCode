package LC3601_3900;

public class LC3638_MaximumBalancedShipments {
    /**
     * You are given an integer array weight of length n, representing the weights of n parcels arranged in a straight
     * line. A shipment is defined as a contiguous subarray of parcels. A shipment is considered balanced if the weight
     * of the last parcel is strictly less than the maximum weight among all parcels in that shipment.
     *
     * Select a set of non-overlapping, contiguous, balanced shipments such that each parcel appears in at most one
     * shipment (parcels may remain unshipped).
     *
     * Return the maximum possible number of balanced shipments that can be formed.
     *
     * Input: weight = [2,5,1,4,3]
     * Output: 2
     *
     * Input: weight = [4,4]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= weight[i] <= 10^9
     * @param weight
     * @return
     */
    // time = O(n), space = O(1)
    public int maxBalancedShipments(int[] weight) {
        int res = 0, mx = -1;
        for (int x : weight) {
            mx = Math.max(mx, x);
            if (x < mx) {
                res++;
                mx = -1;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maxBalancedShipments2(int[] weight) {
        int n = weight.length, res = 0;
        for (int i = 1; i < n; i++) {
            if (weight[i - 1] > weight[i]) {
                res++;
                i++; // 下个子数组至少要有两个数
            }
        }
        return res;
    }
}