package LC1501_1800;

public class LC1538_GuesstheMajorityinaHiddenArray {
    /**
     * We have an integer array nums, where all the integers in nums are 0 or 1. You will not be given direct access to
     * the array, instead, you will have an API ArrayReader which have the following functions:
     *
     * int query(int a, int b, int c, int d): where 0 <= a < b < c < d < ArrayReader.length(). The function returns the
     * distribution of the value of the 4 elements and returns:
     * 4 : if the values of the 4 elements are the same (0 or 1).
     * 2 : if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
     * 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
     * int length(): Returns the size of the array.
     * You are allowed to call query() 2 * n times at most where n is equal to ArrayReader.length().
     *
     * Return any index of the most frequent value in nums, in case of tie, return -1.
     *
     * Input: nums = [0,0,1,0,1,1,1,1]
     * Output: 5
     *
     * Input: nums = [0,0,1,1,0]
     * Output: 0
     *
     * Input: nums = [1,0,1,0,1,0,1,0]
     * Output: -1
     *
     * Follow up: What is the minimum number of calls needed to find the majority element?
     * @param reader
     * @return
     */
    /**
     * // This is the ArrayReader's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface ArrayReader {
     *   public:
     *     // Compares 4 different elements in the array
     *     // return 4 if the values of the 4 elements are the same (0 or 1).
     *     // return 2 if three lements have a value equal to 0 and one element has value equal to 1 or vice versa.
     *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
     *     public int query(int a, int b, int c, int d);
     *
     *     // Returns the length of the array
     *     public int length();
     * };
     */
    // time = O(n), space = O(1)
    public int guessMajority(ArrayReader reader) {
        int n = reader.length();
        int base = reader.query(0, 1, 2, 3);
        int c0 = 1, c1 = 0;
        int idx = -1;
        for (int i = 4; i < n; i++) {
            if (base == reader.query(1, 2, 3, i)) c0++; // check arr[i] == arr[0] ?
            else {
                c1++;
                idx = i;
            }
        }

        if (reader.query(0, 2, 3, 4) == reader.query(1, 2, 3, 4)) c0++;
        else {
            c1++;
            idx = 1;
        }

        if (reader.query(0, 1, 3, 4) == reader.query(1, 2, 3, 4)) c0++;
        else {
            c1++;
            idx = 2;
        }

        if (reader.query(0, 1, 2, 4) == reader.query(1, 2, 3, 4)) c0++;
        else {
            c1++;
            idx = 3;
        }

        if (c0 > c1) return 0;
        else if (c0 < c1) return idx;
        return -1;
    }
}
