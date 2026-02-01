package LC3601_3900;
import java.util.*;
public class LC3822_DesignOrderManagementSystem {
    /**
     * You are asked to design a simple order management system for a trading platform.
     *
     * Each order is associated with an orderId, an orderType ("buy" or "sell"), and a price.
     *
     * An order is considered active unless it is canceled.
     *
     * Implement the OrderManagementSystem class:
     *
     * OrderManagementSystem(): Initializes the order management system.
     * void addOrder(int orderId, string orderType, int price): Adds a new active order with the given attributes. It
     * is guaranteed that orderId is unique.
     * void modifyOrder(int orderId, int newPrice): Modifies the price of an existing order. It is guaranteed that the
     * order exists and is active.
     * void cancelOrder(int orderId): Cancels an existing order. It is guaranteed that the order exists and is active.
     * vector<int> getOrdersAtPrice(string orderType, int price): Returns the orderIds of all active orders that match
     * the given orderType and price. If no such orders exist, return an empty list.
     * Note: The order of returned orderIds does not matter.
     *
     * Input:
     * ["OrderManagementSystem", "addOrder", "addOrder", "addOrder", "getOrdersAtPrice", "modifyOrder", "modifyOrder", "getOrdersAtPrice", "cancelOrder", "cancelOrder", "getOrdersAtPrice"]
     * [[], [1, "buy", 1], [2, "buy", 1], [3, "sell", 2], ["buy", 1], [1, 3], [2, 1], ["buy", 1], [3], [2], ["buy", 1]]
     * Output:
     * [null, null, null, null, [2, 1], null, null, [2], null, null, []]
     *
     * Constraints:
     *
     * 1 <= orderId <= 2000
     * orderId is unique across all orders.
     * orderType is either "buy" or "sell".
     * 1 <= price <= 10^9
     * The total number of calls to addOrder, modifyOrder, cancelOrder, and getOrdersAtPrice does not exceed 2000.
     * For modifyOrder and cancelOrder, the specified orderId is guaranteed to exist and be active.
     */
    // time = O(1), space = O(n)
    HashMap<Integer, int[]> mp1;
    HashMap<String, HashSet<Integer>> mp2;
    public LC3822_DesignOrderManagementSystem() {
        mp1 = new HashMap<>();
        mp2 = new HashMap<>();
    }

    public void addOrder(int orderId, String orderType, int price) {
        int t = orderType.equals("buy") ? 0 : 1;
        mp1.put(orderId, new int[]{t, price});
        String h = t + "#" + price;
        mp2.putIfAbsent(h, new HashSet<>());
        mp2.get(h).add(orderId);
    }

    public void modifyOrder(int orderId, int newPrice) {
        int[] x = mp1.get(orderId);
        String h = x[0] + "#" + x[1];
        mp1.get(orderId)[1] = newPrice;
        mp2.get(h).remove(orderId);
        if (mp2.get(h).size() == 0) mp2.remove(h);
        h = x[0] + "#" + newPrice;
        mp2.putIfAbsent(h, new HashSet<>());
        mp2.get(h).add(orderId);
    }

    public void cancelOrder(int orderId) {
        int[] x = mp1.get(orderId);
        mp1.remove(orderId);
        String h = x[0] + "#" + x[1];
        mp2.get(h).remove(orderId);
        if (mp2.get(h).size() == 0) mp2.remove(h);
    }

    public int[] getOrdersAtPrice(String orderType, int price) {
        int t = orderType.equals("buy") ? 0 : 1;
        String h = t + "#" + price;
        int n = mp2.getOrDefault(h, new HashSet<>()).size();
        if (n == 0) return new int[0];
        int[] res = new int[n];
        int i = 0;
        for (int x : mp2.get(h)) res[i++] = x;
        return res;
    }
}