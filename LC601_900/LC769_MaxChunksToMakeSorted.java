package LC601_900;

public class LC769_MaxChunksToMakeSorted {
    /**
     * You are given an integer array arr of length n that represents a permutation of the integers in the range
     * [0, n - 1].
     *
     * We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After
     * concatenating them, the result should equal the sorted array.
     *
     * Return the largest number of chunks we can make to sort the array.
     *
     * Input: arr = [4,3,2,1,0]
     * Output: 1
     *
     * Input: arr = [1,0,2,3,4]
     * Output: 4
     *
     * Constraints:
     *
     * n == arr.length
     * 1 <= n <= 10
     * 0 <= arr[i] < n
     * All the elements of arr are unique.
     * @param arr
     * @return
     */
    // time = O(n), space = O(1)
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length, max = 0, res = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(arr[i], max);
            if (max == i) res++;
        }
        return res;
    }
}