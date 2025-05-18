package LC1201_1500;
import java.util.*;
public class LC1488_AvoidFloodinTheCity {
    /**
     * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth
     * lake, the nth lake becomes full of water. If it rains over a lake that is full of water, there will be a flood.
     * Your goal is to avoid floods in any lake.
     *
     * Given an integer array rains where:
     *
     * rains[i] > 0 means there will be rains over the rains[i] lake.
     * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
     * Return an array ans where:
     *
     * ans.length == rains.length
     * ans[i] == -1 if rains[i] > 0.
     * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
     * If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
     *
     * Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing
     * changes.
     *
     * Input: rains = [1,2,3,4]
     * Output: [-1,-1,-1,-1]
     *
     * Input: rains = [1,2,0,0,2,1]
     * Output: [-1,-1,2,1,-1,-1]
     *
     * Input: rains = [1,2,0,1,2]
     * Output: []
     *
     * Constraints:
     *
     * 1 <= rains.length <= 10^5
     * 0 <= rains[i] <= 10^9
     * @param rains
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                if (map.containsKey(rains[i])) {
                    if (set.size() > 0 && set.higher(map.get(rains[i])) != null) {
                        Integer hk = set.higher(map.get(rains[i]));
                        res[hk] = rains[i];
                        set.remove(hk);
                        map.remove(rains[i]);
                    } else return new int[0];
                }
                map.put(rains[i], i);
            } else set.add(i);
        }
        while (set.size() > 0) {
            res[set.first()] = 1;
            set.remove(set.first());
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int[] avoidFlood2(int[] rains) {
        int n = rains.length;
        int[] next = new int[n];
        Arrays.fill(next, n + 1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            int r = rains[i];
            if (r > 0) {
                if (map.containsKey(r)) next[i] = map.get(r);
                map.put(r, i);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        HashMap<Integer, Boolean> st = new HashMap<>();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int r = rains[i];
            if (r > 0) {
                if (st.getOrDefault(r, false)) return new int[0];
                st.put(r, true);
                pq.offer(new int[]{next[i], r});
                res[i] = -1;
            } else {
                if (pq.isEmpty()) res[i] = 1;
                else {
                    int[] t = pq.poll();
                    st.put(t[1], false);
                    res[i] = t[1];
                }
            }
        }
        return res;
    }

    // S3
    // time = O(nlogn), space = O(n)
    public int[] avoidFlood3(int[] rains) {
        int n = rains.length;
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = rains[i];
            if (x > 0) {
                map.putIfAbsent(x, new TreeSet<>());
                map.get(x).add(i);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = rains[i];
            if (x > 0) {
                if (!set.add(x)) return new int[0];
                Integer hk = map.get(x).higher(i);
                if (hk != null) pq.offer(new int[]{hk, x});
                res[i] = -1;
            } else {
                int t = 1;
                if (!pq.isEmpty()) t = pq.poll()[1];
                res[i] = t;
                set.remove(t);
            }
        }
        return res;
    }
}