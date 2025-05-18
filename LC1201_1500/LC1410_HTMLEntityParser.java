package LC1201_1500;
import java.util.*;
public class LC1410_HTMLEntityParser {
    /**
     * HTML entity parser is the parser that takes HTML code as input and replace all the entities of the special
     * characters by the characters itself.
     *
     * The special characters and their entities for HTML are:
     *
     * Quotation Mark: the entity is &quot; and symbol character is ".
     * Single Quote Mark: the entity is &apos; and symbol character is '.
     * Ampersand: the entity is &amp; and symbol character is &.
     * Greater Than Sign: the entity is &gt; and symbol character is >.
     * Less Than Sign: the entity is &lt; and symbol character is <.
     * Slash: the entity is &frasl; and symbol character is /.
     * Given the input text string to the HTML parser, you have to implement the entity parser.
     *
     * Return the text after replacing the entities by the special characters.
     *
     * Input: text = "&amp; is an HTML entity but &ambassador; is not."
     * Output: "& is an HTML entity but &ambassador; is not."
     *
     * Input: text = "and I quote: &quot;...&quot;"
     * Output: "and I quote: \"...\""
     *
     * Constraints:
     *
     * 1 <= text.length <= 10^5
     * The string may contain any possible characters out of all the 256 ASCII characters.
     * @param text
     * @return
     */
    // time = O(n), space = O(n)
    public String entityParser(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");

        StringBuilder sb = new StringBuilder();
        int n = text.length();
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if (c == '&') {
                boolean flag = false;
                for (int j = 4; j <= 7; j++) {
                    String s = text.substring(i, Math.min(i + j, n));
                    if (map.containsKey(s)) {
                        sb.append(map.get(s));
                        i += j - 1;
                        flag = true;
                        break;
                    }
                }
                if (!flag) sb.append(c);
            } else sb.append(c);
        }
        return sb.toString();
    }
}