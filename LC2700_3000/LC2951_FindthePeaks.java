package LC2700_3000;
import java.util.*;
public class LC2951_FindthePeaks {
    /**
     * You are given a 0-indexed array mountain. Your task is to find all the peaks in the mountain array.
     *
     * Return an array that consists of indices of peaks in the given array in any order.
     *
     * Notes:
     *
     * A peak is defined as an element that is strictly greater than its neighboring elements.
     * The first and last elements of the array are not a peak.
     *
     * Input: mountain = [2,4,4]
     * Output: []
     *
     * Input: mountain = [1,4,3,8,5]
     * Output: [1,3]
     *
     * Constraints:
     *
     * 3 <= mountain.length <= 100
     * 1 <= mountain[i] <= 100
     * @param mountain
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        int n = mountain.length;
        for (int i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) res.add(i);
        }
        return res;
    }
}