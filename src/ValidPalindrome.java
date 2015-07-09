/**
 * Created by Feiyu on 2015/7/8 0008.
 **/
public class ValidPalindrome {
    /**
     * Accept
     */
    public class Solution {
        public boolean isPalindrome(String s) {
            s = s.toLowerCase();
            s = s.replaceAll("[^a-z0-9]+", "");//[^(a-z0-9)]+ 这样会把括号也包含进去
            int i = 0, j = s.length() - 1;
            boolean isValid = true;
            while (i <= j) {
                if (s.charAt(i) != s.charAt(j)) {
                    isValid = false;
                    break;
                }
                i++;
                j--;
            }
            return isValid;
        }
    }

    public static void main(String... a) {
        Solution solution = new ValidPalindrome().new Solution();
        boolean x = solution.isPalindrome("Damosel, a poem? A carol? Or a cameo pale? (So mad!)");
    }
}
