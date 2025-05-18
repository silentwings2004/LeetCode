package LC1201_1500;
import java.util.*;
public class LC1460_MakeTwoArraysEqualbyReversingSubarrays {
    /**
     * You are given two integer arrays of equal length target and arr. In one step, you can select any non-empty
     * sub-array of arr and reverse it. You are allowed to make any number of steps.
     *
     * Return true if you can make arr equal to target or false otherwise.
     *
     * Input: target = [1,2,3,4], arr = [2,4,1,3]
     * Output: true
     *
     * Constraints:
     *
     * target.length == arr.length
     * 1 <= target.length <= 1000
     * 1 <= target[i] <= 1000
     * 1 <= arr[i] <= 1000
     * @param target
     * @param arr
     * @return
     */
    // time = O(n), space = O(n)
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cnt = new int[1001];
        for (int x : target) cnt[x]++;
        for (int x : arr) {
            cnt[x]--;
            if (cnt[x] < 0) return false;
        }
        return true;
    }

    // S2: Sort
    // time = O(nlogn), space = O(logn)
    public boolean canBeEqual2(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}
