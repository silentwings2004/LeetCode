package LC001_300;
import java.util.*;
public class LC157_ReadNCharactersGivenRead4 {
    /**
     * Given a file and assume that you can only read the file using a given method read4, implement a method to read
     * n characters.
     *
     * Method read4:
     *
     * The API read4 reads four consecutive characters from file, then writes those characters into the buffer array
     * buf4.
     *
     * The return value is the number of actual characters read.
     *
     * Note that read4() has its own file pointer, much like FILE *fp in C.
     *
     * Input: file = "abc", n = 4
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= file.length <= 500
     * file consist of English letters and digits.
     * 1 <= n <= 1000
     * @param buf
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public int read(char[] buf, int n) {
        char[] a = new char[4];
        int cnt = 0, i = 0, j = 0;
        for (j = 0; j < n; j++) {
            if (cnt == 0) {
                cnt = read4(a);
                i = 0;
                if (cnt == 0) break;
            }
            buf[j] = a[i++];
            cnt--;
        }
        return j;
    }

    // helper function
    private int read4(char[] temp) {
        char[] res = new char[4];
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            res[index++] = temp[i];
        }
        return index;
    }
}
