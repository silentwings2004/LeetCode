package LC1201_1500;
import java.util.*;
public class LC1207_UniqueNumberofOccurrences {
    /**
     * Given an array of integers arr, return true if the number of occurrences of each value in the array is unique,
     * or false otherwise.
     *
     * Input: arr = [1,2,2,1,1,3]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= arr.length <= 1000
     * -1000 <= arr[i] <= 1000
     * @param arr
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : arr) map.put(x, map.getOrDefault(x, 0) + 1);

        HashSet<Integer> set = new HashSet<>();
        for (int v : map.values()) {
            if (set.contains(v)) return false;
            set.add(v);
        }
        return true;
    }

    // S2
    // time = O(nlogn), space = O(1)
    public boolean uniqueOccurrences2(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, count = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
                arr[i - 1] = 0;
                count++;
            } else {
                arr[i - 1] = count;
                count = 1;
            }
        }
        arr[n - 1] = count;

        Arrays.sort(arr);
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) continue;
            if (arr[i] == prev) return false;
            prev = arr[i];
        }
        return true;
    }
}
