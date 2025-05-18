package LC601_900;
import java.util.*;
public class LC841_KeysandRooms {
    /**
     * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room
     * may have some keys to access the next room.
     *
     * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1]
     * where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
     *
     * Initially, all the rooms start locked (except for room 0).
     *
     * You can walk back and forth between rooms freely.
     *
     * Return true if and only if you can enter every room.
     *
     * Input: [[1],[2],[3],[]]
     * Output: true
     *
     * Note:
     *
     * 1 <= rooms.length <= 1000
     * 0 <= rooms[i].length <= 1000
     * The number of keys in all rooms combined is at most 3000.
     *
     * @param rooms
     * @return
     */
    // time = O(m + n), space = O(n)
    List<List<Integer>> g;
    boolean[] st;
    int n;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        g = rooms;
        n = g.size();
        st = new boolean[n];

        dfs(0);
        for (int i = 0; i < n; i++) {
            if (!st[i]) return false;
        }
        return true;
    }

    private void dfs(int u) {
        st[u] = true;
        for (int v : g.get(u)) {
            if (!st[v]) dfs(v);
        }
    }
}
