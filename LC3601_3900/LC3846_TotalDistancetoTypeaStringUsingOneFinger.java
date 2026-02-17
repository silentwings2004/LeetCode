package LC3601_3900;

public class LC3846_TotalDistancetoTypeaStringUsingOneFinger {
    /**
     * There is a special keyboard where keys are arranged in a rectangular grid as follows.
     *
     * q	w	e	r	t	y	u	i	o	p
     * a	s	d	f	g	h	j	k	l
     * z	x	c	v	b	n	m
     *
     * You are given a string s that consists of lowercase English letters only. Return an integer denoting the total
     * distance to type s using only one finger. Your finger starts on the key 'a'.
     *
     * The distance between two keys at (r1, c1) and (r2, c2) is |r1 - r2| + |c1 - c2|.
     *
     * Input: s = "hello"
     * Output: 17
     *
     * Input: s = "a"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of lowercase English letters only.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int totalDistance(String s) {
        int[][] pos = new int[26][2];
        String[] strs = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                int u = strs[i].charAt(j) - 'a';
                pos[u] = new int[]{i, j};
            }
        }

        int[] t = pos[0];
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            res += Math.abs(pos[u][0] - t[0]) + Math.abs(pos[u][1] - t[1]);
            t = pos[u];
        }
        return res;
    }
}