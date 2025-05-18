package LC1501_1800;
import java.util.*;
public class LC1656_DesignanOrderedStream {
    /**
     * There is a stream of n (idKey, value) pairs arriving in an arbitrary order, where idKey is an integer between 1
     * and n and value is a string. No two pairs have the same id.
     *
     * Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values
     * after each insertion. The concatenation of all the chunks should result in a list of the sorted values.
     *
     * Implement the OrderedStream class:
     *
     * OrderedStream(int n) Constructs the stream to take n values.
     * String[] insert(int idKey, String value) Inserts the pair (idKey, value) into the stream, then returns the
     * largest possible chunk of currently inserted values that appear next in the order.
     *
     * Input
     * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
     * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
     * Output
     * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 1 <= id <= n
     * value.length == 5
     * value consists only of lowercase letters.
     * Each call to insert will have a unique id.
     * Exactly n calls will be made to insert.
     * @param n
     */
    // time = O(n), space = O(n)
    String[] arr;
    int p;
    public LC1656_DesignanOrderedStream(int n) {
        arr = new String[n];
        p = 0;
    }

    public List<String> insert(int idKey, String value) {
        List<String> res = new ArrayList<>();
        arr[idKey - 1] = value;
        while (p < arr.length && arr[p] != null) res.add(arr[p++]);
        return res;
    }
}
