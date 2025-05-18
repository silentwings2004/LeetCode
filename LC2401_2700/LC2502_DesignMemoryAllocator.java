package LC2401_2700;
import java.util.*;
public class LC2502_DesignMemoryAllocator {
    /**
     * You are given an integer n representing the size of a 0-indexed memory array. All memory units are initially free.
     *
     * You have a memory allocator with the following functionalities:
     *
     * Allocate a block of size consecutive free memory units and assign it the id mID.
     * Free all memory units with the given id mID.
     * Note that:
     *
     * Multiple blocks can be allocated to the same mID.
     * You should free all the memory units with mID, even if they were allocated in different blocks.
     * Implement the Allocator class:
     *
     * Allocator(int n) Initializes an Allocator object with a memory array of size n.
     * int allocate(int size, int mID) Find the leftmost block of size consecutive free memory units and allocate it
     * with the id mID. Return the block's first index. If such a block does not exist, return -1.
     * int free(int mID) Free all memory units with the id mID. Return the number of memory units you have freed.
     *
     * Input
     * ["Allocator", "allocate", "allocate", "allocate", "free", "allocate", "allocate", "allocate", "free", "allocate",
     * "free"]
     * [[10], [1, 1], [1, 2], [1, 3], [2], [3, 4], [1, 1], [1, 1], [1], [10, 2], [7]]
     * Output
     * [null, 0, 1, 2, 1, 3, 1, 6, 3, -1, 0]
     *
     * Constraints:
     *
     * 1 <= n, size, mID <= 1000
     * At most 1000 calls will be made to allocate and free.
     * @param n
     */
    // S1
    int n;
    int[] id;
    public LC2502_DesignMemoryAllocator(int n) {
        this.n = n;
        id = new int[n];
    }
    // time = O(n^2), space = O(n)
    public int allocate(int size, int mID) {
        for (int i = 0; i < n; i++) {
            if (id[i] != 0) continue;
            int j = i + 1;
            while (j < n && j - i < size && id[j] == 0) j++;
            if (j - i >= size) {
                for (int k = i; k < j; k++) {
                    id[k] = mID;
                }
                return i;
            }
            i = j - 1;
        }
        return -1;
    }
    // time = O(n), space = O(n)
    public int free(int mID) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (id[i] == mID) {
                id[i] = 0;
                res++;
            }
        }
        return res;
    }

    // S2
    class Allocator {
        HashMap<Integer, List<Integer>> map;
        boolean[] st;
        int n;
        public Allocator(int n) {
            map = new HashMap<>();
            st = new boolean[n];
            this.n = n;
        }
        // time = O(n), space = O(n)
        public int allocate(int size, int mID) {
            int start = -1;
            for (int i = 0; i < n; i++) {
                if (st[i]) continue;
                int j = i;
                while (j < n && !st[j]) j++;
                int len = j - i;
                if (len >= size) {
                    start = i;
                    break;
                }
            }
            if (start == -1) return -1;
            map.putIfAbsent(mID, new ArrayList<>());
            for (int i = start; i < start + size; i++) {
                st[i] = true;
                map.get(mID).add(i);
            }
            return start;
        }
        // time = O(n), space = O(n)
        public int free(int mID) {
            if (!map.containsKey(mID)) return 0;
            for (int x : map.get(mID)) st[x] = false;
            int res = map.get(mID).size();
            map.remove(mID);
            return res;
        }
    }

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.free(mID);
 */
}