package LC3001_3300;

public class LC3155_MaximumNumberofUpgradableServers {
    /**
     * You have n data centers and need to upgrade their servers.
     *
     * You are given four arrays count, upgrade, sell, and money of length n, which show:
     *
     * The number of servers
     * The cost of upgrading a single server
     * The money you get by selling a server
     * The money you initially have
     * for each data center respectively.
     *
     * Return an array answer, where for each data center, the corresponding element in answer represents the maximum
     * number of servers that can be upgraded.
     *
     * Note that the money from one data center cannot be used for another data center.
     *
     * Input: count = [4,3], upgrade = [3,5], sell = [4,2], money = [8,9]
     * Output: [3,2]
     *
     * Input: count = [1], upgrade = [2], sell = [1], money = [1]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= count.length == upgrade.length == sell.length == money.length <= 10^5
     * 1 <= count[i], upgrade[i], sell[i], money[i] <= 10^5
     * @param count
     * @param upgrade
     * @param sell
     * @param money
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int[] maxUpgrades(int[] count, int[] upgrade, int[] sell, int[] money) {
        int n = count.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0, r = count[i];
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (check(count[i], upgrade[i], sell[i], money[i], mid)) l = mid;
                else r = mid - 1;
            }
            res[i] = r;
        }
        return res;
    }

    private boolean check(int cnt, int upgrade, int sell, int money, int mid) {
        return money + 1L * sell * (cnt - mid) >= 1L * mid * upgrade;
    }
}