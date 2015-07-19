import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/7 0007.
 **/
public class PalindromePartitioning {
    /**
     * WA ： 题目理解错了，应该是求是所有子串都是回数的切割方法，不是求所有回数
     */
    public class Solution1 {
        public List<List<String>> partition(String s) {
            List<List<Integer>> list = new ArrayList<>();
            list.add(new ArrayList<Integer>());
            list.add(null);
            for (int i = 0; i < s.length(); i++) {
                list.get(0).add(i);
                list.get(0).add(i);
                if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                    if (list.get(1) == null)
                        list.set(1, new ArrayList<Integer>());
                    list.get(1).add(i);
                    list.get(1).add(i + 1);
                }
            }
            for (int index = 2; index < s.length(); index++) {
                List<Integer> prevList = list.get(index - 2);
                if (prevList != null) {
                    List<Integer> nowList = new ArrayList<>();
                    for (int prevIndex = 0; prevIndex < prevList.size() / 2; prevIndex++) {
                        int nstart = prevList.get(2 * prevIndex) - 1,
                                nend = prevList.get(2 * prevIndex + 1) + 1;

                        if (nstart >= 0 && nend < s.length()
                                && s.charAt(nstart) == s.charAt(nend)) {
                            nowList.add(nstart);
                            nowList.add(nend);
                        }
                    }
                    if (nowList.size() > 0)
                        list.add(nowList);
                    else
                        list.add(null);
                } else
                    list.add(null);

            }
            List<List<String>> res = new LinkedList<>();
            for (List<Integer> oneList : list) {
                if (oneList != null) {
                    List<String> one = new LinkedList<>();
                    for (int i = 0; i < oneList.size() / 2; i++) {
                        one.add(s.substring(oneList.get(2 * i), oneList.get(2 * i + 1) + 1));
                    }
                    res.add(one);
                }
            }
            return res;
        }
    }

    /**
     * Accept,but >380ms
     */
    public class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> list = new LinkedList<>();
            int[] solution = new int[s.length()];
            for (int i = 0; i < solution.length; i++)
                solution[i] = -1;
            int i = 0;
            while (i >= 0) {
                solution[i]++;
                while (solution[i] < s.length() && !isValid(i, solution, s))
                    solution[i]++;
                if (solution[i] < s.length()) {
                    if (solution[i] == s.length() - 1) {
                        list.add(ToStringList(i, solution, s));
                    } else {
                        i++;
                        solution[i] = solution[i - 1];
                    }
                } else
                    i--;
            }
            return list;
        }

        private boolean isValid(int index, int[] solution, String s) {
            int start = index == 0 ? 0 : solution[index - 1] + 1,
                    end = solution[index];
            if (end == start)
                return true;
            else if (end > start) {
                String tmp = s.substring(start, end + 1);
                int i = 0, j = tmp.length() - 1;
                boolean isPalindrome = false;
                while (i <= j) {
                    if (tmp.charAt(i) == tmp.charAt(j))
                        isPalindrome = true;
                    else {
                        isPalindrome = false;
                        break;
                    }
                    i++;
                    j--;
                }
                return isPalindrome;
            } else
                return false;
        }

        private List<String> ToStringList(int index, int[] solution, String s) {
            List<String> list = new LinkedList<>();
            for (int i = 0; i <= index; i++) {
                list.add(s.substring(i == 0 ? 0 : solution[i - 1] + 1, solution[i] + 1));
            }
            return list;
        }
    }

    static public void main(String... a) {
        Solution solution = new PalindromePartitioning().new Solution();
        List<List<String>> temp = solution.partition("cdd");
        System.out.print(temp);
    }
}
