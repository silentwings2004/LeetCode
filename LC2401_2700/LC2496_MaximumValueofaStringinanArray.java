package LC2401_2700;

public class LC2496_MaximumValueofaStringinanArray {
    /**
     * The value of an alphanumeric string can be defined as:
     *
     * The numeric representation of the string in base 10, if it comprises of digits only.
     * The length of the string, otherwise.
     * Given an array strs of alphanumeric strings, return the maximum value of any string in strs.
     *
     * Input: strs = ["alic3","bob","3","4","00000"]
     * Output: 5
     *
     * Input: strs = ["1","01","001","0001"]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= strs.length <= 100
     * 1 <= strs[i].length <= 9
     * strs[i] consists of only lowercase English letters and digits.
     * @param strs
     * @return
     */
    // time = O(n * k), space = O(1)
    public int maximumValue(String[] strs) {
        int res = 0;
        for (String s : strs) {
            boolean flag = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) {
                    res = Math.max(res, s.length());
                    flag = false;
                    break;
                }
            }
            if (flag) res = Math.max(res, Integer.parseInt(s));
        }
        return res;
    }
}
