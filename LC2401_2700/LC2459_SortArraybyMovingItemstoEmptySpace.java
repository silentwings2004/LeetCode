package LC2401_2700;
import java.util.*;
public class LC2459_SortArraybyMovingItemstoEmptySpace {
    /**
     * You are given an integer array nums of size n containing each element from 0 to n - 1 (inclusive). Each of the
     * elements from 1 to n - 1 represents an item, and the element 0 represents an empty space.
     *
     * In one operation, you can move any item to the empty space. nums is considered to be sorted if the numbers of all
     * the items are in ascending order and the empty space is either at the beginning or at the end of the array.
     *
     * For example, if n = 4, nums is sorted if:
     *
     * nums = [0,1,2,3] or
     * nums = [1,2,3,0]
     * ...and considered to be unsorted otherwise.
     *
     * Return the minimum number of operations needed to sort nums.
     *
     *
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int sortArray(int[] nums) {
        return Math.min(helper(nums.clone(), 0), helper(nums.clone(), 1));
    }

    private int helper(int[] a, int t) {
        int n = a.length, res = 0;
        int diff = t == 0 ? -1 : 0, pos0 = t == 0 ? n - 1 : 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(a[i], i);
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i < n; i++) set.add(i);

        while (set.size() > 0) {
            int k = map.get(0);
            if (k != pos0) {
                int v = k - diff;
                int j = map.get(v);
                map.put(v, k);
                a[k] = v;
                map.put(0, j);
                a[j] = 0;
                res++;
                set.remove(v);
            } else {
                while (set.size() > 0) {
                    int x = set.first();
                    int j = map.get(x);
                    if (x + diff == j) set.remove(x);
                    else {
                        int y = a[x + diff];
                        map.put(x, x + diff);
                        a[x + diff] = x;
                        map.put(y, k);
                        a[k] = y;
                        map.put(0, j);
                        a[j] = 0;
                        res += 2;
                        set.remove(x);
                        break;
                    }
                }
            }
        }
        return res;
    }
}
