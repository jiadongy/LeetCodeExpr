import java.util.ArrayList;

/**
 * Created by Feiyu on 2015/7/11.
 **/
public class DistinctSubsequences {
    /**
     * Wrong Answer:想法错误 N(i)=N(i-1)*max_number[from prevIndex to s.length]。
     * N(t.length-1)只是代表其中一个可行的path的max(而且求出来的paths中还有互斥的）
     */
    public class Solution1 {
        public int numDistinct(String s, String t) {
            ArrayList[] lists = new ArrayList[t.length()];
            for (int i = 0; i < t.length(); i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                if (i == 0) {
                    int sum = 0;
                    for (int j = 0; j < s.length(); j++) {
                        if (t.charAt(i) == s.charAt(j)) {
                            sum++;
                            temp.add(sum);
                            temp.add(j);
                        }
                    }
                    lists[i] = temp;
                } else {
                    ArrayList<Integer> prevArray = lists[i - 1];
                    if (prevArray.size() > 0) {
                        for (int j = 0; j < prevArray.size() / 2; j++) {
                            int sum = 0;
                            int prevSum = prevArray.get(2 * j);
                            int prevEndIndex = prevArray.get(2 * j + 1);
                            for (int k = prevEndIndex + 1; k < s.length(); k++) {
                                if (t.charAt(i) == s.charAt(k)) {
                                    sum++;
                                    temp.add(sum * prevSum);
                                    temp.add(k);
                                }
                            }
                        }
                    }
                    lists[i] = temp;
                }
            }
            int max = 0;
            if (lists[lists.length - 1].size() == 0) {
                max = 0;
            } else {
                for (int i = 0; i < lists[lists.length - 1].size() / 2; i++) {
                    max = (max > ((int) lists[lists.length - 1].get(2 * i))) ?
                            max : (int) lists[lists.length - 1].get(2 * i);
                }
            }
            return max;
        }

    }

    /**
     * return size()/2
     * Time Out
     */
    public class Solution {
        public int numDistinct(String s, String t) {
            ArrayList[] lists = new ArrayList[t.length()];
            for (int i = 0; i < t.length(); i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                if (i == 0) {
                    int sum = 0;
                    for (int j = 0; j < s.length(); j++) {
                        if (t.charAt(i) == s.charAt(j)) {
                            sum++;
                            temp.add(sum);
                            temp.add(j);
                        }
                    }
                    lists[i] = temp;
                } else {
                    ArrayList<Integer> prevArray = lists[i - 1];
                    if (prevArray.size() > 0) {
                        for (int j = 0; j < prevArray.size() / 2; j++) {
                            int sum = 0;
                            int prevSum = prevArray.get(2 * j);
                            int prevEndIndex = prevArray.get(2 * j + 1);
                            for (int k = prevEndIndex + 1; k < s.length(); k++) {
                                if (t.charAt(i) == s.charAt(k)) {
                                    sum++;
                                    temp.add(sum * prevSum);
                                    temp.add(k);
                                }
                            }
                        }
                    }
                    lists[i] = temp;
                }
            }
            int max = 0;
            if (lists[lists.length - 1].size() == 0) {
                max = 0;
            } else {
                for (int i = 0; i < lists[lists.length - 1].size() / 2; i++) {
                    max = (max > ((int) lists[lists.length - 1].get(2 * i))) ?
                            max : (int) lists[lists.length - 1].get(2 * i);
                }
            }
            return max;
        }

    }

    public static void main(String... a) {
        Solution solution = new DistinctSubsequences().new Solution();
        solution.numDistinct("aabdbaabeeadcbbdedacbbeecbabebaeeecaeabaedadcbdbcdaabebdadbbaeabdadeaabbabbecebbebcaddaacccebeaeedababedeacdeaaaeeaecbe",
                "bddabdcae");
    }
}
