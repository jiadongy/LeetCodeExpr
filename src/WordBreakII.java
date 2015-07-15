import java.util.*;

/**
 * Created by Feiyu on 2015/7/15.
 **/
public class WordBreakII {
    /**
     * Accept , but > 320ms
     * Valid(i)=Valid(k) && dict(substring(k,j)  0<=k<i    O(n^2)
     * HashMap 存每个i可能的k ,getStrings求每个i与前一个i的笛卡尔积，递归，迭代怎么做？
     */
    public class Solution {
        public List<String> wordBreak(String s, Set<String> wordDict) {

            HashMap<Integer, List<Integer>> pathsWithK = new HashMap<>();
            boolean[] valid = new boolean[s.length() + 1];
            valid[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                boolean listCreated = false;
                for (int k = i - 1; k >= 0; k--) {
                    boolean tmp = (valid[k] && wordDict.contains(s.substring(k, i)));
                    if (tmp) {
                        if (!listCreated) {
                            pathsWithK.put(i, new LinkedList<Integer>());
                            listCreated = true;
                        }
                        pathsWithK.get(i).add(k);
                    }
                    valid[i] = valid[i] || tmp;
                }
            }
            List<String> result = new LinkedList<>();
            if (valid[s.length()]) {
                result = getStrings(pathsWithK, s.length(), s);
            }
            return result;
        }

        private List<String> getStrings(HashMap<Integer, List<Integer>> paths, int endK, String s) {
            List<String> result = new LinkedList<>();
            for (int prevK : paths.get(endK)) {
                if (prevK > 0) {
                    List<String> prevSList = getStrings(paths, prevK, s);
                    for (String prevS : prevSList)
                        result.add(prevS + " " + s.substring(prevK, endK));
                } else
                    result.add(s.substring(0, endK));

            }
            return result;
        }
    }

    static public void main(String[] args) {
        Solution solution = new WordBreakII().new Solution();
        solution.wordBreak("catsanddog", new HashSet<String>(Arrays.asList(
                new String[]{"cat", "cats", "sand", "and", "dog"})));
    }
}
