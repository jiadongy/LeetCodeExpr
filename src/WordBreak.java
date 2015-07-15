import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Feiyu on 2015/7/15.
 **/
public class WordBreak {
    /**
     * Accept
     */
    public class Solution {
        public boolean wordBreak(String s, Set<String> wordDict) {
            boolean[] valid = new boolean[s.length() + 1];
            valid[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int k = i - 1; k >= 0; k--) {
                    valid[i] = valid[i] || (valid[k] && wordDict.contains(s.substring(k, i)));
                    if (valid[i])
                        break;
                }
            }
            return valid[s.length()];
        }
    }

    static public void main(String[] args) {
        Solution solution = new WordBreak().new Solution();
        solution.wordBreak("catsanddog", new HashSet<String>(Arrays.asList(
                new String[]{"cat", "cats", "sand", "and", "dog"})));
    }
}
