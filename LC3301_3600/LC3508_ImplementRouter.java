package LC3301_3600;
import java.util.*;
public class LC3508_ImplementRouter {
    /**
     * Design a data structure that can efficiently manage data packets in a network router. Each data packet consists
     * of the following attributes:
     *
     * source: A unique identifier for the machine that generated the packet.
     * destination: A unique identifier for the target machine.
     * timestamp: The time at which the packet arrived at the router.
     * Implement the Router class:
     *
     * Router(int memoryLimit): Initializes the Router object with a fixed memory limit.
     *
     * memoryLimit is the maximum number of packets the router can store at any given time.
     * If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
     * bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.
     *
     * A packet is considered a duplicate if another packet with the same source, destination, and timestamp already
     * exists in the router.
     * Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
     * int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.
     *
     * Remove the packet from storage.
     * Return the packet as an array [source, destination, timestamp].
     * If there are no packets to forward, return an empty array.
     * int getCount(int destination, int startTime, int endTime):
     *
     * Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified
     * destination and have timestamps in the inclusive range [startTime, endTime].
     * Note that queries for addPacket will be made in increasing order of timestamp.
     *
     * Input:
     * ["Router", "addPacket", "addPacket", "addPacket", "addPacket", "addPacket", "forwardPacket", "addPacket", "getCount"]
     * [[3], [1, 4, 90], [2, 5, 90], [1, 4, 90], [3, 5, 95], [4, 5, 105], [], [5, 2, 110], [5, 100, 110]]
     *
     * Output:
     * [null, true, true, false, true, true, [2, 5, 90], true, 1]
     *
     * Input:
     * ["Router", "addPacket", "forwardPacket", "forwardPacket"]
     * [[2], [7, 4, 90], [], []]
     *
     * Output:
     * [null, true, [7, 4, 90], []]
     *
     * Constraints:
     *
     * 2 <= memoryLimit <= 10^5
     * 1 <= source, destination <= 2 * 10^5
     * 1 <= timestamp <= 10^9
     * 1 <= startTime <= endTime <= 10^9
     * At most 10^5 calls will be made to addPacket, forwardPacket, and getCount methods altogether.
     * queries for addPacket will be made in increasing order of timestamp.
     * @param memoryLimit
     */
    Queue<int[]> q;
    HashSet<String> set;
    HashMap<Integer, List<Integer>> map;
    HashMap<Integer, Integer> pos;
    int limit;
    public LC3508_ImplementRouter(int memoryLimit) {
        q = new LinkedList<>();
        set = new HashSet<>();
        map = new HashMap<>();
        pos = new HashMap<>();
        limit = memoryLimit;
    }
    // time = O(1), space = O(n)
    public boolean addPacket(int source, int destination, int timestamp) {
        String h = source + "#" + destination + "#" + timestamp;
        if (set.add(h)) {
            q.offer(new int[]{source, destination, timestamp});
            map.putIfAbsent(destination, new ArrayList<>());
            pos.putIfAbsent(destination, 0);
            map.get(destination).add(timestamp);
            if (set.size() > limit) forwardPacket();
            return true;
        }
        return false;
    }
    // time = O(1), space = O(n)
    public int[] forwardPacket() {
        if (q.isEmpty()) return new int[0];
        int[] t = q.poll();
        String h = t[0] + "#" + t[1] + "#" + t[2];
        set.remove(h);
        pos.put(t[1], pos.get(t[1]) + 1);
        return t;
    }
    // time = O(logn), space = O(n)
    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> p = map.getOrDefault(destination, new ArrayList<>());
        int st = pos.getOrDefault(destination, -1);
        if (st == -1 || st >= p.size()) return 0;
        return get(p, endTime, st) - get(p, startTime - 1, st);
    }

    private int get(List<Integer> p, int t, int l) {
        int r = p.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (p.get(mid) <= t) l = mid;
            else r = mid - 1;
        }
        return p.get(r) <= t ? r : r - 1;
    }
}