/**
 * Created by Feiyu on 2015/7/17.
 **/
public class InterleavingString {
    /**
     * Accept
     * V(i,j)=( V(i-1,j) && (c3(i+j)==c1(i) || c3(i+j)==c2(j))) ||
     * ( V(i,j-1) && (c3(i+j)==c1(i) || c3(i+j)==c2(j)))  ,i为s1当前index，j为s2当前index
     * Note：初始化时不要忘记加上对上一格的判断
     */
    public class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            final int m = s1.length(), n = s2.length();
            if (m + n != s3.length())//处理"a","b","a"
                return false;
            boolean[][] solution = new boolean[m + 1][n + 1];
            solution[0][0] = true;
            for (int i = 1; i <= m; i++)
                solution[i][0] = solution[i - 1][0] //处理"db", "b", "cbb"，加上对上一格的依赖
                        && (s1.charAt(i - 1) == s3.charAt(i - 1));
            for (int i = 1; i <= n; i++)
                solution[0][i] = solution[0][i - 1]
                        && (s2.charAt(i - 1) == s3.charAt(i - 1));
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    char c3 = s3.charAt(i + j - 1);
                    solution[i][j] = (solution[i - 1][j]
                            && (s1.charAt(i - 1) == c3))
                            || (solution[i][j - 1]
                            && (s2.charAt(j - 1) == c3));
                }
            }
            return solution[m][n];
        }
    }

    public static void main(String[] a) {
        Solution solution = new InterleavingString().new Solution();
        solution.isInterleave("db", "b", "cbb");
        solution.isInterleave("a", "b", "a");
        solution.isInterleave("aabcc", "dbbca", "aadbbcbcac");//true
        solution.isInterleave("aabcc", "dbbca", "aadbbbaccc");//false
        solution.isInterleave("aab", "", "aab");//true
        solution.isInterleave("", "", "");//true
    }
}
