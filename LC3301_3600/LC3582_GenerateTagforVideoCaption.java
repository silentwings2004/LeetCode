package LC3301_3600;

public class LC3582_GenerateTagforVideoCaption {
    /**
     * You are given a string caption representing the caption for a video.
     *
     * The following actions must be performed in order to generate a valid tag for the video:
     *
     * Combine all words in the string into a single camelCase string prefixed with '#'. A camelCase string is one where
     * the first letter of all words except the first one is capitalized. All characters after the first character in each word must be lowercase.
     *
     * Remove all characters that are not an English letter, except the first '#'.
     *
     * Truncate the result to a maximum of 100 characters.
     *
     * Return the tag after performing the actions on caption.
     *
     * Input: caption = "Leetcode daily streak achieved"
     * Output: "#leetcodeDailyStreakAchieved"
     *
     * Input: caption = "can I Go There"
     * Output: "#canIGoThere"
     *
     * Input: caption = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
     * Output: "#hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
     *
     * Constraints:
     *
     * 1 <= caption.length <= 150
     * caption consists only of English letters and ' '.
     * @param caption
     * @return
     */
    // time = O(n), space = O(n)
    public String generateTag(String caption) {
        String[] strs = caption.split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (String s : strs) {
            if (s.length() == 0) continue;
            s = s.toLowerCase();
            if (sb.length() > 1) {
                sb.append(Character.toUpperCase(s.charAt(0)));
                sb.append(s.substring(1));
            } else sb.append(s);
        }
        if (sb.length() > 100) return sb.toString().substring(0, 100);
        return sb.toString();
    }
}
/**
 * 1. 先把所有字母小写
 * 2. 去掉前导空格
 * 3. 如果一个字母前面是空格，那么这个字母大写
 * 4. 去掉所有空格
 */