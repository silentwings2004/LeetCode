package LC3601_3900;
import java.util.*;
public class LC3829_DesignRideSharingSystem {
    /**
     * A ride sharing system manages ride requests from riders and availability from drivers. Riders request rides, and
     * drivers become available over time. The system should match riders and drivers in the order they arrive.
     *
     * Implement the RideSharingSystem class:
     *
     * RideSharingSystem() Initializes the system.
     * void addRider(int riderId) Adds a new rider with the given riderId.
     * void addDriver(int driverId) Adds a new driver with the given driverId.
     * int[] matchDriverWithRider() Matches the earliest available driver with the earliest waiting rider and removes
     * both of them from the system. Returns an integer array of size 2 where result = [driverId, riderId] if a match
     * is made. If no match is available, returns [-1, -1].
     * void cancelRider(int riderId) Cancels the ride request of the rider with the given riderId if the rider exists
     * and has not yet been matched.
     *
     * Input:
     * ["RideSharingSystem", "addRider", "addDriver", "addRider", "matchDriverWithRider", "addDriver", "cancelRider",
     * "matchDriverWithRider", "matchDriverWithRider"]
     * [[], [3], [2], [1], [], [5], [3], [], []]
     * Output:
     * [null, null, null, null, [2, 3], null, null, [5, 1], [-1, -1]]
     *
     * Input:
     * ["RideSharingSystem", "addRider", "addDriver", "addDriver", "matchDriverWithRider", "addRider", "cancelRider",
     * "matchDriverWithRider"]
     * [[], [8], [8], [6], [], [2], [2], []]
     * Output:
     * [null, null, null, null, [8, 8], null, null, [-1, -1]]
     *
     * Constraints:
     *
     * 1 <= riderId, driverId <= 1000
     * Each riderId is unique among riders and is added at most once.
     * Each driverId is unique among drivers and is added at most once.
     * At most 1000 calls will be made in total to addRider, addDriver, matchDriverWithRider, and cancelRider.
     */
    // time = O(1), space = O(n)
    Queue<Integer> q1, q2;
    HashSet<Integer> set;
    public LC3829_DesignRideSharingSystem() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        set = new HashSet<>();
    }

    public void addRider(int riderId) {
        q1.offer(riderId);
        set.add(riderId);
    }

    public void addDriver(int driverId) {
        q2.offer(driverId);
    }

    public int[] matchDriverWithRider() {
        while (!q1.isEmpty() && !set.contains(q1.peek())) q1.poll();
        if (q1.isEmpty() || q2.isEmpty()) return new int[]{-1, -1};
        int rider = q1.poll();
        int driver = q2.poll();
        set.remove(rider);
        return new int[]{driver, rider};
    }

    public void cancelRider(int riderId) {
        set.remove(riderId);
    }
}