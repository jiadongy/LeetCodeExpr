/**
 * Created by Feiyu on 2015/7/19.
 **/
public class WildcardMatching {
    /**
     * Accept ,but > 520ms
     * tag里有back tracing，greedy，DP，我只用了DP，所以速度不快？
     * M(i,j)=( M(i-1,j-1) && ( s[i]==p[j] || p[j]=='?' || p[j]=='*' ))
     * || ( M(i-1,j) && p[j]=='*' )
     * || ( M(i,j-1) && p[j]=='*' )
     */
    public class Solution {
        public boolean isMatch(String s, String p) {
            boolean[][] table = new boolean[1 + p.length()][1 + s.length()];
            table[0][0] = true;
            for (int i = 1; i <= p.length(); i++)
                table[i][0] = table[i - 1][0] && p.charAt(i - 1) == '*';

            for (int i = 1; i <= p.length(); i++)
                for (int j = 1; j <= s.length(); j++) {
                    table[i][j] =
                            (table[i - 1][j - 1] && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?' || p.charAt(i - 1) == '*'))
                                    || (table[i][j - 1] && p.charAt(i - 1) == '*')
                                    || (table[i - 1][j] && p.charAt(i - 1) == '*');
                }
            boolean res = table[p.length()][s.length()];
            return res;
        }
    }

    static public void main(String[] a) {
        Solution solution = new WildcardMatching().new Solution();
        solution.isMatch("c", "*?*");//true
        solution.isMatch("a", "");//false
        solution.isMatch("", "a");//false
        solution.isMatch("abac", "a*c"); //true
        solution.isMatch("aa", "a"); // false
        solution.isMatch("aa", "aa"); // true
        solution.isMatch("aaa", "aa"); // false
        solution.isMatch("aa", "*"); // true
        solution.isMatch("aa", "a*"); // true
        solution.isMatch("ab", "?*"); // true
        solution.isMatch("aab", "c*a*b"); // false

    }
}
