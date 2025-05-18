package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ValidParenthesisString
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 678. Valid Parenthesis String
 */
public class LC678_ValidParenthesisString {
    /**
     * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether
     * this string is valid. We define the validity of a string by these rules:
     *
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
     * An empty string is also valid.
     *
     * Example 1:
     * Input: "()"
     * Output: True
     * Example 2:
     * Input: "(*)"
     * Output: True
     * Example 3:
     * Input: "(*))"
     * Output: True
     * Note:
     * The string size will be in the range [1, 100].
     * @param s
     * @return
     */
    // S1: Two Stacks
    // 栈存放的是索引,一栈存左括号，一栈存星号，因为入栈的前后顺序决定是否可以匹配
    // 遍历过程中，同时判断是否有足够的右括号使他们出栈, 优先抵消左括号（贪心思想)
    // 两栈同时出栈并判断，要求所有左括号，都有 其右边索引的星号 能使其抵消
    // 如果左括号的index在星号之后，那么星号只能作为empty而不能作为右括号与其抵消
    // 左括号不能还有富余
    // time = O(n), space = O(n)
    public boolean checkValidString(String s) {
        // corner case
        if (s == null || s.length() == 0) return true;


        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') left.push(i); //注意这里push进入2个栈的不是字符c，而是index i，index决定进栈的先后顺序，直接决定了是否匹配。
            else if (c == '*') star.push(i);
            else {
                if (!left.empty()) left.pop(); // 优先消耗左括号(贪心算法)
                else if (!star.empty()) star.pop(); // 左括号不够了再拿星号去充当左括号去抵消右括号
                else return false; // 左括号和星号都消耗殆尽，仍然有右括号进入，那么一定是false
            }
        }

        while (!left.empty() && !star.empty()) {  // 如果左括号和星号都有剩余的话，那么所有的左括号index必须都要在对应星号的左边
            if (left.pop() > star.pop()) return false;
            //如果左括号的index比星号的大，那么意味着左括号在星号右边，星号只能作为empty string而无法与左括号匹配
        }
        return left.empty(); // 如果左括号仍然有剩余，那么肯定是false；而在左括号耗尽的情况下，星号有没有剩余都默认是true
    }

    // S2: Greedy
    // 在检查字符串是否有效时，我们只关心是否 “balance”：当我们分析字符串时计算未配对的左括号的数量。例如，在检查 “(()())” 是否有效时，我们在
    // 分析字符串时得到了 1, 2, 1, 2, 1, 0 的平衡过程：“(” 有1个左括号，“(” 有2个左括号，“(()” 剩下一个左括号，依此类推。
    // 例如，如果我们有字符串 (***)，那么在分析每个符号时，balance 的可能值是 '(' 对应 [1] ,'(*' 对应 [0, 1, 2], '(**' 对应
    // [0, 1, 2, 3], '(***' 对应 [0, 1, 2, 3, 4],'(***)'对应 [0, 1, 2, 3]。
    // 此外，我们可以证明这些状态总是形成一个连续的区间。因此，我们只需要知道这个区间的左右边界。也就是说，我们将把上面描述的中间状态保持为
    // [lo, hi] = [1, 1], [0, 2], [0, 3], [0, 4], [0, 3]
    // time = O(n), space = O(1)
    public boolean checkValidString2(String s) {
        // corner case
        if (s == null || s.length() == 0) return true;

        int lo = 0, hi = 0; // 维护当前左括号的数量范围：[lo, hi]
        for (char c : s.toCharArray()) {
            if (c == '(') { // 遇到左括号：lo++, hi++  左右边界无脑加
                lo++;
                hi++;
            } else if (c == ')') { // 遇到右括号：check左边界，如果大于0才--，否则lo此时肯定为0(不可能<0)，就不动它，check右边界
                if (lo > 0) lo--;
                hi--; // 右边界表示左括号可能的最大值，无脑--，然后判断是否<0，如果是<0，那么之前肯定==0，表示左括号不够用，肯定是false
                if (hi < 0) return false;
            } else { // 遇到星号的话，一般而言左右边界都要向外扩展一位，但同样如果左边界==0就不动，右边界无脑+1
                if (lo > 0) lo--;
                hi++;
            }
        }
        return lo == 0; //最后check左边界是否==0来判断结果，因为左括号兜住的是和星号的匹配度，和右括号的匹配check已经在上面右边界里check了
    }
    /* 这道括号匹配问题的贪心算法最大的难点在于出现星号后，我们focus on的左括号数目的变化情况。首先我们要明白，*可以给左括号带来3种情况，
    分别是-1， 0， +1，但是当出现（** 的时候，左括号的范围究竟是从[0, 2] --> [-1, 3]还是[0, 3]呢？简单来说，就是左括号能不能变成负数？？？
    如果左括号数目<0，也就意味着右括号比左括号多，那也就是说*充当了右括号的角色导致左括号数目不够，那么之后紧接着再出现左括号，能不能让左括号的
    数目从-1再变回0呢？答案是不能！因为如果左括号数目是-1，我们可以理解为此时我们手上剩下的是一个右括号("(**"中第2个*所变)，那么再来一个左括号
    无非就使得当前手上的状态变为")("，这样依然是不valid的。也就是说左括号的数目无论如何不能<0，也就是说左边界不能因为*号的出现而变作负数。这就
    导致在出现*进行range扩展的时候，如果左边界为0，那么左边界扩展的分支就得减掉，只扩展由边界。由于右边界肯定>=左边界且向正方向扩展，所以可以无脑
    +1。那么接下来当遇到右括号的时候，左括号数目要-1，同样的左边界在能不<0的情况下绝对不能<0，因为这样做的话是不valid的，可以直接减掉，而让右边
    界去兜住-1来实现valid的可能性。所以，如果上面是[0, 3],那么现在只会变作[0, 2]而不会是[-1, 2]，-1这一分支不valid直接剔除。但如果连右边界
    -1之后都<0，也就意味着右边界在遇到右括号之前==0，那么也就意味着左括号数目最多为0 --> 左括号在遇到右括号之前已经一个不剩，所以此时再遇到右括
    号，一定是返回false。那么最后剩下的问题就是到了结尾如何check是否最终是valid呢？我们知道左边界无论在任何情况都不会留下<0的case，那么就只有
    ==0和>0的case。如果左边界lo > 0，那也就意味着左括号至少都有>0的个数剩下，也就是说左括号一定有剩余，肯定return false。而如果lo == 0，
    也就是说左括号至少可以一个都不剩，那么按照该题只要"一通百通"的思想，只要有一个valid可能性的case存在，那么就该选取这个case而返回true。
    ==> 所以，该题的贪心算法的终极思想就是：
    大前提：左边界永远不能<0，一旦减到<0就卡在0不动；右边界永远无脑加减，但是一旦减到<0，立刻返回false。
    1. 遇到左括号，两个边界无脑+1
    2. 遇到星号，左边界能减1就-1，右边界无脑+1
    3. 遇到右括号，左边界能减1就-1，右边界无脑-1
     */
}
