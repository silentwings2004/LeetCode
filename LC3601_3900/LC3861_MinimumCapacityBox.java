package LC3601_3900;

public class LC3861_MinimumCapacityBox {
    /**
     * You are given an integer array capacity, where capacity[i] represents the capacity of the ith box, and an integer
     * itemSize representing the size of an item.
     *
     * The ith box can store the item if capacity[i] >= itemSize.
     *
     * Return an integer denoting the index of the box with the minimum capacity that can store the item. If multiple
     * such boxes exist, return the smallest index.
     *
     * If no box can store the item, return -1.
     *
     * Input: capacity = [1,5,3,7], itemSize = 3
     * Output: 2
     *
     * Input: capacity = [3,5,4,3], itemSize = 2
     * Output: 0
     *
     * Input: capacity = [4], itemSize = 5
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= capacity.length <= 100
     * 1 <= capacity[i] <= 100
     * 1 <= itemSize <= 100
     * @param capacity
     * @param itemSize
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumIndex(int[] capacity, int itemSize) {
        int n = capacity.length, res = -1;
        for (int i = 0; i < n; i++) {
            int x = capacity[i];
            if (x >= itemSize && (res == -1 || x < capacity[res])) res = i;
        }
        return res;
    }
}