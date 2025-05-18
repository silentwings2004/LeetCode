package LC3301_3600;
import java.util.*;
public class LC3447_AssignElementstoGroupswithConstraints {
    /**
     * You are given an integer array groups, where groups[i] represents the size of the ith group. You are also given
     * an integer array elements.
     *
     * Your task is to assign one element to each group based on the following rules:
     *
     * An element j can be assigned to a group i if groups[i] is divisible by elements[j].
     * If there are multiple elements that can be assigned, assign the element with the smallest index j.
     * If no element satisfies the condition for a group, assign -1 to that group.
     * Return an integer array assigned, where assigned[i] is the index of the element chosen for group i, or -1 if no
     * suitable element exists.
     *
     * Note: An element may be assigned to more than one group.
     *
     * Input: groups = [8,4,3,2,4], elements = [4,2]
     * Output: [0,0,-1,1,0]
     *
     * Input: groups = [2,3,5,7], elements = [5,3,3]
     * Output: [-1,1,0,-1]
     *
     * Input: groups = [10,21,30,41], elements = [2,1]
     * Output: [0,1,0,1]
     *
     * Constraints:
     *
     * 1 <= groups.length <= 10^5
     * 1 <= elements.length <= 10^5
     * 1 <= groups[i] <= 10^5
     * 1 <= elements[i] <= 10^5
     * @param groups
     * @param elements
     * @return
     */
    // S1
    // time = O(nlogk), space = O(n)
    public int[] assignElements(int[] groups, int[] elements) {
        int n = groups.length, m = elements.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (!map.containsKey(elements[i])) map.put(elements[i], i);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = groups[i];
            res[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= x / j; j++) {
                if (x % j == 0) {
                    if (map.containsKey(j)) res[i] = Math.min(res[i], map.get(j));
                    if (j != x / j && map.containsKey(x / j)) res[i] = Math.min(res[i], map.get(x / j));
                }
            }
            if (res[i] == Integer.MAX_VALUE) res[i] = -1;
        }
        return res;
    }

    // S2
    // time = O(klogn + m), space = O(k)  k: max{groups}
    public int[] assignElements2(int[] groups, int[] elements) {
        int n = groups.length, m = elements.length;
        int mx = groups[0];
        for (int x : groups) mx = Math.max(mx, x);
        int[] w = new int[mx + 1];
        Arrays.fill(w, -1);
        for (int i = 0; i < m; i++) {
            int x = elements[i];
            if (x > mx || w[x] != -1) continue;
            for (int j = 1; j * x <= mx; j++) {
                if (w[j * x] == -1) w[j * x] = i;
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = w[groups[i]];
        return res;
    }
}