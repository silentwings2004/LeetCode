package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CountTheRepetitions
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 466. Count The Repetitions
 */
public class LC466_CountTheRepetitions {
    /**
     * Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".
     *
     * On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from
     * s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can
     * not be obtained from “acbbe”.
     *
     * You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106
     * and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M
     * such that [S2,M] can be obtained from S1.
     *
     * Example:
     *
     * Input:
     * s1="acb", n1=4
     * s2="ab", n2=2
     *
     * Return:
     * 2
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        /**
         * index为c2的索引， num1当前使用了ss1的个数， num2当前匹配的ss2的个数
         */
        int index = 0 , num1 = 0, num2 = 0;
        while (num1 < n1) {
            for (int i = 0 ; i < c1.length ; i++) {
                if (c1[i] == c2[index]) {
                    if (index == c2.length - 1) {
                        index = 0;
                        num2 ++;
                    } else{
                        index ++;
                    }
                }
            }
            num1++;
        }
        return num2 / n2;
    }
}
