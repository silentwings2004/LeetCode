package LC3001_3300;
import java.util.*;
public class LC3023_FindPatterninInfiniteStreamI {
    /**
     * You are given a binary array pattern and an object stream of class InfiniteStream representing a 0-indexed
     * infinite stream of bits.
     *
     * The class InfiniteStream contains the following function:
     *
     * int next(): Reads a single bit (which is either 0 or 1) from the stream and returns it.
     * Return the first starting index where the pattern matches the bits read from the stream. For example, if the
     * pattern is [1, 0], the first match is the highlighted part in the stream [0, 1, 0, 1, ...].
     *
     * Input: stream = [1,1,1,0,1,1,1,...], pattern = [0,1]
     * Output: 3
     *
     * Input: stream = [0,0,0,0,...], pattern = [0]
     * Output: 0
     *
     * Input: stream = [1,0,1,1,0,1,1,0,1,...], pattern = [1,1,0,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= pattern.length <= 100
     * pattern consists only of 0 and 1.
     * stream consists only of 0 and 1.
     * The input is generated such that the pattern's start index exists in the first 105 bits of the stream.
     * @param infiniteStream
     * @param pattern
     * @return
     */
    // time = O(n), space = O(m)
    public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
        int m = pattern.length;
        int[] p = new int[m + 1], ne = new int[m + 1];
        for (int i = 1; i <= m; i++) p[i] = pattern[i - 1];
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && p[i] != p[j + 1]) j = ne[j];
            if (p[i] == p[j + 1]) j++;
            ne[i] = j;
        }
        int res = -1;
        for (int i = 1, j = 0;; i++) {
            int x = infiniteStream.next();
            while (j > 0 && x != p[j + 1]) j = ne[j];
            if (x == p[j + 1]) j++;
            if (j == m) {
                res = i - m;
                break;
            }
        }
        return res;
    }

    class InfiniteStream {
        public InfiniteStream(int[] bits);
        public int next();
    }
}
