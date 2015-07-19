import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Feiyu on 2015/7/18.
 **/
public class LongestValidParentheses {
    /**
     * WA : Case "()(()"，out:4 expected:2
     * 多余的左括号本来应该作为分割点，但无法识别（要最后识别？），导致local没有清零
     */
    public class Solution1 {
        public int longestValidParentheses(String s) {
            if (s.length() < 2)
                return 0;
            int leftBrackets = s.charAt(0) == '(' ? 1 : 0;
            int localPath = 0, globalPrev = 0;
            int[] parentheses = new int[s.length()];

            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    leftBrackets++;
                    parentheses[i] = parentheses[i - 1];
                } else if (s.charAt(i) == ')') {
                    if (leftBrackets > 0) {
                        leftBrackets--;
                        localPath += 2;
                        parentheses[i] = Math.max(globalPrev, localPath);
                        if (leftBrackets == 0) {
                            globalPrev = parentheses[i] =
                                    localPath + globalPrev;
                            localPath = 0;
                        }
                    } else {
                        parentheses[i] = parentheses[i - 1];
                    }
                }
            }
            return parentheses[parentheses.length - 1];
        }
    }

    /**
     * WA : Case "(()))())("，out:0 expected:4
     * 没法判别leftBuckets的左括号位置在什么地方，导致 leftBuckets>0,leftIndex=8,global=0
     */
    public class Solution2 {
        public int longestValidParentheses(String s) {
            if (s.length() < 2)
                return 0;
            int leftBrackets = s.charAt(0) == '(' ? 1 : 0;
            int local = 0, global = 0;
            int[] parentheses = new int[s.length()];
            int leftIndex = s.charAt(0) == '(' ? 0 : -1;//new Add
            int rightIndex = s.charAt(0) == '(' ? 0 : -1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    leftBrackets++;
                    parentheses[i] = local;
                    if (leftIndex == -1) {//new Add
                        leftIndex = i;
                    }
                } else if (s.charAt(i) == ')') {
                    if (leftBrackets >= 1) {
                        leftBrackets--;
                        local += 2;
                        parentheses[i] = local;
                        global = Math.max(global, local);
                        leftIndex = leftBrackets == 0 ? -1 : leftIndex;//new Add
                    } else {
                        local = 0;
                        parentheses[i] = local;
                    }
                }
            }
            if (leftBrackets > 0) {
                int start = 0, end = parentheses.length - 1;
                for (int i = parentheses.length - 1; i >= 0; i--) {
                    if (parentheses[i] == 0) {
                        start = i;
                        break;
                    }
                }
                global = Math.max(parentheses[end] - parentheses[leftIndex],
                        parentheses[leftIndex] - parentheses[start]);
            }
            return global;
        }
    }

    /**
     * Accept , but >420ms , 怎么改进？
     * 数组存所有valid的长度，根据最后的分割点来分别不同的Valid Path
     * 排序是 O( nlogn ) ,影响时间
     */
    public class Solution {
        public int longestValidParentheses(String s) {
            if (s.length() < 2)
                return 0;
            int res = 0;
            int[] parentheses = new int[s.length()];
            ArrayList<Integer> list = new ArrayList<>(),
                    split = new ArrayList<>();
            if (s.charAt(0) == '(')
                list.add(0);
            else if (s.charAt(0) == ')')
                split.add(0);
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    parentheses[i] = parentheses[i - 1];
                    list.add(i);
                } else if (s.charAt(i) == ')') {
                    if (list.size() == 0) {// ) is a split
                        parentheses[i] = parentheses[i - 1];
                        split.add(i);
                    } else {
                        parentheses[i] = parentheses[i - 1] + 2;
                        list.remove(list.size() - 1);
                    }
                }
            }
            split.add(parentheses.length - 1);
            split.add(0);
            split.addAll(list);
            Collections.sort(split);
            for (int i = 1; i < split.size(); i++) {
                res = Math.max(res, parentheses[split.get(i)] - parentheses[split.get(i - 1)]);
            }
            return res;
        }
    }

    public static void main(String[] m) {
        Solution1 solution = new LongestValidParentheses().new Solution1();
        solution.longestValidParentheses("(()))())(");//4
        solution.longestValidParentheses("()(()");//2
        solution.longestValidParentheses("(()");//2
        solution.longestValidParentheses("((((()()))()(()()()()())(()");//6
        solution.longestValidParentheses("()(())");//6
        solution.longestValidParentheses(")()())()()(");//4
        solution.longestValidParentheses(")()())())");//4
        solution.longestValidParentheses("(())(");//4
        solution.longestValidParentheses("())");//2
        solution.longestValidParentheses("()()"); //4
        solution.longestValidParentheses(")()()"); //4
    }
}
