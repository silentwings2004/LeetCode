package LC2401_2700;

public class LC2433_FindTheOriginalArrayofPrefixXor {
    /**
     * You are given an integer array pref of size n. Find and return the array arr of size n that satisfies:
     *
     * pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
     * Note that ^ denotes the bitwise-xor operation.
     *
     * It can be proven that the answer is unique.
     *
     * Input: pref = [5,2,0,3,1]
     * Output: [5,7,2,3,2]
     *
     * Input: pref = [13]
     * Output: [13]
     *
     * Constraints:
     *
     * 1 <= pref.length <= 10^5
     * 0 <= pref[i] <= 10^6
     * @param pref
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] res = new int[n];
        res[0] = pref[0];

        int sum = res[0];
        for (int i = 1; i < n; i++) {
            res[i] = sum ^ pref[i];
            sum ^= res[i];
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] findArray2(int[] pref) {
        int n = pref.length;
        int[] res = new int[n];
        res[0] = pref[0];

        for (int i = 1; i < n; i++) {
            res[i] = pref[i] ^ pref[i - 1];
        }
        return res;
    }
}
