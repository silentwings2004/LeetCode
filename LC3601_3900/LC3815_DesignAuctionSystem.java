package LC3601_3900;
import java.util.*;
public class LC3815_DesignAuctionSystem {
    /**
     * You are asked to design an auction system that manages bids from multiple users in real time.
     *
     * Each bid is associated with a userId, an itemId, and a bidAmount.
     *
     * Implement the AuctionSystem class:
     *
     * AuctionSystem(): Initializes the AuctionSystem object.
     * void addBid(int userId, int itemId, int bidAmount): Adds a new bid for itemId by userId with bidAmount. If the
     * same userId already has a bid on itemId, replace it with the new bidAmount.
     * void updateBid(int userId, int itemId, int newAmount): Updates the existing bid of userId for itemId to
     * newAmount. It is guaranteed that this bid exists.
     * void removeBid(int userId, int itemId): Removes the bid of userId for itemId. It is guaranteed that this bid
     * exists.
     * int getHighestBidder(int itemId): Returns the userId of the highest bidder for itemId. If multiple users have
     * the same highest bidAmount, return the user with the highest userId. If no bids exist for the item, return -1.
     *
     * Input:
     * ["AuctionSystem", "addBid", "addBid", "getHighestBidder", "updateBid", "getHighestBidder", "removeBid",
     * "getHighestBidder", "getHighestBidder"]
     * [[], [1, 7, 5], [2, 7, 6], [7], [1, 7, 8], [7], [2, 7], [7], [3]]
     * Output:
     * [null, null, null, 2, null, 1, null, 1, -1]
     *
     * Constraints:
     *
     * 1 <= userId, itemId <= 5 * 10^4
     * 1 <= bidAmount, newAmount <= 10^9
     * At most 5 * 10^4 total calls to addBid, updateBid, removeBid, and getHighestBidder.
     * The input is generated such that for updateBid and removeBid, the bid from the given userId for the given itemId
     * will be valid.
     */
    // time = O(logn), space = O(n)
    HashMap<Integer, HashMap<Integer, Integer>> mp1;
    HashMap<Integer, TreeSet<int[]>> mp2;
    public LC3815_DesignAuctionSystem() {
        mp1 = new HashMap<>();
        mp2 = new HashMap<>();
    }

    public void addBid(int userId, int itemId, int bidAmount) {
        mp1.putIfAbsent(itemId, new HashMap<>());
        mp2.putIfAbsent(itemId, new TreeSet<>((o1, o2) -> o1[1] != o2[1] ? o2[1] - o1[1] : o2[0] - o1[0]));
        HashMap<Integer, Integer> userMap = mp1.get(itemId);
        TreeSet<int[]> set = mp2.get(itemId);
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, bidAmount);
            set.add(new int[]{userId, bidAmount});
        } else updateBid(userId, itemId, bidAmount);
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        removeBid(userId, itemId);
        HashMap<Integer, Integer> userMap = mp1.get(itemId);
        TreeSet<int[]> set = mp2.get(itemId);
        userMap.put(userId, newAmount);
        set.add(new int[]{userId, newAmount});
    }

    public void removeBid(int userId, int itemId) {
        HashMap<Integer, Integer> userMap = mp1.get(itemId);
        TreeSet<int[]> set = mp2.get(itemId);
        int price = userMap.get(userId);
        userMap.remove(userId);
        set.remove(new int[]{userId, price});
    }

    public int getHighestBidder(int itemId) {
        if (!mp2.containsKey(itemId) || mp2.get(itemId).size() == 0) return -1;
        TreeSet<int[]> set = mp2.get(itemId);
        return set.first()[0];
    }
}
/**
 * 可以使用懒删除堆来做
 */