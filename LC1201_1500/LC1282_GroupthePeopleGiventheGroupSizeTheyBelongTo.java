package LC1201_1500;
import java.util.*;
public class LC1282_GroupthePeopleGiventheGroupSizeTheyBelongTo {
    /**
     * There are n people that are split into some unknown number of groups. Each person is labeled with a unique ID
     * from 0 to n - 1.
     *
     * You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person i is in.
     * For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.
     *
     * Return a list of groups such that each person i is in a group of size groupSizes[i].
     *
     * Each person should appear in exactly one group, and every person must be in a group. If there are multiple
     * answers, return any of them. It is guaranteed that there will be at least one valid solution for the given input.
     *
     * Input: groupSizes = [3,3,3,3,3,1,3]
     * Output: [[5],[0,1,2],[3,4,6]]
     *
     * Input: groupSizes = [2,1,3,3,3,2]
     * Output: [[1],[0,5],[2,3,4]]
     *
     * Constraints:
     *
     * groupSizes.length == n
     * 1 <= n <= 500
     * 1 <= groupSizes[i] <= n
     * @param groupSizes
     * @return
     */
    // time = O(n), space = O(n)
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = groupSizes.length;
        for (int i = 0; i < n; i++) {
            int x = groupSizes[i];
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(i);
            if (map.get(x).size() == x) {
                res.add(map.get(x));
                map.put(x, new ArrayList<>());
            }
        }
        return res;
    }
}
