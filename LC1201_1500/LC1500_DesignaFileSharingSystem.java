package LC1201_1500;
import java.util.*;
public class LC1500_DesignaFileSharingSystem {
    /**
     * We will use a file-sharing system to share a very large file which consists of m small chunks with IDs from 1 to
     * m.
     *
     * When users join the system, the system should assign a unique ID to them. The unique ID should be used once for
     * each user, but when a user leaves the system, the ID can be reused again.
     *
     * Users can request a certain chunk of the file, the system should return a list of IDs of all the users who own
     * this chunk. If the user receives a non-empty list of IDs, they receive the requested chunk successfully.
     *
     *
     * Implement the FileSharing class:
     *
     * FileSharing(int m) Initializes the object with a file of m chunks.
     * int join(int[] ownedChunks): A new user joined the system owning some chunks of the file, the system should
     * assign an id to the user which is the smallest positive integer not taken by any other user. Return the assigned
     * id.
     * void leave(int userID): The user with userID will leave the system, you cannot take file chunks from them anymore.
     * int[] request(int userID, int chunkID): The user userID requested the file chunk with chunkID. Return a list of
     * the IDs of all users that own this chunk sorted in ascending order.
     *
     * Input:
     * ["FileSharing","join","join","join","request","request","leave","request","leave","join"]
     * [[4],[[1,2]],[[2,3]],[[4]],[1,3],[2,2],[1],[2,1],[2],[[]]]
     * Output:
     * [null,1,2,3,[2],[1,2],null,[],null,1]
     *
     * Constraints:
     *
     * 1 <= m <= 105
     * 0 <= ownedChunks.length <= min(100, m)
     * 1 <= ownedChunks[i] <= m
     * Values of ownedChunks are unique.
     * 1 <= chunkID <= m
     * userID is guaranteed to be a user in the system if you assign the IDs correctly.
     * At most 104 calls will be made to join, leave and request.
     * Each call to leave will have a matching call for join.
     *
     * Follow-up:
     *
     * What happens if the system identifies the user by their IP address instead of their unique ID and users
     * disconnect and connect from the system with the same IP?
     * If the users in the system join and leave the system frequently without requesting any chunks, will your solution
     * still be efficient?
     * If all users join the system one time, request all files, and then leave, will your solution still be efficient?
     * If the system will be used to share n files where the ith file consists of m[i], what are the changes you have to
     * make?
     * @param m
     */
    HashMap<Integer, TreeSet<Integer>> map;
    HashMap<Integer, HashSet<Integer>> p2f;
    PriorityQueue<Integer> pq;
    int id;
    public LC1500_DesignaFileSharingSystem(int m) {
        map = new HashMap<>();
        p2f = new HashMap<>();
        pq = new PriorityQueue<>();
        id = 1;
    }
    // time = O(nlogn), space = O(n)
    public int join(List<Integer> ownedChunks) {
        int p;
        if (!pq.isEmpty()) p = pq.poll();
        else p = id++;
        for (int x : ownedChunks) {
            map.putIfAbsent(x, new TreeSet<>());
            map.get(x).add(p);
            p2f.putIfAbsent(p, new HashSet<>());
            p2f.get(p).add(x);
        }
        return p;
    }
    // time = O(nlogn), space = O(n)
    public void leave(int userID) {
        pq.offer(userID);
        for (int x : p2f.getOrDefault(userID, new HashSet<>())) map.get(x).remove(userID);
        p2f.remove(userID);
    }
    // time = O(logn), space = O(n)
    public List<Integer> request(int userID, int chunkID) {
        TreeSet<Integer> set = map.getOrDefault(chunkID, new TreeSet<>());
        if (set.size() == 0) return new ArrayList<>();
        else {
            if (set.contains(userID)) return new ArrayList<>(set);
            List<Integer> res = new ArrayList<>(set);
            map.get(chunkID).add(userID);
            p2f.putIfAbsent(userID, new HashSet<>());
            p2f.get(userID).add(chunkID);
            return res;
        }
    }
}