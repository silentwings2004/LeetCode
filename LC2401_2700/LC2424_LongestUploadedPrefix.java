package LC2401_2700;
import java.util.*;
public class LC2424_LongestUploadedPrefix {
    /**
     * You are given a stream of n videos, each represented by a distinct number from 1 to n that you need to "upload"
     * to a server. You need to implement a data structure that calculates the length of the longest uploaded prefix at
     * various points in the upload process.
     *
     * We consider i to be an uploaded prefix if all videos in the range 1 to i (inclusive) have been uploaded to the
     * server. The longest uploaded prefix is the maximum value of i that satisfies this definition.
     *
     * Implement the LUPrefix class:
     *
     * LUPrefix(int n) Initializes the object for a stream of n videos.
     * void upload(int video) Uploads video to the server.
     * int longest() Returns the length of the longest uploaded prefix defined above.
     *
     * Input
     * ["LUPrefix", "upload", "longest", "upload", "longest", "upload", "longest"]
     * [[4], [3], [], [1], [], [2], []]
     * Output
     * [null, null, 0, null, 1, null, 3]
     * @param n
     */
    boolean[] st;
    int p;
    public LC2424_LongestUploadedPrefix(int n) {
        st = new boolean[n + 2]; // 下面p会走到n + 1，所以这里要开到n + 2!
        p = 0;
    }
    // time = O(n), space = O(n)
    public void upload(int video) {
        st[video] = true;
        while (st[p + 1]) p++;
    }
    // time = O(1), space = O(n)
    public int longest() {
        return p;
    }
}